package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.CAVE_NAMED_QUERY
import com.point18.slg2d.common.worldentities.CaveEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 藏兵洞穴
class Cave(
    var worldId: Long,
    var id: Long,

    var playerId: Long,    // 玩家Id
    var hideForceGroupId: Long,    // 藏兵部队Id
    var hideStartTime: Long,  // 藏兵开始时间
    var hideOverTime: Long    // 藏兵结束时间
) : EntityWrapper<CaveEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): CaveEntity {
        return CaveEntity(
            worldId,
            id,
            playerId,
            hideForceGroupId,
            hideStartTime,
            hideOverTime
        )
    }

    override fun wrap(entity: CaveEntity) {
        worldId = entity.worldId
        id = entity.id
        playerId = entity.playerId
        hideForceGroupId = entity.hideForceGroupId
        hideStartTime = entity.hideStartTime
        hideOverTime = entity.hideOverTime
    }
}

class CacheCave(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val caveMap = OneKeyIndex { it: Cave -> it.playerId }          // 藏兵缓存

    private val cavePq: Queue<Cave> = PriorityQueue { c1, c2 ->
        when {
            c1.hideOverTime > c2.hideOverTime -> 1
            c1.hideOverTime == c2.hideOverTime -> 0
            else -> -1
        }
    }    // buff过期队列
    
    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.caveEntities =
                session.getNamedQuery(CAVE_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.caveEntities.forEach { entity ->
            try {
                val c = db.wdb.recover(entity) { Cave() }

                this.caveMap.insertByKey(c)
                if (c.hideOverTime != zeroTime.time) {
                    this.cavePq.add(c)
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    /**
     * 创建藏兵
     */
    fun createCave(playerId: Long, timeHour: Int, groupId: Long): Cave {
        val id = wpm.generateObjIdNew(areaCache)
        val nowTime = getNowTime()
        val overTime = nowTime + (timeHour * 60 * 60 * 1000)
        val cave = Cave(
            worldId,
            id,
            playerId,
            groupId,
            nowTime,
            overTime
        )
        insert(areaCache, cave)

        caveMap.insertByKey(cave)

        cavePq.add(cave)
        return cave
    }

    /**
     * 删除藏兵
     */
    fun deleteCave(cave: Cave?) {
        if (cave == null || cave.id == 0L) {
            return
        }
        delete(areaCache, cave)

        caveMap.deleteByKey(cave)
        cavePq.remove(cave)
    }

    fun findCaveByPlayerId(playerId: Long): (Cave?) {
        return caveMap.findByKey(playerId)
    }

    //返回头部藏兵心跳但不移除
    fun peekCaveHeart(): (Cave?) {
        val cave = cavePq.peek() ?: return null

        val nowTime = getNowTime()

        if (cave.hideOverTime < nowTime) {
            return cave
        }

        return null
    }

    //返回头部藏兵心跳并移除
    fun popCaveHeart() {
        cavePq.poll()
    }
}
