package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.FarmEmpty

class FarmEmptyEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        if (event is FarmEmpty) {
            onFarmEmpty(cache, playerId, event)
        }
    }

    private fun onFarmEmpty(areaCache: AreaCache, playerId: Long, eventData: FarmEmpty) {
        val farmEmptyAction = when (eventData.resType) {
            RES_FOOD -> {
                when (eventData.lv) {
                    1 -> FARM_FOOD_SUCC_1
                    2 -> FARM_FOOD_SUCC_2
                    3 -> FARM_FOOD_SUCC_3
                    4 -> FARM_FOOD_SUCC_4
                    5 -> FARM_FOOD_SUCC_5
                    else -> return
                }
            }
            RES_WOOD -> {
                when (eventData.lv) {
                    1 -> FARM_WOOD_SUCC_1
                    2 -> FARM_WOOD_SUCC_2
                    3 -> FARM_WOOD_SUCC_3
                    4 -> FARM_WOOD_SUCC_4
                    5 -> FARM_WOOD_SUCC_5
                    else -> return
                }
            }
            RES_STONE -> {
                when (eventData.lv) {
                    1 -> FARM_STONE_SUCC_1
                    2 -> FARM_STONE_SUCC_2
                    3 -> FARM_STONE_SUCC_3
                    4 -> FARM_STONE_SUCC_4
                    5 -> FARM_STONE_SUCC_5
                    else -> return
                }
            }
            RES_IRON -> {
                when (eventData.lv) {
                    1 -> FARM_IRON_SUCC_1
                    2 -> FARM_IRON_SUCC_2
                    3 -> FARM_IRON_SUCC_3
                    4 -> FARM_IRON_SUCC_4
                    5 -> FARM_IRON_SUCC_5
                    else -> return
                }
            }
            RES_GOLD -> {
                when (eventData.lv) {
                    1 -> FARM_GOLD_SUCC_1
                    2 -> FARM_GOLD_SUCC_2
                    3 -> FARM_GOLD_SUCC_3
                    4 -> FARM_GOLD_SUCC_4
                    5 -> FARM_GOLD_SUCC_5
                    else -> return
                }
            }
            else -> return
        }

        onActivityChange(areaCache, playerId, farmEmptyAction, 1)
    }
}