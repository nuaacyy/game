package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceMarkInfo
import pb4client.AllianceMarksChange
import java.util.*

// 联盟标记推送
class AllianceMarksChangeNotifier(
    val msg: AllianceMarksChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceMarksChange_3017, this.msg.build())
    }
}

fun createAllianceMarksChangeNotifier(
    flag: Int,
    marks: List<AllianceMarkInfo>
): AllianceMarksChangeNotifier {
    val allianceMarksChangeBuilder = AllianceMarksChange.newBuilder()
    allianceMarksChangeBuilder.flag = flag
    marks.forEach { allianceMarksChangeBuilder.addMarks(it) }
    return AllianceMarksChangeNotifier(allianceMarksChangeBuilder)
}


