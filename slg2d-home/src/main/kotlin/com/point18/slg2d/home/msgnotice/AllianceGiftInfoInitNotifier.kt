package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.AllianceGiftInfoInit

data class AllianceGiftInfoInitNotifier(
    val msg: AllianceGiftInfoInit.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.AllianceGiftInfoInit_3352, this.msg.build())
    }
}

fun createAllianceGiftInfoInitNotifier(
    openSendAllianceGift: Int
): AllianceGiftInfoInitNotifier {
    val notifierBuilder = AllianceGiftInfoInit.newBuilder()
    notifierBuilder.openSendAllianceGift = openSendAllianceGift
    return AllianceGiftInfoInitNotifier(notifierBuilder)
}
