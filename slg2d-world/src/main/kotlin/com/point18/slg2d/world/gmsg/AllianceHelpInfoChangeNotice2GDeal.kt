package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceHelpInfoChangeNotifier
import pb4server.Public2WorldTell
import java.util.*

class AllianceHelpInfoChangeNotice2GDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {

        val tell = msg.allianceHelpInfoChangeNotic2GTell
        val helpList = LinkedList<Long>()
        for (helpId in tell.helpIdList) {
            helpList.add(helpId)
        }
        val msgNotifier = createAllianceHelpInfoChangeNotifier(tell.changeInfo, helpList)

        for (tPlayerId in tell.playerIdsList) {
            val eachSession = fetchOnlinePlayerSession(areaCache, tPlayerId)
            if (eachSession != null) {
                msgNotifier.notice(eachSession)
            }
        }
    }

}

