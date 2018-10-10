package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 获得指定皮肤
class CheckSkinHave : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val skinDC = checkDep.skinDC
        val skins = skinDC.findSkinsByPlayerId()
        for (skin in skins) {
            if (skin.skinType == checkValue[0]) {
                return AllCheckReturn(true, 1.toLong())
            }
        }

        return AllCheckReturn(false, 0.toLong())
    }
}



