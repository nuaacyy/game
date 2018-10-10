package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.PlayerSession
import pb4client.AllianceFlagChange

// 联盟旗帜变化推送
class AllianceFlagChangeNotifier(
    color: Int, style: Int, effect: Int
) {
    private val msg: AllianceFlagChange.Builder = AllianceFlagChange.newBuilder()

    init {
        msg.color = color
        msg.style = style
        msg.effect = effect
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceFlagChange_3058, this.msg.build())
    }

}

