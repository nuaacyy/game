package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.EVERY_ALLIANCE_ACTIVITY_NAMED_QUERY
import com.point18.slg2d.common.publicentities.EveryAllianceActivityEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class EveryAllianceActivity(
    var id: Long,

    var activityId: Int,  // 活动模版ID
    var score: Int,   // 本活动积分
    var nowTarget: Int,    // 当前完成度(是一个中间值,比如条件是杀100个兵,本次击杀了99个.那么下次击杀1个也就可以完成1次积分获取了)
    var allianceId: Long  // 联盟ID

) : EntityWrapper<EveryAllianceActivityEntity> {
    constructor() : this(0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): EveryAllianceActivityEntity {
        return EveryAllianceActivityEntity(
            id,
            allianceId,
            activityId,
            score,
            nowTarget
        )
    }

    override fun wrap(entity: EveryAllianceActivityEntity) {
        id = entity.id
        allianceId = entity.allianceId
        activityId = entity.activityId
        score = entity.score
        nowTarget = entity.nowTarget
    }
}

class CacheEveryAllianceActivity(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceActivityInfoMap = OneKeyIndexSlice({ it: EveryAllianceActivity -> it.allianceId },
        { ita: EveryAllianceActivity, itb: EveryAllianceActivity -> ita.id == itb.id }) // 缓存
    val allAllianceActivityInfoMap = OneKeyIndex { it: EveryAllianceActivity -> it.id }      // 缓存

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.everyAllianceActivities =
                session.getNamedQuery(EVERY_ALLIANCE_ACTIVITY_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.everyAllianceActivities.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    EveryAllianceActivity()

                }

                this.allianceActivityInfoMap.insertByKey(b)
                this.allAllianceActivityInfoMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceActivityInfo(activityId: Int, allianceId: Long): (EveryAllianceActivity) {
        val allianceActivityInfo = EveryAllianceActivity()

        val id = ppm.generateObjIdNew()
        allianceActivityInfo.id = id
        allianceActivityInfo.activityId = activityId
        allianceActivityInfo.score = 0
        allianceActivityInfo.allianceId = allianceId
        insert(publicCache, allianceActivityInfo)

        // 存入缓存
        allAllianceActivityInfoMap.insertByKey(allianceActivityInfo)
        allianceActivityInfoMap.insertByKey(allianceActivityInfo)

        return allianceActivityInfo
    }

    fun deleteAllianceActivityInfo(everyAllianceActivity: EveryAllianceActivity) {
        delete(publicCache, everyAllianceActivity)
        allianceActivityInfoMap.deleteByKey(everyAllianceActivity)
        allAllianceActivityInfoMap.deleteByKey(everyAllianceActivity)
    }

    // 根据联盟ID拿所有的活动信息
    fun findAllianceActivityInfosByAllianceId(
        allianceId: Long
    ): (LinkedList<EveryAllianceActivity>) {
        val everyAllianceActivityWraps = LinkedList<EveryAllianceActivity>()

        allianceActivityInfoMap.findByKey(allianceId) {
            everyAllianceActivityWraps.add(it)
        }

        return everyAllianceActivityWraps
    }

    // 根据联盟ID + 活动ID 拿对应的活动信息
    fun findAllianceActivityInfoByAllianceIdAndActivityId(
        allianceId: Long,
        activityId: Int
    ): (EveryAllianceActivity?) {
        var activity: EveryAllianceActivity? = null

        publicCache.everyAllianceActivityCache.allianceActivityInfoMap.findByKey(allianceId) {
            if (it.activityId == activityId) {
                activity = it
            }

            it.activityId != activityId
        }

        return activity
    }

    // 获取所有参与过本活动的联盟
    fun findAllAlliancectivityInfos(activityId: Int): (LinkedList<EveryAllianceActivity>) {
        val everyAllianceActivityWraps = LinkedList<EveryAllianceActivity>()

        allAllianceActivityInfoMap.map {
            val eventIProto = com.point18.slg2d.common.pc.pcs.eventAllianceInformationProtoCache.protoMapById[activityId]
            if (eventIProto != null) {
                if ((it.score != 0 || it.nowTarget != 0) && (it.activityId == activityId)) {
                    everyAllianceActivityWraps.add(it)
                }
            }
            true
        }
        return everyAllianceActivityWraps
    }
}









