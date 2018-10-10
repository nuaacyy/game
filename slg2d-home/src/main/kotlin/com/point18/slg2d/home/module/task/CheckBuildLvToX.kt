package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 某个建筑等级达到X
class CheckBuildLvToX : AllCheck {

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
        var maxLv = 0
        for (buildingInfo in buildingSet) {
            val bType = buildingInfo.cityType
            if (bType != checkValue[0]) {
                continue
            }

            val lv = buildingInfo.lv
            if (lv > maxLv) {
                maxLv = lv
            }

            if (maxLv >= checkValue[1]) {
                return AllCheckReturn(true, checkValue[1].toLong())
            }
        }
        return AllCheckReturn(false, maxLv.toLong())
    }
}



