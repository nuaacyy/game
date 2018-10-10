package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType

class CheckInstanceHaveStarNum : AllCheck {
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

        var value = 0
        val instances = areaCache.instanceCache.findInstance(playerId)
        if (instances != null) {
            for ((instanceId, instance) in instances.starInfoMap) {
                if (instanceId != checkValue[0]) {
                    continue
                }
                value = instance.size
                break
            }
        }

        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }

}



