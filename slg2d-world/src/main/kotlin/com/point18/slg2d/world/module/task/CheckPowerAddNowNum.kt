package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.PowerAdd

// 实力提升
class CheckPowerAddNowNum : AllCheck {
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

        var nowNum = nowValue
        if (event is PowerAdd) {
            if (checkValue[0] == 0 || checkValue[0] == event.powerType) {
                nowNum += event.value
            }
        }

        if (nowNum >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}


