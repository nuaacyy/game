package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IEventHandler
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Running
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.findAllWonderReinforce
import com.point18.slg2d.world.event.InOrOffAlliance
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome

class QuitAllianceEventHandler : IEventHandler<AreaCache> {

    override fun handleEvent(cache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
        val allianceId = (event as InOrOffAlliance).allianceId
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

            val rst = findAllWonderReinforce(cache, wonder.wonderProtoId) ?: continue
            val commandGroup = rst.commandGroup
            if (commandGroup.mainPlayerId == playerId) {
                //解散整个增援
                wonder.occupyGroupId = 0
                goHome(cache, commandGroup.nowX, commandGroup.nowY, commandGroup)

                for (group in rst.reinforceGroups) {
                    if (group.runningState == Running) {
                        // 交给WalkModule去做
                    } else {
                        goHome(cache, group.nowX, group.nowY, group)
                    }
                }
                continue
            }

            //自己的部队撤回
            for (group in rst.reinforceGroups) {
                if (group.mainPlayerId != playerId) {
                    continue
                }
                if (group.runningState == Running) {
                    val walk = cache.walkCache.findWalkByGroupId(group.id)
                    if (walk != null) {
                        halfWayGoHome(cache, walk)
                    }
                } else {
                    goHome(cache, group.nowX, group.nowY, group)
                }
            }
        }
    }
}