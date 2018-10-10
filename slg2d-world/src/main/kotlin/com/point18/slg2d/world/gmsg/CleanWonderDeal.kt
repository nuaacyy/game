package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.commonfunc.maxTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.BIG_WONDER
import com.point18.slg2d.common.constg.Running
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.findAllWonderReinforce
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import pb4server.Public2WorldTell

class CleanWonderDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.cleanWonderTell

        val wonderProtoIds = tell.wonderProtoIdList

        for (wonderProtoId in wonderProtoIds) {
            if (wonderProtoId == BIG_WONDER) {
                continue
            }

            val wonderProto = pcs.wonderLocationProtoCache.wonderLocationProtoMap[wonderProtoId]
            if (wonderProto === null) {
                normalLog.error("找不到奇观配置：$wonderProtoId")
                return
            }
            val centerPos = wonderProto.getCenterPos()

            val wonder = areaCache.wonderCache.findWonder(wonderProtoId)
            if (wonder != null) {
                wonder.belongToAllianceId = 0
                wonder.occupyGroupId = 0
                wonder.occupyStartTime = maxTime.time
                wonder.occupyOverTime = maxTime.time

                // 解散指挥官驻守
                val rst = findAllWonderReinforce(areaCache, wonder.wonderProtoId)
                if (rst != null) {
                    val commandGroup = rst.commandGroup
                    goHome(areaCache, commandGroup.nowX, commandGroup.nowY, commandGroup)

                    // 解散增援
                    for (group in rst.reinforceGroups) {
                        if (group.runningState == Running) {
                            val walk = areaCache.walkCache.findWalkByGroupId(group.id)
                            if (walk != null) {
                                halfWayGoHome(areaCache, walk)
                            }
                        } else {
                            goHome(areaCache, group.nowX, group.nowY, group)
                        }
                    }
                }

                //通知客户端刷新奇观地块
                noticeCellUpdate(areaCache, centerPos.x, centerPos.y)
            }
        }
    }
}