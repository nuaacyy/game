package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4server.Home2HomeTell

class ChatRoomNewNameDeal : H2HTell, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        val receiveMsg = msg.chatRoomNewNameTell

        prepare(session) { homePlayerDC ->
            if (homePlayerDC.player.chatRoomList.firstOrNull { it.chatRoomId == receiveMsg.chatRoomId } == null) {
                return@prepare
            }

            createRoomNotifierExtend(
                receiveMsg.chatRoomId,
                receiveMsg.roomName,
                1,
                receiveMsg.iconProtoIdsList,
                receiveMsg.memberNum,
                receiveMsg.roomPlayerId,
                getNowTime() / 1000,
                getNowTime() / 1000
            ).notice(session)
        }
    }

}