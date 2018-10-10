package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.KING_LV_CHANGE
import com.point18.slg2d.home.module.EventData

data class KingLvUpEvent(
    val oldLv: Int,
    val newLv: Int
) : EventData(KING_LV_CHANGE)