package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.MOVE_SERVER_CELL_LOCK_QUERY
import com.point18.slg2d.common.worldentities.MoveServerCellLockEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class MoveServerCellLock(
    var worldId: Long,
    var id: Long,

    var x: Int,
    var y: Int,
    var startTime: Long, // 锁定时间 todo 用来做失败处理放置坐标永久锁死
    var playerId: Long // 锁定坐标的玩家ID
) : EntityWrapper<MoveServerCellLockEntity> {
    constructor() : this(0L, 0, 0, 0, 0L, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): MoveServerCellLockEntity {
        return MoveServerCellLockEntity(
            worldId,
            id,
            x,
            y,
            startTime,
            playerId
        )
    }

    override fun wrap(entity: MoveServerCellLockEntity) {
        worldId = entity.worldId
        id = entity.id
        x = entity.x
        y = entity.y
        startTime = entity.startTime
        playerId = entity.playerId
    }
}

class CacheMoveServerCellLock(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val moveServerCellLockMapByPlayerId = OneKeyIndex<Long, MoveServerCellLock> { it.playerId }
    private val moveServerCellLockMapByXy = TwoKeyIndex<Int, Int, MoveServerCellLock>({ it.x }, { it.y })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.moveServerCellLockEntity =
                session.getNamedQuery(MOVE_SERVER_CELL_LOCK_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.moveServerCellLockEntity.forEach { entity ->
            try {
                val serverActivity = db.wdb.recover(entity) { MoveServerCellLock() }

                this.moveServerCellLockMapByPlayerId.insertByKey(serverActivity)
                this.moveServerCellLockMapByXy.insertByKey(serverActivity)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createMoveServerCellLock(playerId: Long, x: Int, y: Int): MoveServerCellLock {
        val id = wpm.generateObjIdNew(areaCache)
        val moveServerCellLock = MoveServerCellLock(
            worldId,
            id,
            x,
            y,
            getNowTime(),
            playerId
        )

        insert(areaCache, moveServerCellLock)

        // 存入缓存
        moveServerCellLockMapByPlayerId.insertByKey(moveServerCellLock)
        moveServerCellLockMapByXy.insertByKey(moveServerCellLock)

        return moveServerCellLock
    }

    fun deleteMoveServerCellLock(moveServerCellLock: MoveServerCellLock) {
        moveServerCellLockMapByPlayerId.deleteByKey(moveServerCellLock)
        moveServerCellLockMapByXy.deleteByKey(moveServerCellLock)
        delete(areaCache, moveServerCellLock)
    }

    // 根据玩家ID拿锁坐标数据
    fun findMoveServerCellLockByPlayerId(playerId: Long): MoveServerCellLock? {
        return moveServerCellLockMapByPlayerId.findByKey(playerId)
    }

    // 根据XY拿锁坐标数据
    fun findMoveServerCellLockByXy(x: Int, y: Int): MoveServerCellLock? {
        return moveServerCellLockMapByXy.findByKey(x, y)
    }

}