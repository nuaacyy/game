package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.USE_PROPS_AT_ONCE
import com.point18.slg2d.home.module.EventData

class UsePropsAtOnceEvent(
    val propsOnlyId: Long,
    val propsId: Int,
    val num: Int
) : EventData(USE_PROPS_AT_ONCE)