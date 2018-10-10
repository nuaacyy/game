package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.constg.UnLocked

// 迷雾通关
class CheckClearFog : AllCheck {
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

        val fogId = checkValue[0]
        val fog = areaCache.fogCache.findFogById(playerId, fogId) ?: return AllCheckReturn(false, 0)

        if (fog.state != UnLocked) {
            return AllCheckReturn(false, 0)
        }
        return AllCheckReturn(true, 1)
    }
}


