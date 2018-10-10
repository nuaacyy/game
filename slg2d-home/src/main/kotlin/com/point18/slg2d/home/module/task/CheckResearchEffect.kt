package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.*

// 某个科技效果值达到X
class CheckResearchEffect : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = nowFinish
        if (event is ResearchEffectChangeEvent) {
            val v = event.changeEffect[checkValue[0]]
            if (v != null) {
                value = v.toLong()
            }
        } else if (event is BuildingUpFinishEvent) {
            value = event.effectHelper.getResearchEffectValue(session, com.point18.slg2d.common.constg.NO_FILTER, checkValue[0]).toLong()
        } else if(event is GetUnitTaskRewardEvent) {
            value = event.effectHelper.getResearchEffectValue(session, com.point18.slg2d.common.constg.NO_FILTER, checkValue[0]).toLong()
        } else if(event is GetTaskRewardEvent) {
            value = event.effectHelper.getResearchEffectValue(session, com.point18.slg2d.common.constg.NO_FILTER, checkValue[0]).toLong()
        }else if (event is TalentLvChangeEvent){
            value = event.effectHelper.getResearchEffectValue(session, com.point18.slg2d.common.constg.NO_FILTER, checkValue[0]).toLong()
        }

        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



