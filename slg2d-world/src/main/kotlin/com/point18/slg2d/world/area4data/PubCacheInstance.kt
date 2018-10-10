package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.INSTANCE_NAMED_QUERY
import com.point18.slg2d.common.worldentities.InstanceEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 推图数据
class Instance(
    var worldId: Long,
    var id: Long,

    var nowFight: Int,          // 当前正在战斗的关卡
    var starInfoMap: HashMap<Int, HashSet<Int>>, // 每一关的星数 Key : 关卡ID  Value : 获得星数
    var unitInfoMap: HashMap<Int, UnitInfo>, // 玩家的章节奖励领取信息
    var playerId: Long   // 玩家ID
) : EntityWrapper<InstanceEntity> {
    constructor() : this(0, 0, 0, hashMapOf(), hashMapOf(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): InstanceEntity {
        return InstanceEntity(
            worldId,
            id,
            nowFight,
            toJson(starInfoMap),
            toJson(unitInfoMap),
            playerId
        )
    }

    override fun wrap(entity: InstanceEntity) {
        worldId = entity.worldId
        id = entity.id
        nowFight = entity.nowFight
        starInfoMap = toObj(entity.starInfo)
        unitInfoMap = toObj(entity.unitInfos)
        playerId = entity.playerId
    }
}

data class UnitInfo(
    var getStarBox: LinkedList<Int>// 领取过的星数切片 如 3,6,9
)

class CacheInstance(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val instanceMap = OneKeyIndex { it: Instance -> it.playerId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.instanceEntities =
                session.getNamedQuery(INSTANCE_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.instanceEntities.forEach { entity ->
            try {
                val instance = db.wdb.recover(entity) { Instance() }

                this.instanceMap.insertByKey(instance)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun findInstanceEntity(playerId: Long): (InstanceEntity?) {
        val info = instanceMap.findByKey(playerId)
        return info?.toEntity()
    }

    fun createInstanceByMoveServer(b: InstanceEntity) {
        val instance = Instance()
        instance.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        instance.worldId = worldId
        instance.id = id

        insert(areaCache, instance)

        // 添加到缓存中
        instanceMap.insertByKey(instance)
    }

    // 移除某个玩家的所有数据
    fun clearInstanceForMoveServer(playerId: Long) {
        val del = findInstance(playerId)
        if (del != null) {
            delete(areaCache, del)
            // 从缓存中删除
            instanceMap.deleteByKey(del)
        }
    }

    fun createInstance(playerId: Long): (Instance) {
        val id = wpm.generateObjIdNew(areaCache)
        val instance = Instance(worldId, id, pcs.basicProtoCache.firstInstance, hashMapOf(), hashMapOf(), playerId)

        insert(areaCache, instance)
        instanceMap.insertByKey(instance)

        return instance
    }

    fun findInstance(playerId: Long): (Instance?) {
        return instanceMap.findByKey(playerId)
    }
}



