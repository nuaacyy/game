package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.MOVE_CITY
import com.point18.slg2d.home.module.EventData

data class MoveCityEvent(
    val moveType: Int
) : EventData(MOVE_CITY)

