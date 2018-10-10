package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.worldentities.PRISON_NAMED_QUERY
import com.point18.slg2d.common.worldentities.PrisonEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家的监禁
class Prison(
    var worldId: Long,
    var id: Long,

    var prisonPlayerId: Long,  // 被监禁玩家ID
    var ransom: Long,  // 设置的赎金
    var rewardGold: Long,  // 设置的赏金
    var playerId: Long  // 玩家ID
) : EntityWrapper<PrisonEntity> {
    constructor() : this(0L, 0, 0L, 0L, 0L, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): PrisonEntity {
        return PrisonEntity(
            worldId,
            id,
            prisonPlayerId,
            ransom,
            rewardGold,
            playerId
        )
    }

    override fun wrap(entity: PrisonEntity) {
        worldId = entity.worldId
        id = entity.id
        prisonPlayerId = entity.prisonPlayerId
        ransom = entity.ransom
        rewardGold = entity.rewardGold
        playerId = entity.playerId
    }
}

class CachePrison(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val prison = OneKeyIndexSlice<Long, Prison>({ it.playerId }, { pA, pB -> pA.id == pB.id })
    private val prisonMap = OneKeyIndex<Long, Prison> { it.id }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.prisonEntities =
                session.getNamedQuery(PRISON_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.prisonEntities.forEach { entity ->
            try {
                val p = db.wdb.recover(entity) { Prison() }

                prison.insertByKey(p)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 获取玩家所有的监禁中的信息
    fun findPrisonEntityListByPlayerId(playerId: Long): LinkedList<PrisonEntity> {
        // 尝试从缓存中获取
        val prisons = LinkedList<PrisonEntity>()
        // 全部
        prison.findByKey(playerId) { prisons.add(it.toEntity()) }

        return prisons
    }

    fun createPrisonByMoveServer(b: PrisonEntity) {
        val prison = Prison()
        prison.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        prison.worldId = worldId
        prison.id = id

        insert(areaCache, prison)

        // 添加到缓存中
        this.prison.insertByKey(prison)
        prisonMap.insertByKey(prison)
    }

    // 移除某个玩家的所有数据
    fun clearPrisonForMoveServer(playerId: Long) {
        val delList = findPrisonsByPlayerId(playerId)
        for (del in delList) {
            deletePrison(del)
        }
    }

    // 新建监禁信息
    fun createPrison(playerId: Long, prisonId: Long): Prison {
        val id = wpm.generateObjIdNew(areaCache)
        val prison = Prison(
            worldId,
            id,
            prisonId,
            0,
            0,
            playerId
        )

        // 保存

        insert(areaCache, prison)

        this.prison.insertByKey(prison)
        prisonMap.insertByKey(prison)

        return prison
    }

    // 删除监禁信息
    fun deletePrison(prison: Prison) {
        // 删除数据库
        delete(areaCache, prison)
        this.prison.deleteByKey(prison)
        prisonMap.deleteByKey(prison)
    }

    // 获取玩家所有的监禁中的信息
    fun findPrisonsByPlayerId(playerId: Long): List<Prison> {
        // 尝试从缓存中获取
        val prisons = LinkedList<Prison>()
        // 全部
        prison.findByKey(playerId) { prisons.add(it) }

        return prisons
    }

    // 获取玩家监禁中的某个人信息
    fun findPrisonsByPlayerIdAndPrisonPlayerId(playerId: Long, prisonPlayerId: Long): Prison? {
        // 尝试从缓存中获取
        var prison: Prison? = null
        // 全部
        this.prison.findByKey(playerId) {
            if (it.prisonPlayerId == prisonPlayerId) {
                prison = it
            }
            it.prisonPlayerId != prisonPlayerId
        }

        return prison
    }

    // 获取所有的监禁中的信息
    fun findAllPrisons(): List<Prison> {
        // 尝试从缓存中获取
        val prisons = LinkedList<Prison>()
        prisonMap.map { prisons.add(it) }
        return prisons
    }

    //找到监狱中最高的玩家的等级
    fun findMaxLvInPrison(playerId: Long): Int {
        var maxLv = 0
        prison.findByKey(playerId) {
            val player = areaCache.playerCache.findPlayerById(it.prisonPlayerId)
            if (player == null) {
                return@findByKey true
            }
            if (player.kingLv > maxLv) {
                maxLv = player.kingLv
            }
            true
        }
        return maxLv
    }
}