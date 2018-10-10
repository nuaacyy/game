package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.NewPlayerActivityOver

// 新手引导结束
class NewPlayerActivityChangeNotifier(
    val msg: NewPlayerActivityOver.Builder
) {

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.NewPlayerActivityOver_3194, this.msg.build())
    }

}

fun createNewPlayerActivityChangeNotifier(): NewPlayerActivityChangeNotifier {
    val t = NewPlayerActivityOver.newBuilder()
    return NewPlayerActivityChangeNotifier(t)
}

