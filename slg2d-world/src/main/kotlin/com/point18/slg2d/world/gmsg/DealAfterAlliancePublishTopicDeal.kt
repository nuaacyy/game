package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceTopicReplyChgNotifier
import pb4server.Public2WorldTell

class DealAfterAlliancePublishTopicDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.dealAfterAlliancePublishTopicTell
        // 对方背包武将卡信息
        val msgNotifier = createAllianceTopicReplyChgNotifier(tell.aTopicId)

        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId(tell.allianceId)
        for (allianceMember in allAllianceMember) {
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                msgNotifier.notice(session)
            }
        }

	}
}




