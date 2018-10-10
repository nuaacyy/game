package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.MarkNumChange

// 玩家收藏变化
data class MarkNumChangeNotifier(
    val msg: pb4client.MarkNumChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.MarkNumChange_3177, this.msg.build())
    }
}

fun createMarkNumChangeNotifier(changeNum: Int): MarkNumChangeNotifier {
    val markNumChangeBuilder = MarkNumChange.newBuilder()
    markNumChangeBuilder.changeInfo = changeNum
    return MarkNumChangeNotifier(markNumChangeBuilder)
}


