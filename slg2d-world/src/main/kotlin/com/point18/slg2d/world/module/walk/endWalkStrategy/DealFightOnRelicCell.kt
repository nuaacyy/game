package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.module.fightdomain.createFightDataByForceGroup
import com.point18.slg2d.world.module.fightdomain.createFightDataBySoliderTeamId
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.noticeCellRemove
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.event.RelicDisappear
import com.point18.slg2d.world.module.walk.fightStrategy.soliderFight
import com.point18.slg2d.world.wpm
import java.util.*

class DealFightOnRelicCell : ICellDeal {
    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        val posX = walkLineInfo.marchAimsX
        val posY = walkLineInfo.marchAimsY

        val atkFightData = createFightDataByForceGroup(areaCache, group)
        val relicCell = areaCache.relicCache.findRelicByXY(posX, posY)
        if (relicCell == null) {
            goHome(areaCache, posX, posY, group)
            return
        }

        val defFightData = createFightDataBySoliderTeamId(relicCell.lineUpId)

        val atkForceList = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)

        val fightResultData = soliderFight.doSoliderFight(
            areaCache,
            PVE_FIGHT_RELIC_ACTION,
            WalkRelic,
            FIGHT_RELIC_REPORT,
            group.massId != 0L,
            posX,
            posY,
            atkForceList,
            LinkedList(),
            atkFightData,
            defFightData
        )
        val fightResult = fightResultData.fightResult

        if (fightResult == FIGHT_RESULT_WIN) {
            // 删除遗迹地块
            areaCache.relicCache.deleteRelicByXY(posX, posY)

            wpm.es.fire(
                areaCache,
                group.mainPlayerId,
                RELIC_DISAPPEAR,
                RelicDisappear(posX, posY, relicCell.relicId)
            )
        }

        // 回城
        goHome(areaCache, posX, posY, group)

        // 刷新地块
        noticeCellUpdate(areaCache, posX, posY)
    }
}