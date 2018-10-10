package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome

class DealScoutOnCastleCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到行军组中的玩家信息,玩家Id:${group.mainPlayerId}")
            return ResultCode.PARAMETER_ERROR.code
        }

        // 检查自己是否有可侦查的科技效果
        if (getResearchEffectValue(areaCache, NO_FILTER, player, ScoutResearchLv1) == 0) {
            return ResultCode.NO_SCOUT_RESEARCH.code
        }

        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle == null) {
            assert(false) { "在($posX,$posY)找不到玩家城" }
            return ResultCode.PARAMETER_ERROR.code
        }
        val otherPlayer = areaCache.playerCache.findPlayerById(castle.playerId)
        if (otherPlayer == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        // 查看对方是否开罩子
        val (isHaveCover, _) = areaCache.buffCache.isHaveCoverTypeBuff(otherPlayer.id)
        if (isHaveCover) {
            return ResultCode.HAVE_DEF_COVER_BUFF_WHEN_SCOUT.code
        }

        // 查看对方是否反侦察
        val rst = areaCache.buffCache.findBuffValueByBuffType(otherPlayer.id, BUFF_EFFECT_COUNTERRECONNAISSANCE)
        if (rst.isHave) {
            return ResultCode.HAVE_FANZHENCHA_BUFF.code
        }

        //校验联盟
        if (player.allianceId == 0L) {
            return ResultCode.SUCCESS.code
        }
        if (player.allianceId == otherPlayer.allianceId) {
            return ResultCode.IN_SAME_ALLIANCE.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

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

        val castle = areaCache.castleCache.findCastleByXy(posX, posY)
        if (castle == null) {
            assert(false) { "在($posX,$posY)找不到玩家城" }
            return
        }
        scoutReportInfo.genScoutPlayerReport(group.mainPlayerId, castle.playerId, ResultCode.SUCCESS.code)
        scoutReportInfo.genBeScoutReport(group.mainPlayerId, castle.playerId, CELL_CASTLE)

        // 部队回城
        goHome(areaCache, posX, posY, group)

        //添加统计
        targetAddVal(areaCache, group.mainPlayerId, ScoutCount)
    }
}

