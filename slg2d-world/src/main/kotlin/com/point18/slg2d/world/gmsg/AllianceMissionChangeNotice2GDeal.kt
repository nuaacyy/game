package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceLivenessVoChangeNotifier
import pb4server.Public2WorldTell

class AllianceMissionChangeNotice2GDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.allianceMissionChangeNotic2GTell
        val msgNotifier =
            createAllianceLivenessVoChangeNotifier(tell.lv, tell.exp, tell.score, tell.allianceMissionGiftId, tell.todayLv)

        for (eachPlayerId in tell.playerIdsList) {
            // 修改公会礼物模板
            val player = areaCache.playerCache.findPlayerById(eachPlayerId) ?: continue

            if (player.allianceGiftProtoId != tell.allianceMissionGiftId) {
                player.allianceGiftProtoId = tell.allianceMissionGiftId
            }

            // 推送通知
            val eachSession = fetchOnlinePlayerSession(areaCache, eachPlayerId)
            if (eachSession != null) {
                msgNotifier.notice(eachSession)
            }
        }

    }
}




