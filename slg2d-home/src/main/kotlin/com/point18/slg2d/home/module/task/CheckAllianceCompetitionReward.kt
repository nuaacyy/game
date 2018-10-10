package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.GetAllianceCompetitionRewardEvent

// 联盟阶段	类型:阶段	历史记录	3选1那个奖励的最高档次
class CheckAllianceCompetitionReward : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = false

        if (event is GetAllianceCompetitionRewardEvent) {
            if (event.rewardMap.size >= checkValue[0]) {
                value = true
            }
        }

        if (value) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, 0)
    }
}






