package com.point18.slg2d.common.constg

// 新的地块的元素类型
const val CELL_RESOURCE: Int = 1            // 普通资源点
const val CELL_MONSTER: Int = 2             // 普通魔物
const val CELL_RELIC: Int = 3               // 遗迹
const val CELL_CASTLE: Int = 4              // 玩家主城
const val CELL_WONDER: Int = 5              // 奇观
const val CELL_EMPTY: Int = 6               // 空地,可能有玩家占领部队
const val CELL_WORLD_BOSS: Int = 8          // 世界boss
const val CELL_BLOCK: Int = 10              // 阻挡
const val CELL_CALL_BOSS: Int = 11          // 召唤boss
const val CELL_BOSS_DEAD_RESOURCE: Int = 12 // 尸体资源地
const val CELL_ACTIVITY_BOSS: Int = 13      // 活动boss
const val CELL_BELONG: Int = 14             // 属地

const val NORMAL_CELL: Int = 0  //普通地块
const val WONDER_BASE: Int = 1  // 奇观底座
const val WONDER_MID: Int = 2   // 奇观中心禁地
const val WONDER_BLACK: Int = 3 // 奇观黑土地
const val WONDER_AREA: Int = 4  // 奇观最外层
const val SNOW_BASE: Int = 5    // 雪地底座
const val SNOW_MID: Int = 6     // 雪地中心禁地
const val SNOW_AREA: Int = 7    // 雪地