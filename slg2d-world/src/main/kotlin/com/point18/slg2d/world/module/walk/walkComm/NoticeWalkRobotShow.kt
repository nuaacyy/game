package com.point18.slg2d.world.module.walk.walkComm

import akka.actor.ActorRef
import com.point18.slg2d.common.commonfunc.MapMgr
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.walkWrap2WalkRobot
import com.point18.slg2d.world.msgnotice.createWalkRobotShowNotifier
import java.util.*

// 推送多点行军线变化
fun notice2MultiPlayerWalkRobotShow(
    areaCache: AreaCache,
    walkWrap: Walk,
    posMap: HashMap<Int, Int>,
    showType: Int
) {
    val walkRobot = walkWrap2WalkRobot(areaCache, walkWrap) ?: return

    // 收集起点和终点附近的所有玩家
    val playerIdMap = hashMapOf<ActorRef, Int>()
    for ((x, y) in posMap) {
        val gridPos = MapMgr.getScreenGrid(x, y)
        val watcherMap = areaCache.mapCellWatcherCache.findWatcherByXy(gridPos.first, gridPos.second)
        watcherMap?.forEach { playerIdMap[it.key] = 1 }
    }

    // 给每个玩家推送
    val walkRobotShowNotifier = createWalkRobotShowNotifier(showType, walkRobot)
    playerIdMap.forEach {
        walkRobotShowNotifier.notice(areaCache, it.key)
    }
}