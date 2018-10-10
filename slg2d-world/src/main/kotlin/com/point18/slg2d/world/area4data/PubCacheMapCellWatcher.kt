package com.point18.slg2d.world.area4data

import akka.actor.ActorRef
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.mcache.ThreeKeyIndex
import xyz.ariane.util.CommonDao

class CacheMapCellWatcher(areaCache: AreaCache) : BaseCache(areaCache) {

    private val posWatcherMap = ThreeKeyIndex<Int, Int, ActorRef, MapCellWatcher>(
        { it.gridX },
        { it.gridY },
        { it.watcher })
    private val playerWatcherMap = OneKeyIndexSlice<ActorRef, MapCellWatcher>(
        { it.watcher },
        { ita, itb -> ita.gridX == itb.gridX && ita.gridY == itb.gridY })
    private val centerWatcherMap = hashMapOf<ActorRef, Pair<Int, Int>>()

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {

    }

    override fun doInitData(worldInitData: WorldInitData) {

    }

    override fun doCacheLink() {
    }

    fun findCenterWatchPos(watcher: ActorRef): Pair<Int, Int>? {
        return centerWatcherMap[watcher]
    }

    fun setCenterWatchPos(watcher: ActorRef, gridX: Int, gridY: Int) {
        centerWatcherMap[watcher] = Pair(gridX, gridY)
    }

    // 添加到观察列表
    fun add2MapCellWatch(watcher: ActorRef, gridX: Int, gridY: Int) {
        if (posWatcherMap.findByKey(gridX, gridY, watcher) != null) {
            return
        }

        val newMapCellWatcher = MapCellWatcher(gridX, gridY, watcher)
        posWatcherMap.insertByKey(newMapCellWatcher)
        playerWatcherMap.insertByKey(newMapCellWatcher)
    }

    // 从观察列表中移除
    fun removeFromMapCellWatch(watcher: ActorRef, gridX: Int, gridY: Int) {
        val mapCellWatcher = posWatcherMap.findByKey(gridX, gridY, watcher) ?: return
        posWatcherMap.deleteByKey(mapCellWatcher)
        playerWatcherMap.deleteByKey(mapCellWatcher)
    }

    // 从观察列表中全部移除
    fun removeFromMapCellWatch(watcher: ActorRef) {
        val watcherList = playerWatcherMap.findByKey(watcher)
        watcherList?.forEach {
            posWatcherMap.deleteByKey(it)
        }
        playerWatcherMap.deleteWithKey(watcher)

        centerWatcherMap.remove(watcher)
    }

    class MapCellWatcher(
        val gridX: Int,
        val gridY: Int,
        val watcher: ActorRef
    )

    // 找到特定格子坐标的watcher
    fun findWatcherByXy(gridX: Int, gridY: Int): HashMap<ActorRef, MapCellWatcher>? {
        return posWatcherMap.findByTwoKey(gridX, gridY)
    }
}


