package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.KingLvUpEvent

// 君主等级
class CheckKingLv : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        if (event !is KingLvUpEvent){
            return AllCheckReturn(false, nowFinish)
        }

        val homeTargetDC = checkDep.homePlayerDC
        val value = homeTargetDC.player.kingLv.toLong()

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



