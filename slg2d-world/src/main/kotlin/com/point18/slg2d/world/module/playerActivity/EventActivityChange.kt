package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.PlayerActivityChange

class ActivityChangeEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        if (event is PlayerActivityChange) {
            onActivityChange(cache, playerId, event.activityConditionType, event.activityAddNum)
        }
    }
}