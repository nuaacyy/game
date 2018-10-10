package com.point18.slg2d.world.module.bigMap

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.mapHelper

class RefBigMapHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        refBigMapHeart(cache)
    }

    private fun refBigMapHeart(areaCache: AreaCache) {
        val nowTime = getNowTime()
        if (nowTime >= areaCache.mapCellCache.nextResRefTime) {
            mapHelper.refreshMap(areaCache, areaCache.mapCellCache.nowResRefId)
        }
        if (nowTime >= areaCache.mapCellCache.nextCommonBossRefTime) {
            mapHelper.refreshCommonBoss(areaCache, areaCache.mapCellCache.nowCommonBossRefId)
        }
        if (nowTime >= areaCache.mapCellCache.nextWonderBossRefTime) {
            mapHelper.refreshWonderBoss(areaCache, areaCache.mapCellCache.nowWonderBossRefIndex)
        }
        if (nowTime >= areaCache.mapCellCache.nextSnowBossRefTime) {
            mapHelper.refreshSnowBoss(areaCache, areaCache.mapCellCache.nowSnowBossRefIndex)
        }
    }

}
