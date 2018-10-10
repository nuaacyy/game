package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.SERVER_ACTIVITY_NAMED_QUERY
import com.point18.slg2d.common.worldentities.ServerActivityEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class ServerActivity(
    var worldId: Long,
    var id: Long,

    var activityId: Int,        // 活动模板ID
    var overTime: Long          // 结束时间
) : EntityWrapper<ServerActivityEntity> {
    constructor() : this(0L, 0, 0, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ServerActivityEntity {
        return ServerActivityEntity(
            worldId,
            id,
            activityId,
            overTime
        )
    }

    override fun wrap(entity: ServerActivityEntity) {
        worldId = entity.worldId
        id = entity.id
        activityId = entity.activityId
        overTime = entity.overTime
    }
}

class CacheServerActivity(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val serverActivityMap = OneKeyIndex<Int, ServerActivity> { it.activityId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.serverActivityEntities =
                session.getNamedQuery(SERVER_ACTIVITY_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.serverActivityEntities.forEach { entity ->
            try {
                val serverActivity = db.wdb.recover(entity) { ServerActivity() }

                this.serverActivityMap.insertByKey(serverActivity)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createServerActivity(activityId: Int, overTime: Long): ServerActivity {
        val id = wpm.generateObjIdNew(areaCache)
        val serverActivity = ServerActivity(
            worldId,
            id,
            activityId,
            overTime
        )

        insert(areaCache, serverActivity)

        // 存入缓存
        serverActivityMap.insertByKey(serverActivity)

        return serverActivity
    }

    fun deleteServerActivity(serverActivity: ServerActivity) {

        val odlServerActivity = serverActivityMap.deleteByKey(serverActivity)
        if (odlServerActivity != null) {
            delete(areaCache, serverActivity)
        } else {
            assert(false) { "删除serverActivity异常，缓存中没有 ${serverActivity.id} 这个活动" }
        }
    }

    // 根据配置活动ID拿活动信息
    fun findServerActivityByActivityId(activityId: Int): ServerActivity? {
        return serverActivityMap.findByKey(activityId)
    }

    // 找到所有到点的活动
    fun findAllActivityForTimeOver(): List<ServerActivity> {
        val serverActivities = LinkedList<ServerActivity>()
        val nowTime = getNowTime()
        serverActivityMap.map {
            if (it.overTime != 0L && it.overTime < nowTime) {
                serverActivities.add(it)
            }
            true
        }
        return serverActivities
    }

    // 找到所有活动
    fun findAllActivity(): List<ServerActivity> {
        val serverActivityList = LinkedList<ServerActivity>()
        serverActivityMap.map {
            serverActivityList.add(it)
            true
        }
        return serverActivityList
    }
}
