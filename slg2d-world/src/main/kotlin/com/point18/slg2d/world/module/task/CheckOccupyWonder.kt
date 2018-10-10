package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.OccupyWonder
import com.point18.slg2d.common.pc.pcs

class CheckOccupyWonder : AllCheck {
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

        val wonderType = checkValue[0]
        val checkNum = checkValue[1]

        var value = nowValue
        if (event is OccupyWonder) {
            val wonderLocationProto =
                pcs.wonderLocationProtoCache.wonderLocationProtoMap[event.wonderProtoId]
            if (wonderLocationProto != null) {
                if (wonderType == 0) {
                    //任意奇观
                    value += 1
                } else if (wonderLocationProto.wondersType == wonderType) {
                    value += 1
                }
            }
        }

        if (value >= checkNum) {
            return AllCheckReturn(true, checkNum.toLong())
        }

        return AllCheckReturn(false, value)
    }

}