package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.InstanceFightEvent

class CheckInstanceFightNowNum : AllCheck {
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

        var value = nowValue

        if (event is InstanceFightEvent) {
            value += event.fightNum
        }

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, value)
    }

}



