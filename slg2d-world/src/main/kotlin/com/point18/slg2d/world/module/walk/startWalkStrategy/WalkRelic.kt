package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.constg.CanFightRelicLv
import com.point18.slg2d.common.constg.CheckHaveSolider
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.getResearchEffectValue

// 遗迹？
class WalkRelic : BaseWalkStrategy(), IWalkDeal {
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
        val posX = wp.gotoX
        val posY = wp.gotoY

        // 验证地块是否有遗迹
        val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
        if (relicCell == null) {
            return ResultCode.NO_FIND_RELIC_INFO.code
        }

        val relicProto = pcs.relicProtoCache.relicProtoMap[relicCell.relicId]
        if (relicProto == null) {
            return ResultCode.NO_PROTO.code
        }

        val player = areaCache.playerCache.findPlayerById(wp.playerId)
        if (player == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        //验证遗迹等级是否可以攻打
        val canFightLv = getResearchEffectValue(areaCache, NO_FILTER, player, CanFightRelicLv)
        if (canFightLv < relicProto.level) {
            return ResultCode.RELIC_LV_LIMIT_ERROR.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        return ResultCode.SUCCESS.code
    }
}



