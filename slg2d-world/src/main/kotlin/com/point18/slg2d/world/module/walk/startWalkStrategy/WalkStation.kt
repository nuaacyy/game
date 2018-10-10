package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.CheckHaveSolider
import com.point18.slg2d.common.constg.CheckSameAlliance
import com.point18.slg2d.common.constg.Stationed
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.addWalkHot
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

// 驻扎
class WalkStation : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckHaveSolider or CheckSameAlliance
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
        //校验同盟
        val castle = areaCache.castleCache.findCastleByXy(wp.gotoX, wp.gotoY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        val player = areaCache.playerCache.findPlayerById(wp.playerId)
        if (player == null || otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        if (player.allianceId == 0L || player.allianceId != otherPlayer.allianceId) {
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }
        //校验是否对方已经有驻防
        val stationGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(wp.gotoX, wp.gotoY, Stationed)
        if (stationGroups.count() > 0) {
            return ResultCode.OVER_LIMIT_STATION.code
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

        return ResultCode.SUCCESS.code
    }
}

