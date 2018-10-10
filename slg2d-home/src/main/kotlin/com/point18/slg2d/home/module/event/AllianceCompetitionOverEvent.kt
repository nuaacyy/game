package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_OVER
import com.point18.slg2d.home.module.EventData

// 联盟总动员结束
data class AllianceCompetitionOverEvent(
    val rankLv: Int,
    val rank: Int
) : EventData(ALLIANCE_COMPETITION_OVER)