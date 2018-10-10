package com.point18.slg2d.world.msgnotice

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.BigMapRef

// 玩家的好友添加信息发生变化
class BigMapRefNotifier(
    val msg: BigMapRef.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.BigMapRef_3171, this.msg.build())
    }

    fun notice(areaCache: AreaCache, channelActor: ActorRef) {
        val scMsg = ScMessageAtSend(MsgType.BigMapRef_3171, areaCache.currentClientMsgNo, this.msg.build())
        channelActor.tell(scMsg, ActorRef.noSender())
    }
}

fun createBigMapRefNotifier(): BigMapRefNotifier {
    return BigMapRefNotifier(BigMapRef.newBuilder())
}


