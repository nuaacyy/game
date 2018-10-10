package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceLivenessVo
import pb4client.AllianceLivenessVoChange

// 联盟活跃信息发生变化
class AllianceLivenessVoChangeNotifier(
    val msg: AllianceLivenessVoChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceLivenessVoChange_3158, this.msg.build())
    }
}

fun createAllianceLivenessVoChangeNotifier(
    lv: Int,
    exp: Int,
    score: Int,
    giftId: Int,
    todayLv: Int
): AllianceLivenessVoChangeNotifier {
    val allianceLivenessVoChangeBuilder = AllianceLivenessVoChange.newBuilder()
    val allianceLivenessVoBuilder = AllianceLivenessVo.newBuilder()
    allianceLivenessVoBuilder.allianceLivenessLv = lv
    allianceLivenessVoBuilder.allianceLivenessExp = exp
    allianceLivenessVoBuilder.allianceLivenessScore = score
    allianceLivenessVoBuilder.allianceLivenessGiftId = giftId
    allianceLivenessVoBuilder.allianceLivenessTodayLv = todayLv
    allianceLivenessVoChangeBuilder.setAllianceLivenessVo(allianceLivenessVoBuilder)
    return AllianceLivenessVoChangeNotifier(allianceLivenessVoChangeBuilder)
}


