package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.AllianceMissionGiftNumChange

// 联盟活跃信息发生变化
data class AllianceMissionGiftNumChangeNotifier(
    val msg: pb4client.AllianceMissionGiftNumChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.AllianceMissionGiftNumChange_3159, this.msg.build())
    }
}

fun createAllianceMissionGiftNumChangeNotifier(changeType: Int, changeValue: Int): AllianceMissionGiftNumChangeNotifier {
    val allianceMissionGiftNumChangeBuilder = AllianceMissionGiftNumChange.newBuilder()

    allianceMissionGiftNumChangeBuilder.changeType = changeType
    allianceMissionGiftNumChangeBuilder.changeValue = changeValue
    return AllianceMissionGiftNumChangeNotifier(allianceMissionGiftNumChangeBuilder)
}


