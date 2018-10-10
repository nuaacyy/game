package com.point18.slg2d.world.module.walk.walkComm

import akka.actor.ActorRef
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.*
import java.util.*

// 行军的主体中的单位
data class WalkElement(
    var HeroIds: LinkedList<Long>,
    var SoliderMap: HashMap<Int, Int>
)

// 行军参数信息
data class WalkParam(
    var playerId: Long, // 出发的玩家

    var startX: Int,
    var startY: Int,

    var targetId: Long, // 目标ID，不一定是玩家ID

    var gotoX: Int,
    var gotoY: Int,

    var we: WalkElement
)

fun createWalkOnMap(
    areaCache: AreaCache,
    runType: Int,
    arrivalsTime: Long,
    walkSpeed: Double,
    startX: Int,
    startY: Int,
    gotoX: Int,
    gotoY: Int,
    startTime: Long,
    walkForceGroupId: Long,
    isAtkMonsterHome: Int
): Walk {
    val walkWrap = areaCache.walkCache.createWalk(
        runType,
        arrivalsTime,
        walkSpeed,
        startX,
        startY,
        gotoX,
        gotoY,
        startTime,
        walkForceGroupId,
        isAtkMonsterHome
    )

    val posMap = hashMapOf<Int, Int>()
    posMap[startX] = startY
    posMap[gotoX] = gotoY

    // 通知他们行军演示
    notice2MultiPlayerWalkRobotShow(
        areaCache,
        walkWrap,
        posMap,
        ADD_WALKROBOTINFO
    )

    return walkWrap
}

fun deleteWalkOnMap(areaCache: AreaCache, walk: Walk) {
    val posMap = hashMapOf<Int, Int>()
    posMap[walk.marchPlaceX] = walk.marchPlaceY
    posMap[walk.marchAimsX] = walk.marchAimsY
    notice2MultiPlayerWalkRobotShow(
        areaCache,
        walk,
        posMap,
        REMOVE_WALKROBOTINFO
    )

    areaCache.walkCache.deleteWalk(walk)
}

data class CreateMarchResult(
    var walk: Walk,
    val group: WalkForceGroup
)

//新版添加行军记录
fun createMarch(
    areaCache: AreaCache,
    playerId: Long,
    heroIds: LinkedList<Long>,
    soliderMap: HashMap<Int, Int>,
    gotoX: Int,
    gotoY: Int,
    arrivalsTime: Long,
    runType: Int,
    startX: Int,
    startY: Int,
    walkSpeed: Double,
    resFromInfo: String,
    res: LinkedList<ResVo>
): CreateMarchResult {

    // 生成行军主体
    val walkForceGroup =
        areaCache.walkForceGroupCache.createWalkForceGroup(playerId, 0, Running, runType, startX, startY)

    // 生成行军主体中的部队
    areaCache.walkForceCache.createWalkForce(heroIds, soliderMap, resFromInfo, res, walkForceGroup.id, playerId)

    for ((soliderId, soliderNum) in soliderMap) {
        costSolider(areaCache, playerId, soliderId, soliderNum)
    }

    // 存入行军记录
    val now = getNowTime()
    val walk = createWalkOnMap(
        areaCache,
        runType,
        arrivalsTime,
        walkSpeed,
        startX,
        startY,
        gotoX,
        gotoY,
        now,
        walkForceGroup.id,
        0
    )

    return CreateMarchResult(walk, walkForceGroup)
}
