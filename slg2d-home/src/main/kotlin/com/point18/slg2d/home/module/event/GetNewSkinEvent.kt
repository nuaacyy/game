package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_NEW_SKIN
import com.point18.slg2d.home.module.EventData

data class GetNewSkinEvent(
    val skinProto: Int // 皮肤模版
) : EventData(GET_NEW_SKIN)

