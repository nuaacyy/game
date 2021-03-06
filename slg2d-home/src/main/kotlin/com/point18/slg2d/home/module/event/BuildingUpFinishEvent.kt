package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.BUILDING_UP_FINISH
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.module.EventData

data class BuildingUpFinishEvent(
    val buildingType: Int,
    val buildingLv: Int,
    val buildingId: Long,
    val targetHelper: TargetHelper,
    val refreshResHelper: RefreshResourceHelper,
    val effectHelper: ResearchEffectHelper,
    val castleId: Long
) : EventData(BUILDING_UP_FINISH)