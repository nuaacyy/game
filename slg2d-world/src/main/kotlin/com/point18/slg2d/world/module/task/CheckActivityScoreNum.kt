package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType

// 类型：挑战类型：积分获得
class CheckActivityScoreNum : AllCheck {
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

        val score = myTarget.activityScoreMap[checkValue[0]]
        if (score == null) {
            return AllCheckReturn(false, 0)
        } else {
            if (score >= checkValue[1]) {
                return AllCheckReturn(true, checkValue[1].toLong())
            } else {
                return AllCheckReturn(false, score.toLong())
            }
        }
    }
}


