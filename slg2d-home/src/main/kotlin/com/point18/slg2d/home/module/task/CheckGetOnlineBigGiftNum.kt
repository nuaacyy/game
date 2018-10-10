package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.GetOnlineGiftEvent

class CheckGetOnlineBigGiftNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = nowFinish
        if (event is GetOnlineGiftEvent) {
            if (event.isBigGift) {
                value += 1
            }
        }

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



