package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_ACTIVITY_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceActivityEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ALLIANCE_GENERAL_PID
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceActivity(
    var id: Long,
    var publicId: Long,

    var activityId: Int,         // 活动模板ID
    var overTime: Long  // 结束时间

) : EntityWrapper<AllianceActivityEntity> {
    constructor() : this(0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceActivityEntity {
        return AllianceActivityEntity(
            this.id,
            this.publicId,
            this.activityId,
            this.overTime
        )
    }

    override fun wrap(entity: AllianceActivityEntity) {
        id = entity.id
        publicId = entity.publicId
        activityId = entity.activityId
        overTime = entity.overTime
    }

}

class CacheAllianceActivity(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceActivityMap = OneKeyIndex { it: AllianceActivity -> it.activityId } // 缓存

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceActivities =
                    session.getNamedQuery(ALLIANCE_ACTIVITY_NAMED_QUERY)
                        .setLong("publicId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceActivities.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceActivity()
                }

                this.allianceActivityMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceActivity(activityId: Int, overTime: Long): (AllianceActivity) {
        val allianceActivity = AllianceActivity()

        val id = ppm.generateObjIdNew()
        allianceActivity.id = id
        allianceActivity.publicId = ALLIANCE_GENERAL_PID
        allianceActivity.activityId = activityId
        allianceActivity.overTime = overTime
        insert(publicCache, allianceActivity)

        // 存入缓存
        allianceActivityMap.insertByKey(allianceActivity)

        return allianceActivity
    }

    fun deleteAllianceActivity(allianceActivity: AllianceActivity) {
        delete(publicCache, allianceActivity)
        allianceActivityMap.deleteByKey(allianceActivity)
    }

    // 根据活动ID拿活动信息
    fun findAllianceActivityByActivityId(activityId: Int): AllianceActivity? {
        val serverActivity = allianceActivityMap.findByKey(activityId)
            ?: return null

        return serverActivity
    }

    // 找到所有到点的活动
    fun findAllAllianceActivityForTimeOver(): LinkedList<AllianceActivity> {
        val serverActivitys = LinkedList<AllianceActivity>()
        val nowTime = getNowTime()
        allianceActivityMap.map {
            if (it.overTime <= nowTime) {
                serverActivitys.add(it)
            }
            it.overTime > nowTime
        }

        return serverActivitys
    }

    // 找到所有活动
    fun findAllAllianceActivityForGm(): LinkedList<AllianceActivity> {
        val serverActivitys = LinkedList<AllianceActivity>()
        val nowTime = getNowTime()
        allianceActivityMap.map {
            if (true) {
                serverActivitys.add(it)
            }
            it.overTime > nowTime
        }

        return serverActivitys
    }
}
