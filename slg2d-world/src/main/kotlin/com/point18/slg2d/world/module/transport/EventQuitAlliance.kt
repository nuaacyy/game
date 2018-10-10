package com.point18.slg2d.world.module.transport

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.event.InOrOffAlliance
import com.point18.slg2d.world.msgnotice.createTransportRequestChangeNotifier

class QuitAllianceEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }

        val eventData = event as InOrOffAlliance
        val reqList = cache.transportRequestCache.findTransportRequestByAllianceId(eventData.allianceId)
        for (req in reqList) {
            val eachSession = fetchOnlinePlayerSession(cache, req.playerId)
            if (eachSession != null) {
                val notifier = createTransportRequestChangeNotifier(eachSession.player, req, Del)
                notifier.notice(eachSession)
            }
        }
        val quitMemberReq = cache.transportRequestCache.findTransportRequestByPlayerId(playerId)
        if (quitMemberReq != null) {
            deleteTransport(cache, player, quitMemberReq.id)
        }
    }

}