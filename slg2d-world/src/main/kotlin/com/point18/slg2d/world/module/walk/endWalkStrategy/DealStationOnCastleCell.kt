package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.constg.Stationed
import com.point18.slg2d.common.constg.Update
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup

class DealStationOnCastleCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        // 验证地块是否有同盟玩家
        val gotoX = walkLineInfo.marchAimsX
        val gotoY = walkLineInfo.marchAimsY

        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
            ?: return ResultCode.PARAMETER_ERROR.code

        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null || otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        if (player.allianceId == 0L || player.allianceId != otherPlayer.allianceId) {
            // 不在同联盟
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }

        //验证对方是否已有驻扎部队
        val reinforceGroupCount =
            areaCache.walkForceGroupCache.findWalkForceGroupsByPos(gotoX, gotoY).count { it.runningState == Stationed }

        if (reinforceGroupCount > 0) {
            //已有驻扎部队
            return ResultCode.OVER_LIMIT_STATION.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        areaCache.walkForceGroupCache.changeGroupState(group, Stationed)

        // 通知刷新行军组信息
        noticeSelfWalkForceGroup(areaCache, Update, group)
    }
}

