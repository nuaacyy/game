package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 资源累计获得
class CheckGetResNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homeTargetDC = checkDep.homeMyTargetDC
        var value = 0L

        for ((resType, resValue) in homeTargetDC.targetInfo.getResInfo) {
            if (checkValue[0] == 0 || resType == checkValue[0]) {
                value = resValue
            }

        }
        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



