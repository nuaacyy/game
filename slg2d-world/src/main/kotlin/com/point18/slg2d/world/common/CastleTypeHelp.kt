package com.point18.slg2d.world.common

import com.point18.slg2d.common.constg.*
import java.util.*

// 是否是主城
fun isMainCastle(castleType: Int): (Boolean) {
    if (castleType == CASTLE_MAIN) {
        return true
    }
    return false
}

// 是否是要塞
fun isPlayerFortress(castleType: Int): (Boolean) {
    if (castleType == CASTLE_FORTRESS) {
        return true
    }
    return false
}

// 是否是地基
fun isGround(castleType: Int): (Boolean) {
    if (castleType == CASTLE_GROUND) {
        return true
    }
    return false
}

// 是否是玩家占领的野外要塞
fun isNpcFortressByPlayer(castleType: Int): (Boolean) {
    if (castleType == CASTLE_NPC_FORTRESS) {
        return true
    }
    return false
}

// 是否是玩家占领的低级炮塔
fun isLowBatteryByPlayer(castleType: Int): (Boolean) {
    if (castleType == CASTLE_LOW_BATTERY) {
        return true
    }
    return false
}

// 是否是玩家占领的高级炮塔
fun isHighBatteryByPlayer(castleType: Int): (Boolean) {
    if (castleType == CASTLE_HIGH_BATTERY) {
        return true
    }
    return false
}

// 是否是帮派城墙
fun isAllianceWall(castleType: Int): (Boolean) {
    if (castleType == CASTLE_ALLIANCE_WALL) {
        return true
    }
    return false
}

// 是否是帮派魔法阵
fun isAllianceMagic(castleType: Int): (Boolean) {
    if (castleType == CASTLE_ALLIANCE_MAGIC) {
        return true
    }
    return false
}

// 是否是帮派战旗
fun isAllianceFlag(castleType: Int): (Boolean) {
    if (castleType == CASTLE_ALLIANCE_FLAG) {
        return true
    }
    return false
}

// 是否是玩家占领的野外军营
fun isNpcMilitaryCampByPlayer(castleType: Int): (Boolean) {
    if (castleType == CASTLE_NPC_MILITARYCAMP) {
        return true
    }
    return false
}

// 城池的检测配型
typealias CCkT = Int // CastleCheckType

const val CK_MAIN_CITY: CCkT = 1
const val CK_FORTRESS: CCkT = 4
const val CK_GROUND: CCkT = 8
const val CK_NPC_FORTRESS: CCkT = 16
const val CK_NPC_CAMP: CCkT = 32
const val CK_ALLIANCE_WALL: CCkT = 64
const val CK_ALLIANCE_MAGIC: CCkT = 128
const val CK_ALLIANCE_FLAG: CCkT = 256
const val CK_LOW_BATTERY: CCkT = 512
const val CK_HIGH_BATTERY: CCkT = 1024

typealias castleCheckHandle = (Int) -> Boolean

var castleCheckTable: HashMap<CCkT, castleCheckHandle> = hashMapOf()

fun init() {
    castleCheckTable = hashMapOf()
    castleCheckTable[CK_MAIN_CITY] = ::isMainCastle
    castleCheckTable[CK_FORTRESS] = ::isPlayerFortress
    castleCheckTable[CK_GROUND] = ::isGround
    castleCheckTable[CK_NPC_FORTRESS] = ::isNpcFortressByPlayer
    castleCheckTable[CK_NPC_CAMP] = ::isNpcMilitaryCampByPlayer
    castleCheckTable[CK_ALLIANCE_WALL] = ::isAllianceWall
    castleCheckTable[CK_ALLIANCE_MAGIC] = ::isAllianceMagic
    castleCheckTable[CK_ALLIANCE_FLAG] = ::isAllianceFlag
    castleCheckTable[CK_LOW_BATTERY] = ::isLowBatteryByPlayer
    castleCheckTable[CK_HIGH_BATTERY] = ::isHighBatteryByPlayer
}

