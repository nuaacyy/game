package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.module.fightdomain.AllFightInfo
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.FightInfo
import com.point18.slg2d.world.module.report.ReportInfo
import java.util.*

// 竞技场战斗流程
class PvpFightJjcAction(var action: Int) : IHeroFightDeal {
    override fun dealAfterOneFight(
        areaCache: AreaCache,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        posX: Int,
        posY: Int,
        fightInfo: FightInfo
    ): Boolean {
        //生成竞技场战报
        val reportInfo = ReportInfo(
            areaCache,
            posX,
            posY,
            atkFightData,
            defFightData,
            null,
            null,
            fightInfo.detailInfo.detailFightInfo
        )

        reportInfo.genJjcFightReport(
            fightInfo.fightResult,
            ATK_SIDE,
            fightInfo.tempRecords[ChangeRank] as Int,
            fightInfo.tempRecords[DefRank] as Int,
            HasRead
        )
        if (defFightData.playerId != 0L) {
            reportInfo.genJjcFightReport(
                fightInfo.fightResult,
                DEF_SIDE,
                fightInfo.tempRecords[ChangeRank] as Int,
                fightInfo.tempRecords[DefRank] as Int,
                UnRead
            )
        }
        return false
    }

    override fun dealAfterAllFight(
        areaCache: AreaCache,
        atkFightData: FightData,
        defFightDataList: LinkedList<FightData>,
        posX: Int,
        posY: Int,
        allFightInfo: AllFightInfo
    ): Boolean {
        return false
    }
}
