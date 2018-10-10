package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 内城资源产出速度
class CheckBuildResSpeed : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = 0
        val homePlayerDC = checkDep.homePlayerDC

        if (checkValue[0] == 0) {
            // 不限制
            val effValue1 = homePlayerDC.player.innerBuildingEffectInfoMap[WoodCost]
            if (effValue1 != null) {
                value += effValue1
            }
            val effValue2 = homePlayerDC.player.innerBuildingEffectInfoMap[IronCost]
            if (effValue2 != null) {
                value += effValue2
            }
            val effValue3 = homePlayerDC.player.innerBuildingEffectInfoMap[FoodCost]
            if (effValue3 != null) {
                value += effValue3
            }
            val effValue4 = homePlayerDC.player.innerBuildingEffectInfoMap[StoneCost]
            if (effValue4 != null) {
                value += effValue4
            }
            val effValue5 = homePlayerDC.player.innerBuildingEffectInfoMap[CoinCost]
            if (effValue5 != null) {
                value += effValue5
            }
        } else {
            val effValue = homePlayerDC.player.innerBuildingEffectInfoMap[checkValue[0]]
            if (effValue != null) {
                value = effValue
            }
        }

        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }
}



