package com.point18.slg2d.world.module.task

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.world.area4data.AreaCache

class CheckTaskEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        checkAll(cache, event, eventType, playerId)
    }

    // 刷新任务与成就
    private fun checkAll(areaCache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        handleStateEvent(areaCache, event, eventType, playerId)
        handleOverEvent(areaCache, event, eventType, playerId)
    }
}