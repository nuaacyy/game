package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.World2WorldManagerTellDealBase
import com.point18.slg2d.world.WorldProcess
import com.point18.slg2d.world.actor.WorldManagerActor
import com.point18.slg2d.world.wpm
import pb4server.World2WorldManagerTell

class WorldSyncInfo2WorldManagerTellDeal : World2WorldManagerTellDealBase() {
    override fun dealWorldTell(worldManagerActor: WorldManagerActor, msg: World2WorldManagerTell) {
        val tell = msg.worldSyncInfo2WorldManagerTell

        if (tell.worldId == 0L) {
            return
        }

        wpm.getAllWonderInfos().getOrPut(tell.worldId) {
            WorldProcess.EveryWorldInfo(
                tell.worldId,
                WorldProcess.WonderInfo(tell.wonderState)
            )
        }
            .wonderInfo.wonderState = tell.wonderState
    }
}

