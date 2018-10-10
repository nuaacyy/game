package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.PlayerPowerChange

//玩家实力通知
class PlayerPowerChangeNotifier(
    val msg: PlayerPowerChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.PlayerPowerChange_3161, this.msg.build())
    }
}

fun createPlayerPowerChangeNotifier(power: Long): PlayerPowerChangeNotifier {
    val playerPowerChangeBuilder = PlayerPowerChange.newBuilder()
    playerPowerChangeBuilder.playerPower = power
    return PlayerPowerChangeNotifier(playerPowerChangeBuilder)
}


