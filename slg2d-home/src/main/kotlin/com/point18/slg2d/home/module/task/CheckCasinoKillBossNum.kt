package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 赌场杀死boss次数
class CheckCasinoKillBossNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homeMyTargetDC = checkDep.homeMyTargetDC
        val value = homeMyTargetDC.targetInfo.casinoKillBossNum

        var realValue = nowFinish.toInt()
        for (valueTmp in value) {
            if (checkValue[0] == valueTmp.key) {
                realValue = valueTmp.value
                if (realValue >= checkValue[1]) {
                    return AllCheckReturn(true, checkValue[1].toLong())
                }
            }
        }

        return AllCheckReturn(false, realValue.toLong())
    }
}



