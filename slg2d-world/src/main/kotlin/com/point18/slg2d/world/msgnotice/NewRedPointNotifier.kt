package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.NewRedPoint
import pb4client.RedPoint

// 小红点
class ShowRedPointNotifier(
    val msg: NewRedPoint.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.NewRedPoint_3116, this.msg.build())
    }
}

fun createShowRedPointNotifier(redType: Int, redId: Long, time: Long): ShowRedPointNotifier {
    val newRedPointBuilder = NewRedPoint.newBuilder()
    val redPointBuilder = RedPoint.newBuilder()
    redPointBuilder.redPointType = redType
    redPointBuilder.redPointId = redId
    redPointBuilder.redPointShowTime = getTimeSec(time)
    newRedPointBuilder.setRedPoint(redPointBuilder)
    return ShowRedPointNotifier(newRedPointBuilder)
}


