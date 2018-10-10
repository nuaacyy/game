package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 英雄个数
class CheckHeroNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val heroDC = checkDep.heroDC
        val heroMap = heroDC.findHeroMapByPlayer()
        var haveHeroNum = 0
        if (checkValue[0] == 0) {
            haveHeroNum = heroMap.size
        } else {
            for ((_, h) in heroMap) {
                if (h.level >= checkValue[0]) {
                    haveHeroNum += 1
                }
            }
        }
        if (haveHeroNum >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, haveHeroNum.toLong())
    }
}



