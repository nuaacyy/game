package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.ALLIANCE_GENERAL_PID
import com.point18.slg2d.common.constg.ALLIANCE_NAME_IN_USE
import com.point18.slg2d.common.publicentities.*
import com.point18.slg2d.public.PublicMenagerDatabase
import java.io.Serializable
import java.time.Duration

class PublicActivity(
    var id: Long,
    var publicId: Long,

    var activityId: Int,            // 活动ID
    var nowState: Int,            // 当前状态  1-开启中 2-领奖时间中 3-结束
    var rewardTime: Long, // 开始领奖时间
    var overTime: Long // 活动结束时间

) : EntityWrapper<PublicActivityEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): PublicActivityEntity {
        return PublicActivityEntity(
            id,
            publicId,
            activityId,
            nowState,
            rewardTime,
            overTime
        )
    }

    override fun wrap(entity: PublicActivityEntity) {
        id = entity.id
        publicId = entity.publicId
        activityId = entity.activityId
        nowState = entity.nowState
        rewardTime = entity.rewardTime
        overTime = entity.overTime
    }

}

class CachePublicActivityManager(val db: PublicMenagerDatabase) {

    val logger: LoggingAdapter = Logging.getLogger(db.aPublic.context.system(), javaClass)

    val publicActivityMap = OneKeyIndex { it: PublicActivity -> it.activityId }

    fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.publicActivities =
                    session.getNamedQuery(PUBLIC_ACTIVITY_NAMED_QUERY)
                        .setLong("publicId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    fun doInitDataForMenager(publicInitData: PublicInitData) {
        publicInitData.publicActivities.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    PublicActivity()

                }

                this.publicActivityMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    fun createPublicActivity(
        activityId: Int,
        nowState: Int,
        rewardTime: Long,
        overTime: Long
    ): (PublicActivity) {
        val id = ppm.generateObjIdNew()
        val publicActivity = PublicActivity(
            id,
            ALLIANCE_GENERAL_PID,
            activityId,
            nowState,
            rewardTime,
            overTime
        )


        insert(this.db, publicActivity)

        // 添加到缓存中
        publicActivityMap.insertByKey(publicActivity)

        return publicActivity
    }

    fun deletePublicActivity(publicActivity: PublicActivity?) {
        if (publicActivity == null || publicActivity.id == 0L) {
            return
        }

        delete(this.db, publicActivity)
        publicActivityMap.deleteByKey(publicActivity)
    }

    // 查询联盟总动员开启情况
    fun findPublicActivityByActivityId(activityId: Int): (PublicActivity?) {
        val publicActivity = publicActivityMap.findByKey(activityId)
        if (publicActivity == null) {
            return null
        } else {
            return publicActivity
        }

    }
}






