package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.ScoutCount
import com.point18.slg2d.common.constg.ScoutResearchLv1
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome

class DealScoutOnRelicCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到行军组中的玩家信息,行军组Id:%d", group.id)
            return ResultCode.PARAMETER_ERROR.code
        }

        // 检查自己是否有可侦查的科技效果
        if (getResearchEffectValue(areaCache, NO_FILTER, player, ScoutResearchLv1) == 0) {
            return ResultCode.NO_SCOUT_RESEARCH.code
        }

        //检查遗迹是否有反侦察
        val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
        if (relicCell == null) {
            assert(false) { "在($posX,$posY)找不到遗迹地块信息" }
            return ResultCode.NothingOnCell.code
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

        val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
        if (relicCell == null) {
            assert(false) { "在($posX,$posY)找不到遗迹地块信息" }
            return
        }
        scoutReportInfo.genScoutRelicReport(group.mainPlayerId, relicCell)

        // 部队回城
        goHome(areaCache, posX, posY, group)

        targetAddVal(areaCache, group.mainPlayerId, ScoutCount)
    }
}

