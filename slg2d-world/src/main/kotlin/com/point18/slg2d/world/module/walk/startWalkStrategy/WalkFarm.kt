package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.constg.CheckHaveSolider
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.common.resultcode.ResultCode

// 野外采集
class WalkFarm : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckHaveSolider
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
        // 检测地上是否有资源点
        val commonResCell = areaCache.commonResCache.findCommonResByXY(wp.gotoX, wp.gotoY)
        if (commonResCell != null) {
            if (commonResCell.groupId != 0L) {
                return ResultCode.HAVE_IN_FARM.code
            }
            return ResultCode.SUCCESS.code
        }
        val deadBossResCell = areaCache.deadBossResCache.findDeadBossResByXy(wp.gotoX, wp.gotoY)
        if (deadBossResCell != null) {
            if (deadBossResCell.groupId != 0L) {
                return ResultCode.HAVE_IN_FARM.code
            }
            return ResultCode.SUCCESS.code
        }

        return ResultCode.NO_FARM_RES.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        return ResultCode.SUCCESS.code
    }
}



