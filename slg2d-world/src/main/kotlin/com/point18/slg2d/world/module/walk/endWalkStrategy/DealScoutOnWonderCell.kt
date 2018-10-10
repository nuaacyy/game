package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome

class DealScoutOnWonderCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到行军组中的玩家信息,行军组Id:${group.id}")
            return ResultCode.PARAMETER_ERROR.code
        }

        // 检查自己是否有可侦查的科技效果
        if (getResearchEffectValue(areaCache, NO_FILTER, player, ScoutResearchLv1) == 0) {
            return ResultCode.NO_SCOUT_RESEARCH.code
        }

        val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, Reinforce)
        if (groups.count() > 0) {
            val otherPlayer = areaCache.playerCache.findPlayerById(groups[0].mainPlayerId)
            if (otherPlayer == null) {
                return ResultCode.PARAMETER_ERROR.code
            }
            // 查看对方是否反侦察
            val rst =
                areaCache.buffCache.findBuffValueByBuffType(otherPlayer.id, BUFF_EFFECT_COUNTERRECONNAISSANCE)
            if (rst.isHave) {
                return ResultCode.HAVE_FANZHENCHA_BUFF.code
            }
            if (player.allianceId == 0L) {
                return ResultCode.SUCCESS.code
            }
            if (player.allianceId == otherPlayer.allianceId) {
                return ResultCode.IN_SAME_ALLIANCE.code
            }
            return ResultCode.SUCCESS.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val rst = pcs.wonderLocationProtoCache.findInWonderType(posX, posY)
        val wonderProto = rst.wonderLocationProto
        if (wonderProto == null) {
            assert(false) { "坐标($posX,$posY)对应的奇观信息不存在" }
            return
        }

        val scoutReportInfo = ReportInfo(
            areaCache,
            posX,
            posY,
            null,
            null,
            null,
            null,
            "".toByteArray()
        )

        scoutReportInfo.genScoutWonderReport(group.mainPlayerId, wonderProto.id, ResultCode.SUCCESS.code)

        // 部队回城
        goHome(areaCache, posX, posY, group)

        targetAddVal(areaCache, group.mainPlayerId, ScoutCount)
    }
}


