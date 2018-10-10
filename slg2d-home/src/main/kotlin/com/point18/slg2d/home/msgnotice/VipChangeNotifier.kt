package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.VipChange

//vip变化通知
data class VipChangeNotifier(
    val msg: pb4client.VipChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.VipChange_3133, this.msg.build())
    }
}

fun createVipChangeNotifier(lv: Int, exp: Int, nextExp:Int): VipChangeNotifier {
    val vipChangeBuilder = VipChange.newBuilder()
    vipChangeBuilder.vipLv = lv
    vipChangeBuilder.vipExp = exp
    vipChangeBuilder.nextVipExp = nextExp
    return VipChangeNotifier(vipChangeBuilder)
}


