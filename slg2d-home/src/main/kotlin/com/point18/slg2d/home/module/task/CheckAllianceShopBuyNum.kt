package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 联盟商店购买次数
class CheckAllianceShopBuyNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homeMyTargetDC = checkDep.homeMyTargetDC
        val value = homeMyTargetDC.targetInfo.allianceShopBuyNum

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }

}



