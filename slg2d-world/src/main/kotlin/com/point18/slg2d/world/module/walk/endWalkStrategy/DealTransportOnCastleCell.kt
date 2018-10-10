package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.common.updateAllianceMemberInfo
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.event.TransportSuccess
import com.point18.slg2d.world.module.fightdomain.RewardInfoForReport
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.wpm
import java.util.*
import java.util.Arrays.asList

class DealTransportOnCastleCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY
        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null || otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        if (player.allianceId == 0L || otherPlayer.allianceId == 0L || player.allianceId != otherPlayer.allianceId) {
            // 不在同联盟
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walkLineInfo.walkForceGroupId)
        for (force in forces) {
            addForceRes(areaCache, force, posX, posY)
        }

        goHome(areaCache, posX, posY, group)
    }

    private fun addForceRes(areaCache: AreaCache, force: WalkForce, posX: Int, posY: Int) {
        val player = areaCache.playerCache.findPlayerById(force.playerId)
        if (player == null) {
            assert(false) { "找不到玩家数据" }
            return
        }
        val resVos = force.resVo

        // 通知游戏服，给对面玩家添加资源
        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle == null) {
            assert(false) { "（$posX,$posY）坐标点的玩家城池不存在" }
            return
        }
        addResToHome(areaCache, ACTION_TRANSPORT, castle.playerId, resVos)

        //统计运输资源值
        var totalNum = 0L
        for (res in resVos) {
            when (res.resType) {
                RES_COIN,
                RES_FOOD,
                RES_WOOD,
                RES_STONE,
                RES_IRON -> {
                    totalNum += res.num
                }
            }
        }
        //添加运输值totalNum
        if (player.allianceId != 0L) {
            val updateInfoMap = hashMapOf<Int, String>()
            updateInfoMap[UpdateTransportationValue] = totalNum.toString()
            updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfoMap)
        }

        //行军组中资源清除
        force.putResVo(LinkedList())

        // 生成战报
        val transportReport = ReportInfo(
            areaCache,
            posX,
            posY,
            null,
            null,
            RewardInfoForReport(0, hashMapOf(), resVos), null, "".toByteArray()
        )
        transportReport.genTransportReport(force.playerId, castle.playerId)

        for (r in resVos) {
            var transportAction = 0
            when {
                r.resType == RES_FOOD -> transportAction = TRANSPORT_FOOD
                r.resType == RES_WOOD -> transportAction = TRANSPORT_WOOD
                r.resType == RES_STONE -> transportAction = TRANSPORT_STONE
                r.resType == RES_IRON -> transportAction = TRANSPORT_IRON
                r.resType == RES_GOLD -> transportAction = TRANSPORT_GOLD
            }

            when {
                r.resType == RES_FOOD -> {
                    targetAddVal(
                        areaCache,
                        player.id,
                        TransportFoodNum,
                        asList(r.num)
                    )
                }
                r.resType == RES_WOOD -> {
                    targetAddVal(
                        areaCache,
                        player.id,
                        TransportWoodNum,
                        asList(r.num)
                    )
                }
                r.resType == RES_STONE -> {
                    targetAddVal(
                        areaCache,
                        player.id,
                        TransportStoneNum,
                        asList(r.num)
                    )
                }
                r.resType == RES_IRON -> {
                    targetAddVal(
                        areaCache,
                        player.id,
                        TransportIronNum,
                        asList(r.num)
                    )
                }
                r.resType == RES_GOLD -> {
                    targetAddVal(
                        areaCache,
                        player.id,
                        TransportCoinNum,
                        asList(r.num)
                    )
                }
            }

            wpm.es.fire(
                areaCache,
                force.playerId,
                PLAYER_ACTIVITY_CHANGE,
                PlayerActivityChange(transportAction, r.num.toInt(), 0)
            )
        }

        wpm.es.fire(
            areaCache, force.playerId, TRANSPORT_RES_SUCCESS,
            TransportSuccess(totalNum)
        )
    }
}

