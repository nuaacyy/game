package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.TALENT_LV_CHANGE
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.module.EventData

data class TalentLvChangeEvent(
    val refreshResHelper: RefreshResourceHelper,
    val effectHelper: ResearchEffectHelper
) : EventData(TALENT_LV_CHANGE)