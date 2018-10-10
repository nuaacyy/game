package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.JjcRankChangeEvent

class CheckJjcRankChangeNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var jjcRank = 0
        if (event is JjcRankChangeEvent) {
            jjcRank = event.nowRank
        }

        if (jjcRank <= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, jjcRank.toLong())
    }
}



