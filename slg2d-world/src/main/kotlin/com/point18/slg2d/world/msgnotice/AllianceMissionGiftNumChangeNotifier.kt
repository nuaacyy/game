package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceMissionGiftNumChange

// 联盟活跃信息发生变化
class AllianceMissionGiftNumChangeNotifier(
    val msg: AllianceMissionGiftNumChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceMissionGiftNumChange_3159, this.msg.build())
    }
}

fun createAllianceMissionGiftNumChangeNotifier(changeType: Int, changeValue: Int): AllianceMissionGiftNumChangeNotifier {
    val allianceMissionGiftNumChangeBuilder = AllianceMissionGiftNumChange.newBuilder()

    allianceMissionGiftNumChangeBuilder.changeType = changeType
    allianceMissionGiftNumChangeBuilder.changeValue = changeValue
    return AllianceMissionGiftNumChangeNotifier(allianceMissionGiftNumChangeBuilder)
}

