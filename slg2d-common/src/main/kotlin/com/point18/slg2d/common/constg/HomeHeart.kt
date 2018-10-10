package com.point18.slg2d.common.constg

typealias HomeHeartState = Int

const val Normal: HomeHeartState = 0            //正常
const val TriggerNotReplay: HomeHeartState = 1  //触发，但home服未回复
const val ReplayErrorCode: HomeHeartState = 2   //回复，但数据错误
const val Deleted: HomeHeartState = 3           //已删除

typealias DealHeartAction = Int

const val CreateHeart: DealHeartAction = 1
const val DeleteHeart: DealHeartAction = 2
const val UpdateHeart: DealHeartAction = 3

typealias HomeHeartAction = Int

const val Bank: HomeHeartAction = 1
const val InnerCityDestroy: HomeHeartAction = 2
const val InnerCityUpgrade: HomeHeartAction = 3
const val HeroSuperUp: HomeHeartAction = 4
const val MainHeroPrison: HomeHeartAction = 5
const val Vip: HomeHeartAction = 6
const val ResearchLvUp: HomeHeartAction = 7
const val BankAccelerate: HomeHeartAction = 8