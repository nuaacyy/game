package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.ResearchChange
import pb4client.ResearchInfo

// 推送正在进行中的副本的部队信息
data class ResearchChangeNotifier(
    val msg: ResearchChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.ResearchChange_3121, this.msg.build())
    }
}

fun createResearchChangeNotifier(
    researchId: Int,
    researchLv: Int,
    researchOverTime: Long,
    helpId: Long
): ResearchChangeNotifier {
    val researchChangeBuilder = ResearchChange.newBuilder()
    val researchInfoBuilder = ResearchInfo.newBuilder()
    researchInfoBuilder.researchId = researchId
    researchInfoBuilder.researchLv = researchLv
    researchInfoBuilder.researchOverTime = getTimeSec(researchOverTime)
    researchInfoBuilder.helperId = helpId
    researchChangeBuilder.setResearch(researchInfoBuilder)
    return ResearchChangeNotifier(researchChangeBuilder)
}


