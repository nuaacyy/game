package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.common.addHeroExp
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.getResearchEffPlusRate
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.DefCastle
import com.point18.slg2d.world.event.PrisonEvent
import com.point18.slg2d.world.event.RescueCastlePrison
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.RewardInfoForReport
import com.point18.slg2d.world.module.fightdomain.createRewardInfoForReport
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.soliderBattle.fightdomain.SoliderFightInfoData
import com.point18.slg2d.world.wpm
import pb4server.PlunderAskReq
import pb4server.World2HomeAskResp
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

//PVP攻打玩家城战斗流程
class PvpFightPlayerAction(var action: Int) : BaseSoliderFightDeal() {
    override fun dealAfterOneFight(
        areaCache: AreaCache,
        atkForceList: List<WalkForce>,
        defForceList: List<WalkForce>,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        isMass: Boolean,
        posX: Int,
        posY: Int,
        detailFightInfo: SoliderFightInfoData
    ): Boolean {
        //计算战报
        val atkInfoAfterFight = atkFightData.calSoliderAfterFight(areaCache, pcs.basicProtoCache.pvpAttackerDieRate)
        val defInfoAfterFight = defFightData.calSoliderAfterFight(areaCache, 0)

        //分配战斗后的击杀击伤
        divideSoliderAfterFight(atkFightData, defFightData, atkInfoAfterFight, defInfoAfterFight)

        val rewardMap = hashMapOf<Long, RewardInfoForReport>()
        //填充奖励结构
        for (soliderData in atkFightData.soliderDataList) {
            rewardMap[soliderData.playerId] = createRewardInfoForReport()
        }
        for (soliderData in defFightData.soliderDataList) {
            rewardMap[soliderData.playerId] = createRewardInfoForReport()
        }

        //计算攻击方
        var atkTotalWeight = 0L               //用于计算掠夺
        val atkWeightMap = hashMapOf<Long, Int>() //用于计算掠夺
        val atkHeroMap = hashMapOf<Long, Int>()   //用于实际增加英雄经验
        for ((_, killData) in atkInfoAfterFight.killedSoliderDataMap) {
            var totalKingExp = 0.0
            var totalHeroExp = 0.0
            for ((killId, killNum) in killData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[killId]
                if (soliderProto == null) {
                    normalLog.error("士兵配置不存在:%d", killId)
                    continue
                }
                totalKingExp += soliderProto.lordExp * killNum
                totalHeroExp += soliderProto.heroExp * killNum
            }
            val rewardInfo = rewardMap[killData.playerId]
            if (rewardInfo == null) {
                normalLog.error("战斗后的击杀数据中的玩家不存在,%d", killData.playerId)
                continue
            }
            rewardInfo.kingExp = totalKingExp.toInt()
            if (killData.playerId == atkFightData.playerId && atkFightData.heroList.count() > 0) {
                val averageHeroExp = totalHeroExp / atkFightData.heroList.count()
                for (hero in atkFightData.heroList) {
                    rewardInfo.heroExp[hero.protoId] = averageHeroExp.toInt()
                    atkHeroMap[hero.id] = averageHeroExp.toInt()
                }
            }
        }
        for ((_, leftData) in atkInfoAfterFight.leftSoliderDataMap) {
            var totalWeight = 0
            for ((leftId, leftNum) in leftData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[leftId]
                if (soliderProto == null) {
                    normalLog.error("士兵配置不存在:%d", leftId)
                    continue
                }
                totalWeight += soliderProto.weight * leftNum
            }
            atkWeightMap[leftData.playerId] = totalWeight
            atkTotalWeight += totalWeight
        }

        //计算防守方
        val defHeroMap = hashMapOf<Long, Int>() //用于实际增加英雄经验
        for ((_, killData) in defInfoAfterFight.killedSoliderDataMap) {
            var totalKingExp = 0.0
            var totalHeroExp = 0.0
            for ((killId, killNum) in killData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[killId]
                if (soliderProto == null) {
                    normalLog.error("士兵配置不存在:%d", killId)
                    continue
                }
                totalKingExp += soliderProto.lordExp * killNum
                totalHeroExp += soliderProto.heroExp * killNum
            }
            val rewardInfo = rewardMap[killData.playerId]
            if (rewardInfo == null) {
                normalLog.error("战斗后的击杀数据中的玩家不存在,%d", killData.playerId)
                continue
            }
            rewardInfo.kingExp = totalKingExp.toInt()
            if (killData.playerId == defFightData.playerId && defFightData.heroList.count() > 0) {
                val averageHeroExp = totalHeroExp / defFightData.heroList.count()
                for (hero in defFightData.heroList) {
                    rewardInfo.heroExp[hero.protoId] = averageHeroExp.toInt()
                    defHeroMap[hero.id] = averageHeroExp.toInt()
                }
            }
        }

        //结算战斗奖励
        val forceMap = hashMapOf<Long, WalkForce>()
        for (force in atkForceList) {
            forceMap[force.playerId] = force
        }
        for ((playerId, reward) in rewardMap) {
            val action = ACTION_FIGHT_CASTLE
            val player = areaCache.playerCache.findPlayerById(playerId)
            if (player == null) {
                normalLog.error("找不到玩家信息:%d", playerId)
                return false
            }
            reward.kingExp = (reward.kingExp *
                    getResearchEffPlusRate(
                        areaCache,
                        NO_FILTER,
                        player,
                        KingExpAdd
                    )).toInt()

            if (reward.kingExp > 0) {
                addResToHome(
                    areaCache,
                    action,
                    player.id,
                    asList(ResVo(RES_KING_EXP, NOT_PROPS_SUB_TYPE, reward.kingExp.toLong()))
                )
            }
            val force = forceMap[playerId]
            if (force != null) {
                force.resFromInfo = Plunder.toString()
                force.putResVo(reward.resVos)
            }
            if (playerId == atkFightData.playerId) {
                for ((heroId, addExp) in atkHeroMap) {
                    addHeroExp(areaCache, playerId, heroId, addExp)
                }
            } else if (playerId == defFightData.playerId) {
                for ((heroId, addExp) in defHeroMap) {
                    addHeroExp(areaCache, playerId, heroId, addExp)
                }
            }
        }

        //生成战报
        val reportInfo = ReportInfo(
            areaCache,
            posX,
            posY,
            atkFightData,
            defFightData,
            null, null,
            toJson(detailFightInfo).toByteArray(Charsets.UTF_8)
        )

        if (detailFightInfo.fightResult == FIGHT_RESULT_LOSE) {
            reportInfo.genFightPlayerReport(
                rewardMap,
                atkInfoAfterFight,
                defInfoAfterFight,
                isMass,
                false,
                true,
                0,
                detailFightInfo.fightResult,
                false,
                atkFightData.checkAllWipedOut() && atkFightData.checkHaveMainHero(areaCache)
            )
        } else {
            val msg = PlunderAskReq.newBuilder()
            msg.totalWeight = atkTotalWeight
            areaCache.worldActor.createACS<World2HomeAskResp>()
                .ask(
                    areaCache.worldActor.homeShardRegion,
                    areaCache.fillW2HAskMsgHeader(defFightData.playerId) {
                        it.setPlunderAskReq(msg)
                    },
                    World2HomeAskResp::class.java
                )
                .whenCompleteKt { hrt, err ->
                    if (err != null || hrt == null) {
                        normalLog.lzWarn { "请求home掠夺资源错误:{$err}" }
                        return@whenCompleteKt
                    }
                    val rt = hrt.plunderAskRt
                    if (rt.rt != ResultCode.SUCCESS.code) {
                        normalLog.lzWarn { "请求home掠夺资源失败:{${rt.rt}}" }
                        return@whenCompleteKt
                    }
                    //根据负重分配资源，并分发战报
                    val plunderMap: HashMap<Int, Long> = toObj(rt.loseRes)
                    val robotRate = pcs.basicProtoCache.robTransportRate / 10000.0 //掠夺比例
                    for ((playerId, weight) in atkWeightMap) {
                        val weightRate = weight / atkTotalWeight.toDouble() //负重占比
                        for ((resType, num) in plunderMap) {
                            val realGetNum = (num * weightRate * robotRate).toLong()
                            if (realGetNum == 0L) {
                                continue
                            }
                            val rewardInfo = rewardMap[playerId]
                            if (rewardInfo == null) {
                                normalLog.error("战斗后的击杀数据中的玩家不存在,%d", playerId)
                                continue
                            }
                            rewardInfo.resVos.add(ResVo(resType, NOT_PROPS_SUB_TYPE, realGetNum))
                        }
                    }

                    reportInfo.genFightPlayerReport(
                        rewardMap,
                        atkInfoAfterFight,
                        defInfoAfterFight,
                        isMass,
                        false,
                        true,
                        0,
                        detailFightInfo.fightResult,
                        defFightData.checkAllWipedOut() && defFightData.checkHaveMainHero(areaCache),
                        false
                    )

                    //发送掠夺奖励
                    for ((playerId, info) in rewardMap) {
                        addResToHome(areaCache, ACTION_FIGHT_CASTLE, playerId, info.resVos)
                    }
                }
        }

        //============================================更新部队数据======================================================
        //更新攻击方部队数据（全部是行军组士兵）
        atkInfoAfterFight.refreshForceList(areaCache, atkForceList)

        defInfoAfterFight.refreshForceList(areaCache, defForceList)

        //todo 使用上面新的计算方式计算防守方的兵，待验证
//        //更新防守方部队数据（主玩家为主城数据，辅玩家为增援数据）
//        val changeBarracks = hashMapOf<Long, Barracks>()
//        val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(defFightData.playerId)
//        val leftSoliderData = defInfoAfterFight.leftSoliderDataMap[defFightData.playerId]
//        if (leftSoliderData == null) {
//            normalLog.error("战斗后数据中不存在该玩家:%d", defFightData.playerId)
//            return false
//        }
//        for ((id, leftNum) in leftSoliderData.soliderMap) {
//            val soliderProto = pcs.soliderCache.soliderProtoMap[id]
//            if (soliderProto == null) {
//                normalLog.error("士兵配置不存在:%d", id)
//                continue
//            }
//            val barrack = barrackMap[soliderProto.soliderID]
//            if (barrack == null) {
//                normalLog.error("兵营中不存在该类型士兵:%d", soliderProto.soliderID)
//                continue
//            }
//            if (barrack.soldierNum == leftNum) {
//                continue
//            }
//            barrack.soldierNum = leftNum
//            changeBarracks[barrack.id] = barrack
//        }
//
//        val woundedSoliderData = defInfoAfterFight.woundedSoliderDataMap[defFightData.playerId]
//        if (woundedSoliderData == null) {
//            normalLog.error("战斗后数据中不存在该玩家:%d", defFightData.playerId)
//            return false
//        }
//
//        if (isWonderWar(areaCache)) {
//            // 奇观争夺期间
//            val (eventWoundedMap, woundedMap)
//                    = defInfoAfterFight.divideEventWoundedSolider(areaCache, defFightData.playerId)
//
//            for ((id, woundedNum) in eventWoundedMap) {
//                if (woundedNum == 0) {
//                    continue
//                }
//                val barrack = barrackMap[id]
//                if (barrack == null) {
//                    normalLog.error("找不到兵营:%d", id)
//                    continue
//                }
//
//                barrack.canEventCureNum += woundedNum
//                changeBarracks[barrack.id] = barrack
//            }
//
//            for ((id, woundedNum) in woundedMap) {
//                if (woundedNum == 0) {
//                    continue
//                }
//                val barrack = barrackMap[id]
//                if (barrack == null) {
//                    normalLog.error("找不到兵营:%d", id)
//                    continue
//                }
//
//                barrack.canCureNum += woundedNum
//            }
//        } else {
//            // 和平期间
//            for ((id, woundedNum) in woundedSoliderData.soliderMap) {
//                if (woundedNum == 0) {
//                    continue
//                }
//                val barrack = barrackMap[id]
//                if (barrack == null) {
//                    normalLog.error("兵营中不存在该类型士兵:%d", id)
//                    continue
//                }
//                barrack.canCureNum += woundedNum
//                changeBarracks[barrack.id] = barrack
//            }
//        }
//
//        defFightData.soliderDataList.forEach {
//            targetAddVal(areaCache, it.playerId, SoliderStrength)
//            targetAddVal(areaCache, it.playerId, TrapStrength)
//        }
//
//        val session = fetchOnlinePlayerSession(areaCache, defFightData.playerId)
//        if (session != null) {
//            for ((_, barrack) in changeBarracks) {
//                createBarracksChangeNotifier(barrack).notice(session)
//            }
//        }
//
//        //增援玩家数据更新
//        val mainDefPlayer = areaCache.playerCache.findPlayerById(defFightData.playerId)
//        if (mainDefPlayer == null) {
//            normalLog.error("找不到玩家信息:%d", defFightData.playerId)
//            return false
//        }
//        val castle = areaCache.castleCache.findCastleById(mainDefPlayer.focusCastleId)
//        if (castle == null) {
//            normalLog.error("找不打玩家对应的城池信息:%d", mainDefPlayer.id)
//            return false
//        }
//        val reinforceGroups =
//            areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(castle.x, castle.y, Reinforce)
//        for (group in reinforceGroups) {
//            defInfoAfterFight.refreshGroup(areaCache, group)
//        }

        // 统计士兵总数
        var atkTotalSolider = 0
        var defTotalSolider = 0
        for (soliderData in atkFightData.soliderDataList) {
            for ((_, num) in soliderData.soliderMap) {
                atkTotalSolider += num
            }
        }
        for (soliderData in defFightData.soliderDataList) {
            for ((_, num) in soliderData.soliderMap) {
                defTotalSolider += num
            }
        }

        val atkTotalDamageOrKill = statisticSoliderData(areaCache, runType, atkInfoAfterFight)
        val defTotalDamageOrKill = statisticSoliderData(areaCache, runType, defInfoAfterFight)

        // 判断监禁
        if (detailFightInfo.fightResult == FIGHT_RESULT_WIN) {
            // 进攻方胜利的话,判断防守方有没有领主主战,有没有全军覆没
            val winPlayerId = atkFightData.playerId
            val losePlayerId = defFightData.playerId
            wpm.es.fire(
                areaCache,
                0,
                RESCUE_PRISON,
                RescueCastlePrison(winPlayerId, losePlayerId)
            )

            if (defFightData.checkAllWipedOut() && defFightData.checkHaveMainHero(areaCache)) {
                wpm.es.fire(
                    areaCache, 0, PRISON_AFTER_PVP,
                    PrisonEvent(winPlayerId, losePlayerId)
                )
            }

            wpm.es.fire(
                areaCache, 0, PVP_DEF_CASTLE_LOSE, DefCastle(
                    winPlayerId,
                    losePlayerId,
                    ((atkTotalDamageOrKill / defTotalSolider.toDouble()) * 100).toInt(),
                    ((defTotalDamageOrKill / atkTotalSolider.toDouble()) * 100).toInt()
                )
            )

            targetAddVal(
                areaCache,
                defFightData.playerId,
                FailDefCityCount
            )
        } else {
            // 进攻方失败的话,判断进攻方有没有领主主战,有没有全军覆没
            if (atkFightData.checkAllWipedOut() && atkFightData.checkHaveMainHero(areaCache)) {
                val winPlayerId = defFightData.playerId
                val losePlayerId = atkFightData.playerId
                wpm.es.fire(
                    areaCache, 0, PRISON_AFTER_PVP,
                    PrisonEvent(winPlayerId, losePlayerId)
                )
            }
        }

        statisticFightData(areaCache, atkFightData, defFightData, detailFightInfo.fightResult, isMass)

        return false
    }
}

