package com.point18.slg2d.world.common

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.wpm
import pb4server.WorldSyncInfo2WorldManagerTell

fun syncInfo2WorldManager(areaCache: AreaCache) {
    // 区启动完毕 上报给世界管理服
    val worldSyncInfo2WorldManagerTell = WorldSyncInfo2WorldManagerTell.newBuilder()
    worldSyncInfo2WorldManagerTell.worldId = areaCache.worldActor.worldId
    worldSyncInfo2WorldManagerTell.wonderState = areaCache.worldActor.wonder
    wpm.worldManagerProxy.tell(
        wpm.fillW2WMTellMsgHeader {
            it.worldSyncInfo2WorldManagerTell = worldSyncInfo2WorldManagerTell.build()
        },
        ActorRef.noSender()
    )
}