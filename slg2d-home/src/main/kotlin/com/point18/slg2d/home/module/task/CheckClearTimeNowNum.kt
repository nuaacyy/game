package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.ClearTimeEvent

// 清除加速时间
class CheckClearTimeNowNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = nowFinish
        if (event !is ClearTimeEvent) {
            return AllCheckReturn(false, value)
        }

        if (checkValue[0] == 0 || event.clearType == checkValue[0]) {
            value += event.clearSec
        }


        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



