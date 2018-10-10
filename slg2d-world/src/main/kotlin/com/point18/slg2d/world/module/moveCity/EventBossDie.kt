package com.point18.slg2d.world.module.moveCity

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.BossFight

class BossDieEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val eventData = event as BossFight
        if (eventData.remainingHp > 0) {
            return
        }

        val locationXYProtoMap =
            pcs.monsterActivityLocationProtoCache.monsterActivityLocationXYProtoMap
        val locationProto = locationXYProtoMap.findByKey(eventData.disappearX, eventData.disappearY)
        if (locationProto == null) {
            return
        }

        for ((x, subMap) in locationProto.snowMap) {
            for ((y, _) in subMap) {
                val castle = cache.castleCache.findCastleByXy(x, y) ?: continue
                // 随机迁城
                randomFlyCastleAway(cache, castle.playerId)
            }
        }
    }
}