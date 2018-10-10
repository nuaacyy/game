package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createEnterGamePublicRtNotifier
import pb4client.*
import pb4server.Public2WorldTell

class PlayerOnlineNoticeDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.playerOnlineNoticTell

        val rt = EnterGamePublicRt.newBuilder()
        val allianceInfo = AllianceInfo.newBuilder()
        allianceInfo.id = tell.enterGamePublicRt.allianceInfo.id
        allianceInfo.name = tell.enterGamePublicRt.allianceInfo.name
        allianceInfo.shortName = tell.enterGamePublicRt.allianceInfo.shortName
        allianceInfo.color = tell.enterGamePublicRt.allianceInfo.color
        allianceInfo.style = tell.enterGamePublicRt.allianceInfo.style
        allianceInfo.effect = tell.enterGamePublicRt.allianceInfo.effect
        allianceInfo.addAllPositions(tell.enterGamePublicRt.allianceInfo.positionsList)

        rt.setAllianceInfo(allianceInfo)

        // 联盟成员信息
        for (playerM in tell.enterGamePublicRt.membersList) {
            val memberPlayerInfo = MemberPlayerInfo.newBuilder()

            memberPlayerInfo.playerId = playerM.playerId
            memberPlayerInfo.playerName = playerM.playerName
            memberPlayerInfo.isOnline = playerM.isOnline
            memberPlayerInfo.protoId = playerM.protoId
            memberPlayerInfo.addAllPositions(playerM.positionsList)

            rt.addMembers(memberPlayerInfo)
        }

        for (mark in tell.enterGamePublicRt.allianceMarksList) {
            val aPbMark = AllianceMarkInfo.newBuilder()
            aPbMark.type = mark.type
            aPbMark.playerId = mark.playerId
            aPbMark.playerName = mark.playerName
            aPbMark.x = mark.x
            aPbMark.y = mark.y
            aPbMark.title = mark.title
            aPbMark.desp = mark.desp
            aPbMark.markTime = mark.markTime
            aPbMark.pltAreaNo = mark.pltAreaNo
            aPbMark.markId = mark.markId
            aPbMark.addAllPositions(mark.positionsList)
            aPbMark.photoProtoId = mark.photoProtoId
            rt.addAllianceMarks(aPbMark)

        }

        // 联盟小红点
        for (redPoint in tell.enterGamePublicRt.redPointsList) {
            val markRedPoint = RedPoint.newBuilder()
            markRedPoint.redPointType = redPoint.redPointType
            markRedPoint.redPointId = redPoint.redPointId
            markRedPoint.redPointShowTime = redPoint.redPointShowTime
            rt.addRedPoints(markRedPoint)
        }

        for (waijiao in tell.enterGamePublicRt.allianceWaijiaoTimeList) {
            rt.addAllianceWaijiaoTime(waijiao)
        }

        rt.noReadTopic = tell.enterGamePublicRt.noReadTopic

        val allianceLivenessVo = AllianceLivenessVo.newBuilder()
        allianceLivenessVo.allianceLivenessExp = tell.enterGamePublicRt.allianceLivenessVo.allianceLivenessExp
        allianceLivenessVo.allianceLivenessScore = tell.enterGamePublicRt.allianceLivenessVo.allianceLivenessScore
        allianceLivenessVo.allianceLivenessGiftId = tell.enterGamePublicRt.allianceLivenessVo.allianceLivenessGiftId
        allianceLivenessVo.allianceLivenessTodayLv = tell.enterGamePublicRt.allianceLivenessVo.allianceLivenessTodayLv
        allianceLivenessVo.allianceLivenessLv = tell.enterGamePublicRt.allianceLivenessVo.allianceLivenessLv
        rt.allianceLivenessVo = allianceLivenessVo.build()
        rt.rankLv = tell.enterGamePublicRt.rankLv
        rt.reqListNum = tell.enterGamePublicRt.reqListNum

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val msgNotifier = createEnterGamePublicRtNotifier(rt)
            msgNotifier.notice(session)
        }
    }
}
