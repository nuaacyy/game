package com.point18.slg2d.world.module

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSessionByEpNo
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.BaseDeal
import pb4server.ChannelExpiredTell

abstract class ReqDealWithConn : BaseDeal {

    abstract fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long)

    override fun dealReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        if (!cache.inited) {
            // 尚未初始化好，让他下线
            channelActor.tell(ChannelExpiredTell.newBuilder().build(), ActorRef.noSender())
            return
        }

        this.dealConnReq(cache, channelActor, msg, playerId)
    }
}

abstract class ReqDealEntered : BaseDeal {

    abstract fun dealPlayReq(session: PlayerSession, msg: MessageLite)

    override fun dealReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        if (!cache.inited) {
            // 尚未初始化好，让他下线
            channelActor.tell(ChannelExpiredTell.newBuilder().build(), ActorRef.noSender())
            return
        }

        val playerSession = fetchOnlinePlayerSessionByEpNo(cache, channelActor)
        if (playerSession == null) {
            // 尚未进入游戏，直接返回
            channelActor.tell(ChannelExpiredTell.newBuilder().build(), ActorRef.noSender())
            return
        }

        this.dealPlayReq(playerSession, msg)
    }
}