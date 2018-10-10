package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_TOPIC_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceTopicEntity
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import java.io.Serializable
import java.time.Duration

class AllianceTopic(
    var id: Long,

    var allianceId: Long,      // 联盟ID
    var type: Int,                   // 类型 1-联盟全员 2-团成员
    var playerId: Long,         // 发布的玩家ID
    var title: String,    // 发布标题
    var createAt: Long, // 发布时间
    var lastAt: Long,// 最后一次回复时间
    var read: String      // 读取联盟邮件主题的时间
) : EntityWrapper<AllianceTopicEntity> {
    constructor() : this(0, 0, 0, 0, "", 0, 0, "")

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceTopicEntity {
        return AllianceTopicEntity(
            id,
            allianceId,
            type,
            playerId,
            title,
            createAt,
            lastAt,
            read
        )
    }

    override fun wrap(entity: AllianceTopicEntity) {
        id = entity.id
        allianceId = entity.allianceId
        type = entity.ttype
        playerId = entity.playerId
        title = entity.title
        createAt = entity.createAt
        lastAt = entity.lastAt
        read = entity.readT
    }
}

class CacheAllianceTopic(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceTopicMap = mutableMapOf<Long, MutableMap<Long, AllianceTopic>>() // 联盟邮件主题缓存 Key:联盟ID


    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceTopics =
                session.getNamedQuery(ALLIANCE_TOPIC_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceTopics.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceTopic()

                }

                this.saveAllianceTopic2Cache(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceTopic(aid: Long, t: Int, pid: Long, title: String): (AllianceTopic) {
        val reads = mutableMapOf<Long, Int>()
        val id = ppm.generateObjIdNew()
        reads[pid] = 1 // 创建这个主题的玩家已读
        val bs = toJson(reads)
        val aTopic = AllianceTopic(
            id,
            aid,
            t,
            pid,
            title,
            getNowTime(),
            getNowTime(),
            bs
        )


        insert(publicCache, aTopic)

        // 添加到缓存中
        saveAllianceTopic2Cache(aTopic)

        return aTopic
    }

    fun saveAllianceTopic2Cache(aTopic: AllianceTopic) {
        val aTopics = this.allianceTopicMap.getOrPut(aTopic.allianceId) {
            mutableMapOf()
        }

        aTopics[aTopic.id] = aTopic
        this.allianceTopicMap[aTopic.allianceId] = aTopics
    }

    // 查询联盟中所有的邮件主题
    fun findAllianceTopicsByAllianceId(aid: Long): (MutableMap<Long, AllianceTopic>) {
        return allianceTopicMap.getOrPut(aid) {
            mutableMapOf()
        }
    }

    // 根据联盟ID和主题ID查找对应的主题信息
    fun findAllianceTopicBy2Id(aid: Long, topicId: Long): (AllianceTopic?) {
        if (aid == 0L || topicId == 0L) {
            return null
        }

        val aTopics = allianceTopicMap[aid] ?: return null

        for ((_, aTopic) in aTopics) {
            if (aTopic.id == topicId) {
                return aTopic
            }
        }

        return null
    }

    fun deleteAllianceTopic(aTopic: AllianceTopic?) {
        if (aTopic == null || aTopic.id == 0L) {
            return
        }

        delete(publicCache, aTopic)

        // 从联盟ID为索引的缓存中删除
        val aTopics = allianceTopicMap[aTopic.allianceId]
        assert(aTopics != null)
        if (aTopics != null) {
            aTopics.remove(aTopic.allianceId)
            allianceTopicMap[aTopic.allianceId] = aTopics
        }
    }
 }