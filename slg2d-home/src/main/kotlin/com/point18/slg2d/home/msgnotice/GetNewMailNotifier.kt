package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.GetNewMail

// 获得新邮件推送
data class GetNewMailNotifier(
    val msg: pb4client.GetNewMail.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.GetNewMail_3102, this.msg.build())
    }
}

fun createGetNewMailNotifier(mailType: Int): GetNewMailNotifier {
    val getNewMailBuilder = GetNewMail.newBuilder()
    getNewMailBuilder.mailType = mailType
    return GetNewMailNotifier(getNewMailBuilder)
}


