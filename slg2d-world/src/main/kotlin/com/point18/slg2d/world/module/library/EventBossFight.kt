package com.point18.slg2d.world.module.library

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.constg.MONSTER_LIB_ATK
import com.point18.slg2d.common.constg.MONSTER_LIB_KILL
import com.point18.slg2d.common.constg.getLibTypeByBossType
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.BossFight
import pb4server.OpenLibraryItemAskReq
import pb4server.World2HomeAskResp

class BossFightEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val msg = OpenLibraryItemAskReq.newBuilder()
        msg.type = getLibTypeByBossType((event as BossFight).bossType)
        msg.protoId = event.bossId
        if (event.remainingHp > 0) {
            msg.kill = MONSTER_LIB_ATK
        } else {
            msg.kill = MONSTER_LIB_KILL
        }
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