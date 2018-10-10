package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.createRewardInfoForReport
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.soliderBattle.fightdomain.SoliderFightInfoData
import com.point18.slg2d.world.wpm
import pb4server.AddRelicRewardAskReq
import pb4server.World2HomeAskResp
import java.util.*
import java.util.Arrays.asList

// PVE打遗迹战斗流程
class PveFightRelicAction(var action: Int) : ISoliderFightDeal {
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
        // 胜利时添加奖励
        val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
        if (relicCell == null) {
            normalLog.error("(%d,%d)对应的遗迹不存在", posX, posY)
            return false
        }
        val relicProto = pcs.relicProtoCache.relicProtoMap[relicCell.relicId]
        if (relicProto == null) {
            normalLog.error("找不到对应的遗迹配置:", relicCell.relicId)
            return false
        }

        val reward = createRewardInfoForReport()
        if (detailFightInfo.fightResult == FIGHT_RESULT_WIN) {
            val atkInfoAfterFight =
                atkFightData.calSoliderAfterFightWithOutHospital(pcs.basicProtoCache.pvpAttackerDieRate)
            val defInfoAfterFight = defFightData.calSoliderAfterFightWithOutHospital(10000)
            val rst = atkFightData.soliderRecovery(areaCache, pcs.basicProtoCache.relicReviveRate)
            val totalRecoveryNum = rst.totalRecoveryNum
            val atkInfoAfterRecovery = rst.finalSoliderData

            // 发送奖励
            val res = LinkedList(
                asList(
                    ResVo(RES_RELIC_REWARD, relicCell.timeBoxId.toLong(), relicCell.dropRate.toLong())
                )
            )
            reward.resVos = res
            for (info in atkFightData.soliderDataList) {
                // 解锁炼金所,并发送奖励
                val reqMsg = AddRelicRewardAskReq.newBuilder()
                reqMsg.timeBoxId = relicCell.timeBoxId
                reqMsg.dropRate = relicCell.dropRate
                areaCache.worldActor.createACS<World2HomeAskResp>()
                    .ask(
                        areaCache.worldActor.homeShardRegion,
                        areaCache.fillW2HAskMsgHeader(info.playerId) {
                            it.setAddRelicRewardAskReq(reqMsg)
                        },
                        World2HomeAskResp::class.java
                    )
                    .whenCompleteKt { askResp, askErr ->
                        try {
                            //todo 添加遗迹奖励失败处理
                            when {
                                askErr != null -> {
                                    normalLog.error("ArenaFightAskReq Error:${ResultCode.ASK_ERROR1}")
                                    return@whenCompleteKt
                                }
                                askResp == null -> {
                                    normalLog.error("ArenaFightAskReq Error:${ResultCode.ASK_ERROR2}")
                                    return@whenCompleteKt
                                }
                                else -> {
                                    //生成战报
                                    val reportInfo = ReportInfo(
                                        areaCache,
                                        posX,
                                        posY,
                                        atkFightData,
                                        defFightData,
                                        reward,
                                        null,
                                        toJson(detailFightInfo).toByteArray(Charsets.UTF_8)
                                    )
                                    reportInfo.genAtkRelicReport(
                                        relicCell.relicId,
                                        atkInfoAfterFight,
                                        defInfoAfterFight,
                                        totalRecoveryNum,
                                        detailFightInfo.fightResult,
                                        info.playerId,
                                        askResp.addRelicRewardAskRt.rt
                                    )
                                }
                            }

                        } catch (e: Exception) {
                            normalLog.error("ArenaFightAskReq Error::${ResultCode.ASK_ERROR3}", e)
                            return@whenCompleteKt
                        }
                    }
            }

            for (atkP in atkFightData.soliderDataList) {
                val relicLv = relicProto.level
                if (atkP.playerId == atkFightData.playerId) {
                    // 团长
                    var startMassRelicAndFightSuccess = 0
                    when (relicLv) {
                        1 -> startMassRelicAndFightSuccess = START_MASS_RELIC_AND_FIGHT_SUCC_1
                        2 -> startMassRelicAndFightSuccess = START_MASS_RELIC_AND_FIGHT_SUCC_2
                        3 -> startMassRelicAndFightSuccess = START_MASS_RELIC_AND_FIGHT_SUCC_3
                        4 -> startMassRelicAndFightSuccess = START_MASS_RELIC_AND_FIGHT_SUCC_4
                        5 -> startMassRelicAndFightSuccess = START_MASS_RELIC_AND_FIGHT_SUCC_5
                    }

                    wpm.es.fire(
                        areaCache,
                        atkP.playerId,
                        com.point18.slg2d.common.constg.PLAYER_ACTIVITY_CHANGE,
                        PlayerActivityChange(startMassRelicAndFightSuccess, 1, 0)
                    )
                } else {
                    // 团员
                    var massRelicAndFightSuccess = 0
                    when (relicLv) {
                        1 -> massRelicAndFightSuccess = MASS_RELIC_AND_FIGHT_SUCC_1
                        2 -> massRelicAndFightSuccess = MASS_RELIC_AND_FIGHT_SUCC_2
                        3 -> massRelicAndFightSuccess = MASS_RELIC_AND_FIGHT_SUCC_3
                        4 -> massRelicAndFightSuccess = MASS_RELIC_AND_FIGHT_SUCC_4
                        5 -> massRelicAndFightSuccess = MASS_RELIC_AND_FIGHT_SUCC_5
                    }
                    wpm.es.fire(
                        areaCache,
                        atkP.playerId,
                        com.point18.slg2d.common.constg.PLAYER_ACTIVITY_CHANGE,
                        PlayerActivityChange(massRelicAndFightSuccess, 1, 0)
                    )
                }


            }

            //============================================更新部队数据======================================================
            //更新攻击方部队数据（全部是行军组士兵）
            atkInfoAfterRecovery.refreshForceList(areaCache, atkForceList)

            //添加玩家的士兵死亡统计
            for ((_, soliderData) in atkInfoAfterRecovery.diedSoliderDataMap) {
                var loseSolider = 0
                var loseTrap = 0
                for ((soliderId, num) in soliderData.soliderMap) {
                    val soliderPorto = pcs.soliderCache.soliderProtoMap[soliderId] ?: continue
                    if (isSolider(soliderPorto.armyType)) {
                        loseSolider += num
                    } else {
                        loseTrap += num
                    }
                }
                targetAddVal(
                    areaCache,
                    soliderData.playerId,
                    LoseSoliderNum,
                    asList(loseSolider.toLong())
                )
                targetAddVal(
                    areaCache,
                    soliderData.playerId,
                    LoseTrapNum,
                    asList(loseTrap.toLong())
                )
            }

            if (atkFightData.soliderDataList.count() > 1) {
                targetAddVal(
                    areaCache,
                    atkFightData.playerId,
                    MassCount
                )
            }
        }
        return false
    }
}
