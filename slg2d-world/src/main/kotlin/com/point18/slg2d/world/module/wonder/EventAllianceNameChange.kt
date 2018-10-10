package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.event.AllianceNameChange

class AllianceNameChangeEventHandler : IEventHandler<AreaCache> {
    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val allianceId = (event as AllianceNameChange).allianceId
        val allWonders = cache.wonderCache.findAllWonders()
        for (wonder in allWonders) {
            if (wonder.belongToAllianceId != allianceId) {
                continue
            }

            val wonderProto = pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonder.wonderProtoId]
            if (wonderProto === null) {
                normalLog.error("找不到奇观配置：${wonder.wonderProtoId}")
                return
            }
            val centerPos = wonderProto.getCenterPos()

            //通知客户端刷新奇观地块
            noticeCellUpdate(cache, centerPos.x, centerPos.y)
        }
    }

}