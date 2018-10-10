package com.point18.slg2d.world.module.heroBattle.fightLogic

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.Pos
import com.point18.slg2d.common.pc.pcs

//计算距离
fun calDistance(entityA: Entity, entityB: Entity): Double {
    return Math.sqrt(calDistanceSquare(entityA, entityB))
}

//计算X轴距离
fun calXDistance(entityA: Entity, entityB: Entity): Double {
    return Math.abs(
        entityA.getFloatProperty(
            PosX,
            false
        ) - entityB.getFloatProperty(PosX, false)
    )
}

//计算Y轴距离
fun calYDistance(entityA: Entity, entityB: Entity): Double {
    return Math.abs(
        entityA.getFloatProperty(
            PosY,
            false
        ) - entityB.getFloatProperty(PosY, false)
    )
}

//计算sqrt
fun calSqrt(a: Double, b: Double): Double {
    return Math.sqrt(a * a + b * b)
}

//计算距离平方
fun calDistanceSquare(entityA: Entity, entityB: Entity): Double {
    val posAX = entityA.getFloatProperty(PosX, false)
    val posAY = entityA.getFloatProperty(PosY, false)
    val posBX = entityB.getFloatProperty(PosX, false)
    val posBY = entityB.getFloatProperty(PosY, false)
    return (posAX - posBX) * (posAX - posBX) + (posAY - posBY) * (posAY - posBY)
}

//计算距离
fun calDistance(aX: Int, aY: Int, bX: Int, bY: Int): Double {
    val xAbs = Math.abs(aX - bX)
    val yAbs = Math.abs(aY - bY)
    return 1.4 * Math.min(xAbs, yAbs) + Math.abs(xAbs - yAbs)
}

data class CalAttackHurt(
    var isDodge: Boolean,
    var isCrit: Boolean,
    var hurt: Int
)

//计算普攻伤害
fun calAttackHurt(atkEntity: Entity, defEntity: Entity): CalAttackHurt {
    //判断闪避
    if (getRandInt(10000) < defEntity.getFloatProperty(ARR_DODGE) / pcs.basicProtoCache.dodgePara * 10000) {
        return CalAttackHurt(true, false, 0)
    }

    //计算基础伤害
    val hurt = calNormalHurt(atkEntity, defEntity) * calBuffAddition(atkEntity, defEntity)

    //判定暴击
    val rst = calHurtCrit(atkEntity, hurt)
    var finalHurt = Math.ceil(rst.hurt).toInt()
    if (finalHurt < 1) {
        //0伤害特殊处理
        finalHurt = 1
    }
    return CalAttackHurt(false, rst.isCrit, finalHurt)
}

//计算普攻基础伤害
fun calNormalHurt(atkEntity: Entity, defEntity: Entity): Double {
    //计算基础伤害
    val atkType = atkEntity.intPropertyMap[AtkType] ?: 0
    var atkGongji = atkEntity.getFloatProperty(ARR_WUGONG)
    var defFangyu = defEntity.getFloatProperty(ARR_WUFANG)
    if (atkType == FashuXing) {
        atkGongji = atkEntity.getFloatProperty(ARR_FAGONG)
        defFangyu = defEntity.getFloatProperty(ARR_FAFANG)
    }
    //破防系数
    var noBrokeDefPara = 1f
    if (atkGongji < defFangyu) {
        noBrokeDefPara = pcs.basicProtoCache.noBrokenAttackPara
    }
    //攻击浮动
    val atkFloat =
        (10000 - pcs.basicProtoCache.attackFloat + getRandInt(pcs.basicProtoCache.attackFloat * 2)) / 10000f
    //减伤
    val hurtReduce = 1f - pcs.basicProtoCache.maxAttackReduce * defFangyu /
            (defFangyu + (defEntity.intPropertyMap[Lv]
                ?: 0) * pcs.basicProtoCache.lvDefencePara + pcs.basicProtoCache.defencePara)
    return pcs.basicProtoCache.attackPara * atkGongji * hurtReduce * atkFloat * noBrokeDefPara
}

//计算buff加成
fun calBuffAddition(atkEntity: Entity, defEntity: Entity): Double {
    val atkType = atkEntity.intPropertyMap[AtkType]
    var addType = SKILL_WUGONGJIASHANG
    var reduceType = SKILL_WUFANGJIANSHANG
    if (atkType == FashuXing) {
        addType = SKILL_FAGONGJIASHANG
        reduceType = SKILL_FAFANGJIANSHANG
    }
    var add = 0.0
    for ((_, buffList) in atkEntity.buffMap) {
        for (buff in buffList) {
            if (buff.skillEffProto.skillType != addType) {
                continue
            }
            add += buff.skillEffProto.skillEffBasePoint
        }
    }
    var reduce = 0.0
    for ((_, buffList) in defEntity.buffMap) {
        for (buff in buffList) {
            if (buff.skillEffProto.skillType != reduceType) {
                continue
            }
            reduce += buff.skillEffProto.skillEffBasePoint
        }
    }
    return (1 + add / 10000) * (1 - reduce / 10000)
}

data class CalHurtCritResult(
    var isCrit: Boolean,
    var hurt: Double
)

//计算伤害暴击
fun calHurtCrit(atkEntity: Entity, hurt: Double): CalHurtCritResult {
    //判定暴击
    if (getRandInt(10000) < atkEntity.getFloatProperty(ARR_BAOJI) / pcs.basicProtoCache.critPara * 10000) {
        return CalHurtCritResult(
            true,
            hurt * (atkEntity.getFloatProperty(ARR_BAOJILV) / 10000 + 1f)
        )
    }
    return CalHurtCritResult(false, hurt)
}

//检测是否在线型区域内
fun checkInLine(
    fromX: Double,
    fromY: Double,
    targetX: Double,
    targetY: Double,
    radius: Double,
    lengthType: Int,
    length: Double,
    testX: Double,
    testY: Double
): Boolean {
    if (pointToSegDist(testX, testY, fromX, fromY, targetX, targetY) > radius) {
        return false
    }
    //斜率
    val k = -1 / ((targetY - fromY) / (targetX - fromX))
    var b1 = fromY - k * fromX
    var b2 = targetY - k * targetX
    when (lengthType) {
        2 -> {
            val tempB2 = b2
            if (b1 > b2) {
                b2 -= length * Math.sqrt(k * k + 1.0).toFloat()
            } else {
                b2 += length * Math.sqrt(k * k + 1.0).toFloat()
            }
            b1 = tempB2
        }
        3 -> {
            if (b1 > b2) {
                b2 -= length * Math.sqrt(k * k + 1.0).toFloat()
            } else {
                b2 += length * Math.sqrt(k * k + 1.0).toFloat()
            }
        }
    }

    if ((k * testX + b1) > testY && (k * testX + b2) > testY) {
        return false
    }
    if ((k * testX + b1) < testY && (k * testX + b2) < testY) {
        return false
    }
    return true
}

//点到线的最短距离
fun pointToSegDist(x: Double, y: Double, x1: Double, y1: Double, x2: Double, y2: Double): Double {
    val cross = (x2 - x1) * (x - x1) + (y2 - y1) * (y - y1)
    if (cross <= 0) {
        return calSqrt(x - x1, y - y1)
    }
    val d2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)
    if (cross >= d2) {
        return calSqrt(x - x2, y - y2)
    }
    val r = cross / d2
    val px = x1 + (x2 - x1) * r
    val py = y1 + (y2 - y1) * r
    return calSqrt(x - px, py - y1)
}

const val radianToAngle = 180 / Math.PI

//检测是否在扇形区域内
//参考《面试题：检测点是否在扇形之内》，地址：https://www.cnblogs.com/miloyip/archive/2013/04/19/3029852.html
fun checkInFanShaped(
    fromX: Double,
    fromY: Double,
    targetX: Double,
    targetY: Double,
    radius: Double,
    angle: Double,
    testX: Double,
    testY: Double
): Boolean {
    //判断距离的平方
    var dx = testX - fromX
    var dy = testY - fromY
    val squareLength = dx * dx + dy * dy
    if (squareLength > radius * radius) {
        return false
    }

    val length = Math.sqrt(squareLength).toFloat()

    dx /= length
    dy /= length
    //弧度
    val radian = Math.acos(dx * (targetX - fromX) + dy * (targetY - fromY))
    //弧度变角度，角度=180/PI*弧度
    val testAngle = radianToAngle * radian
    return testAngle < angle
}

//以EntityA为移动起点计算新坐标
fun calMovedPos1(entityA: Entity, entityB: Entity, moveRange: Double): Pos {
    val startPosX = entityA.getFloatProperty(PosX, false)
    val startPosY = entityA.getFloatProperty(PosY, false)
    val targetPosX = entityB.getFloatProperty(PosX, false)
    val targetPosY = entityB.getFloatProperty(PosY, false)
    val distance = calDistance(entityA, entityB)
    var moveRate = 0.0
    if (distance > 0) {
        moveRate = moveRange / distance
    }
    var toPosX = startPosX + (targetPosX - startPosX) * moveRate
    var toPosY = startPosY + (targetPosY - startPosY) * moveRate

    if (toPosX < 0) {
        val newRate = Math.abs(startPosX / (startPosX - toPosX))
        toPosX = startPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = startPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    if (toPosX > pcs.basicProtoCache.mapGridX) {
        val newRate = Math.abs((startPosX - pcs.basicProtoCache.mapGridX) / (startPosX - toPosX))
        toPosX = startPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = startPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    if (toPosY < 0) {
        val newRate = Math.abs(startPosY / (startPosY - toPosY))
        toPosX = startPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = startPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    if (toPosY > pcs.basicProtoCache.mapGridY) {
        val newRate = Math.abs((startPosY - pcs.basicProtoCache.mapGridY) / (startPosY - toPosY))
        toPosX = startPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = startPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    return Pos(toPosX, toPosY)
}

//以EntityB为移动起点计算新坐标
fun calMovedPos2(entityA: Entity, entityB: Entity, moveRange: Double): Pos {
    val startPosX = entityA.getFloatProperty(PosX, false)
    val startPosY = entityA.getFloatProperty(PosY, false)
    val targetPosX = entityB.getFloatProperty(PosX, false)
    val targetPosY = entityB.getFloatProperty(PosY, false)

    val distance = calSqrt(startPosX - targetPosX, startPosY - targetPosY)
    var moveRate = 0.0
    if (distance > 0) {
        moveRate = moveRange / distance
    }
    var toPosX = targetPosX + (targetPosX - startPosX) * moveRate
    var toPosY = targetPosY + (targetPosY - startPosY) * moveRate

    if (toPosX < 0) {
        val newRate = Math.abs(targetPosX / (targetPosX - toPosX))
        toPosX = targetPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = targetPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    if (toPosX > pcs.basicProtoCache.mapGridX) {
        val newRate = Math.abs((targetPosX - pcs.basicProtoCache.mapGridX) / (targetPosX - toPosX))
        toPosX = targetPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = targetPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    if (toPosY < 0) {
        val newRate = Math.abs(targetPosY / (targetPosY - toPosY))
        toPosX = targetPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = targetPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    if (toPosY > pcs.basicProtoCache.mapGridY) {
        val newRate = Math.abs((targetPosY - pcs.basicProtoCache.mapGridY) / (targetPosY - toPosY))
        toPosX = targetPosX + (targetPosX - startPosX) * moveRate * newRate
        toPosY = targetPosY + (targetPosY - startPosY) * moveRate * newRate
    }
    return Pos(toPosX, toPosY)
}

//判断是否在边界内
fun inBorder(x: Int, y: Int): Boolean {
    if (x < 0 || x > pcs.basicProtoCache.mapGridX) {
        return false
    }
    if (y < 0 || y > pcs.basicProtoCache.mapGridY) {
        return false
    }
    return true
}

//查看是否在背后
fun inBack(x: Int, y: Int, tx: Int, ty: Int, checkX: Int, checkY: Int): Boolean {
    //法向式直线方程 a(x-x0)+b(y-y0)=0
    val a = x - tx
    val b = y - ty
    if (b == 0) {
        return if (a > 0) {
            checkX > x
        } else {
            checkX < x
        }
    }
    val v = a * (checkX - x) / -b + y
    return checkY > v
}