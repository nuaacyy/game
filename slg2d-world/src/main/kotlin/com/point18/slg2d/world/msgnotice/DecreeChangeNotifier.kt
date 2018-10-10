package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.DecreeChange

// 令牌变化主推
class DecreeChangeNotifier(
    val msg: DecreeChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.DecreeChange_3015, this.msg.build())
    }
}

fun createDecreeChangeNotifier(decreeTime: Long, decreeLimit: Int, decreeNum: Int): DecreeChangeNotifier {
    val decreeChangeBuilder = DecreeChange.newBuilder()
    decreeChangeBuilder.time = getTimeSec(decreeTime)
    decreeChangeBuilder.decreeLimit = decreeLimit
    decreeChangeBuilder.decreeNum = decreeNum
    return DecreeChangeNotifier(decreeChangeBuilder)
}


