package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.INSTANCE_DROP_NAMED_QUERY
import com.point18.slg2d.common.worldentities.InstanceDropEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家推图掉落控制
class InstanceDrop(
    var worldId: Long,
    var id: Long,

    var propsId: Int,  // 监控道具模版ID
    var nowGet: Int,  // 当前轮产出数
    var nowCheckTime: Int,  // 当前轮检测次数
    var playerId: Long   // 玩家ID
) : EntityWrapper<InstanceDropEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): InstanceDropEntity {
        return InstanceDropEntity(
            worldId,
            id,
            propsId,
            nowGet,
            nowCheckTime,
            playerId
        )
    }

    override fun wrap(entity: InstanceDropEntity) {
        worldId = entity.worldId
        id = entity.id
        propsId = entity.propsId
        nowGet = entity.nowGet
        nowCheckTime = entity.nowCheckTime
        playerId = entity.playerId
    }

}

class CacheInstanceDrop(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val instanceDropMap = TwoKeyIndex<Long, Int, InstanceDrop>({ it.playerId }, { it.propsId })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.instanceDropEntities =
                session.getNamedQuery(INSTANCE_DROP_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.instanceDropEntities.forEach { entity ->
            try {
                val instance = db.wdb.recover(entity) { InstanceDrop() }

                this.instanceDropMap.insertByKey(instance)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 通过玩家ID获取数据
    fun findInstanceDropEntityListByPlayId(playerId: Long): LinkedList<InstanceDropEntity> {
        val instanceDropEntityList = LinkedList<InstanceDropEntity>()
        instanceDropMap.findByOneKeyFilter(playerId) {
            instanceDropEntityList.add(it.toEntity())
            true

        }

        return instanceDropEntityList
    }

    // 通过玩家ID获取数据
    private fun findInstanceDropsByPlayId(playerId: Long): List<InstanceDrop> {
        val instanceDropEntityList = LinkedList<InstanceDrop>()
        instanceDropMap.findByOneKeyFilter(playerId) {
            instanceDropEntityList.add(it)
            true

        }

        return instanceDropEntityList
    }

    fun createInstanceDropByMoveServer(b: InstanceDropEntity) {
        val instanceDrop = InstanceDrop()
        instanceDrop.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        instanceDrop.worldId = worldId
        instanceDrop.id = id

        insert(areaCache, instanceDrop)

        // 添加到缓存中
        instanceDropMap.insertByKey(instanceDrop)
    }

    // 移除某个玩家的所有数据
    fun clearInstanceDropForMoveServer(playerId: Long) {
        val delList = findInstanceDropsByPlayId(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            instanceDropMap.deleteByKey(del)
        }
    }

    private fun createInstanceDrop(
        playerId: Long,
        propsId: Int,
        nowGet: Int,
        nowCheckTime: Int
    ): InstanceDrop {
        val id = wpm.generateObjIdNew(areaCache)
        val instanceDrop = InstanceDrop(worldId, id, propsId, nowGet, nowCheckTime, playerId)

        insert(areaCache, instanceDrop)

        instanceDropMap.insertByKey(instanceDrop)
        return instanceDrop
    }

    // 通过道具ID查看保底执行进度
    fun findInstanceDropByPropsId(playerId: Long, propsId: Int): InstanceDrop {
        val instanceDropVo = instanceDropMap.findByKey(playerId, propsId)

        if (instanceDropVo == null) {
            return createInstanceDrop(playerId, propsId, 0, 0)
        }

        return instanceDropVo
    }
}