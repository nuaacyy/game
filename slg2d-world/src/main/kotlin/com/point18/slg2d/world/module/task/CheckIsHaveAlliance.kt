package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType

class CheckIsHaveAlliance : AllCheck {

    override fun check(
        areaCache: AreaCache,
        ventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): AllCheckReturn {
        if (checkValue.isEmpty()) {
            return AllCheckReturn(false, 0)
        }

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null || player.allianceId == 0L) {
            return AllCheckReturn(false, 0)
        }

        return AllCheckReturn(true, 1)
    }
}



