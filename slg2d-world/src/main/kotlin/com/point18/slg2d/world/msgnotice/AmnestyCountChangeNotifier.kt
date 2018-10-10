package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AmnestyCountChange

// 赦免次数变化推送
class AmnestyCountChangeNotifier(
    val msg: AmnestyCountChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AmnestyCountChange_3170, this.msg.build())
    }
}

fun createAmnestyCountChangeNotifier(count: Int): AmnestyCountChangeNotifier {
    val amnestyCountChangeBuilder = AmnestyCountChange.newBuilder()
    amnestyCountChangeBuilder.amnestyCount = count
    return AmnestyCountChangeNotifier(amnestyCountChangeBuilder)
}


