package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType

// 完成魔物猎杀次数
class CheckAtkMonsterNum : AllCheck {
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

        val needMainId = checkValue[0]
        val needLv = checkValue[1]
        val needNum = checkValue[2]

        var nowNum = 0L
        for ((mainId, monsterMap) in myTarget.atkMonsterMap) {
            if (needMainId != 0 && mainId != needMainId) {
                continue
            }
            for ((lv, num) in monsterMap) {
                if (needLv != 0 && lv < needLv) {
                    continue
                }
                nowNum += num
            }
        }

        if (nowNum >= needNum) {
            return AllCheckReturn(true, needNum.toLong())
        }
        return AllCheckReturn(false, nowNum)
    }
}



