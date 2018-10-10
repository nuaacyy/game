package com.point18.slg2d.home.module.task

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.module.EventData

data class CheckDep(
    val homePlayerDC: HomePlayerDC,
    val bagDC: BagDC,
    val homeMyTargetDC: HomeMyTargetDC,
    val homeTaskDC: HomeTaskDC,
    val heroDC: HeroDC,
    val iconDC: IconDC,
    val innerCityDC: InnerCityDC,
    val libraryDC: LibraryDC,
    val equipDC: NewEquipDC,
    val skinDC: SkinDC,
    val vipDC: VipDC
)

interface AllCheck {

    /**
     * 检测
     */
    fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn

}