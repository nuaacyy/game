package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Home2WorldTellDealBase
import com.point18.slg2d.world.common.playerLeaveAlliance
import pb4server.Home2WorldTell
import java.util.*

class JoinNoSucTellDeal : Home2WorldTellDealBase() {
    override fun dealHomeTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Home2WorldTell) {
        val tell = msg.joinNoSucTell

        val player = areaCache.playerCache.findPlayerById(msg.playerId)
        if (player == null) {
            return
        }
        player.joinAllianceReqs.remove(tell.allianceId)
    }
}

