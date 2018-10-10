package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.AttackNotice

class AttackNotifier(val msg: AttackNotice.Builder) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.AttackNotice_3141, this.msg.build())
    }
}

fun createAttackNotifier(reportId: Long, monsterId: Int): AttackNotifier {
    val attackNoticeBuilder = AttackNotice.newBuilder()
    attackNoticeBuilder.reportId = reportId
    attackNoticeBuilder.monsterId = monsterId
    return AttackNotifier(attackNoticeBuilder)
}

