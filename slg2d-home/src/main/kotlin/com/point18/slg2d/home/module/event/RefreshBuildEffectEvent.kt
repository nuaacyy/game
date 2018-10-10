package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.REFRESH_INNER_CITY_EFFECT
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.module.EventData

data class RefreshBuildEffectEvent(
    val targetHelper: TargetHelper,
    val effectHelper: ResearchEffectHelper
) : EventData(REFRESH_INNER_CITY_EFFECT)

