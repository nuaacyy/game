package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor

// 资源增加推送
data class GetResNotifier(
    val msg: pb4client.GetRes.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.GetRes_3184, this.msg.build())
    }
}

fun createGetResNotifier(action: Int, res:String): GetResNotifier {
    val addResChangeBuilder = pb4client.GetRes.newBuilder()
    addResChangeBuilder.actionType = action
    addResChangeBuilder.resString = res

    return GetResNotifier(addResChangeBuilder)
}
