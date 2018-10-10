package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_REPLY_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceReplyEntity
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceReply(
    var id: Long,

    var topicId: Long,   // 主题ID
    var playerId: Long,   // 回复的玩家ID
    var message: String,     // 回复内容
    var replyAt: Long, // 回复时间
    var allianceId: Long

) : EntityWrapper<AllianceReplyEntity> {
    constructor() : this(0, 0, 0, "", 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceReplyEntity {
        return AllianceReplyEntity(
            id,
            allianceId,
            topicId,
            playerId,
            message,
            replyAt
        )
    }

    override fun wrap(entity: AllianceReplyEntity) {
        id = entity.id
        allianceId = entity.allianceId
        topicId = entity.topicId
        playerId = entity.playerId
        message = entity.message
        replyAt = entity.replyAt
    }

}

class CacheAllianceReply(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceReplyMap = mutableMapOf<Long, LinkedList<AllianceReply>>() // ( 联盟邮件主题回复缓存 Key:主题ID

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceReplies =
                    session.getNamedQuery(ALLIANCE_REPLY_NAMED_QUERY)
                        .setLong("allianceId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceReplies.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceReply()

                }

                this.saveAllianceReply2Cache(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceReply(topicId: Long, pid: Long, message: String): (AllianceReply) {
        val id = ppm.generateObjIdNew()
        val aReply = AllianceReply(
            id,
            topicId,
            pid,
            message,
            getNowTime(),
            id
        )

        insert(publicCache, aReply)

        // 添加到缓存中
        saveAllianceReply2Cache(aReply)

        return aReply
    }

    fun saveAllianceReply2Cache(aReply: AllianceReply) {
        val aReplies = this.allianceReplyMap.getOrPut(aReply.topicId) {
            LinkedList()
        }

        aReplies.add(aReply)
        this.allianceReplyMap[aReply.topicId] = aReplies
    }

    // 查询联盟中所有的邮件主题
    fun findAllianceRepliesByTopicId(topicId: Long): (LinkedList<AllianceReply>) {
        return allianceReplyMap.getOrPut(topicId) {
            LinkedList()
        }
    }

    fun deleteAllianceRepliesByTopicId(topicId: Long) {
        if (topicId == 0L) {
            return
        }

        // 从主题ID为索引的缓存中删除
        val aReplies = allianceReplyMap.getOrPut(topicId) {
            LinkedList()
        }
        for (aReply in aReplies) {
            delete(publicCache, aReply)
        }

        allianceReplyMap.remove(topicId)
    }
}



