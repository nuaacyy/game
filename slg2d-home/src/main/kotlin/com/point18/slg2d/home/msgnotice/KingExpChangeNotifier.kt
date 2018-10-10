package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.KingExpChange

//君主经验变化通知
data class KingExpChangeNotifier(
    val msg: pb4client.KingExpChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.KingExpChange_3134, this.msg.build())
    }
}

fun createKingExpChangeNotifier(lv: Int, exp: Int): KingExpChangeNotifier {
    val kingExpChangeBuilder = KingExpChange.newBuilder()
    kingExpChangeBuilder.kingLv = lv
    kingExpChangeBuilder.kingExp = exp
    return KingExpChangeNotifier(kingExpChangeBuilder)
}

