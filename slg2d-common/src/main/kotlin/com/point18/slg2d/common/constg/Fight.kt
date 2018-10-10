package com.point18.slg2d.common.constg

//战斗结果

const val FIGHT_RESULT_ING: Int = 0 //战斗还未结束
const val FIGHT_RESULT_LOSE: Int = 1 //战斗失败
const val FIGHT_RESULT_WIN: Int = 2 //战斗胜利

//查看战报类型

const val FIGHT_INFO_MINE: Int = 1 //个人战报
const val FIGHT_INFO_JJC: Int = 2 //竞技场战报

//pve战斗类型

const val PVE_FIGHT_COMMON_BOSS_ACTION: Int = 5  //打普通魔物
const val PVE_FIGHT_RELIC_ACTION: Int = 12 //打遗迹
const val PVE_FIGHT_STRONGHOLD_ACTION: Int = 15 //打据点
const val PVE_FIGHT_CALL_BOSS_ACTION: Int = 16  //打召唤BOSS
const val PVE_FIGHT_INSTANCE_ACTION: Int = 17 //推图
const val PVE_FIGHT_FOG_ACTION: Int = 18 //迷雾
const val PVE_FIGHT_ACTIVITY_BOSS_ACTION: Int = 19 //打活动BOSS

//pvp战斗类型

const val PVP_FIGHT_PLAYER_ACTION: Int = 1 //打其他玩家
const val PVP_FIGHT_GROUP_ACTION: Int = 2 //打行军组
const val PVP_FIGHT_JJC_ACTION: Int = 3//竞技场

//战斗类型

const val BattleZhanshi: Int = 1  //战士
const val BattleSheshou: Int = 2  //射手
const val BattleQishi: Int = 3  //骑士
const val BattleTank: Int = 4  //战车
const val BattleWall: Int = 10 //城墙
const val BattleTrap1: Int = 5  //陷阱1
const val BattleTrap2: Int = 6  //陷阱2
const val BattleTrap3: Int = 7  //陷阱3
const val BattleHero: Int = 20 //英雄
const val BattleSolider: Int = 30 //士兵-待删除

const val CureSolider = 1 // 治疗的是士兵
const val CureTrap = 2 // 治疗的是陷阱

const val NormalCure = 0 //普通治疗
const val EventCure = 1 //活动治疗

fun isSolider(battleType: Int): Boolean {
    return battleType == BattleQishi || battleType == BattleSheshou ||
        battleType == BattleZhanshi || battleType == BattleTank
}

fun isTrap(battleType: Int): Boolean {
    return battleType == BattleTrap1 || battleType == BattleTrap2 || battleType == BattleTrap3
}

const val HeroBattle: Int = 1  //英雄战
const val SoliderBattle: Int = 2               //士兵战
const val AllBattle = HeroBattle or SoliderBattle //英雄战+士兵战
