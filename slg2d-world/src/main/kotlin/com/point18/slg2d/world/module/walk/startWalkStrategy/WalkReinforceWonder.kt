package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import java.util.*

// 增援奇观
class WalkReinforceWounder : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckNoHero or CheckHaveSolider or CheckSameAlliance
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
        //校验是否是奇观
        val rst = pcs.wonderLocationProtoCache.findInWonderType(wp.gotoX, wp.gotoY)
        val wonderProto = rst.wonderLocationProto
        if (rst.int != WONDER_BASE || wonderProto == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        //校验奇观状态
        val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
        if (wonder == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        // 和平状态无法增援
        if (isWonderPeace(wonder)) {
            return ResultCode.WONDER_IN_PEACE.code
        }
        // 必须在奇观被占领时增援
        if (wonder.belongToAllianceId == 0L) {
            return ResultCode.NOT_OCCUPY_WONDER.code
        }

        //校验自身是否有联盟
        val player = areaCache.playerCache.findPlayerById(wp.playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", wp.playerId)
            return ResultCode.PARAMETER_ERROR.code
        }

        if (player.allianceId == 0L) {
            return ResultCode.ONLY_ALLIANCE_CAN_OCCUPY_WONDER.code
        }

        //校验是否同联盟
        if (wonder.belongToAllianceId != player.allianceId) {
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }

        //校验是否已加入增援
        val reinforceRst = findAllWonderReinforce(areaCache, wonderProto.id)
        if (reinforceRst == null) {
            return ResultCode.NO_COMMAND_IN_WONDER.code
        }
        val commandGroup = reinforceRst.commandGroup
        val reinforceGroups = reinforceRst.reinforceGroups

        if (commandGroup.mainPlayerId == wp.playerId) {
            return ResultCode.ReinforceRepeat.code
        }
        for (group in reinforceGroups) {
            if (group.mainPlayerId == wp.playerId) {
                return ResultCode.ReinforceRepeat.code
            }
        }

        //校验可增援数量
        var haveReinforceSolider = areaCache.walkForceGroupCache.getSoliderNumInGroup(commandGroup.id)
        for (group in reinforceGroups) {
            haveReinforceSolider += areaCache.walkForceGroupCache.getSoliderNumInGroup(group.id)
        }

        //指挥官
        val commandPlayer = areaCache.playerCache.findPlayerById(commandGroup.mainPlayerId)
        if (commandPlayer == null) {
            normalLog.error("找不到玩家信息:%d", commandGroup.mainPlayerId)
            return ResultCode.PARAMETER_ERROR.code
        }

        val massSoliderNum = getResearchEffectValue(
            areaCache,
            NO_FILTER,
            commandPlayer,
            JijieTroopMaxAdd
        ) + areaCache.walkForceGroupCache.getSoliderNumInGroup(commandGroup.id)

        var soliderNumToReinforce = 0 //将要增援的士兵数量
        for ((_, num) in wp.we.SoliderMap) {
            soliderNumToReinforce += num
        }
        if (haveReinforceSolider + soliderNumToReinforce > massSoliderNum) {
            return ResultCode.ReinforceSoliderUpLimit.code
        }
        return ResultCode.SUCCESS.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        val allMass = areaCache.massCache.findAllMassByPos(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        for (mass in allMass) {
            if (mass.fightType == WalkOccupyWonder) {
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
            normalLog.error("找不到玩家信息:%d", playerId)
            return ResultCode.PARAMETER_ERROR.code
        }

        addWalkHot(areaCache, player)

        //通知奇观增援部队变化
        val allAllianceMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
        val memberIds = LinkedList<Long>()
        for (member in allAllianceMembers) {
            memberIds.add(member.id)
        }
        noticeWonderReinforce(areaCache, Add, walkLineInfo.walkForceGroupId, memberIds)

        return ResultCode.SUCCESS.code
    }

}
