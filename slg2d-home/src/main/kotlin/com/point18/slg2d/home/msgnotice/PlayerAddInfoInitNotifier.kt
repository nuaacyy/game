package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.PlayerAddInfoInit

data class PlayerAddInfoInitNotifier(
    val msg: PlayerAddInfoInit.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.PlayerAddInfoInit_3351, this.msg.build())
    }
}

fun createPlayerAddInfoInitNotifier(
    nickName: String
): PlayerAddInfoInitNotifier {
    val notifierBuilder = PlayerAddInfoInit.newBuilder()
    notifierBuilder.nickName = nickName
    return PlayerAddInfoInitNotifier(notifierBuilder)
}
