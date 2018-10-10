package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.VIP_LV_CHANGE
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.module.EventData

class VipLvChangeEvent(
    val oldVipLv: Int,
    val newVipLv: Int,
    val ability: Map<Int, Int>,
    val effectHelper: ResearchEffectHelper,
    val refreshResHelper: RefreshResourceHelper
) : EventData(VIP_LV_CHANGE)