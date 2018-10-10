package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.AllianceInfoChange
import java.util.*

// 联盟信息变化推送
data class AllianceInfoChangeNotifier(
    val msg: pb4client.AllianceInfoChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.AllianceInfoChange_3018, this.msg.build())
    }
}

fun createAllianceInfoChangeNotifier(
    aid: Long,
    name: String,
    shortName: String,
    position: LinkedList<Int>
): AllianceInfoChangeNotifier {
    val allianceInfoChangeBuilder = AllianceInfoChange.newBuilder()

    allianceInfoChangeBuilder.id = aid
    allianceInfoChangeBuilder.name = name
    allianceInfoChangeBuilder.shortName = shortName
    position.forEach { allianceInfoChangeBuilder.addPositions(it) }
    return AllianceInfoChangeNotifier(allianceInfoChangeBuilder)
}


