package com.point18.slg2d.common.constg

// 魔物
const val COMMON_BOSS_TYPE: Int = 1
const val ACTIVITY_BOSS_TYPE: Int = 2

// 活动魔物地域状态
const val BOSS_DISAPPEAR: Int = 0    // 未出现
const val BOSS_APPEAR: Int = 1       // 出现
const val BOSS_WAIT_REFRESH: Int = 2         // 刷新冷却

// 活动魔物预告邮件
const val NOT_SEND_ADVANCE_MAIL = 0
const val SEND_ADVANCE_MAIL = 1

//魔物刷新类型
const val PERMANENT_BOSS = 1 //常驻魔物
const val CYCLE_BOSS = 2 //周期魔物

//刷新间隔
const val REFRESH_INTERVAL = 1000