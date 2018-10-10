package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_ALLIANCE_COMPETITION_QUEST_REWARD
import com.point18.slg2d.home.module.EventData

data class GetAllianceCompetitionQuestRewardEvent(
    val score: Int
) : EventData(GET_ALLIANCE_COMPETITION_QUEST_REWARD)

