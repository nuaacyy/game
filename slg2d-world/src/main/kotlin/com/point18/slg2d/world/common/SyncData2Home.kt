package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.constg.SyncData
import pb4server.SyncHomeAskReq
import pb4server.World2HomeAskResp

/**
 * 同步数据至home服
 */
fun syncData2Home(areaCache: AreaCache, playerId: PlayerId, dataType: SyncData, data: String) {
    val req = SyncHomeAskReq.newBuilder()
    req.dateType = dataType
    req.data = data
    areaCache.worldActor.createACS<World2HomeAskResp>()
        .ask(areaCache.worldActor.homeShardRegion, areaCache.fillW2HAskMsgHeader(playerId) {
            it.setSyncHomeAskReq(req)
        }, World2HomeAskResp::class.java)
        .whenCompleteKt { askResp, err ->
            //todo 同步失败处理
        }
}