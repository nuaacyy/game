package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.STRENGTH_SKIN
import com.point18.slg2d.home.module.EventData

data class StrengthSkinEvent(
    val skinProto: Int, // 皮肤模版
    val skinStarNum: Int // 强化后的星数
) : EventData(STRENGTH_SKIN)

