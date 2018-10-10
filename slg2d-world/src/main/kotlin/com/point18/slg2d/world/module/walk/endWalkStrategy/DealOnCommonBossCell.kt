package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForceGroup

class DealOnCommonBossCell : BaseDealOnBossCell() {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val commonBoss = areaCache.commonBossCache.findCommonBossByXY(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)

        return walkBossCheck(areaCache, group, commonBoss)
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        walkBossDeal(areaCache, group, walkLineInfo)
    }
}