package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.ActivityGetAdvReward

// 挑战达到某阶段X次
class CheckPlayerActivityAdv : AllCheck {
    override fun check(
        areaCache: AreaCache,
        ventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): AllCheckReturn {
        if (checkValue.size < 2) {
            return AllCheckReturn(false, 0)
        }

        var value = nowValue

        if (event is ActivityGetAdvReward) {
            if (event.maxIndex > checkValue[0])
                value += 1
        }

        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value)
    }
}


