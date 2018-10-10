package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.EnterGamePublicRt

// 进游戏时的public服推送过来的数据
class EnterGamePublicRtNotifier(
    val msg: EnterGamePublicRt.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.EnterGamePublicRt_3135, this.msg.build())
    }
}

fun createEnterGamePublicRtNotifier(rt: EnterGamePublicRt.Builder): EnterGamePublicRtNotifier {
    return EnterGamePublicRtNotifier(rt)
}
