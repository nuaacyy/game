package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.NoticeLanMsg
import java.util.*

// lan推送
class LanMsgNotifier(
    val msg: NoticeLanMsg.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.NoticeLanMsg_3154, this.msg.build())
    }
}

fun createLanMsgNotifier(lanId: String, paras: List<String>): LanMsgNotifier {
    val noticeLanMsgBuilder = NoticeLanMsg.newBuilder()
    noticeLanMsgBuilder.lanId = lanId
    paras.forEach { noticeLanMsgBuilder.addParams(it) }
    return LanMsgNotifier(noticeLanMsgBuilder)
}


