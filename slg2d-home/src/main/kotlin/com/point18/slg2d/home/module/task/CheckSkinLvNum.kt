package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 皮肤等级达到X个
class CheckSkinLvNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val skinDC = checkDep.skinDC
        val skins = skinDC.findSkinsByPlayerId()

        var value = 0

        for (skin in skins) {
            if (skin.star >= checkValue[0]) {
                value++
            }
        }
        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }
}



