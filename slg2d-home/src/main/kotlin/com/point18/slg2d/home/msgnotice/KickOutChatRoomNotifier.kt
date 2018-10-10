package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.RoomDel

// 聊天室删除成员
data class RoomDelMemberNotifier(
    val msg: pb4client.RoomDel.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.RoomDel_3073, this.msg.build())
    }
}

fun createRoomDelMemberNotifier(roomId: Long): RoomDelMemberNotifier {
    val roomDelMemberBuilder = RoomDel.newBuilder()
    roomDelMemberBuilder.roomId = roomId
    return RoomDelMemberNotifier(roomDelMemberBuilder)
}
