package com.point18.slg2d.world.module.moveCity

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.world.area4data.AreaCache

class BelongCellOverHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        val overTimeCells = cache.belongCellCache.findAllOverBelongCell()
        for (cell in overTimeCells) {
            cache.belongCellCache.deleteBelongCell(cell)
        }
    }
}