package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.ROOM_DISMISS_CONTENT
import com.point18.slg2d.common.constg.ROOM_DISMISS_TITLE
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.constg.roomChannelOf
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.MailHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createRoomDelMemberNotifier
import pb4server.Home2HomeTell
import java.util.*

// 取消订阅聊天室
class RoomDismissDeal : H2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        val roomId = msg.chatRoomDismissTell.chatRoomId

        prepare(session) { homePlayerDC ->
            val player = homePlayerDC.player
            val tmp = player.chatRoomList.firstOrNull { roomId == it.chatRoomId }
            if (tmp != null) {
                player.chatRoomList.remove(tmp)
            }

            // 邮件通知
            var param1 = ""
            if (msg.chatRoomDismissTell.actPlayerAlliance != "") {
                param1 = "[${msg.chatRoomDismissTell.actPlayerAlliance}]" +
                        msg.chatRoomDismissTell.actionPlayerName +
                        msg.chatRoomDismissTell.actionPlayerShort
            } else {
                param1 = msg.chatRoomDismissTell.actionPlayerName
            }
            val mailHelper = MailHelper()
            val messageParams = LinkedList(Arrays.asList(param1, msg.chatRoomDismissTell.roomName))
            val mailInfo =
                MailInfo(TEXT_READ_LAN, ROOM_DISMISS_TITLE, LinkedList(), ROOM_DISMISS_CONTENT, messageParams)
            mailHelper.sendMail(session, player.playerId, mailInfo)

            session.unsubscribeChannel(roomChannelOf(roomId))

            createRoomDelMemberNotifier(roomId).notice(session)
        }
    }

}