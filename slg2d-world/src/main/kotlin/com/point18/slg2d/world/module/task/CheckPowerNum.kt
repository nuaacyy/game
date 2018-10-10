package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.common.baseg.EventType

// 实力
class CheckPowerNum : AllCheck {
    override fun check(
        areaCache: AreaCache,
        ventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): AllCheckReturn {
        if (checkValue.size < 1) {
            return AllCheckReturn(false, 0)
        }

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (myTarget == null) {
            return AllCheckReturn(false, 0)
        }

        val power = myTarget.getTotalPower()
        if (power >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }
        return AllCheckReturn(false, power)
    }
}


