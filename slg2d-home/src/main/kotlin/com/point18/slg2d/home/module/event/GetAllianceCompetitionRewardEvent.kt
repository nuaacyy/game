package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_ALLIANCE_COMPETITION_REWARD
import com.point18.slg2d.home.module.EventData

// 领取联盟奖励
data class GetAllianceCompetitionRewardEvent(
    val rewardMap: Map<Int, Int>
) : EventData(GET_ALLIANCE_COMPETITION_REWARD)