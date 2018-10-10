package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.soliderBattle.fight.createSoliderFight
import com.point18.slg2d.world.module.soliderBattle.fightdomain.SoliderFightInfoData

//处理军团战接口
interface ISoliderFightDeal {
    fun dealAfterOneFight(
        areaCache: AreaCache,
        atkForceList: List<WalkForce>,
        defForceList: List<WalkForce>,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        isMass: Boolean,
        posX: Int,
        posY: Int,
        detailFightInfo: SoliderFightInfoData
    ): Boolean
}

val soliderFight = SoliderFight()

class SoliderFight {
    fun doSoliderFight(
        areaCache: AreaCache,
        fightType: Int,
        runType: Int,
        reportType: Int,
        isMass: Boolean,
        posX: Int,
        posY: Int,
        atkForceList: List<WalkForce>,
        defForceList: List<WalkForce>,
        atkFightData: FightData,
        defFightData: FightData
    ): SoliderFightInfoData {

        val fastFight = createSoliderFight(
            areaCache,
            posX,
            posY,
            reportType,
            atkFightData,
            defFightData
        )
        val fightResultData = fastFight.initAndStart()

        fightDeal.soliderFightDealMap[fightType]?.dealAfterOneFight(
            areaCache,
            atkForceList,
            defForceList,
            atkFightData,
            defFightData,
            runType,
            isMass,
            posX,
            posY,
            fightResultData
        )
        return fightResultData
    }
}