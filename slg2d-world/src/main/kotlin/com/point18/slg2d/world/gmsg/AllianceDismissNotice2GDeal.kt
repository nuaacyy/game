package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.common.playerLeaveAlliance
import com.point18.slg2d.world.common.sendAllianceInfoChange
import pb4server.Public2WorldTell
import java.util.*

class AllianceDismissNotice2GDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.allianceDismissNotic2GTell
        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId(tell.allianceId)
        for (allianceMember in allAllianceMember) {
            val player = areaCache.playerCache.findPlayerById(allianceMember.id) ?: continue

            val allianceId = player.allianceId
            val oldPos = LinkedList<Int>()
            for ((p, _) in player.alliancePosMap) {
                oldPos.add(p)
            }

            // 从联盟成员中剔除玩家，并更新玩家信息及联盟信息
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                sendAllianceInfoChange(areaCache, session, 0, LinkedList(), "", "")
            }
            playerLeaveAlliance(areaCache, player, session, allianceId, oldPos)
        }
    }
}

