package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.WONDER_BASE
import com.point18.slg2d.common.constg.WONDER_BE_ATTACKED
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.addWalkHot
import com.point18.slg2d.world.common.isWonderPeace
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

// 占领奇观
class WalkOccupyWounder(checkItem: Int) : BaseWalkStrategy(), IWalkDeal {

    init {
        this.checkItem = checkItem
    }

    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        val rst = pcs.wonderLocationProtoCache.findInWonderType(gotoX, gotoY)
        val wonderProto = rst.wonderLocationProto
        if (rst.int != WONDER_BASE || wonderProto == null) {
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, gotoX, gotoY)
        }
        val centerPos = wonderProto.getCenterPos()
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, centerPos.x, centerPos.y)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        /** 校验是否是奇观 **/
        val rst = pcs.wonderLocationProtoCache.findInWonderType(wp.gotoX, wp.gotoY)
        val wonderProto = rst.wonderLocationProto
        if (rst.int != WONDER_BASE || wonderProto == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        /** 校验奇观状态 **/
        val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
            ?: return ResultCode.PARAMETER_ERROR.code
        if (isWonderPeace(wonder)) {
            return ResultCode.WONDER_IN_PEACE.code
        }

        /** 校验自身是否有联盟 **/
        val player = areaCache.playerCache.findPlayerById(wp.playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", wp.playerId)
            return ResultCode.PARAMETER_ERROR.code
        }
        if (player.allianceId == 0L) {
            return ResultCode.ONLY_ALLIANCE_CAN_OCCUPY_WONDER.code
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
        val rst =
            pcs.wonderLocationProtoCache.findInWonderType(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        val wonderProto = rst.wonderLocationProto
        if (wonderProto != null) {
            val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
            if (wonder != null && wonder.belongToAllianceId != player.allianceId) {
                val members = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
                for (member in members) {
                    // 被攻击的奇观所属联盟成员app通知
                    areaCache.pushAppNotice(
                        member.id,
                        WONDER_BE_ATTACKED,
                        0
                    )
                }
            }
        }

        return ResultCode.SUCCESS.code
    }

}

