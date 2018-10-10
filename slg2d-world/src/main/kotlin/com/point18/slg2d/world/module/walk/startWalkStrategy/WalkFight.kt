package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.BE_ATTACKED
import com.point18.slg2d.common.constg.CheckHaveSolider
import com.point18.slg2d.common.constg.CheckNoCoverBuff
import com.point18.slg2d.common.constg.CheckNotSameAlliance
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.addWalkHot
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

// 与玩家进行战斗
class WalkGoFight : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckHaveSolider or CheckNotSameAlliance or CheckNoCoverBuff
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
        val gotoX = wp.gotoX
        val gotoY = wp.gotoY

        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        // 触发战争狂热
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walkLineInfo.walkForceGroupId)
        if (group == null) {
            return ResultCode.WALK_GROUP_NOT_EXIST.code
        }

        val playerId = group.mainPlayerId
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", playerId)
            return ResultCode.PARAMETER_ERROR.code
        }

        addWalkHot(areaCache, player)

        // 发送app通知
        val gotoX = walkLineInfo.marchAimsX
        val gotoY = walkLineInfo.marchAimsY
        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        areaCache.pushAppNotice(
            castle.playerId,
            BE_ATTACKED,
            player.kingLv,
            player.name
        )

        return ResultCode.SUCCESS.code
    }

}
