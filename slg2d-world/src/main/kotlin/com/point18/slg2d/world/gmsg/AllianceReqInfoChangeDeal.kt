package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceReqInfoChangeNotifier
import pb4client.AllianceQueryReqListInfo
import pb4server.Public2WorldTell

class AllianceReqInfoChangeDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.allianceReqInfoChangeNotic2GTell

        val cReq = AllianceQueryReqListInfo.newBuilder()
        cReq.id = tell.allianceQueryReqListInfo.id
        cReq.name = tell.allianceQueryReqListInfo.name
        cReq.photoProtoId = tell.allianceQueryReqListInfo.photoProtoId
        cReq.fightValue = tell.allianceQueryReqListInfo.fightValue

        val msgNotifier = createAllianceReqInfoChangeNotifier(tell.changeInfo, cReq)

        for (eachPlayerId in tell.playersList) {
            val session = fetchOnlinePlayerSession(areaCache, eachPlayerId)
            if (session != null) {
                msgNotifier.notice(session)
            }
        }
    }
}



