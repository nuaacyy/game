package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.constg.CheckHaveSolider
import com.point18.slg2d.common.constg.CheckNotSameAlliance
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

// 占领空地
class WalkOccupyCell : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckHaveSolider or CheckNotSameAlliance
    }

    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, gotoX, gotoY)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        return walkOccupyCellCheck(areaCache, wp.gotoX, wp.gotoY)
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        return ResultCode.SUCCESS.code
    }

    private fun walkOccupyCellCheck(areaCache: AreaCache, gotoX: Int, gotoY: Int): Int {
        // 验证地块是否是空闲状态，并且上面无任何事件（不包括驻守部队）
        val bossCell = areaCache.commonBossCache.findCommonBossByXY(gotoX, gotoY)
        if (bossCell != null) {
            return ResultCode.CELL_NOT_FREE.code
        }

        val farmCell = areaCache.commonResCache.findCommonResByXY(gotoX, gotoY)
        if (farmCell != null) {
            return ResultCode.CELL_NOT_FREE.code
        }

        if (areaCache.relicCache.findRelicByXY(gotoX, gotoY) != null) {
            // 遗迹
            return ResultCode.CELL_NOT_FREE.code
        }

        val rst = pcs.wonderLocationProtoCache.findInWonderType(gotoX, gotoY)
        if (rst.int != 0) {
            return ResultCode.CELL_NOT_FREE.code
        }

        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle != null) {
            // 事件点是玩家城池
            return ResultCode.CELL_NOT_FREE.code
        }
        return ResultCode.SUCCESS.code
    }
}





