package com.point18.slg2d.world.module.task

import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.wpm
import java.util.*

/**
任务检测的接口
 */
data class AllCheckReturn(var b: Boolean, val v: Long)

interface AllCheck {
    fun check(
        areaCache: AreaCache,
        eventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): (AllCheckReturn)
}

class TaskModule : IModule {
    val checkHandles: HashMap<Int, AllCheck> = hashMapOf()

    override fun moduleInit() {
        // 注册事件处理
        wpm.es.register(KING_LV_CHANGE, CheckTaskEventHandler())
        wpm.es.register(HERO_SUPER_UP, CheckTaskEventHandler())
        wpm.es.register(TARGET_CHANGE, CheckTaskEventHandler())
        wpm.es.register(ACHIEVEMENT_FINISH, CheckTaskEventHandler())

        wpm.es.register(HERO_UP_FINISH, CheckTaskEventHandler())
        wpm.es.register(PART_CITY_UP_FINISH, CheckTaskEventHandler())
        wpm.es.register(BUILDING_UP_FINISH, CheckTaskEventHandler())
        wpm.es.register(ENTER_GAME, CheckTaskEventHandler())
        wpm.es.register(OFFLINE, CheckTaskEventHandler())
        wpm.es.register(SKILL_LV_UP, CheckTaskEventHandler())
        wpm.es.register(ALLOW_ALLIANCE, CheckTaskEventHandler())
        wpm.es.register(SOLDIER_NUM_ADD, CheckTaskEventHandler())
        wpm.es.register(HERO_IN_FORMATION, CheckTaskEventHandler())
        wpm.es.register(PVP_FIGHT_WIN, CheckTaskEventHandler())
        wpm.es.register(PVE_FIGHT_WIN, CheckTaskEventHandler())
        wpm.es.register(GET_NEW_TASK, CheckTaskEventHandler())
        wpm.es.register(GET_TAX, CheckTaskEventHandler())
        wpm.es.register(GET_ONLINE_REWARD, CheckTaskEventHandler())
        wpm.es.register(USER_READY_SOLD, CheckTaskEventHandler())
        wpm.es.register(EXCHANGE_ALLIANCE_CARD, CheckTaskEventHandler())
        wpm.es.register(GET_WORLD_TASK, CheckTaskEventHandler())
        wpm.es.register(HERO_STAR_UP, CheckTaskEventHandler())
        wpm.es.register(PLAYER_DELETE_NEW_CELL, CheckTaskEventHandler())
        wpm.es.register(INNER_CITY_EXTEND, CheckTaskEventHandler())
        wpm.es.register(SHOW_NEAR_MAP, CheckTaskEventHandler())
        wpm.es.register(GET_HERO_CARD, CheckTaskEventHandler())
        wpm.es.register(PAY, CheckTaskEventHandler())
        wpm.es.register(CREATE_ALLIANCE, CheckTaskEventHandler())

        wpm.es.register(CURE_SOLDIER, CheckTaskEventHandler())

        wpm.es.register(MAKE_SOLDIER_FINISH, CheckTaskEventHandler())

        wpm.es.register(ATK_PLAYER, CheckTaskEventHandler())

        wpm.es.register(KILL_SOLDIER, CheckTaskEventHandler())

        wpm.es.register(DAMAGE_SOLDIER, CheckTaskEventHandler())

        wpm.es.register(MASS, CheckTaskEventHandler())

        wpm.es.register(POWER_CHANGE, CheckTaskEventHandler())

        wpm.es.register(FARM_RES, CheckTaskEventHandler())

        wpm.es.register(GET_ALLIANCE_GIFT, CheckTaskEventHandler())

        wpm.es.register(PLAYER_ACTIVITY_ADV_REWARD, CheckTaskEventHandler())

        wpm.es.register(PLAYER_ACTIVITY_RANK, CheckTaskEventHandler())

        wpm.es.register(CAVE_SOLIDER, CheckTaskEventHandler())

        wpm.es.register(GET_RESCUE_PRISON_REWARD, CheckTaskEventHandler())

        wpm.es.register(KILL_PRISON, CheckTaskEventHandler())

        wpm.es.register(TRANSPORT_RES_SUCCESS, CheckTaskEventHandler())

        wpm.es.register(POWER_ADD, CheckTaskEventHandler())

        wpm.es.register(PLAYER_ACTIVITY_CHANGE, CheckTaskEventHandler())

        wpm.es.register(FARM_END, CheckTaskEventHandler())

        wpm.es.register(FARM_EMPTY, CheckTaskEventHandler())

        wpm.es.register(JJC_FIGHT_WIN, CheckTaskEventHandler())

        wpm.es.register(SET_ARMY_PLAN, CheckTaskEventHandler())

        wpm.es.register(SET_CITY_DEF_HERO, CheckTaskEventHandler())

        wpm.es.register(COUNTRY_POS_CHANGE, CheckTaskEventHandler())

        wpm.es.register(OCCUPY_WONDER, CheckTaskEventHandler())

        wpm.es.register(CLEAR_FOG_EVENT, CheckTaskEventHandler())

        wpm.es.register(MAKE_TRAP_FINISH, CheckTaskEventHandler())

        wpm.es.register(ACTIVITY_SCORE_OVER, CheckTaskEventHandler())

        checkHandles[CHECK_IS_HAVE_ALLIANCE] = CheckIsHaveAlliance()
        checkHandles[CHECK_OPEN_GIFT_NUM] = CheckGetAllianceGiftNowNum()
        checkHandles[CHECK_ALLIANCE_HELP_ALL_NUM] = CheckAllianceHelpNum()
        checkHandles[CHECK_CURE_SOLIDER_ALL_NUM] = CheckCureSoliderNum()
        checkHandles[CHECK_CURE_SOLIDER_NUM] = CheckCureSoliderNowNum()
        checkHandles[CHECK_MAKE_SOLIDER_ALL_NUM] = CheckMakeSoliderNum()
        checkHandles[CHECK_MAKE_SOLIDER_NUM] = CheckMakeSoliderNowNum()
        checkHandles[CHECK_INSTANCE_NUM] = CheckInstanceFightNowNum()
        checkHandles[CHECK_INSTANCE_ALL_NUM] = CheckInstanceFightNum()
        checkHandles[CHECK_INSTANCE_STAR_NUM] = CheckInstanceStarNum()
        checkHandles[CHECK_INSTANCE_THREE_STAR_NUM] = CheckInstanceThreeStarNum()
        checkHandles[CHECK_INSTANCE_FINISH] = CheckInstanceHaveStarNum()
        checkHandles[CHECK_CANGBING_NUM] = CheckCaveSoliderNum()
        checkHandles[CHECK_GET_PRISON_NUM] = CheckGetPrisonNum()
        checkHandles[CHECK_KILL_PRISON_NUM] = CheckKillPrisonNum()
        checkHandles[CHECK_GET_REWARD_GOLD_NUM] = CheckGetRewardGoldNum()
        checkHandles[CHECK_SCOUT_ALL_NUM] = CheckScoutNum()
        checkHandles[CHECK_ATK_PLAYER_ALL_NUM] = CheckAtkNum()
        checkHandles[CHECK_ATK_PLAYER_NUM] = CheckAtkNowNum()
        checkHandles[CHECK_ATK_WIN_ALL_NUM] = CheckAtkWinNum()
        checkHandles[CHECK_KILL_SOLIDER_ALL_NUM] = CheckKillArmySoliderNum()
        checkHandles[CHECK_KILL_SOLIDER_NUM] = CheckKillArmySoliderNowNum()
        checkHandles[CHECK_DAMAGE_SOLIDER_ALL_NUM] = CheckDamageArmySoliderNum()
        checkHandles[CHECK_DAMAGE_SOLIDER_NUM] = CheckDamageArmySoliderNowNum()
        checkHandles[CHECK_MASS_NUM] = CheckMassNum()
        checkHandles[CHECK_MASS_NOW_NUM] = CheckMassNowNum()
        checkHandles[CHECK_JOIN_MASS_NUM] = CheckJoinMassNum()
        checkHandles[CHECK_JOIN_MASS_NOW_NUM] = CheckJoinMassNowNum()
        checkHandles[CHECK_DEFENSE_SUCESS_NUM] = CheckDefenseSuccessNum()
        checkHandles[CHECK_POWER_NUM] = CheckPowerNum()
        checkHandles[CHECK_POWER_NOW_NUM] = CheckPowerNowNum()
        checkHandles[CHECK_ATK_MONSTER_NUM] = CheckAtkMonsterNum()
        checkHandles[CHECK_ATK_MONSTER_NOW_NUM] = CheckAtkMonsterNowNum()
        checkHandles[CHECK_KILL_MONSTER_NUM] = CheckKillMonsterNum()
        checkHandles[CHECK_KILL_MONSTER_NOW_NUM] = CheckKillMonsterNowNum()
        checkHandles[CHECK_MONSTER_SCORE_NUM] = CheckMonsterScoreNum()
        checkHandles[CHECK_FARM_RES_COUNT] = CheckFarmResCount()
        checkHandles[CHECK_FARM_RES_NOW_COUNT] = CheckFarmResNowCount()
        checkHandles[CHECK_FARM_EMPTY_NUM] = CheckFarmEmptyNum()
        checkHandles[CHECK_ACTIVITY_ADV_REWARD] = CheckPlayerActivityAdv()
        checkHandles[CHECK_ACTIVITY_RANK_REWARD] = CheckPlayerActivityRank()
        checkHandles[CHECK_MAKE_TRAP_ALL_NUM] = CheckMakeTrapNum()
        checkHandles[CHECK_MAKE_TRAP_NUM] = CheckMakeTrapNowNum()
        checkHandles[CHECK_OCCUPY_WONDER] = CheckOccupyWonder()
        checkHandles[CHECK_HOLD_COUNTRY_POS] = CheckHoldCountryPos()
        checkHandles[CHECK_CLEAR_FOG] = CheckClearFog()
        checkHandles[CHECK_FARM_RES_NUM] = CheckFarmResNum()
        checkHandles[CHECK_TRANSPORT_RES_NOW_NUM] = CheckTransportResNowNum()
        checkHandles[CHECK_OPEN_WORLD_MAP] = CheckOpenWorldMapCount()
        checkHandles[CHECK_POWER_ADD_NOW_NUM] = CheckPowerAddNowNum()
        checkHandles[CKECK_JJC_WIN_NUM] = CheckJJCWinNum()
        checkHandles[CHECK_JJC_DEF_STAR_NUM] = CheckJJcDefStarNum()
        checkHandles[CHECK_STATION_HERO_NOW_NUM] = CheckCityDefHeroNum()
        checkHandles[CHECK_CHALLENGE_SCORE_NUM] = CheckActivityScoreNum()
    }

}

var TaskM: TaskModule = TaskModule()