package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.NOTICE_NAMED_QUERY
import com.point18.slg2d.common.worldentities.NoticeEntity
import com.point18.slg2d.world.WorldDatabase
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

// 公告
class Notice(
        var worldId: Long,
        var id: Long,

        private var noticeNextTime: Long,  // 下次公告时间
        private var content: String,       // 内容
        private var noticeTimeStart: Long,  // 公告时间(开始)
        private var noticeTimeEnd: Long,  // 公告时间(结束)
        private var frequency: Int,                // 频率（秒）
        private var noticeType: Int,                // 公告类型
        private var noticePosition: Int,                // 公告位置
        var noticeTid: Int                // 游族公告id
) : EntityWrapper<NoticeEntity> {
    constructor() : this(
        0, 0, 0, "", 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): NoticeEntity {
        return NoticeEntity(
            worldId,
            id,
            noticeNextTime,
            content,
            noticeTimeStart,
            noticeTimeEnd,
            frequency,
            noticeType,
            noticePosition,
            noticeTid
        )
    }

    override fun wrap(entity: NoticeEntity) {
        worldId = entity.worldId
        id = entity.id
        noticeNextTime = entity.noticeNextTime
        content = entity.content
        noticeTimeStart = entity.noticeTimeStart
        noticeTimeEnd = entity.noticeTimeEnd
        frequency = entity.frequency
        noticeType = entity.noticeType
        noticePosition = entity.noticePosition
        noticeTid = entity.noticeTid
    }
}

class CacheNotice(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val noticeMap = OneKeyIndex<Long, Notice> { it.id }
    private val noticeTidMap = OneKeyIndex<Int, Notice> { it.noticeTid }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.noticeEntities =
                session.getNamedQuery(NOTICE_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.noticeEntities.forEach { entity ->
            try {
                val notice = db.wdb.recover(entity) { Notice() }

                this.noticeMap.insertByKey(notice)
                this.noticeTidMap.insertByKey(notice)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

}