package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_MEMBER_TRACE_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceMemberTraceEntity
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceMemberTrace(
    var id: Long,

    var honor: Long,  // 总荣誉值
    var killSolider: Long,  // 总杀敌值
    var cureSolider: Long,  // 总牺牲值
    var killMonster: Long,  // 总猎杀魔物
    var weekHonor: Long,  // 周荣誉值
    var weekKillSolider: Long,  // 周杀敌值
    var weekCureSolider: Long,  // 周牺牲值
    var weekTransportationValue: Long,  // 周运输值
    var weekKillMonster: Long,  // 周猎杀魔物
    var weekMonsterScore: Long, //周魔物积分
    var monsterScore: Long, //累计的魔物积分
    var weekHelp: Long,  // 周帮助次数
    var allianceCompetitionScore: Long,  // 联盟总动员积分
    var allianceCompetitionScoreChangeTime: Long, // 联盟总动员积分变化时间
    var allianceCompetitionQuestSuccessNum: Int,       // 成功完成联盟总动员任务次数
    var allianceCompetitionQuestGetNum: Int,            // 接取联盟总动员任务次数
    var playerId: Long,
    var allianceId: Long // 联盟ID

) : EntityWrapper<AllianceMemberTraceEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceMemberTraceEntity {
        return AllianceMemberTraceEntity(
            id,
            allianceId, // 联盟ID
            honor,  // 总荣誉值
            killSolider,  // 总杀敌值
            cureSolider,  // 总牺牲值
            killMonster,  // 总猎杀魔物
            weekHonor,  // 周荣誉值
            weekKillSolider,  // 周杀敌值
            weekCureSolider,  // 周牺牲值
            weekTransportationValue,  // 周运输值
            weekKillMonster,  // 周猎杀魔物
            weekMonsterScore,//周魔物积分
            monsterScore, //累计的魔物积分
            weekHelp,  // 周帮助次数
            allianceCompetitionScore,  // 联盟总动员积分
            allianceCompetitionScoreChangeTime, // 联盟总动员积分变化时间
            allianceCompetitionQuestSuccessNum,       // 成功完成联盟总动员任务次数
            allianceCompetitionQuestGetNum,            // 接取联盟总动员任务次数
            playerId
        )
    }

    override fun wrap(entity: AllianceMemberTraceEntity) {
        id = entity.id
        allianceId = entity.allianceId // 联盟ID
        honor = entity.honor  // 总荣誉值
        killSolider = entity.killSolider // 总杀敌值
        cureSolider = entity.cureSolider  // 总牺牲值
        killMonster = entity.killMonster  // 总猎杀魔物
        weekHonor = entity.weekHonor  // 周荣誉值
        weekKillSolider = entity.weekKillSolider  // 周杀敌值
        weekCureSolider = entity.weekCureSolider  // 周牺牲值
        weekTransportationValue = entity.weekTransportationValue  // 周运输值
        weekKillMonster = entity.weekKillMonster  // 周猎杀魔物
        weekMonsterScore = entity.weekMonsterScore//周魔物积分
        monsterScore = entity.monsterScore //累计的魔物积分
        weekHelp = entity.weekHelp  // 周帮助次数
        allianceCompetitionScore = entity.allianceCompetitionScore  // 联盟总动员积分
        allianceCompetitionScoreChangeTime = entity.allianceCompetitionScoreChangeTime // 联盟总动员积分变化时间
        allianceCompetitionQuestSuccessNum = entity.allianceCompetitionQuestSuccessNum       // 成功完成联盟总动员任务次数
        allianceCompetitionQuestGetNum = entity.allianceCompetitionQuestGetNum            // 接取联盟总动员任务次数
        playerId = entity.playerId
    }
}

class CacheAllianceMemberTrace(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceMemberTraceByAllianceId = OneKeyIndexSlice({ it: AllianceMemberTrace -> it.allianceId },
        { ita: AllianceMemberTrace, itb: AllianceMemberTrace -> ita.id == itb.id })// 帮派ID key

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceMemberTraces =
                session.getNamedQuery(ALLIANCE_MEMBER_TRACE_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceMemberTraces.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceMemberTrace()

                }

                this.allianceMemberTraceByAllianceId.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceMemberTrace(playerId: Long, allianceId: Long): (AllianceMemberTrace) {
        val allianceMemberTrace = AllianceMemberTrace()
        val id = ppm.generateObjIdNew()
        allianceMemberTrace.id = id
        allianceMemberTrace.playerId = playerId
        allianceMemberTrace.allianceCompetitionScoreChangeTime = getNowTime()
        allianceMemberTrace.allianceId = allianceId

        insert(publicCache, allianceMemberTrace)
        allianceMemberTraceByAllianceId.insertByKey(allianceMemberTrace)

        return allianceMemberTrace
    }

    // 找到一个帮派的所有的(包含曾经来过的玩家)的排行数据
    fun findAllianceMemberTracesByAllianceId(
        allianceId: Long
    ): (LinkedList<AllianceMemberTrace>) {
        // 尝试从缓存中获取
        val rt = LinkedList<AllianceMemberTrace>()
        allianceMemberTraceByAllianceId.findByKey(allianceId) {
            rt.add(it)
        }
        return rt
    }

    fun deleteAllianceMemberTrace(allianceMemberTraceWrap: AllianceMemberTrace?) {
        if (allianceMemberTraceWrap == null || allianceMemberTraceWrap.id == 0L) {
            return
        }

        delete(publicCache, allianceMemberTraceWrap)

        // 从缓存中删除
        allianceMemberTraceByAllianceId.deleteByKey(allianceMemberTraceWrap)
    }

    // 获取帮派某个位置的信息
    fun findAllianceMemberTraceByAllianceIdAndPlayerId(
        allianceId: Long,
        playerId: Long
    ): (AllianceMemberTrace?) {

        var rt: AllianceMemberTrace? = null
        allianceMemberTraceByAllianceId.findByKey(allianceId) {
            if (it.playerId == playerId) {
                rt = it
            }

            it.playerId != playerId
        }

        return rt
    }
}


