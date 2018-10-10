package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_HERO_CARD
import com.point18.slg2d.home.module.EventData

data class LotteryEvent(
    val LotteryType: Int,
    val LotteryNum: Int
) : EventData(GET_HERO_CARD)

