package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.roomChannelOf
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.MyChat
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4server.Home2HomeTell

// 订阅聊天室
class SubscribeRoomMsgDeal : H2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        val roomId = msg.joinChatRoomTell.chatRoomId

        prepare(session) { homePlayerDC: HomePlayerDC ->
            val player = homePlayerDC.player
            if (player.chatRoomList.firstOrNull { it.chatRoomId == roomId } != null) {
                return@prepare
            }

            val nowTime = getNowTime()
            player.chatRoomList.add(MyChat(roomId, nowTime))
            session.subscribeChannel(roomChannelOf(roomId))
            createRoomNotifierExtend(
                msg.joinChatRoomTell.chatRoomId,
                msg.joinChatRoomTell.roomName,
                msg.joinChatRoomTell.unreadNum,
                msg.joinChatRoomTell.iconProtoIdsList,
                msg.joinChatRoomTell.memberNum,
                msg.joinChatRoomTell.roomPlayerId,
                nowTime / 1000,
                msg.joinChatRoomTell.lastTalkTime / 1000
            ).notice(session)
        }
    }
}