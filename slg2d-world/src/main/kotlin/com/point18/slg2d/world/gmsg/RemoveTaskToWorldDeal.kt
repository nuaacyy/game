package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldTellDealBase
import pb4server.Home2WorldTell

class RemoveTaskToWorldDeal : Home2WorldTellDealBase() {
    override fun dealHomeTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Home2WorldTell) {
        val tell = msg.removeTaskToWorldTell
        val player = areaCache.playerCache.findPlayerById(msg.playerId)
        if (player == null) {
            return
        }

        val delTask = areaCache.taskCache.findTaskByProtoId(msg.playerId, tell.taskProtoId)
        if (delTask != null) {
            areaCache.taskCache.deleteTask(delTask)
        }
    }
}

