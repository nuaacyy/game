package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceMemberChangeNotifier
import pb4client.MemberPlayerInfo
import pb4server.Public2WorldTell
import java.util.*
import java.util.Arrays.asList

class AllianceMemberInfoChangeDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.allianceMemberInfoChangeTell

        // 向其他联盟成员（不包括自己）推送 playerM 的信息变化
        val member = MemberPlayerInfo.newBuilder()

        member.playerId = tell.changePlayerId
        member.playerName = tell.playerName
        member.isOnline = tell.isOnline
        member.protoId = tell.protoId
        member.addAllPositions(tell.positionsList)

        val msgNotifier = createAllianceMemberChangeNotifier(tell.changeType, asList(member.build()))

        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId(tell.allianceId)
        for (allianceMember in allAllianceMember) {
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                msgNotifier.notice(session)
            }
        }

    }
}






