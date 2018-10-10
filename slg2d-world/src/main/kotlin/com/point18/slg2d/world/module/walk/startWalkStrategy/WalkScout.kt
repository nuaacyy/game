package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.addWalkHot
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

// 侦查
class WalkScout : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckNoHero or CheckNoSolider or CheckNotSameAlliance
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
        val playerId = wp.playerId
        val gotoX = wp.gotoX
        val gotoY = wp.gotoY

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle != null) {
            val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
            if (otherPlayer == null) {
                return ResultCode.PARAMETER_ERROR.code
            }

            // 查看对方是否开罩子
            val (isHaveCover, _) = areaCache.buffCache.isHaveCoverTypeBuff(otherPlayer.id)
            if (isHaveCover) {
                return ResultCode.HAVE_DEF_COVER_BUFF_WHEN_SCOUT.code
            }

            //判断联盟关系
            if (player.allianceId == 0L) {
                return ResultCode.SUCCESS.code
            }
            if (player.allianceId == otherPlayer.allianceId) {
                return ResultCode.IN_SAME_ALLIANCE.code
            }

            return ResultCode.SUCCESS.code
        }

        val relicCell = areaCache.relicCache.findRelicByXY(gotoX, gotoY)
        if (relicCell != null) {
            return ResultCode.SUCCESS.code
        }

        val rst = pcs.wonderLocationProtoCache.findInWonderType(gotoX, gotoY)
        if (rst.int == WONDER_BASE) {
            return ResultCode.SUCCESS.code
        }

        return ResultCode.NONE_CAN_SCOUT.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
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

        //触发战争狂热
        addWalkHot(areaCache, player)

        // 发送app通知
        val castle = areaCache.castleCache.findCastleByXy(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        if (castle != null) {
            areaCache.pushAppNotice(
                castle.playerId,
                BE_SCOUTED,
                player.kingLv,
                player.name
            )
        }

        val rst =
            pcs.wonderLocationProtoCache.findInWonderType(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        val wonderProto = rst.wonderLocationProto
        if (wonderProto != null) {
            val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
            if (wonder != null) {
                val members = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
                for (member in members) {
                    // 被攻击的奇观所属联盟成员app通知
                    areaCache.pushAppNotice(
                        member.id,
                        WONDER_BE_SCOUTED,
                        0
                    )
                }
            }
        }

        return ResultCode.SUCCESS.code
    }
}



