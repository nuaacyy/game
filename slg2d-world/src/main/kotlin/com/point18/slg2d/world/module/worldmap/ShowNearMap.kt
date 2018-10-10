package com.point18.slg2d.world.module.worldmap

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.MapMgr
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.constg.SHOW_NEAR_MAP
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.constg.isValidXY
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.getEveryLandInfoByXy
import com.point18.slg2d.world.common.walkWrap2WalkRobot
import com.point18.slg2d.world.event.ShowNearMap
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.world.wpm
import pb4client.NewShowNearMap
import pb4client.NewShowNearMapRt
import pb4client.RemoveXY
import java.util.*
import kotlin.collections.HashMap

class ShowNearMapDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val showNearMsg = msg as NewShowNearMap
        val x = showNearMsg.x
        val y = showNearMsg.y
        val isForce = intToBool(showNearMsg.isForce)

        val rtBuilder = this.showNearMap(cache, channelActor, x, y, isForce, playerId)
        val scMsg = ScMessageAtSend(MsgType.NewShowNearMap_110, cache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun showNearMap(
        areaCache: AreaCache,
        channelActor: ActorRef,
        x: Int,
        y: Int,
        isForce: Boolean,
        playerId: Long
    ): NewShowNearMapRt.Builder {
        val nearMapResp = NewShowNearMapRt.newBuilder()
        nearMapResp.rt = ResultCode.SUCCESS.code

        if (!isValidXY(x, y)) {
            nearMapResp.rt = ResultCode.PARAMETER_ERROR.code
            return nearMapResp
        }

        // 变更观察坐标
        val calGridPosRst = MapMgr.getScreenGrid(x, y)
        val diffGridPositions = updateWatchList(areaCache, channelActor, calGridPosRst.first, calGridPosRst.second)
        if (diffGridPositions == null && !isForce) {
            // 相同观察坐标，所以提示客户端没有任何变更
            return nearMapResp
        }

        val walkMap = hashMapOf<Long, Walk>()

        val walkList = areaCache.walkCache.findWalksByGridXY(calGridPosRst.first, calGridPosRst.second)
        if (walkList != null) {
            for (walk in walkList) {
                walkMap[walk.id] = walk
            }
        }

        if (isForce) {
            // 获取目标位置附近9大格的地块信息
            for (i in -1..1) {
                for (j in -1..1) {
                    val posList = MapMgr.getLogicPosListByGrid(calGridPosRst.first + i, calGridPosRst.second + j)
                    if (posList == null) {
                        continue
                    }
                    for (pos in posList) {
                        val nowX = pos.first
                        val nowY = pos.second
                        //以该点为起始点的行军线
                        val gotoWalks = areaCache.walkCache.findWalksByGotoXy(nowX, nowY)
                        for (walk in gotoWalks) {
                            walkMap[walk.id] = walk
                        }

                        val fromWalks = areaCache.walkCache.findWalksByFromXy(nowX, nowY)
                        for (walk in fromWalks) {
                            walkMap[walk.id] = walk
                        }

                        val landInfo = getEveryLandInfoByXy(areaCache, nowX, nowY) ?: continue
                        nearMapResp.addLands(landInfo)
                    }
                }
            }

        } else if (diffGridPositions != null) {
            // - 差量更新
            for (gridPos in diffGridPositions.addGrids) {
                val posList = MapMgr.getLogicPosListByGrid(gridPos.first, gridPos.second)
                if (posList == null) {
                    continue
                }
                for (pos in posList) {
                    val nowX = pos.first
                    val nowY = pos.second
                    //以该点为起始点的行军线
                    val gotoWalks = areaCache.walkCache.findWalksByGotoXy(nowX, nowY)
                    for (walk in gotoWalks) {
                        walkMap[walk.id] = walk
                    }

                    val fromWalks = areaCache.walkCache.findWalksByFromXy(nowX, nowY)
                    for (walk in fromWalks) {
                        walkMap[walk.id] = walk
                    }

                    val landInfo = getEveryLandInfoByXy(areaCache, nowX, nowY) ?: continue
                    nearMapResp.addLands(landInfo)
                }
            }
            for (gridPos in diffGridPositions.delGrids) {
                val removeBuilder = RemoveXY.newBuilder()
                removeBuilder.x = gridPos.first
                removeBuilder.y = gridPos.second
                nearMapResp.addRemoveXY(removeBuilder)
            }
        }

        for ((_, walk) in walkMap) {
            val robot = walkWrap2WalkRobot(areaCache, walk) ?: continue
            nearMapResp.addWalkRobots(robot)
        }

        // 触发刷新地图的事件
        wpm.es.fire(areaCache, playerId, SHOW_NEAR_MAP, ShowNearMap())

        return nearMapResp
    }

    data class DiffGrids(
        var addGrids: LinkedList<Pair<Int, Int>>, // 新增格点
        var delGrids: LinkedList<Pair<Int, Int>> // 移除格点
    )

    // 更新观察列表
    // 返回值表示是否是相同的观察坐标
    // 如果不相同，那么返回差异格子集
    private fun updateWatchList(
        areaCache: AreaCache,
        watcher: ActorRef,
        newGridX: Int,
        newGridY: Int
    ): DiffGrids? {
        // 变更观察坐标
        var originGrid = areaCache.mapCellWatcherCache.findCenterWatchPos(watcher)
        if (originGrid == null) {
            //创建新的观察者
            areaCache.worldActor.context.watch(watcher)
            originGrid = Pair(-1, -1)
            areaCache.mapCellWatcherCache.setCenterWatchPos(watcher, -1, -1)
        }

        val originGridX = originGrid.first
        val originGridY = originGrid.second

        val noWatchGrid = originGridX == -1 && originGridY == -1

        if (originGridX == newGridX && originGridY == newGridY) {
            return null
        }

        // 计算Grid坐标差异
        val oldGridPosMap = hashMapOf<Int, HashMap<Int, Int>>()

        if (!noWatchGrid) {
            // 从老的网格观察列表中移除
            for (gridX in originGridX - 1..originGridX + 1) {
                if (gridX < 0) {
                    continue
                }

                for (gridY in originGridY - 1..originGridY + 1) {
                    if (gridY < 0) {
                        continue
                    }

                    // 存入老坐标map表
                    oldGridPosMap.getOrPut(gridX) { hashMapOf() }[gridY] = 1

                    // 从观察队列中移除
                    areaCache.mapCellWatcherCache.removeFromMapCellWatch(watcher, gridX, gridY)
                }
            }
        }

        // 添加到新的网格观察列表
        val newGridPosMap = hashMapOf<Int, HashMap<Int, Int>>()
        for (gridX in newGridX - 1..newGridX + 1) {
            if (gridX < 0) {
                continue
            }

            for (gridY in newGridY - 1..newGridY + 1) {
                if (gridY < 0) {
                    continue
                }

                // 存入新坐标map表
                newGridPosMap.getOrPut(gridX) { hashMapOf() }[gridY] = 1

                // 添加到观察队列中
                areaCache.mapCellWatcherCache.add2MapCellWatch(watcher, gridX, gridY)
            }
        }

        // 新坐标和老坐标差异缓存
        val diffGrids = DiffGrids(LinkedList(), LinkedList())

        // 生成新增的格子名单
        for ((x, yMap) in newGridPosMap) {
            for ((y, _) in yMap) {
                if (oldGridPosMap[x]?.get(y) != null) {
                    continue
                }
                diffGrids.addGrids.add(Pair(x, y))
            }
        }

        // 生成移除的格子名单
        for ((x, yMap) in oldGridPosMap) {
            for ((y, _) in yMap) {
                if (newGridPosMap[x]?.get(y) != null) {
                    continue
                }
                diffGrids.delGrids.add(Pair(x, y))
            }
        }

        // 修改观察中心坐标
        areaCache.mapCellWatcherCache.setCenterWatchPos(watcher, newGridX, newGridY)

        return diffGrids
    }
}

