package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_LOG_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceLogEntity
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration

class AllianceLog(
    var id: Long,

    var onceKey: String, //为了做到某些日志一天只记录一次，需要根据此字段判定
    var allianceId: Long,        //联盟ID
    var logType: Int,                   //日志类型
    var logInfo: String,     //记录信息
    var logTime: Long       //记录时间

) : EntityWrapper<AllianceLogEntity> {
    constructor() : this(0, "", 0, 0, "", 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceLogEntity {
        return AllianceLogEntity(
            id,
            allianceId,
            onceKey,
            logType,
            logInfo,
            logTime
        )
    }

    override fun wrap(entity: AllianceLogEntity) {
        id = entity.id
        allianceId = entity.allianceId
        onceKey = entity.onceKey
        logType = entity.logType
        logInfo = entity.logInfo
        logTime = entity.logTime
    }

}

class CacheAllianceLog(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceLogMap = mutableMapOf<Long, MutableMap<Long, AllianceLog>>() // 公会日志缓存, 大ID是公会ID

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceLogs =
                session.getNamedQuery(ALLIANCE_LOG_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceLogs.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceLog()

                }

                this.saveAllianceLog2Cache(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceLog(aid: Long, one: String, logType: Int, info: String) {
        val id = ppm.generateObjIdNew()
        val aLog = AllianceLog(
            id,
            one,
            aid,
            logType,
            info,
            getNowTime()
        )

        insert(publicCache, aLog)

        // 存入缓存
        saveAllianceLog2Cache(aLog)
    }

    fun saveAllianceLog2Cache(aLog: AllianceLog) {
        val aLogs = this.allianceLogMap.getOrPut(aLog.allianceId) {
            mutableMapOf()
        }

        aLogs[aLog.id] = aLog
        this.allianceLogMap[aLog.allianceId] = aLogs
    }

    fun findLogsByAllianceId(aid: Long): (MutableMap<Long, AllianceLog>) {
        return allianceLogMap.getOrPut(aid) {
            mutableMapOf()
        }
    }

    fun findLogOnceKey(aid: Long, key: String): (AllianceLog?) {
        val aLogs = allianceLogMap[aid]
        if (aid == 0L || key.isEmpty() || aLogs == null) {
            return null
        }

        for ((_, aLog) in aLogs) {
            if (aLog.onceKey.equals(key, true)) {
                return aLog
            }
        }

        return null
    }
}


fun deleteAllianceLog(aLogs: MutableMap<Long, AllianceLog>, aLogId: Long) {
    aLogs.remove(aLogId)
}
