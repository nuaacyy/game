package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.world.msgnotice.createAllianceInfoChangeNotifier
import pb4server.Public2WorldTell
import java.util.*

class GetNewAlliancePosDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.getNewAlliancePosTell
        val name = tell.allianceName
        val shortName = tell.allianceShortName

        // 职位任命
        val setPlayer = areaCache.playerCache.findPlayerById(playerId)
        if (setPlayer == null) {
            return
        }
        setPlayer.alliancePosMap = toObj(tell.nowPos)

        val nowPos = LinkedList<Int>()
        for ((p, _) in setPlayer.alliancePosMap) {
            nowPos.add(p)
        }

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val chg = createAllianceInfoChangeNotifier(
                setPlayer.allianceId,
                name, shortName, nowPos
            )
            chg.notice(session)
        }
    }
}

