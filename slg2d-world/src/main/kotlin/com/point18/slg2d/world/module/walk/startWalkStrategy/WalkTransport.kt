package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.event.Transport
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.CheckNoHero
import com.point18.slg2d.common.constg.CheckNoSolider
import com.point18.slg2d.common.constg.CheckSameAlliance
import com.point18.slg2d.common.constg.TRANSPORT_START
import com.point18.slg2d.common.resultcode.ResultCode

// 运输资源
class WalkTransport : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckNoHero or CheckNoSolider or CheckSameAlliance
    }

    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        if (targetId == 0L) {
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }
        val targetPlayer = areaCache.playerCache.findPlayerById(targetId)
        if (targetPlayer == null) {
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }
        val castle = areaCache.castleCache.findCastleById(targetPlayer.focusCastleId)
        if (castle == null) {
            normalLog.error("找不到玩家对应的城池信息:%d", targetPlayer.focusCastleId)
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, castle.x, castle.y)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        return walkTransportCheck(areaCache, wp.playerId, wp.gotoX, wp.gotoY)
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        val castle = areaCache.castleCache.findCastleByXy(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        if (castle == null) {
            normalLog.error("(%d,%d)找不到城池信息", walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
            return ResultCode.PARAMETER_ERROR.code
        }

        //发送运输事件
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walkLineInfo.walkForceGroupId)
        for (force in forces) {
            wpm.es.fire(
                areaCache, castle.playerId, TRANSPORT_START, Transport(
                    castle.playerId,
                    force.resVo
                )
            )
        }
        return ResultCode.SUCCESS.code
    }

    private fun walkTransportCheck(areaCache: AreaCache, playerId: Long, gotoX: Int, gotoY: Int): Int {
        //验证地块是否有同盟玩家
        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null || otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        if (player.allianceId == 0L || otherPlayer.allianceId == 0L || player.allianceId != otherPlayer.allianceId) {
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }
        return ResultCode.SUCCESS.code
    }

}
