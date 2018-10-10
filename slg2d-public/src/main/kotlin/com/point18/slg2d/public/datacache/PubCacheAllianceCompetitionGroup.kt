package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.publicentities.ALLIANCE_COMPETITION_GROUP_NAMED_ALL_QUERY
import com.point18.slg2d.common.publicentities.AllianceCompetitionGroupEntity
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.PublicMenagerDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceCompetitionGroup(
    var id: Long,

    var groupId: Int, // 组ID
    var stateRankLv: Int, // 活动开始时的段位
    var overRankLv: Int, // 活动结束的段位
    var score: Int, // 积分
    var allianceId: Long  //联盟ID

) : EntityWrapper<AllianceCompetitionGroupEntity> {

    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceCompetitionGroupEntity {
        return AllianceCompetitionGroupEntity(
            id,
            allianceId,
            groupId,
            stateRankLv,
            overRankLv,
            score
        )
    }

    override fun wrap(entity: AllianceCompetitionGroupEntity) {
        id = entity.id
        allianceId = entity.allianceId
        groupId = entity.groupId
        stateRankLv = entity.stateRankLv
        overRankLv = entity.overRankLv
        score = entity.score
    }

}

class CacheAllianceCompetitionGroupManager(val db: PublicMenagerDatabase) {

    val logger: LoggingAdapter = Logging.getLogger(db.aPublic.context.system(), javaClass)

    val allianceCompetitionGroupMapByAid =
        OneKeyIndex { it: AllianceCompetitionGroup -> it.allianceId }      // 帮派ID为KEY
    val allianceCompetitionGroupMapByGroupid = OneKeyIndexSlice({ it: AllianceCompetitionGroup -> it.groupId },
        { ita: AllianceCompetitionGroup, itb: AllianceCompetitionGroup -> ita.id == itb.id }) // 组ID为KEY

    fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceCompetitionGroups =
                    session.getNamedQuery(ALLIANCE_COMPETITION_GROUP_NAMED_ALL_QUERY)
                        .listNoDup()
        }
    }

    fun doInitDataForManager(publicInitData: PublicInitData) {
        publicInitData.allianceCompetitionGroups.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceCompetitionGroup()
                }

                this.allianceCompetitionGroupMapByGroupid.insertByKey(b)
                this.allianceCompetitionGroupMapByAid.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    fun createAllianceCompetitionGroup(groupId: Int, nowRankLv: Int, allianceId: Long): (AllianceCompetitionGroup) {
        val id = ppm.generateObjIdNew()
        val allianceCompetition =
            AllianceCompetitionGroup(id, groupId, nowRankLv, nowRankLv, 0, allianceId)


        insert(this.db, allianceCompetition)

        // 添加到缓存中
        allianceCompetitionGroupMapByAid.insertByKey(allianceCompetition)
        allianceCompetitionGroupMapByGroupid.insertByKey(allianceCompetition)

        return allianceCompetition
    }

    // 查询某帮派的参赛信息
    fun findAllianceCompetitionGroupByAllianceId(allianceId: Long): (AllianceCompetitionGroup?) {
        return allianceCompetitionGroupMapByAid.findByKey(allianceId)
    }

    // 查询某组里的所有帮派
    fun findAllianceCompetitionGroupsByGroupId(groupId: Int): (MutableMap<Long, AllianceCompetitionGroup>) {
        val acs = mutableMapOf<Long, AllianceCompetitionGroup>()

        allianceCompetitionGroupMapByGroupid.findByKey(groupId) {
            acs[it.allianceId] = it
            true
        }

        return acs
    }

    // 把所有的参赛组装成一个map
    fun findAllianceCompetitionGroups(): (MutableMap<Int, LinkedList<AllianceCompetitionGroup>>) {
        val acs = mutableMapOf<Int, LinkedList<AllianceCompetitionGroup>>()

        allianceCompetitionGroupMapByAid.map {
            val v = acs.getOrPut(it.stateRankLv) {
                LinkedList()
            }
            v.add(it)
            true
        }

        return acs
    }

    fun deleteAllianceCompetitionGroup(allianceCompetitionGroup: AllianceCompetitionGroup?) {
        if (allianceCompetitionGroup == null || allianceCompetitionGroup.id == 0L) {
            return
        }

        delete(this.db, allianceCompetitionGroup)

        // 从缓存中删除
        allianceCompetitionGroupMapByGroupid.deleteByKey(allianceCompetitionGroup)
        allianceCompetitionGroupMapByAid.deleteByKey(allianceCompetitionGroup)
    }
}
