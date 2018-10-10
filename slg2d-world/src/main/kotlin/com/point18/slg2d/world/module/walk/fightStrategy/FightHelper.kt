package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.constg.*

var fightDeal: FightDeal = FightDeal()

class FightDeal {
    val soliderFightDealMap = hashMapOf<Int, ISoliderFightDeal>()
    val heroFightDealMap = hashMapOf<Int, IHeroFightDeal>()
    val bossFightDealMap = hashMapOf<Int, IBossFightDeal>()

    fun initDealFight() {
        this.bossFightDealMap[PVE_FIGHT_COMMON_BOSS_ACTION] = PveFightCommonBossAction(ACTION_FIGHT_BOSS)
        this.bossFightDealMap[PVE_FIGHT_CALL_BOSS_ACTION] = PveFightCallBossAction(ACTION_FIGHT_BOSS)
        this.bossFightDealMap[PVE_FIGHT_ACTIVITY_BOSS_ACTION] = PveFightActivityBossAction(ACTION_FIGHT_BOSS)

        this.heroFightDealMap[PVE_FIGHT_INSTANCE_ACTION] = PveFightInstanceAction(ACTION_FIGHT_MISSION)
        this.heroFightDealMap[PVP_FIGHT_JJC_ACTION] = PvpFightJjcAction(ACTION_FIGHT_JJC)

        this.soliderFightDealMap[PVE_FIGHT_RELIC_ACTION] = PveFightRelicAction(ACTION_FIGHT_RELIC)
        this.soliderFightDealMap[PVP_FIGHT_PLAYER_ACTION] = PvpFightPlayerAction(ACTION_FIGHT_CASTLE)
        this.soliderFightDealMap[PVP_FIGHT_GROUP_ACTION] = PvpFightGroupAction(ACTION_FIGHT_CASTLE)
    }
}





