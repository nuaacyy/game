package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_COMPETITION_QUEST_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceCompetitionQuestEntity
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceCompetitionQuest(
    var id: Long,

    var index: Int,       // 任务位置
    var questId: Int,          // 任务模版ID
    var refTime: Long,// 刷新时间
    var allianceId: Long //联盟ID

) : EntityWrapper<AllianceCompetitionQuestEntity> {
    constructor() : this(0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceCompetitionQuestEntity {
        return AllianceCompetitionQuestEntity(
            id,
            allianceId,
            index,
            questId,
            refTime
        )
    }

    override fun wrap(entity: AllianceCompetitionQuestEntity) {
        id = entity.id
        allianceId = entity.allianceId
        index = entity.indexAddress
        questId = entity.questId
        refTime = entity.refTime
    }

}

class CacheAllianceCompetitionQuest(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceCompetitionQuestByAllianceId = OneKeyIndexSlice({ it: AllianceCompetitionQuest -> it.allianceId },
        { ita: AllianceCompetitionQuest, itb: AllianceCompetitionQuest -> ita.id == itb.id }) // 帮派ID key

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceCompetitionQuests =
                session.getNamedQuery(ALLIANCE_COMPETITION_QUEST_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceCompetitionQuests.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceCompetitionQuest()
                }

                this.allianceCompetitionQuestByAllianceId.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceCompetitionQuest(index: Int, questId: Int, allianceId: Long): (AllianceCompetitionQuest) {
        val allianceCompetitionQuest = AllianceCompetitionQuest()

        val id = ppm.generateObjIdNew()
        allianceCompetitionQuest.id = id
        allianceCompetitionQuest.index = index
        allianceCompetitionQuest.questId = questId
        allianceCompetitionQuest.refTime = 0
        allianceCompetitionQuest.allianceId = allianceId

        insert(publicCache, allianceCompetitionQuest)
        allianceCompetitionQuestByAllianceId.insertByKey(allianceCompetitionQuest)

        return allianceCompetitionQuest
    }

    // 找到一个帮派的所有总动员任务
    fun findAllianceCompetitionQuestsByAllianceId(allianceId: Long): (LinkedList<AllianceCompetitionQuest>) {
        // 尝试从缓存中获取
        val rt = LinkedList<AllianceCompetitionQuest>()
        allianceCompetitionQuestByAllianceId.findByKey(allianceId) {
            rt.add(it)
        }
        return rt
    }

    fun deleteAllianceCompetitionQuest(allianceCompetitionQuest: AllianceCompetitionQuest?) {
        if (allianceCompetitionQuest == null || allianceCompetitionQuest.id == 0L) {
            return
        }
        delete(publicCache, allianceCompetitionQuest)
        // 从缓存中删除
        allianceCompetitionQuestByAllianceId.deleteByKey(allianceCompetitionQuest)
    }

    // 获取帮派某个位置的信息
    fun findAllianceCompetitionQuestByAllianceIdAndIndex(allianceId: Long, index: Int): (AllianceCompetitionQuest?) {
        var rt: AllianceCompetitionQuest? = null
        allianceCompetitionQuestByAllianceId.findByKey(allianceId) {
            if (it.index == index) {
                rt = it
            }
            it.index != index
        }

        return rt
    }
}
