package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.MakeSoliderEvent

// 造兵数量
class CheckMakeSoliderNowNum : AllCheck {
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
        if (event is MakeSoliderEvent) {
            if (checkValue[0] == 0 || checkValue[0] == event.armyType) {
                if (checkValue[1] == 0 || event.step >= checkValue[1]) {
                    nowNum += event.makeNum
                }
            }
        }

        if (nowNum >= checkValue[2]) {
            return AllCheckReturn(true, checkValue[2].toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}


