package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForceGroup

class DealOnCallBossCell : BaseDealOnBossCell() {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val callBoss = areaCache.callBossCache.findCallBossByXy(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)

        return walkBossCheck(areaCache, group, callBoss)
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        walkBossDeal(areaCache, group, walkLineInfo)
    }
}