package com.point18.slg2d.world.module.moveCity

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.InitPlayerSessionAfterMoveServer

class InitPlayerSessionAfterMoveServerDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        initPlayerSession(cache, channelActor, (msg as InitPlayerSessionAfterMoveServer).playerId)
    }

    private fun initPlayerSession(areaCache: AreaCache, channelActor: ActorRef, playerId: Long) {

        println("新的世界服收到了initSession")

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }

        areaCache.sessionCache.preparePlayerSession(areaCache, channelActor, playerId, player)
    }
}

