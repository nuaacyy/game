package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.WonderOccupied

// 奇观被占领
class WonderOccupiedNotifier(
    val msg: WonderOccupied.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.WonderOccupied_3191, this.msg.build())
    }
}

fun createWonderOccupiedNotifier(
    allianceId: Long,
    allianceShortName: String,
    allianceName: String,
    playerId: Long,
    playerName: String,
    wonderProtoId: Int
): WonderOccupiedNotifier {
    val wonderOccupiedBuilder = WonderOccupied.newBuilder()

    wonderOccupiedBuilder.allianceId = allianceId
    wonderOccupiedBuilder.allianceShortName = allianceShortName
    wonderOccupiedBuilder.allianceName = allianceName
    wonderOccupiedBuilder.playerId = playerId
    wonderOccupiedBuilder.playerName = playerName
    wonderOccupiedBuilder.wonderProtoId = wonderProtoId

    return WonderOccupiedNotifier(wonderOccupiedBuilder)
}
