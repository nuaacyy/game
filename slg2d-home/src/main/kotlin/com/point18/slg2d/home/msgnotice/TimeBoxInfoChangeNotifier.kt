package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.TimeBoxInfo
import pb4client.TimeBoxInfoChange

// 时光之盒推送
data class TimeBoxInfoChangeNotifier(
    val msg: TimeBoxInfoChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.TimeBoxInfoChange_3131, this.msg.build())
    }

}

fun createTimeBoxInfoChangeNotifier(index: Int, timeBoxInfo: TimeBoxInfo): TimeBoxInfoChangeNotifier {
    val timeBoxInfoChangeBuilder = TimeBoxInfoChange.newBuilder()
    val timeBoxInfoBuilder = pb4client.TimeBoxInfo.newBuilder()
    timeBoxInfoBuilder.index = index
    timeBoxInfoBuilder.timeBoxId = timeBoxInfo.relicBoxId
    timeBoxInfoBuilder.studyTime = getTimeSec(timeBoxInfo.studyTime)
    timeBoxInfoBuilder.overTime = getTimeSec(timeBoxInfo.timeBoxTimeOver)
    timeBoxInfoChangeBuilder.setTimeBoxInfos(timeBoxInfoBuilder)
    return TimeBoxInfoChangeNotifier(timeBoxInfoChangeBuilder)
}

