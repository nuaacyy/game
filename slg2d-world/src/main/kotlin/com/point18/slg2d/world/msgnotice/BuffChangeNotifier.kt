package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.BuffChange
import pb4client.BuffVo

// 推送buff变化
class BuffChangeNotifier(
    val msg: BuffChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.BuffChange_3151, this.msg.build())
    }
}

fun createBuffChangeNotifier(
    changeType: Int,
    buffId: Long,
    buffProtoId: Int,
    overTime: Long,
    startTime: Long
): BuffChangeNotifier {
    val buffChangeBuilder = BuffChange.newBuilder()
    buffChangeBuilder.changeType = changeType
    val buffVoBuilder = BuffVo.newBuilder()
    buffVoBuilder.buffId = buffId
    buffVoBuilder.buffProtoId = buffProtoId
    buffVoBuilder.buffOverTime = getTimeSec(overTime)
    buffVoBuilder.buffStartTime = getTimeSec(startTime)
    buffChangeBuilder.setBuff(buffVoBuilder)
    return BuffChangeNotifier(buffChangeBuilder)
}


