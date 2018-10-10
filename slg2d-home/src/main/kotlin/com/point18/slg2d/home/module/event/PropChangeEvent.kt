package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.PROP_CHANGE
import com.point18.slg2d.home.module.EventData

data class PropChangeEvent(
    val protoId: Int // 道具图鉴-prop表id
) : EventData(PROP_CHANGE)