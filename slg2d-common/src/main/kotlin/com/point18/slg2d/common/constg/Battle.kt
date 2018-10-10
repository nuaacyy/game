package com.point18.slg2d.common.constg

const val ATK_SIDE: Int = 0  //进攻
const val DEF_SIDE: Int = 1   //防守

//战斗统计数据分类
const val AtkDeadHeroNum: Int = 1    //攻击方死亡数量
const val AtkTotalBeDamage: Int = 2  //攻击方受伤害总量
const val AtkLaunchedSkillNum: Int = 3 //攻击方发动的技能数量
const val AtkAsignHeroInBattle: Int = 4 //攻击方上阵指定英雄
const val WinBattle: Int = 5 //战斗胜利
const val AtkHpBeforeFight: Int = 10 //攻击方战斗后总血量
const val AtkHpAfterFight: Int = 11 //攻击方战斗后总血量
const val DefHpBeforeFight: Int = 20 //防守方战斗后总血量
const val DefHpAfterFight: Int = 21 //防守方战斗后总血量

//战斗临时所需数据
const val CostEnergy: Int = 100 //扣除的行动力
const val ChangeRank: Int = 101 //胜利更改的排名
const val DefRank: Int = 102 //竞技场防守方排行