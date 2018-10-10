package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceMissionGiftNumChangeNotifier
import pb4server.Public2WorldTell
import java.util.*

class AllianceMissionGiftAddNotice2GDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.allianceMissionGiftAddNotic2GTell

        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId(tell.allianceId)
        for (allianceMember in allAllianceMember) {
            var changeNum = 0
            val player = areaCache.playerCache.findPlayerById(allianceMember.id) ?: continue
            // 要过滤掉玩家拿过的档
            val getScoreId = LinkedList<Int>()
            for (addVo in tell.allianceMissionGiftAddVosList) {
                val ex = player.allianceGiftGetMap[addVo.scoreId]
                if (ex != null) {
                    continue
                }
                changeNum += (addVo.addNum)
                getScoreId.add(addVo.scoreId)
            }

            for (sid in getScoreId) {
                player.allianceGiftGetMap[sid] = 1
            }

            player.putAllianceGiftGetMap(player.allianceGiftGetMap)

            if (changeNum != 0) {
                player.allianceGiftNum = player.allianceGiftNum + changeNum
                val msgNotifier = createAllianceMissionGiftNumChangeNotifier(ADD_ALLIANCE_INFO, changeNum)

                val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
                if (session != null) {
                    msgNotifier.notice(session)
                }
            }
        }
    }
}




