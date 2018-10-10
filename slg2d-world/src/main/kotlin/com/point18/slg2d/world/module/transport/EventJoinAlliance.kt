package com.point18.slg2d.world.module.transport

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createTransportRequestChangeNotifier

class JoinAllianceEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }

        val reqList = cache.transportRequestCache.findTransportRequestByAllianceId(player.allianceId)
        for (req in reqList) {
            val eachSession = fetchOnlinePlayerSession(cache, req.playerId)
            if (eachSession != null) {
                val notifier = createTransportRequestChangeNotifier(eachSession.player, req, Add)
                notifier.notice(eachSession)
            }
        }
    }

}