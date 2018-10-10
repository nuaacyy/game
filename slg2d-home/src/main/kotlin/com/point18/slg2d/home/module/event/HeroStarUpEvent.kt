package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.HERO_STAR_UP
import com.point18.slg2d.home.module.EventData

class HeroStarUpEvent(
    //todo 事件参数
    val heroId: Long,
    val oldLv: Int,
    val nowLv: Int
) : EventData(HERO_STAR_UP)

