package com.point18.slg2d.world.module.walk

import akka.actor.ActorRef
import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.MapMgr
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.walkWrap2WalkRobot
import com.point18.slg2d.world.msgnotice.createWalkRobotUpdateNotifier
import pb4client.WalkRobot
import java.util.*

class HeartUpdateWalkRobot : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        cache.walkCache.clearNowAllWalks()
        val changeList = LinkedList<Walk>()
        val allWalk = cache.walkCache.findAllWalk()
        for (walk in allWalk) {
            val isChange = updateWalkRobot(cache, walk)

            if (isChange) {
                changeList.add(walk)
            }
        }

        val noticeMap = hashMapOf<ActorRef, LinkedList<WalkRobot.Builder>>()
        for (change in changeList) {
            val newGridX = change.nowWalkRobotX
            val newGridY = change.nowWalkRobotY
            val watcherMap = cache.mapCellWatcherCache.findWatcherByXy(newGridX, newGridY) ?: continue

            val walkRobot = walkWrap2WalkRobot(cache, change) ?: continue

            // 推地块
            for ((watcher, _) in watcherMap) {
                val robots = noticeMap.getOrPut(watcher) { LinkedList() }
                robots.add(walkRobot)
            }
        }

        for ((watcher, walkRobots) in noticeMap) {

            val walkRobotUpdateNotifier = createWalkRobotUpdateNotifier()

            for (walkRobot in walkRobots) {
                walkRobotUpdateNotifier.append(walkRobot)
            }

            walkRobotUpdateNotifier.notice(cache, watcher)
        }
    }

    private fun updateWalkRobot(areaCache: AreaCache, walk: Walk): Boolean {
        val nowPos = areaCache.walkCache.calCurrentPos(walk)

        val gridPos = MapMgr.getScreenGrid(nowPos.posX.toInt(), nowPos.posY.toInt())

        var isChange = false

        if (gridPos.first != walk.nowWalkRobotX || gridPos.second != walk.nowWalkRobotY) {
            walk.nowWalkRobotX = gridPos.first
            walk.nowWalkRobotY = gridPos.second

            isChange = true
        }

        // 计算部队真实坐标
        areaCache.walkCache.addWalkByGridXY(gridPos.first, gridPos.second, walk)

        return isChange
    }
}


