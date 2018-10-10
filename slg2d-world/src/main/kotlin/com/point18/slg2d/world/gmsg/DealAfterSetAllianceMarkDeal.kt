package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.AllianceMarkRedPoint
import com.point18.slg2d.world.msgnotice.createAllianceMarksChangeNotifier
import com.point18.slg2d.world.msgnotice.createShowRedPointNotifier
import pb4client.AllianceMarkInfo
import pb4server.Public2WorldTell
import java.util.*
import java.util.Arrays.asList

class DealAfterSetAllianceMarkDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.dealAfterSetAllianceMarkTell

        val allianceMarkInfoVo = AllianceMarkInfo.newBuilder()
        allianceMarkInfoVo.type = tell.allianceMarkInfoVo.type
        allianceMarkInfoVo.playerId = tell.allianceMarkInfoVo.playerId
        allianceMarkInfoVo.playerName = tell.allianceMarkInfoVo.playerName
        allianceMarkInfoVo.x = tell.allianceMarkInfoVo.x
        allianceMarkInfoVo.y = tell.allianceMarkInfoVo.y
        allianceMarkInfoVo.title = tell.allianceMarkInfoVo.title
        allianceMarkInfoVo.desp = tell.allianceMarkInfoVo.desp
        allianceMarkInfoVo.markTime = tell.allianceMarkInfoVo.markTime
        allianceMarkInfoVo.markId = tell.allianceMarkInfoVo.markId
        allianceMarkInfoVo.pltAreaNo = tell.allianceMarkInfoVo.pltAreaNo
        allianceMarkInfoVo.addAllPositions(tell.allianceMarkInfoVo.positionsList)
        allianceMarkInfoVo.photoProtoId = tell.allianceMarkInfoVo.photoProtoId

        val aPbMarks = asList(allianceMarkInfoVo.build())
        val marksChangeNotifier = createAllianceMarksChangeNotifier(tell.flag, aPbMarks)
        val markRedPoint = createShowRedPointNotifier(AllianceMarkRedPoint, (tell.allianceId), getNowTime())

        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId((tell.allianceId))
        for (allianceMember in allAllianceMember) {
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                marksChangeNotifier.notice(session)
                if (tell.flag == 1) {
                    markRedPoint.notice(session)
                }
            }
        }

    }
}


