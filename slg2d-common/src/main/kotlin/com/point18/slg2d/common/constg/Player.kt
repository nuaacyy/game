package com.point18.slg2d.common.constg

//玩家属性变化类型:主要是用于推送玩家属性变化的区分
const val PlayerChangeStorageLimit = 1 //仓库上限
const val PlayerChangeFameLimit = 2 //声望上限
const val PlayerChangeFame = 3 //声望值
const val PlayerBuildQu = 4 //建筑队列数
const val PlayerChangeState = 5 //所属国家
const val PlayerChangePower = 6 //势力值

//同步的玩家属性
const val PlayerName = 1
const val AllianceNickName = 2
const val PlayerFame = 3
const val PhotoId = 4
const val ResearchEffectInfo = 8
const val VipLv = 9
const val VipEffectInfo = 10
const val TalentEffectInfo = 11
const val BuildingEffectInfo = 12
const val CoinNum = 13 //金币数量
const val FoodNum = 14 //粮食数量
const val WoodNum = 15 //木材数量
const val StoneNum = 16 //石头数量
const val IronNum = 17 //铁矿数量
const val AllianceCoinNum = 18 //联盟币数量
const val CoinYield = 19
const val FoodYield = 20
const val WoodYield = 21
const val StoneYield = 22
const val IronYield = 23
const val CalYieldTime = 24
const val MainHeroId = 25

const val HeroPhoto: String = "1," //英雄头像
const val SelfDefinePhoto: String = "2," //自定义头像

// 首次登录标记
const val FIRST_ENTER_GAME = 0 // 首次进入游戏初始化还未走
const val OLD_PLAYER = 1 // 已经是老玩家~
