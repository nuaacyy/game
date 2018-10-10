package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.common.baseg.EventType

// 势力值达到X
class TaskPowerValueToX : AllCheck {
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

        val target = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (target == null) {
            return AllCheckReturn(false, 0)
        }
        val pow = target.getTotalPower()
        if (pow >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }
        return AllCheckReturn(false, pow)
    }
}
