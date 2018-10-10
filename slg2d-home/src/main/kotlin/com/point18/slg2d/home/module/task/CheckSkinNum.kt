package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 皮肤个数
class CheckSkinNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val skinDC = checkDep.skinDC
        val skins = skinDC.findSkinsByPlayerId()
        if (skins.size >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, skins.size.toLong())
    }
}



