package com.point18.slg2d.common.constg

import java.util.*

// 世界服同步来自home服的数据的差异类型
const val UPDATE_INFO_BY_HOME_SKIN = 1 // 修改皮肤模版
const val UPDATE_INFO_BY_HOME_CASTLE_LV = 2 // 修改城池等级
const val UPDATE_INFO_BY_HOME_HERO = 3  //修改英雄数据
const val UPDATE_INFO_BY_HOME_TARGET = 4 //修改目标
const val UPDATE_INFO_BY_HOME_ICON = 5  //修改头像
const val UPDATE_INFO_BY_HOME_BUILD_INFO = 6  // 建筑信息变化
const val UPDATE_INFO_BY_HOME_EFFECT = 7    //效果变化
const val UPDATE_INFO_BY_HOME_VIP_LV = 8    //vip等级
const val UPDATE_INFO_BY_HOME_PLAYER_NAME = 9    // 玩家名
const val UPDATE_INFO_BY_HOME_PLAYER_NICK_NAME = 10    // 玩家昵称
const val UPDATE_INFO_BY_HOME_PLAYER_KING_LV = 11    // 君主等级
const val UPDATE_INFO_BY_HOME_PLAYER_KING_EXP = 12    // 君主经验
const val UPDATE_INFO_BY_HOME_UNITTASK = 13    // 章节任务最新章变化
const val UPDATE_INFO_BY_TASK_FINISH = 14    // 主线任务领取了某个奖励

data class UpdateInfoByHomeBuildInfoVo(var buildType: Int, var buildLv: LinkedList<Int>)