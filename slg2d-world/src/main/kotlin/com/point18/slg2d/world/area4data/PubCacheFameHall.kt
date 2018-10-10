package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.FAME_HALL_NAMED_QUERY
import com.point18.slg2d.common.worldentities.FameHallEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 名人堂
class FameHall(
    var worldId: Long,
    var id: Long,
    var allianceName: String,       // 联盟名称
    var allianceShortName: String,  // 联盟简称
    var playerName: String,         // 玩家名称
    var playerPhotoId: Int,         // 玩家头像Id
    var occupyTime: Long,           // 占领奇观时长
    var createTime: Long,           // 占领时间
    var allianceId: Long,           // 联盟ID
    var playerId: Long              // 玩家ID
) : EntityWrapper<FameHallEntity> {

    constructor() : this(
        0, 0, "", "", "", 0, 0L, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): FameHallEntity {
        return FameHallEntity(
            worldId,
            id,
            allianceName,
            allianceShortName,
            playerName,
            playerPhotoId,
            occupyTime,
            createTime,
            allianceId,
            playerId
        )
    }

    override fun wrap(entity: FameHallEntity) {
        worldId = entity.worldId
        id = entity.id
        allianceName = entity.allianceName
        allianceShortName = entity.allianceShortName
        playerName = entity.playerName
        playerPhotoId = entity.playerPhotoId
        occupyTime = entity.occupyTime
        createTime = entity.createTime
        allianceId = entity.allianceId
        playerId = entity.playerId
    }
}

class CacheFameHall(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val fameHallList = LinkedList<FameHall>()
    private val fameHallMap = OneKeyIndex { it: FameHall -> it.playerId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.fameHallEntities =
                session.getNamedQuery(FAME_HALL_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.fameHallEntities.forEach { entity ->
            try {
                val fameHall = db.wdb.recover(entity) { FameHall() }

                this.fameHallMap.insertByKey(fameHall)
                this.fameHallList.add(fameHall)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun createFameHall(
        allianceName: String,
        allianceShortName: String,
        playerName: String,
        playerPhotoId: Int,
        occupyTime: Long,
        createTime: Long,
        allianceId: Long,
        playerId: Long
    ): FameHall {
        val id = wpm.generateObjIdNew(areaCache)
        val fameHallItem = FameHall(
            worldId,
            id,
            allianceName,
            allianceShortName,
            playerName,
            playerPhotoId,
            occupyTime,
            createTime,
            allianceId,
            playerId
        )

        insert(areaCache, fameHallItem)

        // 添加到缓存中
        fameHallMap.insertByKey(fameHallItem)
        fameHallList.add(fameHallItem)
        return fameHallItem
    }
}

