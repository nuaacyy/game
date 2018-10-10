package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.event.InOrOffAlliance
import com.point18.slg2d.world.msgnotice.*
import com.point18.slg2d.world.wpm
import pb4client.AllianceMarkInfo
import pb4client.MemberPlayerInfo
import pb4server.HaveAllianceTell
import pb4server.Public2WorldTell
import java.util.*

// 同意申请加入联盟
class JoinInAllianceSuccessDeal : Public2WorldTellDealBase() {

    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.joinInAllianceSuccessTell

        val allianceId = (tell.allianceId)
        val allianceName = tell.allianceName
        val allianceShortName = tell.allianceShortName
        val flagColor = (tell.flagColor)
        val flagStyle = (tell.flagStyle)
        val flagEffect = (tell.flagEffect)
        val reqPlayer = areaCache.playerCache.findPlayerById(playerId)
        if (reqPlayer == null) {
            return
        }

        reqPlayer.allianceAt = getNowTime()
        reqPlayer.allianceId = allianceId
        reqPlayer.joinAllianceReqs.clear()
        areaCache.playerCache.addPlayer2Alliance(reqPlayer)
        reqPlayer.allianceName = allianceName
        reqPlayer.allianceShortName = allianceShortName
        reqPlayer.flagColor = flagColor
        reqPlayer.flagStyle = flagStyle
        reqPlayer.flagEffect = flagEffect

        reqPlayer.resetWrapPosition()

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        val reqEpNo = fetchEpNo(areaCache, playerId)
        updatePlayerAllianceInfo(areaCache, allianceName, allianceShortName, session, reqPlayer)

        // 推送联盟旗帜
        if (session != null) {
            val flagNotifier = AllianceFlagChangeNotifier(flagColor, flagStyle, flagEffect)
            flagNotifier.notice(session)
        }

        // 触发加入同盟事件
        val aPos = LinkedList<Int>()
        aPos.add(ALLIANCE_POSITION_MEMBER)
        wpm.es.fire(
            areaCache, playerId, ALLOW_ALLIANCE,
            InOrOffAlliance(reqEpNo, allianceId, aPos)
        )

        // 推送关系变化，客户端刷新玩家视野地块信息
        refreshRelation(
            areaCache,
            RELATION_CHANGE_JOIN_ALLIANCE,
            reqPlayer,
            allianceId,
            allianceName,
            allianceShortName
        )

        // 发送首次进帮的奖励
        var isFirst = false
        if (reqPlayer.isFirstJoinAlliance == 0) {
            isFirst = true
            addResToHome(
                areaCache,
                ACTION_ALLIANCE_FIRST_JOIN,
                reqPlayer.id,
                pcs.basicProtoCache.firstJoinCripsAward
            )
            reqPlayer.isFirstJoinAlliance = 1
        }
        reqPlayer.allianceResearchNum = 0
        reqPlayer.lastAllianceResearchTime = getNowTime()
        val n = createPlayerAllianceResearchChangeNotifier(
            reqPlayer.allianceResearchNum,
            reqPlayer.lastAllianceResearchTime.toInt() / 1000
        )
        if (session != null) {
            n.notice(session)
        }

        // 向playerM推送成员列表
        val members = LinkedList<MemberPlayerInfo>()

        // for (playerA in msg.members) {
        for (playerA in tell.membersList) {
            // 联盟成员信息
            val member = MemberPlayerInfo.newBuilder()
            member.playerId = playerA.playerId
            member.playerName = playerA.playerName
            member.isOnline = playerA.isOnline
            member.protoId = playerA.protoId
            member.addAllPositions(playerA.positionsList)
            members.add(member.build())
        }

        val aPbMarks = LinkedList<AllianceMarkInfo>()
        // for (aMark in msg.marks) {
        for (aMark in tell.marksList) {
            val aPbMark = AllianceMarkInfo.newBuilder()
            aPbMark.playerId = aMark.playerId
            aPbMark.playerName = aMark.playerName
            aPbMark.type = aMark.type
            aPbMark.x = aMark.x
            aPbMark.y = aMark.y
            aPbMark.title = aMark.title
            aPbMark.desp = aMark.desp
            aPbMark.markTime = aMark.markTime
            aPbMark.markId = aMark.markId
            aPbMark.pltAreaNo = aMark.pltAreaNo
            aPbMark.addAllPositions(aMark.positionsList)
            aPbMark.photoProtoId = aMark.photoProtoId

            aPbMarks.add(aPbMark.build())
        }

        if (session != null) {
            val msgNotifier = createAllianceMemberChangeNotifier(ALLIANCE_MEMBER_FLAG_JOIN, members)
            msgNotifier.notice(session)

            val marksChangeNotifier = createAllianceMarksChangeNotifier(1, aPbMarks)
            marksChangeNotifier.notice(session)
        }

        val reqTarget = areaCache.targetCache.findMyTargetByPlayerId(reqPlayer.id)
        if (reqTarget == null) {
            return
        }

        val updateInfoMap = mutableMapOf<Int, String>()
        updateInfoMap[UpdatePower] = reqTarget.getTotalPower().toString()
        updateInfoMap[UpdateCanHelpNum] =
                getResearchEffectValue(areaCache, NO_FILTER, reqPlayer, CanHelpNum).toString()
        updateInfoMap[UpdateVipLv] =
                (areaCache.infoByHomeCache.findInfoByHomeByPlayerId(reqPlayer.id)?.vipLv ?: 0).toString()

        updateAllianceMemberInfo(areaCache, reqPlayer.allianceId, reqPlayer.id, updateInfoMap)

        // 玩家联盟发生变化,推送地图变化
        val castle = areaCache.castleCache.findCastleById(reqPlayer.focusCastleId)
        if (castle == null) {
            return
        }

        noticeCellUpdate(areaCache, castle.x, castle.y)

        val homeTell = HaveAllianceTell.newBuilder()
        homeTell.allianceId = allianceId
        homeTell.allianceName = allianceName
        homeTell.allianceShortName = allianceShortName
        homeTell.flagColor = flagColor
        homeTell.flagStyle = flagStyle
        homeTell.flagEffect = flagEffect
        homeTell.isFirst = if (isFirst) TRUE else FALSE

        areaCache.tellHome(areaCache.fillW2HTellMsgHeader(reqPlayer.id) {
            it.setHaveAllianceTell(homeTell)
        })
    }
}

fun updatePlayerAllianceInfo(
    areaCache: AreaCache,
    allianceName: String,
    allianceShortName: String,
    session: PlayerSession?,
    player: Player
) {
    if (player.allianceId == 0L) {
        return
    }

    // 玩家
    areaCache.playerCache.addPlayer2Alliance(player)

    // 联盟信息推送
    val positions = LinkedList<Int>()
    for ((pos, _) in player.alliancePosMap) {
        positions.add(pos)
    }
    var name = ""
    var shortName = ""

    var position = LinkedList<Int>()
    if (player.allianceId != 0L) {
        name = allianceName
        shortName = allianceShortName
        position = positions
    }

    if (session != null) {
        val chg = createAllianceInfoChangeNotifier(player.allianceId, name, shortName, position)
        chg.notice(session)
    }
}
