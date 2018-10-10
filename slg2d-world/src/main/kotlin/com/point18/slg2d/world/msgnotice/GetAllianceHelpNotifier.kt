package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.GetAllianceHelp

// 获得帮助推送
class GetAllianceHelpNotifier(
    val msg: GetAllianceHelp.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.GetAllianceHelp_3122, this.msg.build())
    }
}

fun createGetAllianceHelpNotifier(name: String, helpType: Int): GetAllianceHelpNotifier {
    val getAllianceHelpBuilder = GetAllianceHelp.newBuilder()
    getAllianceHelpBuilder.helpPlayerName = name
    getAllianceHelpBuilder.helpType = helpType
    return GetAllianceHelpNotifier(getAllianceHelpBuilder)
}


