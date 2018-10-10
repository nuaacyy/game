package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4server.Home2HomeTell

// 聊天室信息变化
class ChatRoomInfoChangeDeal : H2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        val roomId = msg.chatRoomChangeInfoTell.chatRoomId

        prepare(session) { homePlayerDC ->
            val player = homePlayerDC.player

            // 没有这个聊天室就不推送
            val chatRoom = player.chatRoomList.firstOrNull { it.chatRoomId == roomId } ?: return@prepare

            createRoomNotifierExtend(
                msg.chatRoomChangeInfoTell.chatRoomId,
                msg.chatRoomChangeInfoTell.roomName,
                msg.chatRoomChangeInfoTell.unreadNum,
                msg.chatRoomChangeInfoTell.iconProtoIdsList,
                msg.chatRoomChangeInfoTell.memberNum,
                msg.chatRoomChangeInfoTell.roomPlayerId,
                chatRoom.lastReadTime / 1000,
                msg.chatRoomChangeInfoTell.lastSendTime / 1000
            ).notice(session)
        }
    }
}