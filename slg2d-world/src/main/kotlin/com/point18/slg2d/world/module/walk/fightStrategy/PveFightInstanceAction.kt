package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.module.fightdomain.AllFightInfo
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.FightInfo
import java.util.*

// 推图战斗流程
class PveFightInstanceAction(var action: Int) : IHeroFightDeal {
    override fun dealAfterOneFight(
        areaCache: AreaCache,
        atkFightData: FightData,
        defFightData: FightData,
        runType: Int,
        posX: Int,
        posY: Int,
        fightInfo: FightInfo
    ): Boolean {
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
