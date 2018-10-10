package com.point18.slg2d.world.area4data

import akka.actor.ActorRef

// 根据玩家id获取通讯端口ID
fun fetchEpNo(areaCache: AreaCache, playerId: Long): ActorRef? {
    val playerSession = fetchOnlinePlayerSession(areaCache, playerId)
    if (playerSession == null) {
        return null
    }

    return playerSession.channelActor
}
