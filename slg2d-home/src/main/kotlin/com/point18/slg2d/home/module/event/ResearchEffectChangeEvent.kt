package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.RESEARCH_EFFECT_CHANGE
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.module.EventData

class ResearchEffectChangeEvent(
    val changeEffect: Map<Int, Int>,
    val targetHelper: TargetHelper,
    val effectHelper: ResearchEffectHelper,
    val refreshRes: RefreshResourceHelper
) : EventData(RESEARCH_EFFECT_CHANGE)