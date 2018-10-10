package com.point18.slg2d.world.module.bigMap

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.baseg.TimeEvent
import com.point18.slg2d.common.constg.RefreshBigMap
import com.point18.slg2d.world.area4data.AreaCache

class RefreshBigMapEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val timeTaskType = (event as TimeEvent).TimeTaskType
        if (timeTaskType == RefreshBigMap) {
            // 重置玩家信息
            for (p in cache.playerCache.findAllPlayers()) {
                p.allianceGiftGetMap = hashMapOf()
                p.allianceGiftNum = 0
            }
        }
    }
}