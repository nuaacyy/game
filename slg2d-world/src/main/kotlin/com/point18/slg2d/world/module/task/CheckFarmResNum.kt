package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType

// 采集资源
class CheckFarmResNum : AllCheck {
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

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (myTarget == null) {
            return AllCheckReturn(false, 0)
        }

        val needResType = checkValue[0]
        val needNum = checkValue[1]

        var nowNum = 0L
        for ((resType, num) in myTarget.farmResNumMap) {
            if (needResType != 0 && resType != needResType) {
                continue
            }
            nowNum += num
        }

        if (nowNum >= needNum) {
            return AllCheckReturn(true, needNum.toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}



