package com.point18.slg2d.common.constg

//战报类别
const val FIGHT_PLAYER_REPORT: Int = 1  //与玩家战斗战报
const val ATK_MONSTER_REPORT: Int = 2  //与魔物战斗战报
const val FIGHT_RELIC_REPORT: Int = 4  //与遗迹战斗战报
const val SCOUT_REPORT: Int = 6  //侦查战报
const val BE_SCOUT_REPORT: Int = 7  //被侦查战报
const val FARM_REPORT: Int = 8  //采集战报
const val TRANSPORT_REPORT: Int = 9  //运输战报
const val ATK_ALLIANCE_MONSTER_REPORT: Int = 10 //与联盟魔物战斗战报
const val ATK_WORLD_MONSTER_REPORT: Int = 11 //与世界魔物战斗战报
const val FIGHT_GROUP_REPORT: Int = 14 //和行军组战斗的战报
const val HUNTER_CALL_REPORT: Int = 15 //召唤礼战报
const val JJC_FIGHT_REPORT: Int = 16 //竞技场战报
const val STATION_DEF_REPORT: Int = 17//驻守部队防守战报

//战报状态
const val UnRead: Int = 0
const val HasRead: Int = 1

const val ScoutHaveSolider = 1          //侦查有士兵
const val ScoutHaveSoliderHospital = 2  //侦查有士兵医院
const val ScoutHaveTrap = 4             //侦查有陷阱
const val ScoutHaveTrapHospital = 8     //侦查有陷阱医院
const val ScoutHaveReinforce = 16       //侦查有增援
const val ScoutHaveStation = 32         //侦查有驻扎
const val ScoutHaveMass = 64            //侦查有集结
const val ScoutHaveHide = 128           //侦查有藏兵