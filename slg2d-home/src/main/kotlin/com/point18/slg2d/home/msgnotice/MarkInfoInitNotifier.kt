package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.MarkInfoInit

data class MarkInfoInitNotifier(
    val msg: MarkInfoInit.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.MarkInfoInit_3353, this.msg.build())
    }
}

fun createMarkInfoInitNotifier(
    maxMark: Int
): MarkInfoInitNotifier {
    val notifierBuilder = MarkInfoInit.newBuilder()
    notifierBuilder.maxMark = maxMark
    return MarkInfoInitNotifier(notifierBuilder)
}
