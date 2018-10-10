package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.PLAYER_ACTIVITY_NAMED_QUERY
import com.point18.slg2d.common.worldentities.PlayerActivityEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 玩家活动数据
class PlayerActivity(
    var worldId: Long,
    var id: Long,

    var activityId: Int,            // 活动模版ID
    var score: Int,                 // 本活动积分
    var nowTarget: Int,             // 当前完成度(是一个中间值,比如条件是杀100个兵,本次击杀了99个.那么下次击杀1个也就可以完成1次积分获取了)
    var castleLv: Int,              // 参加这个活动时的主堡等级
    var playerId: Long              // 玩家ID
) : EntityWrapper<PlayerActivityEntity> {
    constructor() : this(0L, 0, 0, 0, 0, 0, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): PlayerActivityEntity {
        return PlayerActivityEntity(
            worldId,
            id,
            activityId,
            score,
            nowTarget,
            castleLv,
            playerId
        )
    }

    override fun wrap(entity: PlayerActivityEntity) {
        worldId = entity.worldId
        id = entity.id
        activityId = entity.activityId
        score = entity.score
        nowTarget = entity.nowTarget
        castleLv = entity.castleLv
        playerId = entity.playerId
    }
}

class CachePlayerActivity(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val playerActivityMap =
        OneKeyIndexSlice<Long, PlayerActivity>({ it.playerId }, { acA, acB -> acA.id == acB.id })
    private val allPlayerActivityMap = OneKeyIndex<Long, PlayerActivity> { it.id }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.playerActivityEntities =
                    session.getNamedQuery(PLAYER_ACTIVITY_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.playerActivityEntities.forEach { entity ->
            try {
                val playerActivity = db.wdb.recover(entity) { PlayerActivity() }

                this.playerActivityMap.insertByKey(playerActivity)
                this.allPlayerActivityMap.insertByKey(playerActivity)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 根据玩家ID拿所有的活动信息
    fun findPlayerActivityEntityListByPlayerId(playerId: Long): LinkedList<PlayerActivityEntity> {
        val playerActivityList = LinkedList<PlayerActivityEntity>()

        playerActivityMap.findByKey(playerId) { playerActivityList.add(it.toEntity()) }

        return playerActivityList
    }

    fun createPlayerActivityMoveServer(b: PlayerActivityEntity) {
        val playerActivity = PlayerActivity()
        playerActivity.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        playerActivity.worldId = worldId
        playerActivity.id = id

        insert(areaCache, playerActivity)

        // 添加到缓存中
        playerActivityMap.insertByKey(playerActivity)
        allPlayerActivityMap.insertByKey(playerActivity)
    }

    // 移除某个玩家的所有数据
    fun clearPlayerActivityForMoveServer(playerId: Long) {
        val delList = findPlayerActivityListByPlayerId(playerId)
        for (del in delList) {
            delete(areaCache, del)

            // 从缓存中删除
            playerActivityMap.deleteByKey(del)
            allPlayerActivityMap.deleteByKey(del)
        }
    }

    fun createPlayerActivity(activityId: Int, playerId: Long, castleLv: Int): PlayerActivity {
        val id = wpm.generateObjIdNew(areaCache)
        val playerActivity = PlayerActivity(
            worldId,
            id,
            activityId,
            0,
            0,
            castleLv,
            playerId
        )

        insert(areaCache, playerActivity)

        // 存入缓存
        playerActivityMap.insertByKey(playerActivity)
        allPlayerActivityMap.insertByKey(playerActivity)

        return playerActivity
    }

    // 根据玩家ID拿所有的活动信息
    fun findPlayerActivityListByPlayerId(playerId: Long): List<PlayerActivity> {
        val playerActivityList = LinkedList<PlayerActivity>()

        playerActivityMap.findByKey(playerId) { playerActivityList.add(it) }

        return playerActivityList
    }

    // 根据玩家ID + 活动ID 拿对应的活动信息
    fun findPlayerActivityByPlayerIdAndActivityId(playerId: Long, activityId: Int): PlayerActivity? {
        var activity: PlayerActivity? = null
        playerActivityMap.findByKey(playerId) {
            if (it.activityId == activityId) {
                activity = it
            }
            it.activityId != activityId
        }

        return activity
    }

    // 获取所有参与过本活动的玩家
    fun findAllPlayerActivityInfoMap(activityId: Int): HashMap<Int, LinkedList<PlayerActivity>> {
        val playerActivityMap = hashMapOf<Int, LinkedList<PlayerActivity>>()
        allPlayerActivityMap.map {
            val protoMap = pcs.eventInformationProtoCache.protoMap[activityId] ?: return@map true
            val eventInfoProto = protoMap[it.castleLv] ?: return@map true
            if (((it.score != 0 || it.nowTarget != 0)) && (it.activityId == activityId)) {
                val eventInfoProtoMap = pcs.eventInformationProtoCache.protoMap[it.activityId]
                if (eventInfoProtoMap == null || eventInfoProtoMap[it.castleLv] == null) {
                    //Assert
                    return@map true
                }
                val activityList = playerActivityMap.getOrPut(eventInfoProto.id) { LinkedList() }
                activityList.add(it)
            }
            true
        }
        return playerActivityMap
    }
}