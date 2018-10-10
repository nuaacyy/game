package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.fightdomain.createFightDataByForceGroup
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.module.walk.fightStrategy.soliderFight

class DealOccupyOnEmptyCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到行军组中的玩家信息,行军组Id:%d", group.id)
            return ResultCode.PARAMETER_ERROR.code
        }
        val groupsOnCell = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(
            posX,
            posY,
            Stationed
        )
        for (cellGroup in groupsOnCell) {
            if (cellGroup.mainPlayerId == group.mainPlayerId) {
                // 自身部队已经驻扎
                return ResultCode.CELL_NOT_FREE.code
            }

            if (player.allianceId != 0L) {
                val otherPlayer = areaCache.playerCache.findPlayerById(cellGroup.mainPlayerId)
                if (otherPlayer == null) {
                    normalLog.error("找不到行军组中的玩家信息,行军组Id:%d", cellGroup.id)
                    return ResultCode.PARAMETER_ERROR.code
                }
                if (player.allianceId == otherPlayer.allianceId) {
                    // 同盟部队驻扎中
                    return ResultCode.CELL_NOT_FREE.code
                }
            }
        }
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY
        val fightStationForceResult = fightWithStationForce(areaCache, group, walkLineInfo)
        if (fightStationForceResult == FIGHT_RESULT_LOSE) {
            return
        }

        areaCache.walkForceGroupCache.changeGroupState(group, Stationed)
        noticeSelfWalkForceGroup(areaCache, Update, group)
        noticeCellUpdate(areaCache, posX, posY)
    }

    //和驻扎部队战斗
    private fun fightWithStationForce(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val stationGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(
            posX,
            posY,
            Stationed
        )
        for (stationGroup in stationGroups) {
            val atkFightData = createFightDataByForceGroup(areaCache, group)
            val defFightData = createFightDataByForceGroup(areaCache, stationGroup)
            val atkForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
            val defForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(stationGroup.id)

            val fightResultData = soliderFight.doSoliderFight(
                areaCache,
                PVP_FIGHT_GROUP_ACTION,
                WalkOccupyCell,
                FIGHT_GROUP_REPORT,
                group.massId != 0L,
                posX,
                posY,
                atkForceList,
                defForceList,
                atkFightData,
                defFightData
            )
            val fightResult = fightResultData.fightResult

            if (fightResult == FIGHT_RESULT_LOSE) {
                atkFightData.checkPrison(
                    areaCache,
                    group,
                    defFightData.playerId
                )
                //攻击方回城
                goHome(areaCache, posX, posY, group)
                return FIGHT_RESULT_LOSE
            }
            //防守驻防部队回城
            goHome(areaCache, posX, posY, stationGroup)
        }

        return FIGHT_RESULT_WIN
    }
}