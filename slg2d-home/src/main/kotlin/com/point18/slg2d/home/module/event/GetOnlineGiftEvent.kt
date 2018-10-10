package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_ONLINE_REWARD
import com.point18.slg2d.home.module.EventData

data class GetOnlineGiftEvent(
    val isBigGift: Boolean // 是否是大礼物
) : EventData(GET_ONLINE_REWARD)

