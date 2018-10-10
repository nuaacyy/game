package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.CASTLE_MAIN
import com.point18.slg2d.common.constg.CastleStateEndTimeSync
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.CASTLE_NAMED_QUERY
import com.point18.slg2d.common.worldentities.CastleEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 城池
class Castle(
        var dbId: Long, // 毫无意义的ID 只作为存储数据库的主键 绝对不允许业务逻辑使用
        var worldId: Long,
        var id: Long,

        var name: String,              // 城池名
        var protoId: Int,              // 城池模板ID
        var lv: Int,              // 城池等级
        var x: Int,              // 城池x坐标
        var y: Int,              // 城池y坐标
        var type: Int,              // 城池类型
        var castleState: Int,              // 城池状态  0-和平 3-冒烟 4-冒火
        var castleStatusEndTime: Long, // 城堡状态结束时间
        var playerId: Long  // 玩家id
) : EntityWrapper<CastleEntity> {
    constructor() : this(
            0,
            0,
            0,
            "",
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): CastleEntity {
        return CastleEntity(
                worldId,
                dbId,
                id,
                name,
                protoId,
                lv,
                x,
                y,
                type,
                castleState,
                castleStatusEndTime,
                playerId
        )
    }

    override fun wrap(entity: CastleEntity) {
        worldId = entity.worldId
        id = entity.id
        name = entity.name
        protoId = entity.protoId
        lv = entity.lv
        x = entity.x
        y = entity.y
        type = entity.type
        castleState = entity.castleState
        playerId = entity.playerId
        dbId = entity.dbId
    }
}

class CacheCastle(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val castleMap = OneKeyIndex<Long, Castle> { it.id }    // 城池缓存
    private val playerCastleMap = OneKeyIndexSlice<Long, Castle>({ it.playerId },
            { ita, itb -> ita.id == itb.id }) // 以玩家ID为key的城池缓存
    private val castleMapByXy = TwoKeyIndex({ it: Castle -> it.x }, { it: Castle -> it.y })     // 通过XY找到一个城池

    private val castle4castleState = PriorityQueue<Castle> { a, b ->
        when {
            a.castleStatusEndTime > b.castleStatusEndTime -> 1
            a.castleStatusEndTime < b.castleStatusEndTime -> -1
            else -> 0
        }
    }

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.castleEntities =
                    session.getNamedQuery(CASTLE_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.castleEntities.forEach { entity ->
            try {
                val castle = db.wdb.recover(entity) {
                    Castle()
                }

                this.castleMap.insertByKey(castle)
                this.playerCastleMap.insertByKey(castle)
                this.castleMapByXy.insertByKey(castle)
                if (castle.castleStatusEndTime != 0L) {
                    castle4castleState.add(castle)
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 根据玩家Id获取他的城池
    fun findCastleEntityListFromPlayerId(playerId: Long): (LinkedList<CastleEntity>) {
        // 尝试从缓存中获取
        val castles = LinkedList<CastleEntity>()
        playerCastleMap.findByKey(playerId) {
            castles.add(it.toEntity())
        }

        return castles
    }

    fun createCastleByMoveServer(c: CastleEntity, x: Int, y: Int) {
        val castle = Castle()
        castle.wrap(c)
        val dbId = wpm.generateObjIdNew(areaCache)
        castle.worldId = worldId
        castle.dbId = dbId
        castle.x = x
        castle.y = y

        insert(areaCache, castle)

        // 添加到缓存中
        castleMap.insertByKey(castle)
        playerCastleMap.insertByKey(castle)
        castleMapByXy.insertByKey(castle)
        if (castle.castleStatusEndTime != 0L) {
            castle4castleState.add(castle)
        }
    }

    // 暂时移除某个玩家的优先级队列数据,如果迁服失败再重新添回来
    fun stopCastleForMoveServer(playerId: Long) {
        val delList = findCastleFromPlayerId(playerId)
        for (del in delList) {
            if (del.castleStatusEndTime != 0L) {
                castle4castleState.remove(del)
            }
        }
    }

    // 迁服失败,把数据重新添加到优先级队列
    fun reviveCastleForMoveServer(playerId: Long) {
        val delList = findCastleFromPlayerId(playerId)
        for (del in delList) {
            if (del.castleStatusEndTime != 0L) {
                castle4castleState.add(del)
            }
        }
    }

    // 移除某个玩家的所有数据
    fun clearCastleForMoveServer(playerId: Long) {
        val delList = findCastleFromPlayerId(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            castleMap.deleteByKey(del)
            playerCastleMap.deleteByKey(del)
            castleMapByXy.deleteByKey(del)
        }
    }

    // 保存新城池
    fun createCastle(
            playerId: Long,
            name: String,
            protoId: Int,
            posX: Int,
            posY: Int,
            lv: Int,
            castleType: Int
    ): Castle {
        val castleId = wpm.generateObjIdNew(areaCache)
        val dbId = wpm.generateObjIdNew(areaCache)
        val castle = Castle(
                dbId,
                worldId,
                castleId,
                name,
                protoId,
                lv,
                posX,
                posY,
                castleType,
                0,
                0,
                playerId
        )
        insert(areaCache, castle)

        // 添加到缓存中
        castleMap.insertByKey(castle)
        playerCastleMap.insertByKey(castle)
        castleMapByXy.insertByKey(castle)

        return castle
    }

    // 根据城池Id获取城池
    fun findCastleById(castleId: Long): (Castle?) {
        // 尝试从缓存中获取
        return castleMap.findByKey(castleId)
    }

    // 根据玩家Id获取他的城池
    fun findCastleFromPlayerId(playerId: Long): (LinkedList<Castle>) {
        // 尝试从缓存中获取
        val castles = LinkedList<Castle>()
        playerCastleMap.findByKey(playerId) {
            castles.add(it)
        }

        return castles
    }

    // 找到玩家的主城
    fun findMainCastleByPlayerId(playerId: Long): (Castle?) {
        // 尝试从缓存中获取
        var targetCastle: Castle? = null

        playerCastleMap.findByKey(playerId) {
            if (it.type == CASTLE_MAIN) {
                targetCastle = it
            }
            it.type != CASTLE_MAIN
        }


        return targetCastle
    }

    // 根据精准坐标获取城池
    fun findCastleByXy(x: Int, y: Int): (Castle?) {
        // 尝试从缓存中获取
        return castleMapByXy.findByKey(x, y)
    }

    //更新玩家城坐标
    fun updateCastlePos(castle: Castle, x: Int, y: Int) {
        castleMapByXy.updateByKey(x, y, castle, fun() {
            castle.x = x
            castle.y = y
        })
    }

    fun updateCastleStateEndTime(castle: Castle, endTime: Long) {
        val oldTime = castle.castleStatusEndTime
        if (oldTime != 0L) {
            castle4castleState.remove(castle)
        }
        castle.castleStatusEndTime = endTime
        syncData2Home(
            areaCache,
            castle.playerId,
            CastleStateEndTimeSync,
            castle.castleStatusEndTime.toString()
        )
        if (endTime != 0L) {
            castle4castleState.add(castle)
        }
    }

    fun peekCastleStateEndTimeFinish(): (Castle?) {
        val castle = castle4castleState.peek()
        if (castle == null) {
            return null
        }

        val nowTime = getNowTime()

        if (castle.castleStatusEndTime < nowTime) {
            return castle
        }

        return null
    }

    fun popCastleStateEndTime() {
        castle4castleState.poll()
    }
}