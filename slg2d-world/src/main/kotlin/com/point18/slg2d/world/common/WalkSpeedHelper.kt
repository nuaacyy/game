package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import java.util.*

data class CalWalkTimeRst(
    val walkTime: Int,
    val walkSpeed: Double
)

// 计算行军时间
fun calWalkTime(
    areaCache: AreaCache,
    playerId: Long,
    runType: Int,
    soliderMap: HashMap<Int, Int>,
    startX: Int,
    startY: Int,
    gotoX: Int,
    gotoY: Int,
    isAtkMonsterHome: Boolean = false,
    isMass: Boolean = false,
    isGoHome: Boolean = false
): CalWalkTimeRst {
    if (runType == WalkGoHome) {
        return CalWalkTimeRst(0, 0.0)
    }
    val player = areaCache.playerCache.findPlayerById(playerId)
    if (player == null) {
        normalLog.error("WalkGroupHelper.kt : areaCache.playerCache.findPlayerById(group.mainPlayerId) == null")
        return CalWalkTimeRst(0, 0.0)
    }
    val speed: Double
    var speedAdd =
        getResearchEffectValue(areaCache, NO_FILTER, player, ResearchEffectAllWalkSpeedAdd) + getResearchEffectValue(
            areaCache,
            NO_FILTER,
            player,
            AddWorldWalkSpeed
        )
    var addSec = 0
    if (isAtkMonsterHome) {
        addSec = 5
    }
    when (runType) {
        WalkCommonBoss,
        WalkCallBoss,
        WalkActivityBoss -> {
            speed = pcs.basicProtoCache.baseHeroSpeed
            speedAdd += getResearchEffectValue(areaCache, NO_FILTER, player, MapWalkFastAdd)
        }
        WalkMainHeroGoHome -> {
            speed = pcs.basicProtoCache.baseHeroSpeed
        }
        WalkScout -> speed = pcs.basicProtoCache.baseSpySpeed
        WalkTransport -> {
            speed = pcs.basicProtoCache.baseTransportSpeed
            speedAdd += getResearchEffectValue(areaCache, NO_FILTER, player, TransporSpeedAdd)
        }
        WalkReinforce,
        WalkReinforceWonder -> {
            speed = calWalkSpeed(soliderMap)
            speedAdd += getResearchEffectValue(areaCache, NO_FILTER, player, ReinforceWalkSpeedAdd)
        }
        WalkJoinRelicMass -> {
            speed = calWalkSpeed(soliderMap)
            speedAdd += pcs.basicProtoCache.darkLairSpeed
        }
        else -> {
            speed = calWalkSpeed(soliderMap)
            if (isGoHome && (runType == WalkRelic || runType == WalkJoinRelicMass)) {
                //加入遗迹集结/打遗迹回城
                speedAdd += pcs.basicProtoCache.darkLairSpeed
            }
        }
    }

    if (isMass) {
        speedAdd += (getResearchEffectValue(areaCache, NO_FILTER, player, AddMassWalkSpeed))
    }

    if (speed == 0.0) {
        return CalWalkTimeRst(0, 0.0)
    }

    val startResult = areaCache.walkCache.calCenter(startX, startY)
    val gotoResult = areaCache.walkCache.calCenter(gotoX, gotoY)

    val lines = getRunLine(startResult.posX, startResult.posY, gotoResult.posX, gotoResult.posY)
    var distance = 0.0
    var snowDistance = 0.0
    for (line in lines) {
        if (line.type == RUN_SPEED_LIMIT_NORMAL) {
            distance += line.length
        } else if (line.type == RUN_SPEED_LIMIT_SNOW) {
            snowDistance += line.length
        }
    }

    val snowSpeedReduce = pcs.basicProtoCache.snowSpeed

    val normalSpeed = speed * (10000 + speedAdd) / 10000 / pcs.basicProtoCache.speedPara
    val snowSpeed = normalSpeed * (10000 - snowSpeedReduce) / 10000

    var walkTime = distance / normalSpeed
    walkTime += snowDistance / snowSpeed
    val walkTimeI = Math.ceil(walkTime).toInt()

    var finalSpeed = 0.0
    if (walkTimeI > 0) {
        finalSpeed = (distance + snowDistance) / walkTimeI
    }
    return CalWalkTimeRst(walkTimeI + addSec, finalSpeed)
}

data class CellTypeLine(
    var type: Int,
    var length: Double
)

fun getRunLine(
    fromX: Double,
    fromY: Double,
    targetX: Double,
    targetY: Double
): List<CellTypeLine> {
    val lines = LinkedList<CellTypeLine>()
    var beFind = false
    var targetInSnow = false
    for ((_, proto) in pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap) {
        val xMin = (proto.snowIndex[0]).toDouble()
        val xMax = (proto.snowIndex[0] + proto.snowSizeIndex[0] - 1).toDouble()
        val yMin = (proto.snowIndex[1]).toDouble()
        val yMax = (proto.snowIndex[1] + proto.snowSizeIndex[1] - 1).toDouble()

        // 是否经过该区域
        if (!targetInSnow) {
            targetInSnow = (targetX >= xMin) and (targetX <= xMax) and (targetY >= yMin) and (targetY <= yMax)
        }

        val crossPosList = isInView(fromX, fromY, targetX, targetY, xMin, yMin, xMax, yMax)
        if (crossPosList.isNotEmpty()) {
            if (crossPosList.count() == 1) {
                /** 有一个交点 **/
                val pt1 = crossPosList[0]
                // 雪地外部份
                val distance =
                    Math.sqrt((fromX - pt1.posX) * (fromX - pt1.posX) + (fromY - pt1.posY) * (fromY - pt1.posY))
                lines.add(CellTypeLine(RUN_SPEED_LIMIT_NORMAL, distance))
                // 雪地内部分
                val distance2 =
                    Math.sqrt((targetX - pt1.posX) * (targetX - pt1.posX) + (targetY - pt1.posY) * (targetY - pt1.posY))
                lines.add(CellTypeLine(RUN_SPEED_LIMIT_SNOW, distance2))
            } else if (crossPosList.count() == 2) {
                /** 有两个交点，穿过雪地 **/
                val pt1 = crossPosList[0]
                val pt2 = crossPosList[1]
                // 雪地内部分
                val distance1 =
                    Math.sqrt((pt2.posX - pt1.posX) * (pt2.posX - pt1.posX) + (pt2.posY - pt1.posY) * (pt2.posY - pt1.posY))
                lines.add(CellTypeLine(RUN_SPEED_LIMIT_SNOW, distance1))

                val len1 =
                    Math.sqrt((fromX - pt1.posX) * (fromX - pt1.posX) + (fromY - pt1.posY) * (fromY - pt1.posY))
                val len2 =
                    Math.sqrt((fromX - pt2.posX) * (fromX - pt2.posX) + (fromY - pt2.posY) * (fromY - pt2.posY))

                val distance3 = if (len1 < len2) {
                    lines.add(CellTypeLine(RUN_SPEED_LIMIT_NORMAL, len1))
                    Math.sqrt((targetX - pt2.posX) * (targetX - pt2.posX) + (targetY - pt2.posY) * (targetY - pt2.posY))
                } else {
                    lines.add(CellTypeLine(RUN_SPEED_LIMIT_NORMAL, len2))
                    Math.sqrt((targetX - pt1.posX) * (targetX - pt1.posX) + (targetY - pt1.posY) * (targetY - pt1.posY))
                }
                lines.add(CellTypeLine(RUN_SPEED_LIMIT_NORMAL, distance3))
            }
            beFind = true
        }
    }

    // 未经过雪地
    if (!beFind) {
        val distance =
            Math.sqrt((fromX - targetX) * (fromX - targetX) + (fromY - targetY) * (fromY - targetY))
        if (targetInSnow) {
            // 全程雪地
            lines.add(CellTypeLine(RUN_SPEED_LIMIT_SNOW, distance))
        } else {
            lines.add(CellTypeLine(RUN_SPEED_LIMIT_NORMAL, distance))
        }
    }

    return lines
}

// 判断线段和矩形是否有焦点,返回相交的点
fun isInView(
    linePtX1: Double,
    linePtY1: Double,
    linePtX2: Double,
    linePtY2: Double,
    recLeftTopX: Double,
    recLeftTopY: Double,
    recBottomRightX: Double,
    recBottomRightY: Double
): List<Pos> {
    val vec = LinkedList<Pos>()
    //判断重复的点(两个线的交点)
    val dicHasPt = hashMapOf<String, Int>()
    var key: String

    //情况线和矩形相交
    val p1Start = Pos(linePtX1, linePtY1)
    val p1End = Pos(linePtX2, linePtY2)

    //上矩形边
    val p2Start = Pos(recLeftTopX, recLeftTopY)
    val p2End = Pos(recBottomRightX, recLeftTopY)
    val p2SamePoint = hasSamePoint(p1Start, p1End, p2Start, p2End)
    if (p2SamePoint != null) {
        key = "${p2SamePoint.posX}_${p2SamePoint.posY}"
        if (!dicHasPt.containsKey(key)) {
            vec.add(p2SamePoint)
            dicHasPt[key] = 1
        }
    }

    //下矩形边
    val p3Start = Pos(recLeftTopX, recBottomRightY)
    val p3End = Pos(recBottomRightX, recBottomRightY)
    val p3SamePoint = hasSamePoint(p1Start, p1End, p3Start, p3End)
    if (p3SamePoint != null) {
        key = "${p3SamePoint.posX}_${p3SamePoint.posY}"
        if (!dicHasPt.containsKey(key)) {
            vec.add(p3SamePoint)
            dicHasPt[key] = 1
        }
    }

    //左矩形边
    val p4Start = Pos(recLeftTopX, recLeftTopY)
    val p4End = Pos(recLeftTopX, recBottomRightY)
    val p4SamePoint = hasSamePoint(p1Start, p1End, p4Start, p4End)
    if (p4SamePoint != null) {
        key = "${p4SamePoint.posX}_${p4SamePoint.posY}"
        if (!dicHasPt.containsKey(key)) {
            vec.add(p4SamePoint)
            dicHasPt[key] = 1
        }
    }

    //右矩形边
    val p5Start = Pos(recBottomRightX, recLeftTopY)
    val p5End = Pos(recBottomRightX, recBottomRightY)
    val p5SamePoint = hasSamePoint(p1Start, p1End, p5Start, p5End)
    if (p5SamePoint != null) {
        key = "${p5SamePoint.posX}_${p5SamePoint.posY}"
        if (!dicHasPt.containsKey(key)) {
            vec.add(p5SamePoint)
            dicHasPt[key] = 1
        }
    }
    return vec
}

//判断两条线是否相交,返回相交的点
fun hasSamePoint(
    a: Pos,
    b: Pos,
    c: Pos,
    d: Pos
): Pos? {
    /** 1 解线性方程组, 求线段交点. **/
    // 如果分母为0 则平行或共线, 不相交
    val denominator = (b.posY - a.posY) * (d.posX - c.posX) - (a.posX - b.posX) * (c.posY - d.posY)
    if (denominator == 0.0) {
        return null
    }
    // 线段所在直线的交点坐标 (x , y)
    val x =
        ((b.posX - a.posX) * (d.posX - c.posX) * (c.posY - a.posY) + (b.posY - a.posY) * (d.posX - c.posX) * a.posX -
                (d.posY - c.posY) * (b.posX - a.posX) * c.posX) / denominator
    val y =
        -((b.posY - a.posY) * (d.posY - c.posY) * (c.posX - a.posX) + (b.posX - a.posX) * (d.posY - c.posY) * a.posY -
                (d.posX - c.posX) * (b.posY - a.posY) * c.posY) / denominator

    /** 2 判断交点是否在两条线段上 **/
    val bLine1 = (x - a.posX) * (x - b.posX) <= 0 && (y - a.posY) * (y - b.posY) <= 0 //交点在线段1上
    val bLine2 = (x - c.posX) * (x - d.posX) <= 0 && (y - c.posY) * (y - d.posY) <= 0 //且交点也在线段2上
    if (bLine1 && bLine2) {
        // 返回交点p
        return Pos(x, y)
    }
    //否则不相交
    return null
}

//计算行军速度
fun calWalkSpeed(soliderMap: HashMap<Int, Int>): Double {
    var minSpeed = 0.0
    soliderMap.forEach { a ->
        if (a.value <= 0) {
            return@forEach
        }
        val solider = pcs.soliderCache.soliderProtoMap[a.key]
        if (solider == null) {
            //Assert
            return@forEach
        }
        if (minSpeed == 0.0 || minSpeed > solider.speed) {
            minSpeed = solider.speed
        }
    }
    return minSpeed
}

//计算负重
fun calWeight(areaCache: AreaCache, groupId: Long): Int {
    var totalWeight = 0
    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(groupId)
    for (force in forces) {
        force.soliderMap.forEach { a ->
            val solider = pcs.soliderCache.soliderProtoMap[a.key]
            if (solider == null) {
                //Assert
                return@forEach
            }
            totalWeight += solider.weight * a.value
        }
    }

    val forceGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(groupId)
    if (forceGroup == null) {
        //Assert
        return totalWeight
    }
    val player = areaCache.playerCache.findPlayerById(forceGroup.mainPlayerId)
    if (player == null) {
        //Assert
        return totalWeight
    }
    totalWeight += totalWeight * getResearchEffectValue(areaCache, NO_FILTER, player, FuzhongAdd) / 10000
    return totalWeight
}
