package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.StrengthChange

// 令牌变化主推
class StrengthChangeNotifier(
    val msg: StrengthChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.StrengthChange_3175, this.msg.build())
    }
}

fun createStrengthChangeNotifier(strength: Int, strengthTime: Int): StrengthChangeNotifier {
    val strengthChange = StrengthChange.newBuilder()
    strengthChange.strength = strength
    strengthChange.strengthTime = strengthTime
    return StrengthChangeNotifier(strengthChange)
}


