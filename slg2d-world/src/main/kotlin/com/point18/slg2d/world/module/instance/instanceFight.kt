package com.point18.slg2d.world.module.instance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.event.InstanceFightEvent
import com.point18.slg2d.world.event.InstanceWin
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.createFightDataByArmyPlan
import com.point18.slg2d.world.module.fightdomain.createFightDataByUnitTeam
import com.point18.slg2d.world.wpm
import pb4battle.BattleMsg
import pb4battle.BattleServiceGrpc
import pb4client.InstanceFightRt
import java.util.*

// 推图战斗
class InstanceFight : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        msg as pb4client.InstanceFight
        val rt = instanceFight(session, msg) ?: return
        session.sendMsg(MsgType.InstanceFight_1471, rt.build())
    }

    private fun instanceFight(session: PlayerSession, reqMsg: pb4client.InstanceFight): (InstanceFightRt.Builder?) {
        val floorId = reqMsg.floorId

        val rt = InstanceFightRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.floorId = floorId

        // 验证一些模版基础数据
        val player = session.player

        val (rst, instanceProto, unitTeamProtoList, clearanceProtoList, instanceUnitProto) = instanceFightCheck.checkInstanceFightCond(
            session,
            floorId
        )

        if (rst != ResultCode.SUCCESS.code) {
            rt.rt = rst
            return rt
        }

        if (instanceProto == null || unitTeamProtoList == null || clearanceProtoList == null || instanceUnitProto == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val instanceVo = session.areaCache.instanceCache.findInstance(session.playerId)
        if (instanceVo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        //验证部队阵型
        val armyPlan = session.areaCache.armyPlanCache.findFirstArmyPlan(session.playerId, MissionFight)
        if (armyPlan == null) {
            rt.rt = ResultCode.NO_SET_ARMY_PLAN.code
            return rt
        }

        // 进行战斗
        val atkFightData = createFightDataByArmyPlan(session.areaCache, armyPlan)
        atkFightData.heroList.forEach {
            it.initMorale = instanceProto.basicMorale
        }

        val defFightDataList = LinkedList<FightData>()
        unitTeamProtoList.forEach {
            defFightDataList.add(createFightDataByUnitTeam(it))
        }

        //========================================================================
        checkFight(
            session,
            pcs.basicProtoCache.battleTime,
            reqMsg.randSeed,
            atkFightData,
            defFightDataList,
            reqMsg.reportList
        ) { checkRst, rtBuilder ->
            rtBuilder.floorId = floorId
            rtBuilder.fightResult = reqMsg.fightResult
            val combineRecords = hashMapOf<Int, Int>()

            reqMsg.reportList.forEach { report ->
                report.statisticsList.forEach { statistics ->
                    combineRecords[statistics.recordKey] = (combineRecords[statistics.recordKey] ?: 0) +
                            statistics.recordValue
                }
            }

            val starSet = hashSetOf<Int>()

            if (checkRst == ResultCode.SUCCESS.code) {
                //统计星数
                clearanceProtoList.forEach {
                    if (it.checkCondition(combineRecords)) {
                        starSet.add(it.id)
                    }
                }

                costStrength(session.areaCache, player, instanceProto.winStrength - instanceProto.loseStrength)
                val winReward = LinkedList<ResVo>() // 战斗胜利奖励
                // 这一关玩家是否是首次获胜
                val v = instanceVo.starInfoMap[floorId]
                if (v == null) {
                    // 首次通关
                    instanceVo.starInfoMap[floorId] = starSet
                    winReward += instanceProto.firstWinRewardMap
                    instanceVo.nowFight = instanceProto.nextId
                    // 同步到home去
                    com.point18.slg2d.world.common.syncData2Home(
                        session.areaCache,
                        player.id,
                        com.point18.slg2d.common.constg.InstanceFloorSync,
                        instanceVo.nowFight.toString()
                    )
                } else {
                    starSet.forEach {
                        v.add(it)
                    }
                }

                // 发武将奖励
                for ((_, heroId) in armyPlan.heroMap) {
                    val heroBuilder =
                        genHeroInfoForReport(session.areaCache, player.id, heroId, instanceProto.heroExp)
                            ?: continue
                    rtBuilder.addHeroInfos(heroBuilder)

                    com.point18.slg2d.world.common.addHeroExp(
                        session.areaCache,
                        player.id,
                        heroId,
                        instanceProto.heroExp
                    )
                }

                // 获取随机奖励
                for (rList in instanceProto.randomRewardMap) {
                    val r = getRandInt(10000)
                    // 查看该掉落物品有没有控制掉落
                    val dropExtra = pcs.instanceDropExtraProtoCache.instanceDropExtraProtoMap[rList.dropPropsId]
                    if (dropExtra == null) {
                        // 没有控制
                        if (r <= rList.dropOdds) {
                            // 随到了
                            winReward.add(
                                ResVo(RES_PROPS, rList.dropPropsId.toLong(), rList.dropNum.toLong())
                            )
                        }
                    } else {
                        // 监控了,进行保底逻辑
                        val instanceDropVo =
                            session.areaCache.instanceDropCache.findInstanceDropByPropsId(
                                session.playerId,
                                rList.dropPropsId
                            )
                        if (r <= rList.dropOdds) {
                            // 随到了  但是有可能已经达到峰值了,不一定给全
                            if (instanceDropVo.nowCheckTime == dropExtra.times - 1) {
                                if (instanceDropVo.nowGet < dropExtra.number) {
                                    winReward += ResVo(
                                        RES_PROPS,
                                        rList.dropPropsId.toLong(),
                                        (dropExtra.number - instanceDropVo.nowGet).toLong()
                                    )
                                }
                                // 然后重置状态
                                instanceDropVo.nowCheckTime = 0
                                instanceDropVo.nowGet = 0
                            } else {
                                // 检测下正常给会不会溢出
                                var canGetNum = rList.dropNum
                                if (instanceDropVo.nowGet + rList.dropNum > dropExtra.number) {
                                    canGetNum = dropExtra.number - instanceDropVo.nowGet
                                }
                                winReward += ResVo(RES_PROPS, rList.dropPropsId.toLong(), canGetNum.toLong())

                                // 还没到峰值
                                instanceDropVo.nowCheckTime += 1
                                instanceDropVo.nowGet += canGetNum
                            }
                        } else {
                            // 没随到  但是有可能已经达到峰值了,必须给
                            if (instanceDropVo.nowCheckTime == dropExtra.times - 1) {
                                if (instanceDropVo.nowGet < dropExtra.number) {
                                    winReward.add(
                                        ResVo(
                                            RES_PROPS,
                                            rList.dropPropsId.toLong(),
                                            (dropExtra.number - instanceDropVo.nowGet).toLong()
                                        )
                                    )
                                }
                                // 然后重置状态
                                instanceDropVo.nowCheckTime = 0
                                instanceDropVo.nowGet = 0
                            } else {
                                // 还没到峰值
                                instanceDropVo.nowCheckTime += 1
                            }
                        }
                    }
                }

                addResToHome(session.areaCache, ACTION_INSTANCE_FIGHT, player.id, winReward)

                wpm.es.fire(
                    session.areaCache,
                    atkFightData.playerId,
                    PVE_FIGHT_WIN,
                    InstanceWin(floorId)
                )

                rtBuilder.reward = resVoToResString(winReward)
            }

            starSet.forEach { rtBuilder.addMeetCondition(it) }

            val myTarget = session.areaCache.targetCache.findMyTargetByPlayerId(player.id)
            if (myTarget != null) {
                myTarget.instanceFightNum++
            }

            wpm.es.fire(
                session.areaCache,
                session.playerId,
                INSTANCE_FIGHT,
                InstanceFightEvent(1, starSet.size, floorId)
            )
        }

        return null
    }

    private fun checkFight(
        session: PlayerSession,
        battleTime: Int,
        randSeed: Int,
        atkFightData: FightData,
        defFightDataList: List<FightData>,
        reportList: List<pb4client.HeroFightReport>,
        onCheckFightFinish: (Int, InstanceFightRt.Builder) -> Unit
    ) {
        val areaCache = session.areaCache
        areaCache.worldActor.createACS<pb4battle.BattleMsg.CheckHeroFightResp>().computeKt {
            val channel = wpm.battleServiceMgr.selectBattleService()
            if (channel == null) {
                val checkRtBuilder = pb4battle.BattleMsg.CheckHeroFightResp.newBuilder()
                checkRtBuilder.rt = ResultCode.PROCESS_ERROR_BATTLE_SERVICE_ERROR.code
                return@computeKt checkRtBuilder.build()
            }

            val requestBuilder = BattleMsg.CheckHeroFightReq.newBuilder()
            requestBuilder.battleTime = battleTime
            requestBuilder.ranSeed = randSeed
            requestBuilder.setAtkFightData(fightData2Builder(areaCache, atkFightData))
            for (defFightData in defFightDataList) {
                requestBuilder.addDefFightDatas(fightData2Builder(areaCache, defFightData))
            }
            reportList.forEach {
                requestBuilder.addReports(convertHeroFightData(it))
            }
            val stub = BattleServiceGrpc.newBlockingStub(channel)
            stub.checkHeroFight(requestBuilder.build())
        }.whenCompleteKt { checkFightResult, err ->
            val rtBuilder = InstanceFightRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code
            try {
                when {
                    err != null -> {
                        rtBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.InstanceFight_1471, rtBuilder.build())
                        return@whenCompleteKt
                    }
                    checkFightResult == null -> {
                        rtBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.InstanceFight_1471, rtBuilder.build())
                        return@whenCompleteKt
                    }
                    else -> {
                        //战斗校验完成后的处理
                        onCheckFightFinish(checkFightResult.rt, rtBuilder)
                        rtBuilder.rt = checkFightResult.rt
                        session.sendMsg(MsgType.InstanceFight_1471, rtBuilder.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("CheckHeroFight Error!", e)
                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.InstanceFight_1471, rtBuilder.build())
            }
        }
    }

}

