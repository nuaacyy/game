package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_NEW_HERO
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.IconDC
import com.point18.slg2d.home.module.EventData

data class GainHeroEvent(
    val heroId: Long
) : EventData(GET_NEW_HERO)