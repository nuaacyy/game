package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 历史总清除加速时间
class CheckClearTimeNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homeMyTargetDC = checkDep.homeMyTargetDC
        var value = 0L

        if (checkValue[0] == 0) {
            // 不区分加速类型
            for ((_, v) in homeMyTargetDC.targetInfo.clearTimeInfo) {
                value += v
            }
        } else {
            value += homeMyTargetDC.targetInfo.clearTimeInfo.getOrPut(checkValue[0]) { 0 }
        }


        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



