package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.HERO_SUPER_UP
import com.point18.slg2d.home.module.EventData

class HeroSuperUpEvent(
    val heroId: Long,
    val oldLv: Int,
    val nowLv: Int
) : EventData(HERO_SUPER_UP)

