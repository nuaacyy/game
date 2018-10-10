package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_HELP_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceHelpEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceHelp(
    var id: Long,

    var helpType: Int,             // 帮助类型  1-研发帮助
    var nowHelpNum: Int,                 // 当前帮助次数
    var helpPlayerId: Long,  // 需要帮助的玩家ID
    var helpValue1: Long,  // 信息1  对于不同的帮助类型有不同的意义,如 研发帮助 这个字段表示科技ID
    var helpValue2: Long,  // 信息2  对于不同的帮助类型有不同的意义,如 研发帮助 这个字段表示科技等级
    var helpValue3: Long,  // 信息3  对于不同的帮助类型有不同的意义,如 研发帮助 这个字段暂无意义,预留
    var helpValue4: Long,  // 信息4  对于不同的帮助类型有不同的意义,如 研发帮助 这个字段暂无意义,预留
    var sendTime: Long, // 发布的时间
    var helperIds: LinkedList<Long>, // 已经帮助过我的玩家的ID , 这个只是用来打开界面的时候做掉判断而已,帮助的时候会根据被帮助玩家的数据为准
    var allianceId: Long

) : EntityWrapper<AllianceHelpEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, LinkedList(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceHelpEntity {
        return AllianceHelpEntity(
            id,
            allianceId,
            helpType,
            nowHelpNum,
            helpPlayerId,
            helpValue1,
            helpValue2,
            helpValue3,
            helpValue4,
            sendTime,
            toJson(helperIds)
        )
    }

    override fun wrap(entity: AllianceHelpEntity) {
        id = entity.id
        allianceId = entity.allianceId
        helpType = entity.helpType
        nowHelpNum = entity.nowHelpNum
        helpPlayerId = entity.helpPlayerId
        helpValue1 = entity.helpValue1
        helpValue2 = entity.helpValue2
        helpValue3 = entity.helpValue3
        helpValue4 = entity.helpValue4
        sendTime = entity.sendTime
        helperIds = toObj(entity.helperIds)
    }
}

class CacheAllianceHelp(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allAllianceHelpMap = OneKeyIndex { it: AllianceHelp -> it.id }      // 联盟帮助表 Key:唯一ID

    val allianceHelpMap = OneKeyIndexSlice({ it: AllianceHelp -> it.allianceId },
        { ita: AllianceHelp, itb: AllianceHelp -> ita.id == itb.id }) // 联盟帮助表 Key:帮派ID

    val allianceHelpMapByPlayer = OneKeyIndexSlice({ it: AllianceHelp -> it.helpPlayerId },
        { ita: AllianceHelp, itb: AllianceHelp -> ita.id == itb.id }) // 联盟帮助表 Key:帮派ID

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceHelps =
                    session.getNamedQuery(ALLIANCE_HELP_NAMED_QUERY)
                        .setLong("allianceId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceHelps.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceHelp()

                }

                this.allianceHelpMap.insertByKey(b)
                this.allAllianceHelpMap.insertByKey(b)
                this.allianceHelpMapByPlayer.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceHelp(
        helpType: Int, helpPlayerId: Long,
        helpValue1: Long, helpValue2: Long, helpValue3: Long, helpValue4: Long, allianceId: Long
    ): (AllianceHelp) {
        val id = ppm.generateObjIdNew()
        val allianceHelp = AllianceHelp(
            id,
            helpType,
            0,
            helpPlayerId,
            helpValue1,
            helpValue2,
            helpValue3,
            helpValue4,
            getNowTime(),
            LinkedList(),
            allianceId //联盟ID
        )

        insert(publicCache, allianceHelp)

        // 添加到缓存中
        allAllianceHelpMap.insertByKey(allianceHelp)
        allianceHelpMap.insertByKey(allianceHelp)
        allianceHelpMapByPlayer.insertByKey(allianceHelp)

        return allianceHelp
    }

    // 查询联盟中所有的帮助信息
    fun findAllianceHelpById(id: Long): (AllianceHelp?) {
        return allAllianceHelpMap.findByKey(id)
    }

    // 查询联盟中所有的帮助信息
    fun findAllianceHelpByAllianceId(allianceId: Long): (LinkedList<AllianceHelp>) {
        val helps = LinkedList<AllianceHelp>()
        allianceHelpMap.findByKey(allianceId) {
            helps.add(it)
        }
        return helps
    }

    // 查询某玩家所有的帮助信息
    fun findAllianceHelpByPlayerId(playerId: Long): (LinkedList<AllianceHelp>) {
        val helps = LinkedList<AllianceHelp>()
        allianceHelpMapByPlayer.findByKey(playerId) {
            helps.add(it)
        }
        return helps
    }

    // 根据联盟ID查找最近一次帮助的时间点
    fun findLastHelpTimeByAllianceId(aid: Long): Long {
        var t = 0L
        val helps = findAllianceHelpByAllianceId(aid)
        for (help in helps) {
            if (help.sendTime > t) {
                t = help.sendTime
            }
        }
        return t
    }

    fun deleteAllianceHelp(allianceHelp: AllianceHelp?) {
        if (allianceHelp == null || allianceHelp.id == 0L) {
            return
        }

        delete(publicCache, allianceHelp)

        // 从缓存中删除
        allAllianceHelpMap.deleteByKey(allianceHelp)
        allianceHelpMap.deleteByKey(allianceHelp)
        allianceHelpMapByPlayer.deleteByKey(allianceHelp)
    }
}

