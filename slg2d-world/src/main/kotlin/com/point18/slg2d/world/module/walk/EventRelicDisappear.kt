package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.refreshRelicInfo
import com.point18.slg2d.world.event.RelicDisappear

class RelicDisappearEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val eventData = event as RelicDisappear
        refreshRelicInfo(cache, eventData.disappearX, eventData.disappearY, eventData.relicId)
    }
}