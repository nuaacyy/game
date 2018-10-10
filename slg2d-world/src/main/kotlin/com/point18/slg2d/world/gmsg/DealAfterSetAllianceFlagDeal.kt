package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.AllianceFlagChangeNotifier
import pb4server.Public2WorldTell

class DealAfterSetAllianceFlagDeal : Public2WorldTellDealBase() {

    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.dealAfterSetAllianceFlagTell
        val color = (tell.color)
        val style = (tell.style)
        val effect = (tell.effect)

        val flagNotifier = AllianceFlagChangeNotifier(color, style, effect)

        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId(tell.allianceId)
        for (allianceMember in allAllianceMember) {
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                flagNotifier.notice(session)
            }
        }

    }
}

