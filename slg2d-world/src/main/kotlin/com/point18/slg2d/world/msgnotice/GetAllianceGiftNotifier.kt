package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceGiftVo
import pb4client.GetNewAllianceGift

// 获得一个联盟礼物
class GetAllianceGiftNotifier(
    val msg: GetNewAllianceGift.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.GetNewAllianceGift_3145, this.msg.build())
    }
}

fun createGetAllianceGiftNotifier(allianceGiftVo: AllianceGiftVo.Builder): GetAllianceGiftNotifier {
    val getNewAllianceGiftBuilder = GetNewAllianceGift.newBuilder()
    getNewAllianceGiftBuilder.setAllianceGiftVo(allianceGiftVo)

    return GetAllianceGiftNotifier(getNewAllianceGiftBuilder)
}


