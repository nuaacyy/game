package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 某个建筑等级达到X达到Y个
class CheckTypeBuildLvToX : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val buildDC = checkDep.innerCityDC
        val homePlayerDC = checkDep.homePlayerDC

        val buildingSet = buildDC.findInnerCityListFromCastleId(homePlayerDC.player.focusCastleId)
        // 读取出相应城池的信息放入map中
        var value = 0
        for (buildingInfo in buildingSet) {
            val bType = buildingInfo.cityType
            if (bType != checkValue[0]) {
                continue
            }

            if (buildingInfo.lv >= checkValue[1]) {
                value++
            }
        }

        if (value >= checkValue[2]) {
            return AllCheckReturn(true, checkValue[2].toLong())
        }
        return AllCheckReturn(false, value.toLong())
    }
}



