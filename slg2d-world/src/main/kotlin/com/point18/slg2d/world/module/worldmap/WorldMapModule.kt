package com.point18.slg2d.world.module.worldmap

import akka.actor.ActorRef
import com.point18.slg2d.common.commonfunc.MapConvertMgr
import com.point18.slg2d.common.commonfunc.MapMgr
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.TerminatedBaseDeal
import com.point18.slg2d.world.channelTerminatedDeal

class WorldMapModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {
        //初始化大地图节点
        MapMgr = MapConvertMgr(pcs.basicProtoCache.allArea)

        channelTerminatedDeal.registerTerminatedDeal(object : TerminatedBaseDeal {
            override fun dealTerminated(areaCache: AreaCache, channelActor: ActorRef) {
                areaCache.mapCellWatcherCache.removeFromMapCellWatch(channelActor)
                areaCache.worldActor.context.unwatch(channelActor)
            }
        })
    }
}

var WorldMapM: WorldMapModule = WorldMapModule()


