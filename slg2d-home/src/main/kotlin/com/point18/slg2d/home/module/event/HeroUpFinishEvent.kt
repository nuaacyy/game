package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.HERO_UP_FINISH
import com.point18.slg2d.home.module.EventData

class HeroUpFinishEvent(
    val heroId: Long,
    val oldLv: Int,
    val nowLv: Int
) : EventData(HERO_UP_FINISH)
