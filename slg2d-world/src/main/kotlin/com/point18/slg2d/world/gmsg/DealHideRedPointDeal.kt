package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createHideRedPointNotifier
import pb4server.Public2WorldTell

class DealHideRedPointDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val tell = msg.dealHideRedPointTell
            val notice = createHideRedPointNotifier(tell.redPointType, tell.allianceId)

            notice.notice(session)
        }
    }
}

