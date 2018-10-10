package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 总动员积分
class CheckCompetitionScoreNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homePlayerDC = checkDep.homePlayerDC
        val player = homePlayerDC.player

        if (player.allianceCompetitionMyScore >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, player.allianceCompetitionMyScore.toLong())
    }
}



