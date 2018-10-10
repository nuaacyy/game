package com.point18.slg2d.world.module.transport

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.Update
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.event.Transport
import com.point18.slg2d.world.msgnotice.createTransportRequestChangeNotifier
import java.util.*

class TransportStartEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val eventData = event as Transport
        val req = cache.transportRequestCache.findTransportRequestByPlayerId(eventData.targetPlayerId)
        if (req == null) {
            return
        }
        val player = cache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }
        val targetPlayer = cache.playerCache.findPlayerById(eventData.targetPlayerId)
        if (targetPlayer == null) {
            return
        }

        val finalResVo = LinkedList<ResVo>()
        for (resVo in req.resVo) {
            var haveNum = 0L
            for (vo in eventData.resVo) {
                if (vo.resType == resVo.resType) {
                    haveNum = vo.num
                    break
                }
            }
            if (resVo.num <= haveNum) {
                continue
            }

            val cutResVo = ResVo(resVo.resType, resVo.subType, resVo.num - haveNum)
            finalResVo.add(cutResVo)
        }

        if (finalResVo.count() == 0) {
            //已运满
            deleteTransport(cache, player, req.id)
            return
        }
        req.putResVo(finalResVo)

        val allMembers = cache.playerCache.findPlayersByAllianceId(player.allianceId)
        for (member in allMembers) {
            val eachSession = fetchOnlinePlayerSession(cache, member.id)
            if (eachSession != null) {
                val notifier = createTransportRequestChangeNotifier(targetPlayer, req, Update)
                notifier.notice(eachSession)
            }
        }
    }
}