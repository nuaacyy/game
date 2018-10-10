package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.PlayerAllianceResearchChange

// 建筑的某些公共信息变化的推送,关于联盟帮助的
class PlayerAllianceResearchChangeNotifier(
    val msg: PlayerAllianceResearchChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.PlayerAllianceResearchChange_3126, this.msg.build())
    }
}

fun createPlayerAllianceResearchChangeNotifier(
    allianceResearchNum: Int,
    lastAllianceResearchTime: Int
): PlayerAllianceResearchChangeNotifier {
    val playerAllianceResearchChangeBuilder = PlayerAllianceResearchChange.newBuilder()
    playerAllianceResearchChangeBuilder.allianceResearchNum = allianceResearchNum
    playerAllianceResearchChangeBuilder.lastAllianceResearchTime = lastAllianceResearchTime
    return PlayerAllianceResearchChangeNotifier(playerAllianceResearchChangeBuilder)
}


