package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.common.constg.MAX_MAP_SIZE
import com.point18.slg2d.common.pc.pcs

// 检测是否有玩家城存在在一格内
fun checkFreeCellWithCastle(areaCache: AreaCache, x: Int, y: Int): (Boolean) {
    // 过滤边缘
    if (x == MAX_MAP_SIZE || y == MAX_MAP_SIZE) {
        return false
    }

    return true
}

// 检测是否有玩家城存在在一格内
fun checkFreeCellWithCastleForGm(areaCache: AreaCache, x: Int, y: Int): (Boolean) {
    // 过滤边缘
    if (x == MAX_MAP_SIZE || y == MAX_MAP_SIZE) {
        return false
    }

    return true
}

fun checkMoveCastleFreeCell(areaCache: AreaCache, playerId: Long, x: Int, y: Int): (Boolean) {
    val castle = areaCache.castleCache.findCastleByXy(x, y)
    if (castle != null) {
        if (castle.playerId != playerId) {
            return false
        }
        if (castle.x == x && castle.y == y) {
            return false
        }
    }

    // 过滤边缘
    if (x == MAX_MAP_SIZE || y == MAX_MAP_SIZE) {
        return false
    }

    return true
}

// 寻找可用的城池位置
data class FindFreeCastlePosReturnXy(var x: Int, var y: Int)

fun findFreeCastlePos(areaCache: AreaCache): FindFreeCastlePosReturnXy {
    val bc = pcs.basicProtoCache
    var x = 0
    var y = 0

    // 总人数的判断
    if (areaCache.playerCache.findAllPlayerNum() >= bc.peopleNum) {
        return FindFreeCastlePosReturnXy(-1, -1)
    }

    // 数据规整
    for (i in 0..10000) {
        val mapAreaOnly = areaCache.areaOnlyCache.findAreaOnly()
        val maxNum = mapAreaOnly.nowCreatePlayerCircleNum * bc.bornUp + bc.bornFrist

        val xyMap = getFarmInfoByGridId(mapAreaOnly.nowCreatePlayerOnArea, bc.resourceArea) // 获取到这个小方块里所有的XY
        var canFind = false
        for ((_, info) in xyMap) {
            if (!checkFreeCellWithCastle(areaCache, info.x, info.y)) {
                continue
            }

            val normalCell = checkNormalCell(info.x, info.y)
            if (!normalCell) {
                continue
            }

            val freeCell = checkFreeCell(areaCache, info.x, info.y, 0)
            if (freeCell) {
                canFind = true
                break
            }
        }

        var playerNum = 0
        if (!canFind) {
            // 如果找不到落脚点也不需要做这个了
            for ((_, info) in xyMap) {
                // 过滤玩家城
                val castle = areaCache.castleCache.findCastleByXy(info.x, info.y)
                if (castle != null) {
                    playerNum++
                }
            }
        }

        if (playerNum >= maxNum || !canFind) {
            mapAreaOnly.nowCreatePlayerOnArea = areaCache.playerCreateMap[mapAreaOnly.nowCreatePlayerOnArea] ?: 0

            if (mapAreaOnly.nowCreatePlayerOnArea == 0) {
                mapAreaOnly.nowCreatePlayerCircleNum = mapAreaOnly.nowCreatePlayerCircleNum + 1
            }
        } else {
            break
        }
    }

    // 重新获取
    val mapAreaOnly = areaCache.areaOnlyCache.findAreaOnly()

    val xyMap = getFarmInfoByGridId(mapAreaOnly.nowCreatePlayerOnArea, bc.resourceArea) // 获取到这个小方块里所有的XY

    // 选取坐标
    var forNum = 1 // 保险丝
    while (true) {
        if (forNum >= 50000) {
            break
        }

        forNum += 1
        val rand = getRandInt(xyMap.size)
        val luckCell = xyMap[rand] // 被选中的地 检测下这个坐标的周边4格是否都安全

        if (luckCell == null) {
            break
        }

        if (!checkFreeCellWithCastle(areaCache, luckCell.x, luckCell.y)) {
            continue
        }

        val normalCell = checkNormalCell(luckCell.x, luckCell.y)
        if (!normalCell) {
            continue
        }

        val freeCell = checkFreeCell(areaCache, luckCell.x, luckCell.y, 0)
        if (freeCell) {
            x = luckCell.x
            y = luckCell.y
            break
        }
    }

    if (x == 0 && y == 0) {
        return FindFreeCastlePosReturnXy(-1, -1)
    }

    // 更新数据
    val everySmallMapPlayerNumInfo = mapAreaOnly.everySmallMapPlayerNumMap[mapAreaOnly.nowCreatePlayerOnArea]
    if (everySmallMapPlayerNumInfo != null) {
        everySmallMapPlayerNumInfo.playerNum += 1

        putEverySmallMapPlayerNumMap(mapAreaOnly, mapAreaOnly.nowCreatePlayerOnArea, everySmallMapPlayerNumInfo)
    } else {
        putEverySmallMapPlayerNumMap(mapAreaOnly, mapAreaOnly.nowCreatePlayerOnArea, MapAreaPlayerInfo(1))
    }

    //ziyuandaiLv = getZiyuandaiLvByXY(x, y)
    //xx = Int(float64(x) / float64(200))
    //yy = Int(float64(y) / float64(200))
    //fmt.Printf("===新玩家被创建,坐标为:%v,%v,隶属资源带等级为:%v,隶属的生态格子ID为:%v\n", x, y, ziyuandaiLv, (yy*6 + (xx + 1)))
    return FindFreeCastlePosReturnXy(x, y)
}

// 寻找可用的城池位置(GM用的 写死了一个生态)
data class FindFreeCastlePosForGmReturnXy(var x: Int, var y: Int)

fun findFreeCastlePosForGm(areaCache: AreaCache, shengtaiId: Int): (FindFreeCastlePosForGmReturnXy) {
    val bc = pcs.basicProtoCache
    var x = 0
    var y = 0

    // 总人数的判断
    if (areaCache.playerCache.findAllPlayerNum() >= bc.peopleNum) {
        println("人数爆炸:${areaCache.playerCache.findAllPlayerNum()},${bc.peopleNum}")
        return FindFreeCastlePosForGmReturnXy(-1, -1)
    }

    // 数据规整
    val xyMap = getFarmInfoByGridId(shengtaiId, bc.resourceArea) // 获取到这个小方块里所有的XY
    var canFind = false
    for ((_, info) in xyMap) {
        if (!checkFreeCellWithCastleForGm(areaCache, info.x, info.y)) {
            continue
        }

        val normalCell = checkNormalCell(info.x, info.y)
        if (!normalCell) {
            continue
        }

        val freeCell = checkFreeCell(areaCache, info.x, info.y, 0)
        if (freeCell) {
            canFind = true
            x = info.x
            y = info.y
            break
        }
    }

    if (!canFind) {
        return FindFreeCastlePosForGmReturnXy(-1, -1)
    }

    return FindFreeCastlePosForGmReturnXy(x, y)
}
