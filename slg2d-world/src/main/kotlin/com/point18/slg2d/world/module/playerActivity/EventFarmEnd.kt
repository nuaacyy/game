package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.FarmEnd

class FarmEndEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        if (event is FarmEnd) {
            onFarmEnd(cache, playerId, event)
        }
    }

    private fun onFarmEnd(areaCache: AreaCache, playerId: Long, eventData: FarmEnd) {
        val farmEndAction = when (eventData.resType) {
            RES_FOOD -> FARM_FOOD
            RES_WOOD -> FARM_WOOD
            RES_STONE -> FARM_STONE
            RES_IRON -> FARM_IRON
            RES_GOLD -> FARM_GOLD
            else -> return
        }

        onActivityChange(areaCache, playerId, farmEndAction, eventData.farmNum)
    }
}