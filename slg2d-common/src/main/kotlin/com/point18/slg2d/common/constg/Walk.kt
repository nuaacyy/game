package com.point18.slg2d.common.constg

// 新版本的出征状态
const val WalkHiding: Int = 0 // 藏兵
const val WalkCommonBoss: Int = 1  // 打普通魔物
const val WalkJoinMass: Int = 2  // 加入集结（不包括遗迹）
const val WalkFightPlayer: Int = 3  // 攻打玩家
const val WalkFarm: Int = 4  // 采集:只要采集点还在,如果是友军,回来,如果是敌军,战斗,如果资源点已经被刷了,就回来
const val WalkTransport: Int = 5  // 运输:目标点只能是友军城池,如果达到之后发现不是友军,就回来了
const val WalkMainHeroGoHome: Int = 6  // 领主释放 -- PS : 有个秒到的规则
const val WalkScout: Int = 7  // 侦查 --> 目标点有主并且不是我帮  -->如果不满足了就回来,侦查失败
const val WalkGoHome: Int = 8  // 回城
const val WalkOccupyCell: Int = 9  // 占领土地
const val WalkOccupyWonder: Int = 10 // 占领奇观
const val WalkStation: Int = 11 // 驻扎
const val WalkReinforce: Int = 12 // 增援
const val WalkRelic: Int = 13 // 遗迹
const val WalkReinforceWonder: Int = 17 // 增援奇观
const val WalkCallBoss: Int = 18 //打个人召唤boss
const val WalkChangeOccupyWonder: Int = 19 //指挥官换防
const val WalkActivityBoss: Int = 20 //打活动boss
const val WalkJoinRelicMass: Int = 21 //加入攻打遗迹的集结
const val WalkWalkDrill: Int = 100 // 行军演习 走过去走回来 什么都不干 目标地是什么都可以

// 资源来源
const val Plunder: Int = 1 //掠夺
const val Farm: Int = 2 //采集
const val Transfer: Int = 3 //运输

// 行军线所属种类
const val SelfWalkGroup: Int = 1 //自身行军组
const val WarnWalkGroup: Int = 2 //预警线

// 行军组状态
const val Running: Int = 1              //行军中
const val Stationed: Int = 2            //驻扎中
const val Reinforce: Int = 4            //增援中(奇观驻扎中)
const val Farming: Int = 8              //采集中
const val MassWaiting: Int = 16         //集结等待中
const val Hiding: Int = 32              //藏兵中
const val WaitFight: Int = 64           //等待战斗

const val ADD_WALKROBOTINFO = 1 // 新增行军线
const val REMOVE_WALKROBOTINFO = 2 // 删除行军线

//行军检测项
const val CheckHaveHero: Int = 1         //检测必须有英雄
const val CheckNoHero: Int = 2         //检测必须无英雄
const val CheckHaveSolider: Int = 4         //检测必须有满足条件的士兵
const val CheckNoSolider: Int = 8         //检测必须无士兵
const val CheckNoCoverBuff: Int = 32         //检测无防护罩
const val CheckSameAlliance: Int = 64         //检测必须同联盟
const val CheckNotSameAlliance: Int = 128         //检测必须非同盟

//行军地块类型
const val RUN_SPEED_LIMIT_NORMAL = 1
const val RUN_SPEED_LIMIT_SNOW = 2      // 雪地行军减速
