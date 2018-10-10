package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.StoreLimitChange

// 向客户端推送资源产量上限
data class StoreLimitChangeNotifier(
    val msg: pb4client.StoreLimitChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.StoreLimitChange_3165, this.msg.build())
    }
}

fun createStoreLimitChangeNotifier(
    woodLimit: Long,
    ironLimit: Long,
    stoneLimit: Long,
    foodLimit: Long,
    coinLimit: Long
): StoreLimitChangeNotifier {
    val storeLimitChangeBuilder = StoreLimitChange.newBuilder()
    storeLimitChangeBuilder.woodLimit = woodLimit
    storeLimitChangeBuilder.ironLimit = ironLimit
    storeLimitChangeBuilder.stoneLimit = stoneLimit
    storeLimitChangeBuilder.foodLimit = foodLimit
    storeLimitChangeBuilder.coinLimit = coinLimit
    return StoreLimitChangeNotifier(storeLimitChangeBuilder)
}


