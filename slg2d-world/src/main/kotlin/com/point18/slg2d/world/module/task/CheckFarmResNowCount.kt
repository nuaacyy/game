package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.FarmResEvent

// 采集数量
class CheckFarmResNowCount : AllCheck {
    override fun check(
        areaCache: AreaCache,
        ventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): AllCheckReturn {
        if (checkValue.size < 3) {
            return AllCheckReturn(false, 0)
        }

        var nowNum = nowValue
        if (event is FarmResEvent) {
            if (checkValue[0] == 0 || checkValue[0] == event.resType) {
                nowNum += 1
            }
        }

        if (nowNum >= checkValue[2]) {
            return AllCheckReturn(true, checkValue[2].toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}


