package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.HideRedPoint
import pb4client.RedPoint

// 推送正在进行中的副本的部队信息
class HideRedPointNotifier(
    val msg: HideRedPoint.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.HideRedPoint_3117, this.msg.build())
    }
}

fun createHideRedPointNotifier(redType: Int, redId: Long): HideRedPointNotifier {
    val hideRedPointBuilder = HideRedPoint.newBuilder()
    val redPointBuilder = RedPoint.newBuilder()
    redPointBuilder.redPointType = redType
    redPointBuilder.redPointId = redId
    redPointBuilder.redPointShowTime = getTimeSec(getNowTime())
    hideRedPointBuilder.setRedPoint(redPointBuilder)
    return HideRedPointNotifier(hideRedPointBuilder)
}


