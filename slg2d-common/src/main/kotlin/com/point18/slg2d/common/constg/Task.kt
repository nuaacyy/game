package com.point18.slg2d.common.constg

import com.point18.slg2d.common.baseg.EventType

var taskCheckWhiteList: MutableMap<Int, MutableList<EventType>> = mutableMapOf() // 任务检测的白名单 大Key : 检测条件ID  小Key : 事件类型
var taskCheckParameterNum: MutableMap<Int, Int> = mutableMapOf() // 任务的检测参数数量(就是配置里的任务检测类型后面的值用_分割之后的长度)

fun initTaskCheck() {
    initTaskCheckWhiteList()
    initTaskCheckParameterNum()
}

// 检测配置参数长度是否正确
fun initTaskCheckParameterNum() {
    taskCheckParameterNum[CHECK_WORLD_TALK_NUM] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_TALK_NUM] = 1
    taskCheckParameterNum[CHECK_ADD_FRIEND_NUM] = 1
    taskCheckParameterNum[CHECK_NORMAL_LOTTERY_ALL] = 1
    taskCheckParameterNum[CHECK_NORMAL_LOTTERY] = 1
    taskCheckParameterNum[CHECK_SUPER_LOTTERY_ALL] = 1
    taskCheckParameterNum[CHECK_SUPER_LOTTERY] = 1
    taskCheckParameterNum[CHECK_HAVE_HERO_NUM] = 2
    taskCheckParameterNum[CHECK_HAVE_AWAKE_HERO_NUM] = 2
    taskCheckParameterNum[CHECK_HAVE_STAR_HERO_NUM] = 2
    taskCheckParameterNum[CHECK_HERO_EQUIP_LV_UP_NUM_ALL] = 1
    taskCheckParameterNum[CHECK_HERO_EQUIP_LV_UP_NUM] = 1
    taskCheckParameterNum[CHECK_HERO_SKILL_STRONG_ALL] = 1
    taskCheckParameterNum[CHECK_HERO_SKILL_STRONG] = 1
    taskCheckParameterNum[CHECK_HERO_SKILL_NUM] = 2
    taskCheckParameterNum[CHECK_BUILD_TYPE_LV] = 2
    taskCheckParameterNum[CHECK_BUILD_TYPE_LV_NUM] = 3
    taskCheckParameterNum[CHECK_BUILD_RES_SPEED] = 2
    taskCheckParameterNum[CHECK_ALL_GET_RES_NUM] = 2
    taskCheckParameterNum[CHECK_SKIN_NUM] = 1
    taskCheckParameterNum[CHECK_SKIN_STRONG_NUM] = 1
    taskCheckParameterNum[CHECK_GET_ONE_SKIN] = 1
    taskCheckParameterNum[CHECK_SKIN_LV_NUM] = 2
    taskCheckParameterNum[CHECK_MAKE_KING_EQUIP_NUM] = 1
    taskCheckParameterNum[CHECK_HAVE_KING_EQUIP_Q] = 2
    taskCheckParameterNum[CHECK_MAKE_EQUIP] = 2
    taskCheckParameterNum[CHECK_COMM_CARD_NUM] = 2
    taskCheckParameterNum[CHECK_ON_CARD_NUM] = 2
    taskCheckParameterNum[CHECK_NORMAL_MOVE_CITY_NUM] = 1
    taskCheckParameterNum[CHECK_RANK_MOVE_CITY_NUM] = 1
    taskCheckParameterNum[CHECK_JJC_FIGHT_NUM] = 1
    taskCheckParameterNum[CHECK_JJC_FIGHT_NUM_ALL] = 1
    taskCheckParameterNum[CHECK_JJC_TO_RANK] = 1
    taskCheckParameterNum[CHECK_JJC_SHOP_BUY_NUM] = 1
    taskCheckParameterNum[CHECK_JJC_SHOP_REFRESH_NUM] = 1
    taskCheckParameterNum[CHECK_BUY_SUPRISE_NUM] = 1
    taskCheckParameterNum[CHECK_BUY_SUPRISE_NUM_ALL] = 1
    taskCheckParameterNum[CHECK_BANK_NUM] = 2
    taskCheckParameterNum[CHECK_BANK_MONEY] = 2
    taskCheckParameterNum[CHECK_BEFORE_BANK_NUM] = 2
    taskCheckParameterNum[CHECK_LIBRARY_NUM] = 1
    taskCheckParameterNum[CHECK_PHOTO_NUM] = 1
    taskCheckParameterNum[CHECK_DIAMOND_SHOP_BUY_NUM] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_SHOP_BUY_NUM] = 1
    taskCheckParameterNum[CHECK_ACTIVITY_SHOP_BUY_NUM] = 1
    taskCheckParameterNum[CHECK_GET_ONLINE_GIFT_NUM] = 1
    taskCheckParameterNum[CHECK_GET_ONLINE_BIG_GIFT_NUM] = 1
    taskCheckParameterNum[CHECK_DIAMOND_COST_NUM] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_COIN__COST_NUM] = 1
    taskCheckParameterNum[CHECK_VIP_LV] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_HELP_NUM] = 1
    taskCheckParameterNum[CHECK_UNIT_TASK_FINISH] = 1
    taskCheckParameterNum[CHECK_UNIT_TASK_NUM] = 1
    taskCheckParameterNum[CHECK_MAIN_TASK_NUM] = 1
    taskCheckParameterNum[CHECK_MAIN_TASK_FINISH] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_COMPETITION_SCORE] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_COMPETITION_TASK_NUM] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_COMPETITION_REWARD] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_COMPETITION_RANK_LV] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_COMPETITION_RANK] = 1
    taskCheckParameterNum[CHECK_CASINO_BOSS_NUM] = 2
    taskCheckParameterNum[CHECK_CASINO_NUM] = 2
    taskCheckParameterNum[CHECK_CASINO_KILL_BOSS_NUM] = 2
    taskCheckParameterNum[CHECK_RESEARCH] = 1
    taskCheckParameterNum[CHECK_COMPETITION_SCORE_NUM] = 1
    taskCheckParameterNum[CHECK_EFFECT_VALUE] = 2
    taskCheckParameterNum[CHECK_KING_LV] = 1
    taskCheckParameterNum[CHECK_BUY_SHIP_PROP_COUNT] = 1
    taskCheckParameterNum[CHECK_IS_HAVE_ALLIANCE] = 1
    taskCheckParameterNum[CHECK_OPEN_GIFT_NUM] = 1
    taskCheckParameterNum[CHECK_ALLIANCE_HELP_ALL_NUM] = 1
    taskCheckParameterNum[CHECK_CURE_SOLIDER_ALL_NUM] = 1
    taskCheckParameterNum[CHECK_CURE_SOLIDER_NUM] = 1
    taskCheckParameterNum[CHECK_MAKE_SOLIDER_ALL_NUM] = 3
    taskCheckParameterNum[CHECK_MAKE_SOLIDER_NUM] = 3
    taskCheckParameterNum[CHECK_INSTANCE_NUM] = 1
    taskCheckParameterNum[CHECK_INSTANCE_ALL_NUM] = 1
    taskCheckParameterNum[CHECK_INSTANCE_STAR_NUM] = 1
    taskCheckParameterNum[CHECK_INSTANCE_THREE_STAR_NUM] = 1
    taskCheckParameterNum[CHECK_INSTANCE_FINISH] = 2
    taskCheckParameterNum[CHECK_CANGBING_NUM] = 1
    taskCheckParameterNum[CHECK_GET_PRISON_NUM] = 1
    taskCheckParameterNum[CHECK_KILL_PRISON_NUM] = 1
    taskCheckParameterNum[CHECK_GET_REWARD_GOLD_NUM] = 1
    taskCheckParameterNum[CHECK_SCOUT_ALL_NUM] = 1
    taskCheckParameterNum[CHECK_ATK_PLAYER_ALL_NUM] = 1
    taskCheckParameterNum[CHECK_ATK_PLAYER_NUM] = 1
    taskCheckParameterNum[CHECK_ATK_WIN_ALL_NUM] = 1
    taskCheckParameterNum[CHECK_KILL_SOLIDER_ALL_NUM] = 3
    taskCheckParameterNum[CHECK_KILL_SOLIDER_NUM] = 3
    taskCheckParameterNum[CHECK_DAMAGE_SOLIDER_ALL_NUM] = 3
    taskCheckParameterNum[CHECK_DAMAGE_SOLIDER_NUM] = 3
    taskCheckParameterNum[CHECK_MASS_NUM] = 1
    taskCheckParameterNum[CHECK_MASS_NOW_NUM] = 1
    taskCheckParameterNum[CHECK_JOIN_MASS_NUM] = 1
    taskCheckParameterNum[CHECK_JOIN_MASS_NOW_NUM] = 1
    taskCheckParameterNum[CHECK_DEFENSE_SUCESS_NUM] = 1
    taskCheckParameterNum[CHECK_POWER_NUM] = 1
    taskCheckParameterNum[CHECK_POWER_NOW_NUM] = 1
    taskCheckParameterNum[CHECK_ATK_MONSTER_NUM] = 3
    taskCheckParameterNum[CHECK_ATK_MONSTER_NOW_NUM] = 3
    taskCheckParameterNum[CHECK_KILL_MONSTER_NUM] = 3
    taskCheckParameterNum[CHECK_KILL_MONSTER_NOW_NUM] = 3
    taskCheckParameterNum[CHECK_MONSTER_SCORE_NUM] = 1
    taskCheckParameterNum[CHECK_FARM_RES_COUNT] = 2
    taskCheckParameterNum[CHECK_FARM_RES_NOW_COUNT] = 3
    taskCheckParameterNum[CHECK_FARM_EMPTY_NUM] = 2
    taskCheckParameterNum[CHECK_ACTIVITY_ADV_REWARD] = 2
    taskCheckParameterNum[CHECK_ACTIVITY_RANK_REWARD] = 1
    taskCheckParameterNum[CHECK_MAKE_TRAP_ALL_NUM] = 3
    taskCheckParameterNum[CHECK_MAKE_TRAP_NUM] = 3
    taskCheckParameterNum[CHECK_STATION_HERO_NOW_NUM] = 1
    taskCheckParameterNum[CHECK_OCCUPY_WONDER] = 2
    taskCheckParameterNum[CHECK_HOLD_COUNTRY_POS] = 1
    taskCheckParameterNum[CKECK_JJC_WIN_NUM] = 1
    taskCheckParameterNum[CHECK_JJC_DEF_STAR_NUM] = 1
    taskCheckParameterNum[CHECK_CLEAR_FOG] = 1
    taskCheckParameterNum[CHECK_OPEN_WORLD_MAP] = 1
    taskCheckParameterNum[CHECK_FARM_RES_NUM] = 2
    taskCheckParameterNum[CHECK_TRANSPORT_RES_NOW_NUM] = 1
    taskCheckParameterNum[CHECK_POWER_ADD_NOW_NUM] = 2
    taskCheckParameterNum[CHECK_CHALLENGE_SCORE_NUM] = 2
    taskCheckParameterNum[CHECK_CLEAR_TIME_NOW_NUM] = 2
    taskCheckParameterNum[CHECK_CLEAR_TIME_NUM] = 2
}

fun initTaskCheckWhiteList() {
    // home服的任务检测白名单
    taskCheckWhiteList.getOrPut(CHECK_WORLD_TALK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, CHAT))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_TALK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, CHAT))
    taskCheckWhiteList.getOrPut(CHECK_ADD_FRIEND_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_FRIEND))
    taskCheckWhiteList.getOrPut(CHECK_NORMAL_LOTTERY_ALL) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_HERO_CARD))
    taskCheckWhiteList.getOrPut(CHECK_NORMAL_LOTTERY) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_HERO_CARD))
    taskCheckWhiteList.getOrPut(CHECK_SUPER_LOTTERY_ALL) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_HERO_CARD))
    taskCheckWhiteList.getOrPut(CHECK_SUPER_LOTTERY) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_HERO_CARD))
    taskCheckWhiteList.getOrPut(CHECK_HAVE_HERO_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_HERO_CARD, GET_NEW_HERO, HERO_UP_FINISH))
    taskCheckWhiteList.getOrPut(CHECK_HAVE_AWAKE_HERO_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO))
    taskCheckWhiteList.getOrPut(CHECK_HAVE_STAR_HERO_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO))
    taskCheckWhiteList.getOrPut(CHECK_HERO_EQUIP_LV_UP_NUM_ALL) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO, HERO_EQUIP_LV_UP))
    taskCheckWhiteList.getOrPut(CHECK_HERO_EQUIP_LV_UP_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO, HERO_EQUIP_LV_UP))
    taskCheckWhiteList.getOrPut(CHECK_HERO_SKILL_STRONG_ALL) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO, HERO_SKILL_LV_UP))
    taskCheckWhiteList.getOrPut(CHECK_HERO_SKILL_STRONG) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO, HERO_SKILL_LV_UP))
    taskCheckWhiteList.getOrPut(CHECK_HERO_SKILL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, HERO_STAR_UP, HERO_SUPER_UP, GET_NEW_HERO, HERO_SKILL_LV_UP))
    taskCheckWhiteList.getOrPut(CHECK_BUILD_TYPE_LV) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, BUILDING_UP_FINISH, GM_REFRESH_EVENT))
    taskCheckWhiteList.getOrPut(CHECK_BUILD_TYPE_LV_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, BUILDING_UP_FINISH, GM_REFRESH_EVENT))
    taskCheckWhiteList.getOrPut(CHECK_BUILD_RES_SPEED) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RESEARCH_EFFECT_CHANGE, BUILDING_UP_FINISH))
    taskCheckWhiteList.getOrPut(CHECK_ALL_GET_RES_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RESEARCH_EFFECT_CHANGE, RES_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_SKIN_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_SKIN))
    taskCheckWhiteList.getOrPut(CHECK_SKIN_STRONG_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_SKIN, STRENGTH_SKIN))
    taskCheckWhiteList.getOrPut(CHECK_GET_ONE_SKIN) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_SKIN, STRENGTH_SKIN))
    taskCheckWhiteList.getOrPut(CHECK_SKIN_LV_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_SKIN, STRENGTH_SKIN))
    taskCheckWhiteList.getOrPut(CHECK_MAKE_KING_EQUIP_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_KING_EQUIP))
    taskCheckWhiteList.getOrPut(CHECK_HAVE_KING_EQUIP_Q) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RESEARCH_EFFECT_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_MAKE_EQUIP) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_KING_EQUIP))
    taskCheckWhiteList.getOrPut(CHECK_COMM_CARD_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, COMPOUND_CARD))
    taskCheckWhiteList.getOrPut(CHECK_ON_CARD_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, KING_EQUIP_ON_CARD))
    taskCheckWhiteList.getOrPut(CHECK_NORMAL_MOVE_CITY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, MOVE_CITY))
    taskCheckWhiteList.getOrPut(CHECK_RANK_MOVE_CITY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, MOVE_CITY))
    taskCheckWhiteList.getOrPut(CHECK_JJC_FIGHT_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, JJC_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_JJC_FIGHT_NUM_ALL) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, JJC_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_JJC_TO_RANK) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, JJC_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_JJC_SHOP_BUY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RES_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_JJC_SHOP_REFRESH_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, JJC_SHOP_REFRESH))
    taskCheckWhiteList.getOrPut(CHECK_BUY_SUPRISE_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, MERCHANT_SHIP_BUY_SURPRISE))
    taskCheckWhiteList.getOrPut(CHECK_BUY_SUPRISE_NUM_ALL) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, MERCHANT_SHIP_BUY_SURPRISE))
    taskCheckWhiteList.getOrPut(CHECK_BANK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_BANK_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_BANK_MONEY) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_BANK_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_BEFORE_BANK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_BANK_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_LIBRARY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_LIBRARY))
    taskCheckWhiteList.getOrPut(CHECK_PHOTO_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_NEW_PHOTO))
    taskCheckWhiteList.getOrPut(CHECK_DIAMOND_SHOP_BUY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, BUY_SHOP_TOTAL))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_SHOP_BUY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, BUY_SHOP_TOTAL))
    taskCheckWhiteList.getOrPut(CHECK_ACTIVITY_SHOP_BUY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, BUY_SHOP_TOTAL))
    taskCheckWhiteList.getOrPut(CHECK_GET_ONLINE_GIFT_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_ONLINE_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_GET_ONLINE_BIG_GIFT_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_ONLINE_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_DIAMOND_COST_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RES_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_COIN__COST_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RES_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_VIP_LV) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, VIP_LV_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_HELP_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GO_ALLIANCE_HELP))
    taskCheckWhiteList.getOrPut(CHECK_UNIT_TASK_FINISH) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_UNIT_TASK_REWARD, GET_NEW_TASK))
    taskCheckWhiteList.getOrPut(CHECK_UNIT_TASK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK))
    taskCheckWhiteList.getOrPut(CHECK_MAIN_TASK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK))
    taskCheckWhiteList.getOrPut(CHECK_MAIN_TASK_FINISH) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_COMPETITION_SCORE) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_ALLIANCE_COMPETITION_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_COMPETITION_TASK_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK, GET_ALLIANCE_COMPETITION_REWARD, ALLIANCE_COMPETITION_OVER))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_COMPETITION_REWARD) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK, GET_ALLIANCE_COMPETITION_REWARD, ALLIANCE_COMPETITION_OVER))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_COMPETITION_RANK_LV) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK, GET_ALLIANCE_COMPETITION_REWARD, ALLIANCE_COMPETITION_OVER))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_COMPETITION_RANK) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_TASK_REWARD, GET_NEW_TASK, GET_ALLIANCE_COMPETITION_REWARD, ALLIANCE_COMPETITION_OVER))
    taskCheckWhiteList.getOrPut(CHECK_CASINO_BOSS_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_CASINO_BOSS_NUM, GET_CASINO_KILL_BOSS_NUM))
    taskCheckWhiteList.getOrPut(CHECK_CASINO_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_CASINO_BOSS_NUM, GET_CASINO_KILL_BOSS_NUM))
    taskCheckWhiteList.getOrPut(CHECK_CASINO_KILL_BOSS_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_CASINO_BOSS_NUM, GET_CASINO_KILL_BOSS_NUM))
    taskCheckWhiteList.getOrPut(CHECK_RESEARCH) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, RESEARCH_EFFECT_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_COMPETITION_SCORE_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, GET_ALLIANCE_COMPETITION_QUEST_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_BUY_SHIP_PROP_COUNT) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, MERCHANT_SHIP_BUY_SURPRISE))
    taskCheckWhiteList.getOrPut(CHECK_EFFECT_VALUE) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, BUILDING_UP_FINISH, RESEARCH_EFFECT_CHANGE,GET_UNIT_TASK_REWARD,GET_TASK_REWARD,TALENT_LV_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_CLEAR_TIME_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, CLEAR_TIME_EVENT))
    taskCheckWhiteList.getOrPut(CHECK_CLEAR_TIME_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, CLEAR_TIME_EVENT))

    taskCheckWhiteList.getOrPut(CHECK_KING_LV) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, KING_LV_CHANGE))

    // world服的任务检测条件白名单
    taskCheckWhiteList.getOrPut(CHECK_IS_HAVE_ALLIANCE) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, ALLOW_ALLIANCE))
    taskCheckWhiteList.getOrPut(CHECK_OPEN_GIFT_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, GET_ALLIANCE_GIFT))
    taskCheckWhiteList.getOrPut(CHECK_ALLIANCE_HELP_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, GO_ALLIANCE_HELP))
    taskCheckWhiteList.getOrPut(CHECK_CURE_SOLIDER_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, CURE_SOLDIER))
    taskCheckWhiteList.getOrPut(CHECK_CURE_SOLIDER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, CURE_SOLDIER))
    taskCheckWhiteList.getOrPut(CHECK_MAKE_SOLIDER_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MAKE_SOLDIER_FINISH))
    taskCheckWhiteList.getOrPut(CHECK_MAKE_SOLIDER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MAKE_SOLDIER_FINISH))
    taskCheckWhiteList.getOrPut(CHECK_INSTANCE_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, INSTANCE_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_INSTANCE_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, INSTANCE_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_INSTANCE_STAR_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, INSTANCE_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_INSTANCE_THREE_STAR_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, INSTANCE_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_INSTANCE_FINISH) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, INSTANCE_FIGHT, PVE_FIGHT_WIN))
    taskCheckWhiteList.getOrPut(CHECK_CANGBING_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, CAVE_SOLIDER))
    taskCheckWhiteList.getOrPut(CHECK_GET_PRISON_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, PRISON_AFTER_PVP))
    taskCheckWhiteList.getOrPut(CHECK_KILL_PRISON_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, KILL_PRISON))
    taskCheckWhiteList.getOrPut(CHECK_GET_REWARD_GOLD_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, GET_RESCUE_PRISON_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_SCOUT_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_ATK_PLAYER_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_ATK_PLAYER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, ATK_PLAYER))
    taskCheckWhiteList.getOrPut(CHECK_ATK_WIN_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_KILL_SOLIDER_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_KILL_SOLIDER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, KILL_SOLDIER))
    taskCheckWhiteList.getOrPut(CHECK_DAMAGE_SOLIDER_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_DAMAGE_SOLIDER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, DAMAGE_SOLDIER))
    taskCheckWhiteList.getOrPut(CHECK_MASS_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MASS))
    taskCheckWhiteList.getOrPut(CHECK_MASS_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MASS))
    taskCheckWhiteList.getOrPut(CHECK_JOIN_MASS_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MASS))
    taskCheckWhiteList.getOrPut(CHECK_JOIN_MASS_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MASS))
    taskCheckWhiteList.getOrPut(CHECK_DEFENSE_SUCESS_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_POWER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_POWER_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, POWER_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_ATK_MONSTER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_ATK_MONSTER_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_KILL_MONSTER_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_KILL_MONSTER_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_MONSTER_SCORE_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_FARM_RES_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, FARM_END, FARM_EMPTY, FARM_RES))
    taskCheckWhiteList.getOrPut(CHECK_FARM_RES_NOW_COUNT) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, FARM_END, FARM_EMPTY, FARM_RES))
    taskCheckWhiteList.getOrPut(CHECK_FARM_EMPTY_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, FARM_END, FARM_EMPTY, FARM_RES))
    taskCheckWhiteList.getOrPut(CHECK_ACTIVITY_ADV_REWARD) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, PLAYER_ACTIVITY_ADV_REWARD))
    taskCheckWhiteList.getOrPut(CHECK_ACTIVITY_RANK_REWARD) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, PLAYER_ACTIVITY_RANK))
    taskCheckWhiteList.getOrPut(CHECK_MAKE_TRAP_ALL_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MAKE_TRAP_FINISH))
    taskCheckWhiteList.getOrPut(CHECK_MAKE_TRAP_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, MAKE_TRAP_FINISH))
    taskCheckWhiteList.getOrPut(CKECK_JJC_WIN_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, JJC_FIGHT_WIN, TARGET_CHANGE, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_JJC_DEF_STAR_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, SET_ARMY_PLAN, TARGET_CHANGE, BOSS_FIGHT))
    taskCheckWhiteList.getOrPut(CHECK_STATION_HERO_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, SET_CITY_DEF_HERO, TARGET_CHANGE))
    taskCheckWhiteList.getOrPut(CHECK_CHALLENGE_SCORE_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, SHOW_NEAR_MAP, ACTIVITY_SCORE_OVER))
    taskCheckWhiteList.getOrPut(CHECK_OCCUPY_WONDER) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, OCCUPY_WONDER))
    taskCheckWhiteList.getOrPut(CHECK_HOLD_COUNTRY_POS) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))

    taskCheckWhiteList.getOrPut(CHECK_CLEAR_FOG) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, CLEAR_FOG_EVENT))
    taskCheckWhiteList.getOrPut(CHECK_OPEN_WORLD_MAP) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP))
    taskCheckWhiteList.getOrPut(CHECK_POWER_ADD_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, POWER_ADD))
    taskCheckWhiteList.getOrPut(CHECK_TRANSPORT_RES_NOW_NUM) { mutableListOf() }.addAll(mutableListOf(ENTER_GAME, TARGET_CHANGE, SHOW_NEAR_MAP, TRANSPORT_RES_SUCCESS))
}

/**
 * 任务完成条件类型
 * 1-500的任务类型为home服检测条件类型
 * 501-1000的任务类型为world服检测类型
 * 新加检测检测一定不能乱加哦 错误就会导致任务永远检测不到
 */

// home服的任务检测条件 1-500
const val CHECK_WORLD_TALK_NUM: Int = 1 // 世界发言	类型:次数
const val CHECK_ALLIANCE_TALK_NUM: Int = 2 // 联盟发言	类型:次数
const val CHECK_ADD_FRIEND_NUM: Int = 3 // 添加一个好友	类型:次数
const val CHECK_NORMAL_LOTTERY_ALL: Int = 4 // 低级抽卡X次	类型:次数  历史
const val CHECK_NORMAL_LOTTERY: Int = 5 // 低级抽卡X次	类型:次数
const val CHECK_SUPER_LOTTERY_ALL: Int = 6 // 高级抽卡X次	类型:次数  历史
const val CHECK_SUPER_LOTTERY: Int = 7 // 高级抽卡X次	类型:次数
const val CHECK_HAVE_HERO_NUM: Int = 8 // 获得X个英雄	类型:等级:数量
const val CHECK_HAVE_AWAKE_HERO_NUM: Int = 9 // 数量+阶级	类型:阶数:数量
const val CHECK_HAVE_STAR_HERO_NUM: Int = 10 // 数量+星级	类型:星数:数量
const val CHECK_HERO_EQUIP_LV_UP_NUM_ALL: Int = 11 // 进阶装备次数	类型:次数  历史
const val CHECK_HERO_EQUIP_LV_UP_NUM: Int = 12 // 进阶装备次数	类型:次数
const val CHECK_HERO_SKILL_STRONG_ALL: Int = 13 // 强化英雄技能次数	类型:次数  历史
const val CHECK_HERO_SKILL_STRONG: Int = 14 // 强化英雄技能次数	类型:次数
const val CHECK_HERO_SKILL_NUM: Int = 15 // 技能等级+数量	类型:技能等级:数量
const val CHECK_BUILD_TYPE_LV: Int = 16 // 建筑ID+等级	类型:建筑类型:等级
const val CHECK_BUILD_TYPE_LV_NUM: Int = 18 // 同时拥有建筑ID+等级+数量	类型:建筑类型:等级:数量
const val CHECK_BUILD_RES_SPEED: Int = 19 // 资源产出速度	类型:资源类型:数值（每小时）
const val CHECK_ALL_GET_RES_NUM: Int = 20 // 资源累计获得	类型:资源类型:数值
const val CHECK_SKIN_NUM: Int = 21 // 皮肤数量	类型:数值
const val CHECK_SKIN_STRONG_NUM: Int = 22 // 皮肤强化次数	类型:数值
const val CHECK_GET_ONE_SKIN: Int = 23 // 获得指定主城皮肤	类型:皮肤ID
const val CHECK_SKIN_LV_NUM: Int = 24 // 等级+数量	类型:皮肤等级:数量
const val CHECK_MAKE_KING_EQUIP_NUM: Int = 25 // 锻造装备数量	类型:数值
const val CHECK_HAVE_KING_EQUIP_Q: Int = 27 // 穿戴品质数量的装备	类型:品质:数量
const val CHECK_MAKE_EQUIP: Int = 28 // 锻造指定品质的装备	类型:品质:数值
const val CHECK_COMM_CARD_NUM: Int = 29 // 合成不同品质卡片次数	类型:品质:数值
const val CHECK_ON_CARD_NUM: Int = 30 // 镶嵌不同品质卡片次数	类型:品质:数值
const val CHECK_NORMAL_MOVE_CITY_NUM: Int = 31 // 指定迁城次数	类型:数值
const val CHECK_RANK_MOVE_CITY_NUM: Int = 32 // 随机迁城次数	类型:数值
const val CHECK_JJC_FIGHT_NUM: Int = 33     // JJC挑战次数	类型:数值
const val CHECK_JJC_FIGHT_NUM_ALL: Int = 34 // JJC挑战总次数	类型:数值
const val CHECK_JJC_TO_RANK: Int = 35 // JJC达到排名	类型:数值
const val CHECK_JJC_SHOP_BUY_NUM: Int = 37 // JJC购买竞技场商店购买次数	类型:数值
const val CHECK_JJC_SHOP_REFRESH_NUM: Int = 38 // JJC竞技场商店刷新次数	类型:数值
const val CHECK_BUY_SUPRISE_NUM: Int = 39 // 购买惊喜奖励次数	类型:数值
const val CHECK_BUY_SUPRISE_NUM_ALL: Int = 40 // 购买惊喜奖励次数	类型:数值
const val CHECK_BANK_NUM: Int = 41 // 投资次数	类型+投资类型+次数    投资类型（1:加速投资，2：钻石投资，0：任意）
const val CHECK_BANK_MONEY: Int = 42 // 投资收益	类型+投资类型+数值    投资类型（1:加速投资，2：钻石投资）
const val CHECK_LIBRARY_NUM: Int = 43 // 达成图书馆次数	类型:达成次数
const val CHECK_PHOTO_NUM: Int = 44 // 激活人物头像数量	类型:数量
const val CHECK_DIAMOND_SHOP_BUY_NUM: Int = 45 // 购买钻石商店次数	类型:数量
const val CHECK_ALLIANCE_SHOP_BUY_NUM: Int = 46 // 购买联盟商店次数	类型:数量
const val CHECK_ACTIVITY_SHOP_BUY_NUM: Int = 47 // 购买挑战商店次数	类型:数量
const val CHECK_GET_ONLINE_GIFT_NUM: Int = 48 // 领取在线奖励次数	类型:数值
const val CHECK_GET_ONLINE_BIG_GIFT_NUM: Int = 49 // 领取在线奖励大奖次数	类型:数值
const val CHECK_DIAMOND_COST_NUM: Int = 50 // 消耗钻石数量	类型:数值
const val CHECK_ALLIANCE_COIN__COST_NUM: Int = 51 // 消耗联盟币数量	类型:数值
const val CHECK_VIP_LV: Int = 52 // 达成VIP等级    类型:数值
const val CHECK_ALLIANCE_HELP_NUM: Int = 53 // 帮助    类型:次数
const val CHECK_UNIT_TASK_FINISH: Int = 54 // 达成章节任务	类型:章节ID
const val CHECK_UNIT_TASK_NUM: Int = 55 // 完成章节任务次数	类型:数量
const val CHECK_MAIN_TASK_NUM: Int = 56 // 完成主线任务次数	类型:数量
const val CHECK_MAIN_TASK_FINISH: Int = 57 // 完成指定的主线任务	类型:任务ID
const val CHECK_ALLIANCE_COMPETITION_SCORE: Int = 58 //个人任务积分	类型:数值	历史记录
const val CHECK_ALLIANCE_COMPETITION_TASK_NUM: Int = 59 // 完成任务次数	类型:次数	历史记录
const val CHECK_ALLIANCE_COMPETITION_REWARD: Int = 60 // 联盟阶段	类型:阶段	历史记录	3选1那个的次数
const val CHECK_ALLIANCE_COMPETITION_RANK_LV: Int = 61 // 联盟联赛	类型:阶段值	历史记录	段位
const val CHECK_ALLIANCE_COMPETITION_RANK: Int = 62 // 类型:排行值	历史记录	排名
const val CHECK_CASINO_BOSS_NUM: Int = 63 // 赌场遇见boss   类型:赌场类型_次数 (赌场类型：1 普通，2 精英)
const val CHECK_CASINO_NUM: Int = 64 // 赌场抽奖    类型:赌场类型_次数
const val CHECK_CASINO_KILL_BOSS_NUM: Int = 65 // 赌场杀死boss    类型:赌场类型_次数
const val CHECK_RESEARCH: Int = 66 //科技研发 类型：科技ID
const val CHECK_COMPETITION_SCORE_NUM: Int = 67 //总动员积分 类型：联盟总动员积分数
const val CHECK_EFFECT_VALUE: Int = 69 //某科技效果值达到X 类型：效果ID:万分比数值
const val CHECK_KING_LV: Int = 70 //君主等级 类型：君主等级
const val CHECK_BUY_SHIP_PROP_COUNT: Int = 71 //检测商船购买道具次数 类型：次数
const val CHECK_CLEAR_TIME_NOW_NUM: Int = 72 // 接到任务后 累计使用加速
const val CHECK_CLEAR_TIME_NUM: Int = 73 // 总累计使用加速
const val CHECK_BEFORE_BANK_NUM: Int = 74 // 银行次数（取消也算）类型+投资类型+次数    投资类型（1:加速投资，2：钻石投资，0：任意）
const val LAST_HOME_TASK_CHECK = 500 // 最后一个home服的任务检测条件,占位

// WORLD服的任务检测条件 501-1000
const val CHECK_IS_HAVE_ALLIANCE: Int = 501 // 加入	类型:是否，1表示加入联盟，0表示无需求
const val CHECK_OPEN_GIFT_NUM: Int = 502 // 开启礼物次数    类型:次数
const val CHECK_ALLIANCE_HELP_ALL_NUM: Int = 504 // 帮助    类型:次数   历史
const val CHECK_CURE_SOLIDER_ALL_NUM: Int = 505 // 治疗数量    类型:数量   历史
const val CHECK_CURE_SOLIDER_NUM: Int = 506 // 治疗数量    类型:数量
const val CHECK_MAKE_SOLIDER_ALL_NUM: Int = 509 // 类型+等级+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量  历史
const val CHECK_MAKE_SOLIDER_NUM: Int = 510 // 类型+等级+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量
const val CHECK_INSTANCE_NUM: Int = 511 // 副本推图次数    类型:数值
const val CHECK_INSTANCE_ALL_NUM: Int = 512 // 副本推图次数    类型:数值  历史
const val CHECK_INSTANCE_STAR_NUM: Int = 513 // 副本累计获得星数    类型:数量
const val CHECK_INSTANCE_THREE_STAR_NUM: Int = 514 // 完成3星通关的数量    类型:数量
const val CHECK_INSTANCE_FINISH: Int = 515 // 指定副本的已获得星数    类型:关卡id:星数
const val CHECK_CANGBING_NUM: Int = 516 // 藏兵次数    类型:数值
const val CHECK_GET_PRISON_NUM: Int = 517 // 抓捕领主次数（可刷）    类型:数值
const val CHECK_KILL_PRISON_NUM: Int = 518 // 处决领主次数    类型:数值
const val CHECK_GET_REWARD_GOLD_NUM: Int = 519 // 累计获得赏金数量    类型:数值
const val CHECK_SCOUT_ALL_NUM: Int = 520 //侦查 类型:次数 历史
const val CHECK_ATK_PLAYER_ALL_NUM: Int = 521 //攻击 类型:次数 历史
const val CHECK_ATK_PLAYER_NUM: Int = 522 //攻击 类型:次数
const val CHECK_ATK_WIN_ALL_NUM: Int = 523 //攻击胜利 类型:次数 历史
const val CHECK_KILL_SOLIDER_ALL_NUM: Int = 524 // 类型+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量  历史
const val CHECK_KILL_SOLIDER_NUM: Int = 525 // 类型+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量
const val CHECK_DAMAGE_SOLIDER_ALL_NUM: Int = 526 // 类型+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量  历史
const val CHECK_DAMAGE_SOLIDER_NUM: Int = 527 // 类型+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量
const val CHECK_MASS_NUM: Int = 528 //集结 类型:次数 历史
const val CHECK_MASS_NOW_NUM: Int = 529 //集结 类型:次数
const val CHECK_JOIN_MASS_NUM: Int = 530 //集结 类型:次数 历史
const val CHECK_JOIN_MASS_NOW_NUM: Int = 531 //集结 类型:次数
const val CHECK_DEFENSE_SUCESS_NUM: Int = 532 //防守成功 类型:次数
const val CHECK_POWER_NUM: Int = 533 //实力 类型:数值 历史
const val CHECK_POWER_NOW_NUM: Int = 534 //实力 类型:数值
const val CHECK_ATK_MONSTER_NUM: Int = 535 //攻击魔物 类型:次数 历史
const val CHECK_ATK_MONSTER_NOW_NUM: Int = 536 //攻击魔物 类型:次数
const val CHECK_KILL_MONSTER_NUM: Int = 537 //击杀魔物 类型:次数 历史
const val CHECK_KILL_MONSTER_NOW_NUM: Int = 538 //击杀魔物 类型:次数
const val CHECK_MONSTER_SCORE_NUM: Int = 539 //魔物讨伐点数 类型:次数 历史
const val CHECK_FARM_RES_COUNT: Int = 540 //采集资源 类型:次数 历史
const val CHECK_FARM_RES_NOW_COUNT: Int = 541 //采集资源 类型:次数
const val CHECK_FARM_EMPTY_NUM: Int = 542 //采空资源 类型:次数
const val CHECK_ACTIVITY_ADV_REWARD: Int = 543 // 完成活动阶段	类型:数值:次数	挑战活动
const val CHECK_ACTIVITY_RANK_REWARD: Int = 544 // 完成活动排名	类型:数值	挑战活动
const val CHECK_MAKE_TRAP_ALL_NUM: Int = 545 // 类型+等级+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量  历史
const val CHECK_MAKE_TRAP_NUM: Int = 546 // 类型+等级+数量    类型:兵种类型(0表示为任意):兵种等级(0表示为任意):数量
const val CHECK_STATION_HERO_NOW_NUM: Int = 547 //驻守城墙  类型:英雄数量
const val CHECK_OCCUPY_WONDER: Int = 548 //联盟占领奇观/大奇观 类型:奇观类型:数量
const val CHECK_HOLD_COUNTRY_POS: Int = 549 //担任王国职位 类型：王国职位Type 历史
const val CKECK_JJC_WIN_NUM: Int = 551 //竞技场胜利 类型：次数
const val CHECK_JJC_DEF_STAR_NUM: Int = 552 //竞技场防守星象 类型：数量
const val CHECK_CLEAR_FOG: Int = 553 //战役迷雾通关 类型:关卡ID 历史
const val CHECK_OPEN_WORLD_MAP: Int = 554 //前往王国地图 类型:1
const val CHECK_FARM_RES_NUM: Int = 555 //采集资源 类型：资源类型(0表示任意)：数量 历史
const val CHECK_TRANSPORT_RES_NOW_NUM: Int = 561 //成功运输资源次数 类型：次数
const val CHECK_POWER_ADD_NOW_NUM: Int = 562 //实力提升 类型：类型(0表示任意，1表示建筑，2表示科研，3表示训练【士兵+陷进】):数值
const val CHECK_CHALLENGE_SCORE_NUM: Int = 563 //挑战活动获得积分 类型：挑战类型：积分获得 历史

//获得新任务条件类型.
const val GET_NEW_QUEST_FOR_FINISH_QUEST: Int = 1 //完成某些任务
const val GET_NEW_QUEST_FOR_BUILDINGLV: Int = 2 //建筑达到某些等级的起点ID
const val GET_NEW_QUEST_FOR_POWER: Int = 3 //势力值达到X
const val GET_NEW_QUEST_FOR_UNIT_TASK: Int = 5 //当前章节完成到X

//个人任务状态
const val TaskgoAlong: Int = 0 //任务进行中
const val TaskHasFinish: Int = 1 //任务已完成但是奖励未领取
const val TaskHasGetReward: Int = 2 //已领取奖励

//个人成就状态
const val AchievegoAlong: Int = 1 //任务进行中
const val AchieveHasFinish: Int = 0 //任务已完成但是奖励未领取
const val AchieveHasGetReward: Int = 2 //已领取奖励

// 任务类型
const val TaskNorMal = 1 // 主线任务
const val TaskChapter = 2 // 章节任务
const val TaskAllianceCompetition = 3 // 联盟总动员任务
