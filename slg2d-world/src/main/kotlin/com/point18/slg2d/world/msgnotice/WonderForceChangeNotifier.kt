package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.MassForce
import pb4client.WonderForceChange

// 奇观部队变化
class WonderForceChangeNotifier(
    val msg: WonderForceChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.WonderForceChange_3169, this.msg.build())
    }
}

fun createWonderForceChangeNotifier(
    changeType: Int,
    forces: List<MassForce.Builder>
): WonderForceChangeNotifier {
    val wonderForceChangeBuilder = WonderForceChange.newBuilder()
    wonderForceChangeBuilder.changeType = changeType
    forces.forEach { wonderForceChangeBuilder.addForces(it) }
    return WonderForceChangeNotifier(wonderForceChangeBuilder)
}


