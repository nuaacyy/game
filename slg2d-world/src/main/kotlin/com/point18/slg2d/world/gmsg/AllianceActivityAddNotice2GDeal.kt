package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createPlayerActivityChangeNotifier
import pb4server.Public2WorldTell

class AllianceActivityAddNotice2GDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.allianceActivityChangeNotic2GTell

        for (eachPlayerId in tell.playerIdsList) {
            val session = fetchOnlinePlayerSession(areaCache, eachPlayerId)
            if (session != null) {
                createPlayerActivityChangeNotifier(tell.activityId, tell.score, tell.rank, tell.isActivityOver)
                    .notice(session)
            }
        }
    }
}




