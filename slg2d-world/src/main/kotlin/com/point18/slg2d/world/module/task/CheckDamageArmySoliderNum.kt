package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType

// 击伤兵数量
class CheckDamageArmySoliderNum : AllCheck {
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

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (myTarget == null) {
            return AllCheckReturn(false, 0)
        }

        var nowNum = 0L
        for ((armyType, soliderMap) in myTarget.damageSoliderMap) {
            if (checkValue[0] != 0 && armyType != checkValue[0]) {
                continue
            }
            for ((step, num) in soliderMap) {
                if (checkValue[1] != 0 && step < checkValue[1]) {
                    continue
                }
                nowNum += num
            }
        }

        if (nowNum >= checkValue[2]) {
            return AllCheckReturn(true, checkValue[2].toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}


