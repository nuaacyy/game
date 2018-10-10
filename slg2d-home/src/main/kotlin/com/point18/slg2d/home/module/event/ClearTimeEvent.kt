package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.CLEAR_TIME_EVENT
import com.point18.slg2d.home.module.EventData

// 联盟总动员结束
data class ClearTimeEvent(
    val clearType: Int, // 加速类型
    val clearSec: Int // 加速秒数,免费的不算
) : EventData(CLEAR_TIME_EVENT)