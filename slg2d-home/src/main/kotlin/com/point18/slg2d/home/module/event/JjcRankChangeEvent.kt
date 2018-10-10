package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.HERO_SKILL_LV_UP
import com.point18.slg2d.home.module.EventData

data class JjcRankChangeEvent(
    val change: Int, // 上升了多少名次
    val nowRank: Int // 当前名次
) : EventData(HERO_SKILL_LV_UP)

