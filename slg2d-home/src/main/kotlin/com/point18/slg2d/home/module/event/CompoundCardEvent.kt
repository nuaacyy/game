package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.COMPOUND_CARD
import com.point18.slg2d.home.module.EventData

data class CompoundCardEvent(
    val num: Int,
    val qul: Int
) : EventData(COMPOUND_CARD)

