package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.common.resultcode.ResultCode

// 领主回家
class WalkMainHeroHome : BaseWalkStrategy(), IWalkDeal {
    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, gotoX, gotoY)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        return ResultCode.SUCCESS.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        return ResultCode.SUCCESS.code
    }
}