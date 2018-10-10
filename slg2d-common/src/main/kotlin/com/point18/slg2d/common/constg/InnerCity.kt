package com.point18.slg2d.common.constg

const val BORN_HAVE: Int = 1 // 出生就有的且已解锁
const val BORN_LOCK: Int = 2 // 出生就有但锁住的
const val BORN_NEED: Int = 3 // 需要建造

const val BUILDING_INNER_CITY: Int = 1 // 新建建筑
const val DELETE_INNER_CITY: Int = 2   // 拆除建筑
const val CHANGE_INNER_CITY: Int = 3   // 改变建筑

const val UNLOCK_BY_BUILDING_LV = 1 // 根据建筑等级解锁类型
const val UNLOCK_BY_KING_LV = 2 // 根据领主等级解锁类型

const val MAX_INNER_CITY_QUEUE_COUNT = 1 // 最大建筑队列

const val DESTROY_INNER_CITY_NOW = 1    // 立即拆除建筑
const val DESTROY_INNER_CITY_NORMAL = 2 // 正常拆除
