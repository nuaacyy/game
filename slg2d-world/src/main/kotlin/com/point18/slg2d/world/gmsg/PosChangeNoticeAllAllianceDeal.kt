package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.msgnotice.createAllianceMemberChangeNotifier
import pb4client.MemberPlayerInfo
import pb4server.Public2WorldTell
import java.util.*
import java.util.Arrays.asList

class PosChangeNoticeAllAllianceDeal : Public2WorldTellDealBase() {

    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.posChangeNoticAllAllianceTell

        val pos = tell.pos
        val playerName = tell.playerName
        val getPosPlayerName = tell.getPosPlayerName
        val changeType = tell.changeType
        // val positions = msg.positions
        val positions = tell.positionsList
        val setPlayer = tell.setPlayerId
        val isOnline = tell.isOnline

        // 向其他联盟成员（不包括自己）推送 playerM 的信息变化
        val member = MemberPlayerInfo.newBuilder()
        member.playerId = setPlayer
        member.playerName = playerName
        member.isOnline = isOnline
        member.protoId = tell.photoProtoId
        member.addAllPositions(positions)

        val msgNotifier =
            createAllianceMemberChangeNotifier(ALLIANCE_MEMBER_FLAG_POSITION, LinkedList(asList(member.build())))

        // 告诉联盟成员playerM的信息变化
        val allAllianceMember = areaCache.playerCache.findPlayersByAllianceId(tell.allianceId)
        for (allianceMember in allAllianceMember) {
            val session = fetchOnlinePlayerSession(areaCache, allianceMember.id)
            if (session != null) {
                msgNotifier.notice(session)
            }

            // 如果是官位的话要发邮件给大家
            if (changeType == GETNEW_POS) {
                if (changeType == GETNEW_POS) {
                    val mailInfo = MailInfo(
                        TEXT_READ_LAN,
                        MAIL_SET_POSITION_TITLE,
                        LinkedList(),
                        MAIL_SET_POSITION_CONTENT,
                        LinkedList(asList(playerName, getPosPlayerName, pos.toString()))
                    )
                    sendMail(areaCache, allianceMember.id, mailInfo, mailType = ALLIANCE_MAIL)

                } else {
                    val mailInfo = MailInfo(
                        TEXT_READ_LAN,
                        MAIL_SET_POSITION_TITLE,
                        LinkedList(),
                        MAIL_ALLIANCE_BAMIAN_CONTENT,
                        LinkedList(asList(playerName, getPosPlayerName, pos.toString()))
                    )
                    sendMail(areaCache, allianceMember.id, mailInfo, mailType = ALLIANCE_MAIL)
                }
            }

        }
    }
}
