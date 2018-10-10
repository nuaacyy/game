package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.CheckConfig

// 配置检测推送
class CheckConfigNotifier(
    val msg: CheckConfig.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.CheckConfig_3192, this.msg.build())
    }
}

fun createCheckConfigNotifier(info: String): CheckConfigNotifier {
    val noticeInfoBuilder = CheckConfig.newBuilder()
    noticeInfoBuilder.info = info
    return CheckConfigNotifier(noticeInfoBuilder)
}

