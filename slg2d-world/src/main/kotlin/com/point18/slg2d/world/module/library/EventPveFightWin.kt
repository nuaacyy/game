package com.point18.slg2d.world.module.library

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.OPEN_MONSTER_LIB_ITEM
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.InstanceWin
import pb4server.OpenLibraryItemAskReq
import pb4server.World2HomeAskResp

class PveFightWinEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val msg = OpenLibraryItemAskReq.newBuilder()
        msg.type = OPEN_MONSTER_LIB_ITEM
        msg.protoId = (event as InstanceWin).floorId
        cache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                cache.worldActor.homeShardRegion,
                cache.fillW2HAskMsgHeader(playerId) {
                    it.setOpenLibraryItemAskReq(msg)
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { hrt, err ->
                //todo 同步失败处理
            }
    }
}