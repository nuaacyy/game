package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 英雄阶级达到X的个数
class CheckHeroAwakeNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val heroDC = checkDep.heroDC
        val heroMap = heroDC.findHeroMapByPlayer()

        var value = 0
        for ((_, hero) in heroMap) {
            if (hero.awake >= checkValue[0]) {
                value++
            }
        }


        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }
}



