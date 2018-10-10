package com.point18.slg2d.common.constg

const val Add: Int = 1
const val Del: Int = 2
const val Update: Int = 3

const val GRID_DIFF_PARAM: Int = 10000

const val MAX_MAP_SIZE = 512 //地图大小

//判断坐标点是否是有效坐标点
fun isValidXY(x: Int, y: Int): Boolean {
    if (x in 0..(MAX_MAP_SIZE - 1) && y in 0..(MAX_MAP_SIZE - 1)) {
        return true
    }
    return false
}


// castle的类型
const val CASTLE_MAIN: Int = 100 // 主城

const val CASTLE_FORTRESS: Int = 106 // 玩家要塞
const val CASTLE_GROUND: Int = 109 // 地基

const val CASTLE_NPC_FORTRESS: Int = 301 //玩家可以占领的野外要塞
const val CASTLE_NPC_MILITARYCAMP: Int = 302 //玩家可以占领的野外军营

const val CASTLE_LOW_BATTERY: Int = 305 //玩家可以占领的低级炮塔
const val CASTLE_HIGH_BATTERY: Int = 306 //玩家可以占领的高级炮塔

const val CASTLE_ALLIANCE_WALL: Int = 501 // 帮派建筑-城墙
const val CASTLE_ALLIANCE_MAGIC: Int = 502 // 帮派建筑-魔法阵
const val CASTLE_ALLIANCE_FLAG: Int = 503 // 帮派建筑-战旗
