package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_TASK_REWARD
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.module.EventData

class GetTaskRewardEvent(
    val effectHelper: ResearchEffectHelper
) : EventData(GET_TASK_REWARD)

