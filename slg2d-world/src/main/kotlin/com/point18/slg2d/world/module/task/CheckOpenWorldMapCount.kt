package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.ShowNearMap

// 打开大地图次数
class CheckOpenWorldMapCount : AllCheck {
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
        if (event is ShowNearMap) {
            nowNum += 1
        }

        if (nowNum >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}


