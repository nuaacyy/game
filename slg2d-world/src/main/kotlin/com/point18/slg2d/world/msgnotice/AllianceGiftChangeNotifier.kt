package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceBigGiftVo
import pb4client.AllianceGiftChange

// 联盟大礼物信息变化
class AllianceGiftChangeNotifier(
    val msg: AllianceGiftChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceGiftChange_3146, this.msg.build())
    }
}

fun createAllianceGiftChangeNotifier(allianceBigGiftVo: AllianceBigGiftVo.Builder): AllianceGiftChangeNotifier {
    val allianceGiftChangeBuilder = AllianceGiftChange.newBuilder()
    allianceGiftChangeBuilder.setAllianceBigGiftVo(allianceBigGiftVo)
    return AllianceGiftChangeNotifier(allianceGiftChangeBuilder)
}


