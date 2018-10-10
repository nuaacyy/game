package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.AllianceCompetitionOverEvent

// 联盟联赛	类型:阶段值	历史记录	段位
class CheckAllianceCompetitionRankLv : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = false

        if (event is AllianceCompetitionOverEvent) {
            if (event.rankLv >= checkValue[0]) {
                value = true
            }
        }

        if (value) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, 0)
    }
}






