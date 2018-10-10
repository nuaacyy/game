package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ClearanceProto
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.UnitTeamProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.area4data.findJjcRobotByRank
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.event.JjcFightWin
import com.point18.slg2d.world.module.fightdomain.AllFightInfo
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.createFightDataByArmyPlan
import com.point18.slg2d.world.module.fightdomain.createFightDataByUnitTeam
import com.point18.slg2d.world.module.walk.fightStrategy.heroFight
import com.point18.slg2d.world.msgnotice.createArenaRankChangeNotifier
import com.point18.slg2d.world.wpm
import pb4client.HeroFightReport
import pb4client.JjcFightRt
import pb4server.ArenaFightAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import java.util.*
import java.util.Arrays.asList

class ArenaFightWorldDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val receiveMsg = req.arenaFightAskReq
        val defRank = receiveMsg.defRank
        val defPlayerId = receiveMsg.defPlayerId
        val rt2Home = ArenaFightAskRt.newBuilder()
        rt2Home.rt = ResultCode.SUCCESS.code
        val rt2Player = JjcFightRt.newBuilder()
        rt2Player.rt = ResultCode.SUCCESS.code
        resp.setArenaFightAskRt(rt2Home)

        val playerId = req.playerId
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session == null) {
            rt2Home.rt = ResultCode.SESSION_NOT_FOUND.code
            rt2Player.rt = ResultCode.SESSION_NOT_FOUND.code
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        val player = session.player

        // 挑战胜利后增加君主经验
        val kingExpProto = pcs.kingExpCache.kingExpMap[player.kingLv]
        if (kingExpProto == null) {
            normalLog.error("找不到对应玩家等级的经验配置:%d", player.kingLv)
            rt2Home.rt = ResultCode.NO_PROTO.code
            rt2Player.rt = ResultCode.NO_PROTO.code
            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        val starProto = pcs.starAttributeWeekProtoCache.findStarAttributeWeekProtoByWeek()
        if (starProto == null) {
            rt2Home.rt = ResultCode.NO_PROTO.code
            rt2Player.rt = ResultCode.NO_PROTO.code
            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        val unitTeamId = findJjcRobotByRank(defRank)
        val unitTeamProto = pcs.unitTeamProtoCache.protoMap[unitTeamId]
        if (unitTeamProto == null) {
            rt2Home.rt = ResultCode.NO_PROTO.code
            rt2Player.rt = ResultCode.NO_PROTO.code
            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        val jjcInfo = areaCache.jjcCache.findJjc(playerId)
        if (jjcInfo == null) {
            rt2Home.rt = ResultCode.JJC_WORLD_INFO_NOT_FOUND.code
            rt2Player.rt = ResultCode.JJC_WORLD_INFO_NOT_FOUND.code
            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        // 挑战对手是否已经发生变化
        if (defRank != jjcInfo.rank1 && defRank != jjcInfo.rank2 && defRank != jjcInfo.rank3) {
            rt2Home.rt = ResultCode.JJC_FIGHT_ERROR_NO_FIND_DEF_PLAYER.code
            rt2Player.rt = ResultCode.JJC_FIGHT_ERROR_NO_FIND_DEF_PLAYER.code
            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        // 校验要挑战的玩家id与rank是否符合
        val defPlayer = areaCache.playerCache.findPlayerByJjcRank(defRank)
        if (defPlayer != null) {
            if (defPlayer.id != defPlayerId) {
                rt2Home.rt = ResultCode.PARAMETER_ERROR.code
                rt2Player.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
                resp.setArenaFightAskRt(rt2Home)
                return
            }
        }

        if (defPlayer == null) {
            if (defPlayerId != 0L) {
                rt2Home.rt = ResultCode.PARAMETER_ERROR.code
                rt2Player.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
                resp.setArenaFightAskRt(rt2Home)
                return
            }
        }

        // 取玩家的进攻部队
        val attackPlayerAttPlan = areaCache.armyPlanCache.findArmyPlan(playerId, JjcFight, JjcAtk)
        if (attackPlayerAttPlan == null || attackPlayerAttPlan.heroMap.count() <= 0) {
            // 没有找到玩家的进攻部队
            rt2Home.rt = ResultCode.JJC_FORCE_GRID_NOT_EXIST.code
            rt2Player.rt = ResultCode.JJC_FORCE_GRID_NOT_EXIST.code
            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
            return
        }

        // 取进攻布阵作为防守布阵
        val defPlan = areaCache.armyPlanCache.findArmyPlan(playerId, JjcFight, JjcDef)
        if (defPlan == null) {
            areaCache.armyPlanCache.createArmyPlan(playerId, JjcFight, JjcDef, attackPlayerAttPlan.heroMap)
        }

        val nowTime = getNowTime()

        val myOldRank = player.jjcRank
        var diffRank = myOldRank - defRank //这是我与对手之间的名次差异
        if (diffRank <= 0) {
            diffRank = 0 //这个说明我打了比我弱的玩家
        }

        val dealAfterFight = { result: AllFightInfo ->

            result.detailInfoList.forEach {
                rt2Player.addReport(HeroFightReport.parseFrom(it.detailFightInfo))
            }

            var score = 0
            when (result.fightResult) {
                // 赢了
                FIGHT_RESULT_WIN -> {
                    targetAddVal(
                        areaCache,
                        player.id,
                        JjcWinRecord
                    )

                    wpm.es.fire(
                        areaCache, player.id, JJC_FIGHT_WIN,
                        JjcFightWin()
                    )
                    rt2Player.fightResult = FIGHT_RESULT_WIN
                    // 发奖励,名次调换
                    if (defPlayer == null) {
                        //打的是机器人,如果我的排名在他之后,只要把我的名次换成是他的就好了
                        if (player.jjcRank > defRank) {
                            // 同步数据到home
                            syncData2Home(
                                areaCache,
                                player.id,
                                JjcRankSync,
                                player.jjcRank.toString()
                            )

                            areaCache.playerCache.updateJjcRank(player, defRank)

                            // 发送app通知
                            areaCache.pushAppNotice(
                                player.id,
                                JJC_RANK_SETTING,
                                0
                            )

                            targetAddVal(
                                areaCache,
                                player.id,
                                NowJJcRank,
                                asList(defRank.toLong())
                            )
                        } else {
                            // 打机器人输了什么都不干
                        }
                    } else {
                        // 打的是玩家,我与他名次对换
                        if (player.jjcRank > defRank) {
                            // 不可以直接交换 需要借助中间值
                            val myRank = player.jjcRank
                            areaCache.playerCache.updateJjcRank(defPlayer, 9999999)
                            areaCache.playerCache.updateJjcRank(player, defRank)
                            areaCache.playerCache.updateJjcRank(defPlayer, myRank)

                            // 同步数据到home
                            syncData2Home(
                                areaCache,
                                player.id,
                                JjcRankSync,
                                defRank.toString()
                            )

                            // 同步数据到home
                            syncData2Home(
                                areaCache,
                                defPlayer.id,
                                JjcRankSync,
                                myRank.toString()
                            )

                            // 发送app通知
                            areaCache.pushAppNotice(
                                player.id,
                                JJC_RANK_SETTING,
                                0
                            )

                            // 发送app通知
                            areaCache.pushAppNotice(
                                defPlayer.id,
                                JJC_RANK_SETTING,
                                0
                            )

                            targetAddVal(
                                areaCache,
                                player.id,
                                NowJJcRank,
                                asList(defRank.toLong())
                            )

                            targetAddVal(
                                areaCache,
                                defPlayer.id,
                                NowJJcRank,
                                asList(myRank.toLong())
                            )

                            // 发送给被打的人的排名变化
                            val defSession = fetchOnlinePlayerSession(areaCache, defPlayer.id)
                            if (defSession != null) {
                                val notifier = createArenaRankChangeNotifier(myRank)
                                notifier.notice(defSession)
                            }

                        } else {
                            // 什么都不干
                        }

                        // 如果挑战对手在线，那么需要刷新对方的3个挑战对手
                        val defSession =
                            fetchOnlinePlayerSession(areaCache, defPlayer.id)
                        if (defSession != null) {
                            val rst =
                                pcs.jjcChallengeCache.fetchThreeRank(defPlayer.jjcRank)
                            val defJjcInfo = areaCache.jjcCache.findJjc(defPlayer.id)
                            if (defJjcInfo != null) {
                                defJjcInfo.rank1 = rst.rank1
                                defJjcInfo.rank2 = rst.rank2
                                defJjcInfo.rank3 = rst.rank3
                            }
                        }
                    }

                    // 历史最高排名更新
                    if (defRank < jjcInfo.maxRank) {
                        jjcInfo.maxRank = defRank
                        // 更新到home
                        syncData2Home(
                            areaCache,
                            player.id,
                            JjcMaxRankSync,
                            jjcInfo.maxRank.toString()
                        )
                        targetAddVal(
                            areaCache,
                            player.id,
                            MaxJJcRank,
                            asList(jjcInfo.maxRank.toLong())
                        )
                    }

                    val addKingExp = (kingExpProto.pveExp.toLong() *
                            getResearchEffPlusRate(
                                areaCache,
                                NO_FILTER,
                                player,
                                KingExpAdd
                            )).toLong()
                    addResToHome(
                        areaCache, ACTION_LOG_PVE_FIGHT_JJC, playerId,
                        asList(
                            ResVo(RES_KING_EXP, NOT_PROPS_SUB_TYPE, addKingExp)
                        )
                    )
                    rt2Player.exp = addKingExp

                    // 挑战成功后，需要刷新玩家的3个挑战对手
                    val rst = pcs.jjcChallengeCache.fetchThreeRank(player.jjcRank)

                    // 保存3个挑战对手
                    jjcInfo.rank1 = rst.rank1
                    jjcInfo.rank2 = rst.rank2
                    jjcInfo.rank3 = rst.rank3
                    score = pcs.basicProtoCache.jjcFightWinScore
                }

                // 输了
                FIGHT_RESULT_LOSE -> {
                    rt2Player.fightResult = FIGHT_RESULT_LOSE
                    // 主动挑战失败获得的积分
                    score = pcs.basicProtoCache.jjcFightFailureScore
                }

            }

            // 更新每天积分
            if (isAfterGameRefTime(jjcInfo.scoreTime)) {
                jjcInfo.scoreTime = nowTime
                jjcInfo.score += score
            } else {
                jjcInfo.score = score
            }

            // 同步到home
            syncData2Home(
                areaCache,
                player.id,
                JjcScoreSync,
                jjcInfo.score.toString()
            )

            // 同步到home
            syncData2Home(
                areaCache,
                player.id,
                JjcScoreTimeSync,
                jjcInfo.scoreTime.toString()
            )

            rt2Player.nowRank = player.jjcRank

            // 返回最新的最高排名及本日积分
            rt2Player.maxRank = jjcInfo.maxRank
            rt2Player.score = jjcInfo.score
            rt2Player.oldRank = myOldRank

            // 返回挑战3个对手
            rt2Player.setChallenge1(fetchChallenge(areaCache, jjcInfo.rank1))
            rt2Player.setChallenge2(fetchChallenge(areaCache, jjcInfo.rank2))
            rt2Player.setChallenge3(fetchChallenge(areaCache, jjcInfo.rank3))

            // 英雄加经验 每次点击挑战按钮都加经验
            for ((_, heroId) in attackPlayerAttPlan.heroMap) {
                val hero = areaCache.heroCache.findHeroById(player.id, heroId)
                if (hero == null) {
                    normalLog.error("找不到英雄信息:%d", heroId)
                    continue
                }
                val jjcExpProto = pcs.heroLevelUpCache.fetchLevelUpProto(hero.level)
                if (jjcExpProto == null) {
                    normalLog.error("找不到经验配置：%d", hero.level)
                    continue
                }

                val heroBuilder =
                    genHeroInfoForReport(session.areaCache, player.id, heroId, jjcExpProto.arenaExp)
                        ?: continue
                rt2Player.addHeroInfos(heroBuilder)

                addHeroExp(areaCache, player.id, heroId, jjcExpProto.arenaExp)
            }

            // 各种资源变化
            jjcInfo.lastFightTime = nowTime

            //统计星数
            if (result.fightResult == FIGHT_RESULT_WIN) {
                val clearanceProtoList = LinkedList<ClearanceProto>()
                pcs.basicProtoCache.arenaSuccess.forEach {
                    val clearanceProto = pcs.clearanceProtoCache.clearanceMap[it] ?: return@forEach
                    clearanceProtoList.add(clearanceProto)
                }

                val combineRecords = hashMapOf<Int, Int>()
                result.detailInfoList.forEach { eachFightInfo ->
                    eachFightInfo.record.intRecordMap.forEach {
                        combineRecords[it.key] = (combineRecords[it.key] ?: 0) + it.value
                    }
                }

                clearanceProtoList.forEach {
                    if (it.checkCondition(combineRecords)) {
                        rt2Player.addStar(it.id)
                    }
                }
            }

            session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
            resp.setArenaFightAskRt(rt2Home)
        }

        //当前星象数据
        val additionPropertyMap: HashMap<Set<Int>, Map<Int, Int>> = hashMapOf()
        additionPropertyMap[starProto.starGroupSet] = starProto.starNatureMap

        if (defPlayer != null) {
            // 打的目标是玩家
            val defendPlayerDefPlan = areaCache.armyPlanCache.findArmyPlan(defPlayer.id, JjcFight, JjcDef)

            if (defendPlayerDefPlan == null) {
                rt2Home.rt = ResultCode.NO_SET_ARMY_PLAN.code
                rt2Player.rt = ResultCode.NO_SET_ARMY_PLAN.code
                session.sendMsg(MsgType.JjcFight_720, rt2Player.build())
                resp.setArenaFightAskRt(rt2Home)
                return
            }

            // 进行战斗
            val atkFightData =
                createFightDataByArmyPlan(areaCache, attackPlayerAttPlan)

            val defFightData =
                createFightDataByArmyPlan(areaCache, defendPlayerDefPlan)

            val defFightDataList = LinkedList<FightData>(asList(defFightData))
            heroFight.doHeroFight(
                areaCache,
                PVP_FIGHT_JJC_ACTION,
                0,
                0,
                0,
                atkFightData,
                defFightDataList,
                hashMapOf(Pair(ChangeRank, diffRank), Pair(DefRank, defRank)),
                additionPropertyMap,
                {
                    dealAfterFight(it)
                }, {
                    //todo 错误的情况
                }
            )
        } else {
            //   打机器
            val unitTeamProtoList = LinkedList<UnitTeamProto>()
            unitTeamProtoList.add(unitTeamProto)

            // 进行战斗
            val atkFightData =
                createFightDataByArmyPlan(areaCache, attackPlayerAttPlan)

            val defFightDataList = LinkedList<FightData>()
            unitTeamProtoList.forEach {
                defFightDataList.add(createFightDataByUnitTeam(it))
            }

            heroFight.doHeroFight(
                areaCache,
                PVP_FIGHT_JJC_ACTION,
                0,
                0,
                0,
                atkFightData,
                defFightDataList,
                hashMapOf(Pair(ChangeRank, diffRank), Pair(DefRank, defRank)),
                additionPropertyMap,
                {
                    dealAfterFight(it)
                },
                {
                    //todo 错误的情况
                }
            )
        }

        resp.setArenaFightAskRt(rt2Home)
        return
    }
}