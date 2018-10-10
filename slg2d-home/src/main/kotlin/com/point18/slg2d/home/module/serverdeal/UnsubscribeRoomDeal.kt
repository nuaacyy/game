package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.roomChannelOf
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createRoomDelMemberNotifier
import pb4server.Home2HomeTell

// 取消订阅聊天室
class UnsubscribeRoomDeal : H2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        val roomId = msg.chatRoomKickOutTell.chatRoomId

        prepare(session) { homePlayerDC ->
            val player = homePlayerDC.player
            val tmp = player.chatRoomList.firstOrNull { roomId == it.chatRoomId }
            if (tmp != null) {
                player.chatRoomList.remove(tmp)
            }
            session.unsubscribeChannel(roomChannelOf(roomId))
            createRoomDelMemberNotifier(roomId).notice(session)
        }
    }
}