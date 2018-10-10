package com.point18.slg2d.world.module.king

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.ACTION_KING_LV_UP
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.event.KingLvUp

class KingLvChangeEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val eventData = event as KingLvUp

        val player = cache.playerCache.findPlayerById(playerId)

        if (player == null) {
            return
        }
        val oldLv = eventData.oldLv
        val newLv = eventData.newLv

        for (lv in oldLv + 1..newLv) {
            val proto = pcs.kingExpCache.kingExpMap[lv]
            if (proto == null) {
                continue
            }

            // 加奖励
            addResToHome(cache, ACTION_KING_LV_UP, player.id, proto.rewardResVo)

        }
    }

}