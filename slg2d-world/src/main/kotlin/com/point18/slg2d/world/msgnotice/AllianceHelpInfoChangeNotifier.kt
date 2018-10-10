package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceHelpInfoChange
import java.util.*

// 联盟申请信息变化
class AllianceHelpInfoChangeNotifier(
    val msg: AllianceHelpInfoChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceHelpInfoChange_3153, this.msg.build())
    }
}

fun createAllianceHelpInfoChangeNotifier(changeType: Int, helpIds: LinkedList<Long>): AllianceHelpInfoChangeNotifier {
    val allianceHelpInfoChangeBuilder = AllianceHelpInfoChange.newBuilder()
    allianceHelpInfoChangeBuilder.changeInfo = changeType
    helpIds.forEach { allianceHelpInfoChangeBuilder.addHelpId(it) }

    return AllianceHelpInfoChangeNotifier(allianceHelpInfoChangeBuilder)
}


