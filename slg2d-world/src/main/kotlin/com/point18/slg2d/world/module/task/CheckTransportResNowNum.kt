package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.TransportSuccess

// 运输次数
class CheckTransportResNowNum : AllCheck {
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

        var nowNum = nowValue
        if (event is TransportSuccess) {
            nowNum += 1
        }

        if (nowNum >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}


