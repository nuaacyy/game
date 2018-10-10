package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.MapCallProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.randomSelect
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.calBossGrid
import com.point18.slg2d.world.area4data.calRelicGrid
import com.point18.slg2d.world.area4data.calResGrid
import com.point18.slg2d.world.msgnotice.UpdateLandBelongNotifier
import pb4client.CellPoint
import xyz.ariane.util.toDefaultEpochMilli
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap

val mapHelper = MapHelper()

class MapHelper {
    //刷新全部大地图
    fun refreshAllMap(areaCache: AreaCache) {
        val bc = pcs.basicProtoCache

        val mapRoundLimit = bc.mapRounds

        //设置下次刷新魔物时间
        areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime = getNowTime() + bc.mapMonsterRefreshTime * 1000

        val mapCallProto = findMapCall(areaCache)
        if (mapCallProto != null) {
            for (round in 1..mapRoundLimit) {
                // 撒资源点
                initMapFarmInfo(areaCache, mapCallProto, -1, round)

                // 初始化普通魔物
                initCommonMonsterInfo(areaCache, mapCallProto, -1, round)

                // 初始化奇观魔物
                initWounderMonsterInfo(areaCache, -1, round)

                // 初始化雪地魔物
                initSnowMonsterInfo(areaCache, -1, round)

                // 初始化遗迹
                initMapRelicInfo(areaCache, mapCallProto, -1, round)
            }
        }
    }

    // 大地图刷新所有元素
    // nowResRefId 刷新生态ID 0-开服刷 非0 表示只刷新这个序号的生态
    fun refreshMap(areaCache: AreaCache, nowRefId: Int) {
        val bc = pcs.basicProtoCache

        var timeRefIsOver = false
        val addCellList = LinkedList<Pair<Int, Int>>()
        val delCellList = LinkedList<Pair<Int, Int>>()
        val mapRoundLimit = bc.mapRounds

        val mapCallProto = findMapCall(areaCache)
        if (mapCallProto != null) {
            var farmRefOver = false
            var relicRefOver = false
            for (round in 1..mapRoundLimit) {
                // 撒资源点
                if (!farmRefOver) {
                    farmRefOver = initMapFarmInfo(areaCache, mapCallProto, nowRefId, round, addCellList, delCellList)
                }

                // 初始化遗迹
                if (!relicRefOver) {
                    relicRefOver = initMapRelicInfo(
                        areaCache,
                        mapCallProto,
                        nowRefId,
                        round,
                        addCellList,
                        delCellList
                    )
                }

                // 返回4个true 就是说明记录的 nowResRefId 已经超过了所有元素的生态数量,表示刷新解释,重置数据
                if (farmRefOver && relicRefOver) {
                    timeRefIsOver = true
                    break
                }
            }
        }

        if (timeRefIsOver) {
            areaCache.mapCellCache.nowResRefId = 0

            val localDate = LocalDate.now()
            val localDateTime = LocalDateTime.of(
                localDate.year, localDate.month, localDate.dayOfMonth,
                bc.timeZone, 0, 0
            )
            areaCache.mapCellCache.nextResRefTime = localDateTime.toDefaultEpochMilli()

        } else {
            areaCache.mapCellCache.nowResRefId += 1
            areaCache.mapCellCache.nextResRefTime += REFRESH_INTERVAL
        }

        noticeCellChange(areaCache, addCellList, delCellList)
    }

    //刷新普通魔物
    fun refreshCommonBoss(areaCache: AreaCache, nowRefId: Int) {
        val bc = pcs.basicProtoCache

        val addCellList = LinkedList<Pair<Int, Int>>()
        val delCellList = LinkedList<Pair<Int, Int>>()
        val mapRoundLimit = bc.mapRounds

        val mapCallProto = findMapCall(areaCache)
        if (mapCallProto == null) {
            return
        }
        var bossRefOver = false
        for (round in 1..mapRoundLimit) {
            // 初始化魔物
            bossRefOver = initCommonMonsterInfo(areaCache, mapCallProto, nowRefId, round, addCellList, delCellList)
            if (bossRefOver) {
                break
            }
        }

        if (nowRefId == 0) {
            //刷新第一块地块时，记录下次刷新时间
            areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime = getNowTime() + bc.mapMonsterRefreshTime * 1000
        }

        if (bossRefOver) {
            //全部刷新完毕，重置刷新标记
            areaCache.mapCellCache.nowCommonBossRefId = 0
            areaCache.mapCellCache.nextCommonBossRefTime = areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime
        } else {
            //刷新标记+1
            areaCache.mapCellCache.nowCommonBossRefId += 1
            areaCache.mapCellCache.nextCommonBossRefTime += REFRESH_INTERVAL
        }

        //地块变化通知
        noticeCellChange(areaCache, addCellList, delCellList)
    }

    //刷新奇观魔物
    fun refreshWonderBoss(areaCache: AreaCache, nowRefIndex: Int) {
        val bc = pcs.basicProtoCache

        val addCellList = LinkedList<Pair<Int, Int>>()
        val delCellList = LinkedList<Pair<Int, Int>>()
        val mapRoundLimit = bc.mapRounds

        var bossRefOver = false
        for (round in 1..mapRoundLimit) {
            // 初始化魔物
            bossRefOver = initWounderMonsterInfo(areaCache, nowRefIndex, round, addCellList, delCellList)
            if (bossRefOver) {
                break
            }
        }

        if (bossRefOver) {
            //全部刷新完毕，重置刷新标记
            areaCache.mapCellCache.nowWonderBossRefIndex = 0
            areaCache.mapCellCache.nextWonderBossRefTime = areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime
        } else {
            //刷新标记+1
            areaCache.mapCellCache.nowWonderBossRefIndex += 1
            areaCache.mapCellCache.nextWonderBossRefTime += REFRESH_INTERVAL
        }

        //地块变化通知
        noticeCellChange(areaCache, addCellList, delCellList)
    }

    //刷新雪地魔物
    fun refreshSnowBoss(areaCache: AreaCache, nowRefId: Int) {
        val bc = pcs.basicProtoCache

        val addCellList = LinkedList<Pair<Int, Int>>()
        val delCellList = LinkedList<Pair<Int, Int>>()
        val mapRoundLimit = bc.mapRounds

        var bossRefOver = false
        for (round in 1..mapRoundLimit) {
            // 初始化魔物
            bossRefOver = initSnowMonsterInfo(areaCache, nowRefId, round, addCellList, delCellList)
            if (bossRefOver) {
                break
            }
        }

        if (bossRefOver) {
            //全部刷新完毕，重置刷新标记
            areaCache.mapCellCache.nowSnowBossRefIndex = 0
            areaCache.mapCellCache.nextSnowBossRefTime = areaCache.areaOnlyCache.findAreaOnly().nextRefBossTime
        } else {
            //刷新标记+1
            areaCache.mapCellCache.nowSnowBossRefIndex += 1
            areaCache.mapCellCache.nextSnowBossRefTime += REFRESH_INTERVAL
        }

        //地块变化通知
        noticeCellChange(areaCache, addCellList, delCellList)
    }

    //通知地块变化
    private fun noticeCellChange(
        areaCache: AreaCache,
        addList: List<Pair<Int, Int>>,
        delList: List<Pair<Int, Int>>
    ) {
        //
        val noticeGridMap = hashMapOf<Long, HashMap<Int, LinkedList<Pair<Int, Int>>>>()
        for (pos in addList) {
            val gridPos = MapMgr.getScreenGrid(pos.first, pos.second)
            val id = doubleInt2Long(gridPos.first, gridPos.second)
            noticeGridMap.getOrPut(id) { hashMapOf() }.getOrPut(Add) { LinkedList() }.add(pos)
        }
        for (pos in delList) {
            val gridPos = MapMgr.getScreenGrid(pos.first, pos.second)
            val id = doubleInt2Long(gridPos.first, gridPos.second)
            noticeGridMap.getOrPut(id) { hashMapOf() }.getOrPut(Del) { LinkedList() }.add(pos)
        }
        for ((id, gridMap) in noticeGridMap) {
            val (gridX, gridY) = long2DoubleInt(id)
            val watcherMap = areaCache.mapCellWatcherCache.findWatcherByXy(gridX, gridY) ?: continue
            val updateLandBelongNotifier = UpdateLandBelongNotifier(null, null)

            for ((addOrDel, posList) in gridMap) {
                if (addOrDel == Add) {
                    for ((x, y) in posList) {
                        getEveryLandInfoByXy(areaCache, x, y, Pair(gridX, gridY))?.let {
                            updateLandBelongNotifier.addUpdateLand(it)
                        }
                    }
                } else {
                    for ((x, y) in posList) {
                        val cellPointBuilder = CellPoint.newBuilder()
                        cellPointBuilder.x = x
                        cellPointBuilder.y = y
                        updateLandBelongNotifier.addDelLand(cellPointBuilder)
                    }
                }
            }
            for ((watcher, _) in watcherMap) {
                updateLandBelongNotifier.notice(areaCache, watcher)
            }
        }
    }

    data class MoreRandReturn<T>(
        var randList: LinkedList<T>,
        var remainingList: LinkedList<T>
    )

    private fun <T> getMoreRands(randSeed: Random, xys: LinkedList<T>, num: Int): MoreRandReturn<T> {
        val rs = LinkedList<T>()

        val xyLen = xys.size
        if (xyLen <= 0) {
            return MoreRandReturn(rs, xys)
        }

        if (num > xyLen) {
            // 超出大小限制，直接返回空
            return MoreRandReturn(rs, xys)
        }

        // 构造一份相同的
        val randHelps = LinkedList<T>()
        for (it in xys) {
            randHelps.add(it)
        }

        // 开始随机
        for (i in 0 until num) {
            // 计算取哪个位置的
            val remainingLen = randHelps.size
            val randIndex = getRandIntAtArea(randSeed, remainingLen)

            val value = randHelps[randIndex]
            randHelps.removeAt(randIndex)
            rs.add(value)
        }

        return MoreRandReturn(rs, randHelps)
    }

    //根据开服天数查找MapCall
    private fun findMapCall(areaCache: AreaCache): MapCallProto? {
        return pcs.mapCallProtoCache.findMapCallByOpenServerTime(areaCache.areaConfig.areaPublishTime)
    }

    private fun initMapFarmInfo(
        areaCache: AreaCache,
        mapCallProto: MapCallProto,
        nowRefGridId: Int,
        roundNum: Int,
        addCells: LinkedList<Pair<Int, Int>> = LinkedList(),
        delCells: LinkedList<Pair<Int, Int>> = LinkedList()
    ): Boolean {
        val bc = pcs.basicProtoCache

        // 初始化大地图的资源点数据
        // 资源带撒点,把大地图按照规则划分成N个x*y的小块进行处理,保证撒点密度

        val eachNum = bc.allArea / bc.resourceArea // 每边生态数量
        val resXyMapAllNum = bc.resourceArea * bc.resourceArea // 生态的格子数
        val xys = Array(resXyMapAllNum) { it } // 生态的一个二维数组
        val resTypeList = bc.resProportionList // basic.xml resProportion

        if (nowRefGridId == -1) {
            // 开服刷
            // allResGridNum：生态的数量=地图长度(allArea)/资源块长度(resourceArea)
            for (gridId in 0..(bc.allResGridNum - 1)) {
                initMapFarmInfoHelp(
                    areaCache,
                    gridId, // 生态的序号
                    eachNum, // 每边生态的数量
                    xys, // 生态的一个二维数组
                    nowRefGridId,
                    mapCallProto,
                    resXyMapAllNum, // 生态的格子数
                    resTypeList,
                    addCells,
                    delCells,
                    roundNum == 1
                )

                // 重新填充
                for (i in 0..(resXyMapAllNum - 1)) {
                    xys[i] = i
                }
            }

        } else {
            // 局部刷,看当前的进度是否超过了这个刷新元素的总生态数
            if (nowRefGridId >= bc.allResGridNum) {
                return true
            } else {
                initMapFarmInfoHelp(
                    areaCache,
                    nowRefGridId,
                    eachNum,
                    xys,
                    nowRefGridId,
                    mapCallProto,
                    resXyMapAllNum,
                    resTypeList,
                    addCells,
                    delCells,
                    roundNum == 1
                )
            }
        }

        return false
    }


    //假设整张地图是1200*1200的(坐标是从[0，0]开始计算的)，生态是100*100的，划分成12*12，从左往右，从上到下，依次用序号0 ~ 143
    private fun initMapFarmInfoHelp(
        areaCache: AreaCache,
        gridId: Int, // 生态的序号
        eachNum: Int, // 每条边生态数
        xys: Array<Int>, // 生态的一个二维数组
        nowRefId: Int, // 当前生态的序号
        mapCall: MapCallProto, // 当前的mapCall.xml的配置
        xyMapAllNum: Int, // 一个生态大个子内的格子数
        resTypeList: List<Int>, // 资源种类的数量(ex:5 有5种资源)
        addCells: LinkedList<Pair<Int, Int>>,
        delCells: LinkedList<Pair<Int, Int>>,
        clearOldFarmCell: Boolean //清理旧的采集地
    ) {
        val bc = pcs.basicProtoCache
        val mapLocationProtoCache = pcs.mapLocationProtoCache

        // 计算出当前资源块的左上角格子在整张地图上的坐标
        val baseX = (gridId % eachNum) * bc.resourceArea
        val baseY = (gridId / eachNum) * bc.resourceArea

        val useNumMap = hashMapOf<Int, Int>() //<resType, num>
        var delNum = 0 //排除队列中格子的数量
        for (index in xys.indices) { // 遍历资源块中的每一个格子
            // 计算当前格子在地图上的坐标
            val x = baseX + index % bc.resourceArea
            val y = baseY + index / bc.resourceArea

            //定时刷新需要考虑部队采集的情况，这些地保留
            if (nowRefId != -1) {
                val cell = areaCache.commonResCache.findCommonResByXY(x, y)
                if (cell != null) {
                    val resProto = pcs.resPointProtoCache.resPointMap[cell.resId]
                    if (resProto == null) {
                        continue
                    }

                    //有采集部队或者有队伍正在前往采集
                    val inUse = cell.groupId != 0L || areaCache.walkCache.findWalksByGotoXy(x, y).isEmpty()

                    if (inUse) {
                        // 这个坐标已经被用了，添加到排除队列中
                        xys[index] = -1
                        delNum++

                        useNumMap[resProto.level] = (useNumMap[resProto.level] ?: 0) + 1
                        continue
                    }
                    if (clearOldFarmCell) {
                        areaCache.commonResCache.deleteCommonResByXY(x, y)
                        delCells.add(Pair(x, y))
                        continue
                    }
                }
            }

            val checkRt = checkFreeCell(areaCache, x, y, 0)
            if (!checkRt) {
                // 这个坐标已经被用了，添加到排除队列中
                xys[index] = -1
                delNum++
                continue
            }
        }

        // 每个资源带刷新
        val resGridProto = pcs.resZoneProtoCache.fetchResInfoByGridId(gridId)
        val resZoneLv = resGridProto.resZoneProto.level
        val mapLocationProto = mapLocationProtoCache.mapLocationProtoMap[mapCall.mapLocation]?.get(resZoneLv)
        if (mapLocationProto == null) {
            normalLog.error("找不到mapLocation模版数据,寻找的资源带等级为:$resZoneLv,类型为:${mapCall.mapLocation}")
            return
        }

        val disMap = mapLocationProto.mapDistributionMap // 这个资源带要产生的资源点的权值

        val sum = mapLocationProto.totalResNum

        // 如果这次要撒的点比总量的地还要多,或者这个生态内 已经有X%以上的元素了,就不撒了
        val remainingXyMapNum = xyMapAllNum - delNum
        if (sum > remainingXyMapNum || 10000 - remainingXyMapNum * 10000 / xyMapAllNum >= bc.areaMaxDensity) {
            return
        }

        // 重新生成cell表
        val newXys = LinkedList<Int>()
        for (cellId in xys) {
            if (cellId != -1) {
                newXys.add(cellId)
            }
        }

        // 从小方块中获取到了sum个幸运儿,返回的是幸运儿的key的集合
        var (rands, _) = getMoreRands(areaCache.randSeed, newXys, sum)

        // 选出资源地
        for ((lv, num) in disMap) {
            //去除保留的资源地后，最终需要的资源地数量
            val reallyNum = num - (useNumMap[lv] ?: 0)
            if (reallyNum <= 0) {
                continue
            }
            // 再次筛选，根据权值与总数算出这个资源带要产生这个等级的资源点的数量为num个
            val (thisLvRand, remainings) = getMoreRands(areaCache.randSeed, rands, reallyNum)
            rands = remainings
            for (cellId in thisLvRand) {
                val x = baseX + cellId % bc.resourceArea
                val y = baseY + cellId / bc.resourceArea

                var reallyLv = lv

                val (wonderProtoType, wonderAddress) = pcs.wonderLocationProtoCache.findInWonderType(x, y)
                if (wonderProtoType != null) {
                    if (wonderAddress == WONDER_BLACK) {
                        val wonderProto = pcs.wonderProtoCache.wonderProtoMap[wonderProtoType.wondersType]
                        if (wonderProto != null) {
                            reallyLv = wonderProto.incidenceMidLv
                        }
                    } else if (wonderAddress == WONDER_AREA) {
                        val wonderProto = pcs.wonderProtoCache.wonderProtoMap[wonderProtoType.wondersType]
                        if (wonderProto != null) {
                            reallyLv = wonderProto.incidenceBigLv
                        }
                    }
                }

                val (snowLocationProto, snowArea) =
                        pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(x, y)
                if (snowLocationProto != null) {
                    if (snowArea == SNOW_AREA) {
                        reallyLv = snowLocationProto.incidenceBigLv
                    }
                }

                val randPosAtResTypeList = getRandIntAtArea(areaCache.randSeed, resTypeList.size)
                val resType = resTypeList[randPosAtResTypeList]
                val resNumMap = pcs.resPointProtoCache.resPointProtoMap[resType]
                if (resNumMap == null) {
                    continue
                }
                val resProto = resNumMap[reallyLv]
                if (resProto == null) {
                    continue
                }

                areaCache.commonResCache.createCommonRes(x, y, resProto.id, resProto.resTotal, reallyLv)
                addCells.add(Pair(x, y))
            }
        }
    }

    //初始化普通魔物
    private fun initCommonMonsterInfo(
        areaCache: AreaCache,
        mapCall: MapCallProto,
        nowRefGridId: Int,
        roundNum: Int,
        addCells: LinkedList<Pair<Int, Int>> = LinkedList(),
        delCells: LinkedList<Pair<Int, Int>> = LinkedList()
    ): Boolean {
        val bc = pcs.basicProtoCache

        val eachNum = bc.allArea / bc.monsterArea // 每边大格子数量
        val xyMapAllNum = bc.monsterArea * bc.monsterArea

        // 初始化大地图的资源点数据
        // 资源带撒点,把大地图按照规则划分成N个x*y的小块进行处理,保证撒点密度
        if (nowRefGridId == -1) {
            // 开服刷
            for (gridId in 0 until bc.allMonsterGridNum) {
                val baseX = (gridId % eachNum) * bc.monsterArea
                val baseY = (gridId / eachNum) * bc.monsterArea
                val xys = Array(xyMapAllNum) { Pair(baseX + it % bc.monsterArea, baseY + it / bc.monsterArea) }
                initMonsterInfoHelp(
                    areaCache,
                    xys,
                    mapCall.mapMonster,
                    addCells,
                    delCells,
                    roundNum == 1,
                    true
                )
            }
        } else {
            // 局部刷,看当前的进度是否超过了这个刷新元素的总生态数
            if (nowRefGridId >= bc.allMonsterGridNum) {
                return true
            } else {
                val baseX = (nowRefGridId % eachNum) * bc.monsterArea
                val baseY = (nowRefGridId / eachNum) * bc.monsterArea
                val xys = Array(xyMapAllNum) { Pair(baseX + it % bc.monsterArea, baseY + it / bc.monsterArea) }
                initMonsterInfoHelp(
                    areaCache,
                    xys,
                    mapCall.mapMonster,
                    addCells,
                    delCells,
                    roundNum == 1,
                    true
                )
            }
        }

        return false
    }

    //初始化奇观魔物
    private fun initWounderMonsterInfo(
        areaCache: AreaCache,
        nowRefIndex: Int,
        roundNum: Int,
        addCells: LinkedList<Pair<Int, Int>> = LinkedList(),
        delCells: LinkedList<Pair<Int, Int>> = LinkedList()
    ): Boolean {

        // 初始化大地图的资源点数据
        // 资源带撒点,把大地图按照规则划分成N个x*y的小块进行处理,保证撒点密度
        if (nowRefIndex == -1) {
            // 开服刷
            for (wonderLocationPorto in pcs.wonderLocationProtoCache.wonderLocationProtoList) {
                val xys = wonderLocationPorto.onlyBlackList
                initMonsterInfoHelp(
                    areaCache,
                    xys,
                    wonderLocationPorto.incidenceMidMonster,
                    addCells,
                    delCells,
                    roundNum == 1,
                    false
                )
            }
        } else {
            // 局部刷,看当前的进度是否超过了这个刷新元素的总生态数
            if (nowRefIndex >= pcs.wonderLocationProtoCache.wonderLocationProtoList.size) {
                return true
            } else {
                val wonderLocationPorto = pcs.wonderLocationProtoCache.wonderLocationProtoList[nowRefIndex]
                val xys = wonderLocationPorto.onlyBlackList
                initMonsterInfoHelp(
                    areaCache,
                    xys,
                    wonderLocationPorto.incidenceMidMonster,
                    addCells,
                    delCells,
                    roundNum == 1,
                    false
                )
            }
        }

        return false
    }

    //初始化雪地魔物
    private fun initSnowMonsterInfo(
        areaCache: AreaCache,
        nowRefId: Int,
        roundNum: Int,
        addCells: LinkedList<Pair<Int, Int>> = LinkedList(),
        delCells: LinkedList<Pair<Int, Int>> = LinkedList()
    ): Boolean {

        // 初始化大地图的资源点数据
        // 资源带撒点,把大地图按照规则划分成N个x*y的小块进行处理,保证撒点密度
        if (nowRefId == -1) {
            // 开服刷
            for (snowLocationPorto in pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoList) {
                val xys = snowLocationPorto.onlySnowList
                initMonsterInfoHelp(
                    areaCache,
                    xys,
                    snowLocationPorto.incidenceMidMonster,
                    addCells,
                    delCells,
                    roundNum == 1,
                    false
                )
            }
        } else {
            // 局部刷,看当前的进度是否超过了这个刷新元素的总生态数
            if (nowRefId >= pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoList.size) {
                return true
            } else {
                val snowLocationPorto = pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoList[nowRefId]
                val xys = snowLocationPorto.onlySnowList
                initMonsterInfoHelp(
                    areaCache,
                    xys,
                    snowLocationPorto.incidenceMidMonster,
                    addCells,
                    delCells,
                    roundNum == 1,
                    false
                )
            }
        }

        return false
    }

    //初始化魔物帮助
    private fun initMonsterInfoHelp(
        areaCache: AreaCache,
        xys: Array<Pair<Int, Int>>,
        mapMonster: Int,
        addCells: LinkedList<Pair<Int, Int>>,
        delCells: LinkedList<Pair<Int, Int>>,
        clearOldBossCell: Boolean,
        filterSpecialCell: Boolean = false //过滤特殊地块
    ) {
        val mapMonsterProto = pcs.mapMonsterProtoCache.mapMonsterProtoMap[mapMonster]
        if (mapMonsterProto == null) {
            normalLog.error("找不到mapMonsterProto模版数据,寻找的id为:$mapMonster")
            return
        }

        val bc = pcs.basicProtoCache
        val xyMapAllNum = xys.size
        // 撒魔物的时候要过滤掉已经撒了资源带的地
        var freeXys = LinkedList<Pair<Int, Int>>()
        var delNum = 0
        for (pos in xys) {
            val x = pos.first
            val y = pos.second
            if (clearOldBossCell) {
                val commonBoss = areaCache.commonBossCache.findCommonBossByXY(x, y)
                if (commonBoss != null) {
                    onCommonBossRemove(areaCache, commonBoss)
                    delCells.add(Pair(x, y))
                }
            }

            val checkRt = checkFreeCell(areaCache, x, y, 0)
            if (!checkRt) {
                delNum++
            } else {
                freeXys.add(pos)
            }
        }

        var remainingXyMapNum = xyMapAllNum - delNum
        for ((monsterType, monsterInfo) in mapMonsterProto.monsterInfoMap) {

            val sum = monsterInfo.totalNum

            // 如果这次要撒的点比总量的地还要多,或者这个生态内 已经有X%以上的元素了,就不撒了
            if (sum > remainingXyMapNum || 10000 - remainingXyMapNum * 10000 / xyMapAllNum >= bc.areaMaxDensity) {
                return
            }
            remainingXyMapNum -= sum

            // 从小方块中获取到了sum个幸运儿,返回的是幸运儿的key的集合
            var (rands, remainingXys) = getMoreRands(areaCache.randSeed, freeXys, sum)
            freeXys = remainingXys

            for ((lv, num) in monsterInfo.monsterMap) {
                // 根据权值与总数算出这个资源带要产生这个等级的资源点的数量为num个
                val (thisLvRand, remainingList) = getMoreRands(areaCache.randSeed, rands, num)
                rands = remainingList
                for (pos in thisLvRand) {
                    val x = pos.first
                    val y = pos.second

                    if (filterSpecialCell) {
                        //过滤奇观
                        if (pcs.wonderLocationProtoCache.inWonderBlack(x, y) != null) {
                            continue
                        }

                        //过滤雪地
                        if (pcs.monsterActivityLocationProtoCache.inSnowArea(x, y) != null) {
                            continue
                        }
                    }

                    val monsterTypeByDay = pcs.monsterRefreshProtoCache.findMonsterType(monsterType, lv)
                    val bosses = pcs.monsterProtoCache.findMonsterProtoByTypeLv(monsterTypeByDay, lv)
                    if (bosses == null || bosses.isEmpty()) {
                        continue
                    }

                    // 纯随机出一个魔物
                    val randBossIndex = getRandInt(bosses.size)
                    val boss = bosses[randBossIndex]

                    val unit = pcs.unitBaseCache.protoMap[boss.unit]
                    if (unit == null) {
                        normalLog.error("刷大地图的时候找不到Unit信息:${boss.unit}")
                        continue
                    }

                    areaCache.commonBossCache.createCommonBoss(
                        boss.id,
                        x,
                        y,
                        Math.ceil(unit.hp).toInt()
                    )

                    addCells.add(Pair(x, y))
                }
            }
        }
    }

    private fun initMapRelicInfo(
        areaCache: AreaCache,
        mapCell: MapCallProto,
        nowRefGridId: Int,
        roundNum: Int,
        addCells: LinkedList<Pair<Int, Int>> = LinkedList(),
        delCells: LinkedList<Pair<Int, Int>> = LinkedList()
    ): Boolean {
        val bc = pcs.basicProtoCache

        val eachNum = bc.allArea / bc.relicArea // 每边大格子数量
        val xyMapAllNum = bc.relicArea * bc.relicArea
        val xys = Array(xyMapAllNum) { it }

        // 初始化大地图的资源点数据
        // 资源带撒点,把大地图按照规则划分成N个x*y的小块进行处理,保证撒点密度
        val nowRelicMap = hashMapOf<Int, HashMap<Int, Int>>() // 已经确定下来的遗迹点
        if (nowRefGridId == -1) {
            // 开服刷
            for (gridId in 0..(bc.allMonsterGridNum - 1)) {
                initMapRelicInfoHelp(
                    areaCache,
                    gridId,
                    eachNum,
                    xys,
                    xyMapAllNum,
                    mapCell,
                    nowRelicMap,
                    addCells,
                    delCells,
                    roundNum == 1
                )

                //重置
                for (i in 0..(xyMapAllNum - 1)) {
                    xys[i] = i
                }
            }

        } else {
            // 局部刷,看当前的进度是否超过了这个刷新元素的总生态数
            if (nowRefGridId >= bc.allRelicGridNum) {
                return true
            } else {
                initMapRelicInfoHelp(
                    areaCache,
                    nowRefGridId,
                    eachNum,
                    xys,
                    xyMapAllNum,
                    mapCell,
                    nowRelicMap,
                    addCells,
                    delCells,
                    roundNum == 1
                )
            }
        }

        return false
    }

    private fun initMapRelicInfoHelp(
        areaCache: AreaCache,
        gridId: Int,
        eachNum: Int,
        xys: Array<Int>,
        xyMapAllNum: Int,
        mapCall: MapCallProto,
        nowRelicMap: HashMap<Int, HashMap<Int, Int>>,
        addCells: LinkedList<Pair<Int, Int>>,
        delCells: LinkedList<Pair<Int, Int>>,
        clearOldRelicCell: Boolean //清理旧的遗迹地块
    ) {
        val bc = pcs.basicProtoCache
        val mapRelicProtoCache = pcs.mapRelicProtoCache
        val baseX = (gridId % eachNum) * bc.relicArea
        val baseY = (gridId / eachNum) * bc.relicArea
        var delNum = 0

        val useNumMap = hashMapOf<Int, Int>() //<relicLv, num>

        // 撒遗迹的时候要过滤掉已经撒了资源带或者魔物的地
        for (index in xys.indices) {
            val x = baseX + index % bc.relicArea
            val y = baseY + index / bc.relicArea

            val relic = areaCache.relicCache.findRelicByXY(x, y)
            if (relic != null) {
                val inUse = areaCache.walkCache.findWalksByGotoXy(x, y).isEmpty()
                if (inUse) {
                    // 这个坐标已经被用了，添加到排除队列中
                    xys[index] = -1
                    delNum++

                    val relicProto = pcs.relicProtoCache.relicProtoMap[relic.relicId]
                    if (relicProto != null) {
                        useNumMap[relicProto.level] = (useNumMap[relicProto.level] ?: 0) + 1
                    }
                    continue
                }

                if (clearOldRelicCell) {
                    //直接根据坐标删除遗迹
                    areaCache.relicCache.deleteRelicByXY(x, y)
                    delCells.add(Pair(x, y))
                }
            }

            // 检测超出边缘
            if (x == MAX_MAP_SIZE || y == MAX_MAP_SIZE) {
                xys[index] = -1
                delNum++
                continue
            }

            // 检测不可用的点
            if (!checkFreeCell(areaCache, x, y, 0)) {
                xys[index] = -1
                delNum++
                continue
            }

            // 通过.这个格子可以放遗迹,记录下来
            val yMap = nowRelicMap.getOrPut(x) {
                hashMapOf()
            }
            yMap[y] = 1
        }

        val mapRelicProto = mapRelicProtoCache.mapRelicProtoMap[mapCall.mapRelic]
        if (mapRelicProto == null) {
            // 这边做了启动时配置验证，不太可能失败了
            return
        }

        val sum = mapRelicProto.totalRelicNum

        // 如果这次要撒的点比总量的地还要多,或者这个生态内 已经有X%以上的元素了,就不撒了
        val remainingXyMapNum = xyMapAllNum - delNum
        if (sum > remainingXyMapNum || 10000 - remainingXyMapNum * 10000 / xyMapAllNum >= bc.areaMaxDensity) {
            return
        }

        // 重新生成cell表
        val newXys = LinkedList<Int>()
        for (cellId in xys) {
            if (cellId != -1) {
                newXys.add(cellId)
            }
        }

        val relicProtoCache = pcs.relicProtoCache
        var (rands, _) = getMoreRands(areaCache.randSeed, newXys, sum) // 从小方块中获取到了sum个幸运儿,返回的是幸运儿的key的集合
        for ((lv, num) in mapRelicProto.numRelicMap) {
            //去除保留的遗迹地块后，最终需要的遗迹地块数量
            val reallyNum = num - (useNumMap[lv] ?: 0)
            if (reallyNum <= 0) {
                continue
            }

            // 根据权值与总数算出这个资源带要产生这个等级的资源点的数量为reallyNum个
            val (thisLvRand, remainingList) = getMoreRands(areaCache.randSeed, rands, reallyNum)
            rands = remainingList
            for (cellId in thisLvRand) {
                val x = baseX + cellId % bc.relicArea
                val y = baseY + cellId / bc.relicArea
                val relics = relicProtoCache.relicProtoMapByLv[lv]
                if (relics == null) {
                    normalLog.error("找不到该等级的遗迹集合:$lv")
                    continue
                }

                val relicRand = getRandIntAtArea(areaCache.randSeed, relics.size)
                val relic = relics[relicRand]
                val dropTimeBox = randomSelect(relic.drops, areaCache.randSeed, relic.totalDropRate)
                if (dropTimeBox == null) {
                    continue
                }
                val teamInfo = randomSelect(relic.soliderTeamList, areaCache.randSeed, relic.totalTeamRate)
                if (teamInfo == null) {
                    continue
                }

                val isUnScout = getRandIntAtArea(areaCache.randSeed, 10000) <= relic.bonuses
                areaCache.relicCache.createRelic(
                    relic.id,
                    x,
                    y,
                    isUnScout,
                    dropTimeBox.id,
                    teamInfo.lineUpId,
                    dropTimeBox.num
                )
                addCells.add(Pair(x, y))
            }
        }
    }
}

/**
 * 检测地块是否是普通地块，用于：
 *  1 主城坐标生成
 */
fun checkNormalCell(x: Int, y: Int): Boolean {
    // 过滤奇观区域
    val (_, wonderAddress) = pcs.wonderLocationProtoCache.findInWonderType(x, y)
    if (wonderAddress != NORMAL_CELL) {
        return false
    }

    // 过滤雪地区域
    val (_, snowArea) = pcs.monsterActivityLocationProtoCache.findActivityBossAreaType(x, y)
    if (snowArea != NORMAL_CELL) {
        return false
    }

    return true
}

/**
 * 检测地块是否是空地块，用于：
 *  1 校验撒资源、普通魔物、遗迹(废弃)
 *  2 校验刷新资源、普通魔物、遗迹(废弃)
 *  3 世界boss(废弃)
 *  4 迁城校验
 */
fun checkFreeCell(areaCache: AreaCache, x: Int, y: Int, playerId: Long): Boolean {
    // 过滤普通魔物
    val commonBossCell = areaCache.commonBossCache.findCommonBossByXY(x, y)
    if (commonBossCell != null) {
        return false
    }

    //过滤召唤魔物
    val callBossCell = areaCache.callBossCache.findCallBossByXy(x, y)
    if (callBossCell != null) {
        return false
    }

    //过滤普通资源点
    val farmCell = areaCache.commonResCache.findCommonResByXY(x, y)
    if (farmCell != null) {
        return false
    }

    //过滤尸体资源地
    val deadBossResCell = areaCache.deadBossResCache.findDeadBossResByXy(x, y)
    if (deadBossResCell != null) {
        return false
    }

    //过滤遗迹
    val relicCell = areaCache.relicCache.findRelicByXY(x, y)
    if (relicCell != null) {
        return false
    }

    // 过滤大地图阻挡
    if (pcs.bigMapBlockProtoCache.isCompose(x, y)) {
        return false
    }

    //过滤迷雾废墟
    val fogId = pcs.mapOpenProtoCache.mapOpenPosMap[x]?.get(y)
    if (fogId != null) {
        return false
    }

    // 过滤奇观中心禁地
    val wonderMid = pcs.wonderLocationProtoCache.isWonderMid(x, y)
    if (wonderMid) {
        return false
    }

    // 过滤雪地中心禁地
    val snowMid = pcs.monsterActivityLocationProtoCache.isSnowMid(x, y)
    if (snowMid) {
        return false
    }

    // 过滤玩家城
    val castle = areaCache.castleCache.findCastleByXy(x, y)
    if (castle != null) {
        return false
    }

    // 过滤玩家占领地
    val walkForceGroup = areaCache.walkForceGroupCache.findStationedWalkForceGroupsByPos(x, y)
    if (walkForceGroup.isNotEmpty()) {
        return false
    }

    //过滤属地
    val belongCell = areaCache.belongCellCache.findBelongCellByXy(x, y)
    if (belongCell != null) {
        if (getNowTime() < belongCell.overTime) {
            if (playerId != belongCell.playerId) {
                return false
            }
        }
    }

    // 根据XY拿锁坐标数据
    val lockCell = areaCache.moveServerCellLockCache.findMoveServerCellLockByXy(x, y)
    if (lockCell != null) {
        return false
    }

    return true
}

// 一个资源点被采集完毕,马上在该生态内在刷一个出来
fun refreshFarmInfo(areaCache: AreaCache, disappearX: Int, disappearY: Int, oldLv: Int) {
    val bc = pcs.basicProtoCache

    val gridId = calResGrid(disappearX, disappearY)

    val xyMap = getFarmInfoByGridId(gridId, bc.resourceArea) // 获取到这个小方块里所有的XY

    // 选取坐标
    var forNum = 1 // 保险丝
    while (true) {
        if (forNum >= 500) {
            break
        }

        forNum += 1
        val rand = getRandIntAtArea(areaCache.randSeed, xyMap.size)
        val luckCell = xyMap[rand] // 被选中的地 检测下这个坐标的周边4格是否都安全

        if (luckCell == null) {
            continue
        }

        // 判断点是否可以使用
        val checkRt = checkFreeCell(areaCache, luckCell.x, luckCell.y, 0)
        if (!checkRt) {
            continue
        }

        var reallyLv = oldLv

        val (wonderProtoType, wonderAddress) = pcs.wonderLocationProtoCache.findInWonderType(luckCell.x, luckCell.y)
        if (wonderProtoType != null) {
            if (wonderAddress == WONDER_BLACK) {
                val wonderProto = pcs.wonderProtoCache.wonderProtoMap[wonderProtoType.wondersType]
                if (wonderProto != null) {
                    reallyLv = wonderProto.incidenceMidLv
                }
            } else if (wonderAddress == WONDER_AREA) {
                val wonderProto = pcs.wonderProtoCache.wonderProtoMap[wonderProtoType.wondersType]
                if (wonderProto != null) {
                    reallyLv = wonderProto.incidenceBigLv
                }
            }
        }

        val resType = bc.resProportionList[getRandIntAtArea(areaCache.randSeed, bc.resProportionList.size)]
        val resNumMap = pcs.resPointProtoCache.resPointProtoMap[resType]
        if (resNumMap == null) {
            continue
        }

        val resProto = resNumMap[reallyLv]
        if (resProto == null) {
            continue
        }

        areaCache.commonResCache.createCommonRes(luckCell.x, luckCell.y, resProto.id, resProto.resTotal, reallyLv)

        // 新地点刷新采集点
        noticeCellUpdate(areaCache, luckCell.x, luckCell.y)

        break
    }

}

// 一个魔物被杀死,马上在该生态内在刷一个出来
fun refreshMonsterInfo(areaCache: AreaCache, bossId: Int, disappearX: Int, disappearY: Int) {
    val bc = pcs.basicProtoCache
    val boss = pcs.monsterProtoCache.findMonsterProto(bossId) ?: return
    val unit = pcs.unitBaseCache.fetchProtoById(boss.unit) ?: return

    //判断消失点是否是奇观黑土地或雪地，还是普通地块，选取的区域不同
    var filterSpecialCell = false
    val xyList: Array<Pair<Int, Int>>

    val wonderProto = pcs.wonderLocationProtoCache.inWonderBlack(disappearX, disappearY)
    if (wonderProto != null) {
        xyList = wonderProto.onlyBlackList
    } else {
        val snowProto = pcs.monsterActivityLocationProtoCache.inSnowArea(disappearX, disappearY)
        if (snowProto != null) {
            xyList = snowProto.onlySnowList
        } else {
            val gridId = calBossGrid(disappearX, disappearY)
            val eachNum = bc.allArea / bc.monsterArea // 每边大格子数量
            val xyMapAllNum = bc.monsterArea * bc.monsterArea
            val baseX = (gridId % eachNum) * bc.monsterArea
            val baseY = (gridId / eachNum) * bc.monsterArea
            xyList = Array(xyMapAllNum) { Pair(baseX + it % bc.monsterArea, baseY + it / bc.monsterArea) }
            filterSpecialCell = true
        }
    }

    // 选取坐标
    var forNum = 1 // 保险丝
    while (true) {
        forNum += 1
        if (forNum >= 50000) {
            break
        }

        val rand = getRandIntAtArea(areaCache.randSeed, xyList.size)
        val luckCell = xyList[rand] // 被选中的地 检测下这个坐标的周边4格是否都安全

        val x = luckCell.first
        val y = luckCell.second
        // 判断点是否可以使用
        if (filterSpecialCell) {
            //过滤奇观
            if (pcs.wonderLocationProtoCache.inWonderBlack(x, y) != null) {
                continue
            }

            //过滤雪地
            if (pcs.monsterActivityLocationProtoCache.inSnowArea(x, y) != null) {
                continue
            }
        }

        val checkRt = checkFreeCell(areaCache, x, y, 0)
        if (!checkRt) {
            continue
        }

        areaCache.commonBossCache.createCommonBoss(
            boss.id,
            x,
            y,
            Math.ceil(unit.hp).toInt()
        )

        // 新地点刷新Boss
        noticeCellUpdate(areaCache, x, y)
        return
    }
}

// 一个遗迹被清理,马上在该生态内在刷一个出来
fun refreshRelicInfo(areaCache: AreaCache, disappearX: Int, disappearY: Int, relicId: Int) {
    val bc = pcs.basicProtoCache
    val gridId = calRelicGrid(disappearX, disappearY)

    val xyMap = getFarmInfoByGridId(gridId, bc.relicArea) // 获取到这个小方块里所有的XY

    // 选取坐标
    var forNum = 1 // 保险丝
    while (true) {
        if (forNum >= 50000) {
            break
        }
        forNum += 1
        val rand = getRandIntAtArea(areaCache.randSeed, xyMap.size)
        val luckCell = xyMap[rand] // 被选中的地 检测下这个坐标的周边4格是否都安全

        if (luckCell == null) {
            continue
        }
        // 过滤边缘
        if (luckCell.x == MAX_MAP_SIZE || luckCell.y == MAX_MAP_SIZE) {
            forNum += 1
            continue
        }

        if (!checkFreeCell(areaCache, luckCell.x, luckCell.y, 0)) {
            continue
        }

        val relic = pcs.relicProtoCache.relicProtoMap[relicId]
        if (relic == null) {
            continue
        }
        val dropTimeBox = randomSelect(relic.drops, areaCache.randSeed, relic.totalDropRate)
        if (dropTimeBox == null) {
            continue
        }

        val teamInfo = randomSelect(relic.soliderTeamList, areaCache.randSeed, relic.totalTeamRate)
        if (teamInfo == null) {
            continue
        }

        val isUnScout = getRandIntAtArea(areaCache.randSeed, 10000) <= relic.bonuses

        areaCache.relicCache.createRelic(
            relicId,
            luckCell.x,
            luckCell.y,
            isUnScout,
            dropTimeBox.id,
            teamInfo.lineUpId,
            dropTimeBox.num
        )

        // 新地点刷新Boss
        noticeCellUpdate(areaCache, luckCell.x, luckCell.y)
        break
    }
}

data class GridCellInfo(var x: Int, var y: Int)

// 通过划分成的小格子的物理顺序ID来获取整个格子中所有的坐标
fun getFarmInfoByGridId(gridId: Int, smallLen: Int): (HashMap<Int, GridCellInfo>) {
    val xNum = pcs.basicProtoCache.allArea / smallLen // 大地图X轴被划分了多少块

    val row = gridId / xNum // 这个ID指代小方块的在第几行
    val col = gridId % xNum // 这个ID指代小方块的在第几列

    // 下面这个算法理解,假设有4*4一个正方形
    // 给过来的ID是2 那么上面的两行算下来就是0余2 表示2在4*4中就是第1行的第3列
    // 给过来的ID是4 那么上面的两行算下来就是1余0 表示4在4*4中就是第2行的第1列
    // 给过来的ID是7 那么上面的两行算下来就是1余3 表示7在4*4中就是第2行的第4列

    val xyMap = hashMapOf<Int, GridCellInfo>()

    var index = 0

    for (x in col * smallLen..((col + 1) * smallLen - 1)) {
        for (y in row * smallLen..((row + 1) * smallLen - 1)) {
            xyMap[index] = GridCellInfo(x, y)
            index++
        }

    }

    return xyMap
}

