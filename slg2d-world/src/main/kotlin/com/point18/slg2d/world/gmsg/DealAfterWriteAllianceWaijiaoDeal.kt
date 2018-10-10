package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.msgnotice.createShowRedPointNotifier
import pb4server.Public2WorldTell

class DealAfterWriteAllianceWaijiaoDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.dealAfterWriteAllianceWaijiaoTell

        val notice = createShowRedPointNotifier(tell.redPointType, tell.allianceId, getNowTime())

        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId((tell.allianceId))
        for (allianceMember in allAllianceMember) {
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                notice.notice(session)
            }
        }
    }
}
