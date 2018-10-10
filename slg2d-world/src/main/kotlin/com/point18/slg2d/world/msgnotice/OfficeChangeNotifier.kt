package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.CountryPositionChange

// 官职变化推送
class OfficeChangeNotifier(
    val msg: CountryPositionChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.CountryPositionChange_3166, this.msg.build())
    }
}

fun createOfficeChangeNotifier(playerId: Long, currentPos: Int): OfficeChangeNotifier {
    val countryPositionChangeBuilder = CountryPositionChange.newBuilder()
    countryPositionChangeBuilder.playerId = playerId
    countryPositionChangeBuilder.currentPos = currentPos
    return OfficeChangeNotifier(countryPositionChangeBuilder)
}


