package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GO_ALLIANCE_HELP
import com.point18.slg2d.home.module.EventData

data class GoAllianceHelpEvent(
    val helpNum: Int // 帮助次数
) : EventData(GO_ALLIANCE_HELP)