package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.addWalkHot
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.noticeReinforceMassGroup
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

// 增援
class WalkReinforce : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckNoHero or CheckHaveSolider or CheckSameAlliance
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
            normalLog.error("找不到玩家数据:$targetId")
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }

        val castle = areaCache.castleCache.findCastleById(targetPlayer.focusCastleId)
        if (castle == null) {
            normalLog.error("找不到玩家对应的城池数据:${targetPlayer.focusCastleId}")
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, castle.x, castle.y)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        val checkResult = this.walkReinforceCheck(areaCache, wp.playerId, wp.gotoX, wp.gotoY)
        if (checkResult != ResultCode.SUCCESS.code) {
            return checkResult
        }
        //只能向一个玩家增援
        val reinforceGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(wp.gotoX, wp.gotoY, Reinforce)
        for (reinforceGroup in reinforceGroups) {
            if (reinforceGroup.mainPlayerId == wp.playerId) {
                return ResultCode.ReinforceRepeat.code
            }
        }
        val walks = areaCache.walkCache.findWalksByGotoXy(wp.gotoX, wp.gotoY)
        for (walk in walks) {
            if (walk.marchState != WalkReinforce) {
                continue
            }
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walk.walkForceGroupId)
            if (group == null) {
                normalLog.error("行军线对应的行军组不存在,行军线Id:${walk.id}")
                return ResultCode.PARAMETER_ERROR.code
            }

            if (group.mainPlayerId == wp.playerId) {
                return ResultCode.ReinforceRepeat.code
            }
        }
        // 校验增援部队总量
        var nowReinforceNum = 0
        val groups =
            areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(wp.gotoX, wp.gotoY, Running or Reinforce)
        for (group in groups) {
            if (group.runningState == Reinforce) {
                // 增援部队已到
                nowReinforceNum += areaCache.walkForceGroupCache.getSoliderNumInGroup(group.id)
                continue
            }
            val walk = areaCache.walkCache.findWalkByGroupId(group.id)
            if (walk == null) {
                continue
            }
            if (walk.marchState != WalkReinforce) {
                continue
            }
            nowReinforceNum += areaCache.walkForceGroupCache.getSoliderNumInGroup(group.id)
        }

        var addSoliderNum = 0
        for ((_, num) in wp.we.SoliderMap) {
            addSoliderNum += num
        }

        val castle = areaCache.castleCache.findCastleByXy(wp.gotoX, wp.gotoY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }
        val targetPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        if (targetPlayer == null) {
            normalLog.error("找不到玩家信息:${castle.playerId}")
            return ResultCode.PARAMETER_ERROR.code
        }

        val canReinforceNum =
            getResearchEffectValue(areaCache, NO_FILTER, targetPlayer, ReinforceTroopsMaxAdd)

        if (addSoliderNum + nowReinforceNum > canReinforceNum) {
            if (rs != null) {
                rs.limit = canReinforceNum - nowReinforceNum
            }
            return ResultCode.ReinforceSoliderUpLimit.code
        }

        return checkResult
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        val allMass = areaCache.massCache.findAllMassByPos(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        for (mass in allMass) {
            if (mass.fightType == WalkFightPlayer) {
                noticeReinforceMassGroup(areaCache, Update, mass)
            }
        }

        // 触发战争狂热
        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walkLineInfo.walkForceGroupId)
        if (group == null) {
            return ResultCode.WALK_GROUP_NOT_EXIST.code
        }

        val playerId = group.mainPlayerId
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:$playerId")
            return ResultCode.PARAMETER_ERROR.code
        }

        addWalkHot(areaCache, player)

        return ResultCode.SUCCESS.code
    }

    private fun walkReinforceCheck(areaCache: AreaCache, playerId: Long, gotoX: Int, gotoY: Int): Int {
        // 验证地块是否有同盟玩家
        val castle = areaCache.castleCache.findCastleByXy(gotoX, gotoY)
        if (castle == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null || otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        if (player.allianceId == 0L || player.allianceId != otherPlayer.allianceId) {
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }

        return ResultCode.SUCCESS.code
    }
}


