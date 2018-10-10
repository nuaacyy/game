package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.CHAT
import com.point18.slg2d.home.module.EventData

// 联盟总动员结束
data class ChatEvent(
    val chatType: Int // 频道
) : EventData(CHAT)