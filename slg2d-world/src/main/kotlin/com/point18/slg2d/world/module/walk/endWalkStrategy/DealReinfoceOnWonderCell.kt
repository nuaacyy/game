package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.JijieTroopMaxAdd
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.Reinforce
import com.point18.slg2d.world.common.findAllWonderReinforce
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.isWonderPeace
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

class DealReinforceOnWonderICell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val playerId = group.mainPlayerId

        val rst = pcs.wonderLocationProtoCache.findInWonderType(walkLineInfo.marchAimsX, walkLineInfo.marchAimsY)
        val wonderProto = rst.wonderLocationProto
        if (wonderProto == null) {
            normalLog.error("坐标(${walkLineInfo.marchAimsX},${walkLineInfo.marchAimsY})的奇观信息不存在")
            return ResultCode.PARAMETER_ERROR.code
        }
        //校验奇观状态
        val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
        if (wonder == null) {
            normalLog.error("配置对应的奇观数据不存在:${wonderProto.id}")
            return ResultCode.PARAMETER_ERROR.code
        }
        if (isWonderPeace(wonder)) {
            return ResultCode.WONDER_IN_PEACE.code
        }
        if (wonder.belongToAllianceId == 0L) {
            return ResultCode.NOT_OCCUPY_WONDER.code
        }

        //校验自身是否有联盟
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到行军组中的玩家信息,行军组Id:${group.id}")
            return ResultCode.PARAMETER_ERROR.code
        }
        if (player.allianceId == 0L) {
            return ResultCode.ONLY_ALLIANCE_CAN_OCCUPY_WONDER.code
        }

        //校验是否同联盟
        if (wonder.belongToAllianceId != player.allianceId) {
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }

        //校验是否有指挥官
        val reinforceRst = findAllWonderReinforce(areaCache, wonderProto.id)
        if (reinforceRst == null) {
            return ResultCode.NO_COMMAND_IN_WONDER.code
        }
        val commandGroup = reinforceRst.commandGroup
        val reinforceGroups = reinforceRst.reinforceGroups


        //校验可增援数量
        var haveReinforceSolider = areaCache.walkForceGroupCache.getSoliderNumInGroup(commandGroup.id)
        for (reinforceGroup in reinforceGroups) {
            haveReinforceSolider += areaCache.walkForceGroupCache.getSoliderNumInGroup(reinforceGroup.id)
        }

        //指挥官
        val commandPlayer = areaCache.playerCache.findPlayerById(commandGroup.mainPlayerId)
        if (commandPlayer == null) {
            normalLog.error("找不到行军组中的玩家信息,行军组Id:${commandGroup.id}")
            return ResultCode.PARAMETER_ERROR.code
        }
        val massSoliderNum = getResearchEffectValue(
            areaCache,
            NO_FILTER,
            commandPlayer,
            JijieTroopMaxAdd
        ) + areaCache.walkForceGroupCache.getSoliderNumInGroup(commandGroup.id)

        val soliderNumToReinforce =
            areaCache.walkForceGroupCache.getSoliderNumInGroup(walkLineInfo.walkForceGroupId) //将要增援的士兵数量
        if (haveReinforceSolider + soliderNumToReinforce > massSoliderNum) {
            return ResultCode.ReinforceSoliderUpLimit.code
        }
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        areaCache.walkForceGroupCache.changeGroupState(group, Reinforce)
        noticeSelfWalkForceGroup(areaCache, Add, group)
    }
}

