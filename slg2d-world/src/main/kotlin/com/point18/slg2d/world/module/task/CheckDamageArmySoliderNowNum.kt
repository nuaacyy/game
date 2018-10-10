package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.DamageSoliderEvent
import com.point18.slg2d.common.pc.pcs

// 击伤兵数量
class CheckDamageArmySoliderNowNum : AllCheck {
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
        if (event is DamageSoliderEvent) {
            for ((soliderId, num) in event.damageMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
                if (soliderProto == null) {
                    continue
                }
                if (checkValue[0] != 0 && soliderProto.armyType != checkValue[0]) {
                    continue
                }
                if (checkValue[1] != 0 && soliderProto.step < checkValue[1]) {
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


