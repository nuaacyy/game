package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createGetAllianceHelpNotifier
import pb4server.Public2WorldTell

class DealHelperNoticeDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.dealHelperNoticeTell
        val notice = createGetAllianceHelpNotifier(tell.playerName, tell.helpType)

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            notice.notice(session)
        }
    }
}

