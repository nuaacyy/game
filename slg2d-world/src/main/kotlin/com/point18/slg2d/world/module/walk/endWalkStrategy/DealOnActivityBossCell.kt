package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.world.area4data.*

class DealOnActivityBossCell : BaseDealOnBossCell() {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val activityBoss =
            areaCache.activityBossCache.findActivityBossByXy(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)

        return walkBossCheck(areaCache, group, activityBoss)
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        walkBossDeal(areaCache, group, walkLineInfo)
    }
}