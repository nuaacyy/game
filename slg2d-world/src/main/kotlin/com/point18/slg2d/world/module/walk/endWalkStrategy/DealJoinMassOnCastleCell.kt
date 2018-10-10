package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.constg.MassWaiting
import com.point18.slg2d.common.constg.Update
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup

class DealJoinMassOnCastleCell : ICellDeal {

    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val castle = areaCache.castleCache.findCastleByXy(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null || otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        if (player.allianceId == 0L || player.allianceId != otherPlayer.allianceId) {
            // 不是同联盟
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        areaCache.walkForceGroupCache.changeGroupState(group, MassWaiting)
        noticeSelfWalkForceGroup(areaCache, Update, group)
    }
}

