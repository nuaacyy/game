package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.constg.OCCUPY_WONDER
import com.point18.slg2d.common.constg.Update
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.event.OccupyWonder
import com.point18.slg2d.world.wpm
import pb4server.Public2WorldTell

class OccupyWonderDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.occupyWonderTell
        val occupyWonderInfoList = tell.occupyWonderInfoList
        val changeType = tell.changeType
        for (tPlayerId in tell.playerIdsList) {
            val player = areaCache.playerCache.findPlayerById(tPlayerId) ?: return
            when (changeType) {
                Add -> {
                    for (occupyWonder in occupyWonderInfoList) {
                        val wonderProtoMap =
                            player.allianceOccupyInfo.getOrPut(occupyWonder.worldId) { hashMapOf() }
                        for (wonderProtoId in occupyWonder.wonderIdsList) {
                            wonderProtoMap[wonderProtoId] = 1
                        }
                    }
                }
                Del -> {
                    for (occupyWonder in occupyWonderInfoList) {
                        val wonderProtoMap = player.allianceOccupyInfo[occupyWonder.worldId] ?: return
                        for (wonderProtoId in occupyWonder.wonderIdsList) {
                            wonderProtoMap.remove(wonderProtoId)
                        }
                        if (wonderProtoMap.size == 0) {
                            player.allianceOccupyInfo.remove(occupyWonder.worldId)
                        }
                    }
                }
                Update -> {
                    for (occupyWonder in occupyWonderInfoList) {
                        for (wonderProtoId in occupyWonder.wonderIdsList) {
                            wpm.es.fire(areaCache, tPlayerId, OCCUPY_WONDER, OccupyWonder(wonderProtoId))
                        }
                    }
                }
            }
        }
    }
}