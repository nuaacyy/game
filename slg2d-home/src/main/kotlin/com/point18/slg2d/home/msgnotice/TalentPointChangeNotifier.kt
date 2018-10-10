package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.TalentPoint
import pb4client.TalentPointChange

//天赋点变化通知
data class TalentPointChangeNotifier(
    val msg: pb4client.TalentPointChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.TalentPointChange_3132, this.msg.build())
    }
}

fun createTalentPointChangeNotifier(pointInfo: HashMap<Int, Int>): TalentPointChangeNotifier {
    val talentPointChangeBuilder = TalentPointChange.newBuilder()
    for ((pointKey, pointVal) in pointInfo) {
        val talentPointBuilder = TalentPoint.newBuilder()
        talentPointBuilder.talentType = pointKey
        talentPointBuilder.leftTalentPoint = pointVal
        talentPointChangeBuilder.addLeftTalentPoint(talentPointBuilder)
    }
    return TalentPointChangeNotifier(talentPointChangeBuilder)
}


