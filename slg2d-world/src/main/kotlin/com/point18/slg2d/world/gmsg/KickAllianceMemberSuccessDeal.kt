package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.ALLIANCE_MAIL
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.constg.MAIL_REMOVE_PLAYER_CONTENT
import com.point18.slg2d.common.constg.MAIL_REMOVE_PLAYER_TITLE
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.playerLeaveAlliance
import com.point18.slg2d.world.common.sendAllianceInfoChange
import com.point18.slg2d.world.common.sendMail
import pb4server.Public2WorldTell
import java.util.*
import java.util.Arrays.asList

class KickAllianceMemberSuccessDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.kickAllianceMemberSuccessTell

        val allianceId = tell.allianceId
        val playerName = tell.playerName
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }

        // 从联盟成员中剔除玩家，并更新玩家信息及联盟信息
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            sendAllianceInfoChange(areaCache, session, 0, LinkedList(), "", "")
        }

        // 触发退出同盟事件
        val oldPos = LinkedList<Int>()
        for ((p, _) in player.alliancePosMap) {
            oldPos.add(p)
        }

        // 邮件提醒玩家已被移除联盟
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            MAIL_REMOVE_PLAYER_TITLE,
            LinkedList(),
            MAIL_REMOVE_PLAYER_CONTENT,
            LinkedList(asList(playerName))
        )
        sendMail(areaCache, player.id, mailInfo, mailType = ALLIANCE_MAIL)

        // 联盟频道广播

        playerLeaveAlliance(areaCache, player, session, allianceId, oldPos)
    }
}
