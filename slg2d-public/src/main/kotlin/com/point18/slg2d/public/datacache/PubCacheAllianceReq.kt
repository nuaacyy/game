package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_REQ_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceReqEntity
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceReq(
    var id: Long,

    var allianceId: Long,   //联盟ID
    var playerId: Long,  //玩家ID
    var playerName: String,   // 玩家名
    var playerPhoto: Int,   // 玩家头像
    var playerFightValue: Long,     //玩家战斗力
    var reqTime: Long,//请求时间
    var pltAreaNo: Long,
    var areaNo: Int

) : EntityWrapper<AllianceReqEntity> {
    constructor() : this(0, 0, 0, "", 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceReqEntity {
        return AllianceReqEntity(
            id,
            allianceId,
            playerId,
            playerName,
            playerPhoto,
            playerFightValue,
            reqTime,
            pltAreaNo,
            areaNo
        )
    }

    override fun wrap(entity: AllianceReqEntity) {
        id = entity.id
        allianceId = entity.allianceId
        playerId = entity.playerId
        playerName = entity.playerName
        playerPhoto = entity.playerPhoto
        playerFightValue = entity.playerFightValue
        reqTime = entity.reqTime
        pltAreaNo = entity.pltAreaNo
        areaNo = entity.areaNo
    }
}

class CacheAllianceReq(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val mapByAllianceId = TwoKeyIndex({ it: AllianceReq -> it.allianceId },
        { it: AllianceReq -> it.playerId }) // 联盟的玩家申请缓存	联盟ID:玩家ID:申请信息
    val mapByPlayerId = TwoKeyIndex({ it: AllianceReq -> it.playerId },
        { it: AllianceReq -> it.allianceId }) // 玩家的联盟申请缓存	玩家ID:联盟ID:申请信息

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceReqs =
                session.getNamedQuery(ALLIANCE_REQ_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceReqs.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceReq()

                }

                this.saveAllianceReq2Cache(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }



    // 创建玩家加入联盟请求
    fun createAllianceReq(
        aid: Long,
        pid: Long,
        fightValue: Long,
        name: String,
        photo: Int,
        pltAreaNo: Long,
        areaNo: Int
    ) {
        val id = ppm.generateObjIdNew()
        val aReq = AllianceReq(
            id,
            aid,
            pid,
            name,
            photo,
            fightValue,
            getNowTime(),
            pltAreaNo,
            areaNo
        )

        insert(publicCache, aReq)

        // 存入缓存
        saveAllianceReq2Cache(aReq)
    }

    fun saveAllianceReq2Cache(aReq: AllianceReq) {
        // 以联盟ID为索引的缓存
        this.mapByAllianceId.insertByKey(aReq)
        // 已玩家ID为索引的缓存
        this.mapByPlayerId.insertByKey(aReq)
    }

    fun findReqsByPlayerId(pid: Long): (LinkedList<AllianceReq>) {
        val aReqs = LinkedList<AllianceReq>()
        mapByPlayerId.findByOneKeyFilter(pid) {
            aReqs.add(it)
        }
        return aReqs
    }

    // 删除对应的请求
    fun deleteAllianceReq(req: AllianceReq?) {
        if (req == null || req.id == 0L) {
            return
        }

        delete(publicCache, req)

        // 从联盟ID为索引的缓存中删除
        mapByAllianceId.deleteByKey(req)

        // 从玩家ID为索引的缓存中删除
        mapByPlayerId.deleteByKey(req)
    }

    //根据玩家ID和联盟ID获取对应请求信息
    fun findAllianceReqByAidWithPid(aid: Long, pid: Long): (AllianceReq?) {
        if (aid == 0L || pid == 0L) {
            return null
        }

        val aReq = mapByAllianceId.findByKey(aid, pid)
        if (aReq == null) {
            return null
        } else {
            return aReq
        }
    }
}









//根据联盟ID获取所有申请加入本联盟的请求
fun findAllianceReqsByAllianceId(publicCache: PublicCache, aid: Long): (LinkedList<AllianceReq>) {
    val aReqs = LinkedList<AllianceReq>()
    publicCache.allianceReqCache.mapByAllianceId.findByOneKeyFilter(aid) {
        aReqs.add(it)
        true
    }

    return aReqs
}

//根据联盟ID获取所有申请加入本联盟的请求中最近的一个
fun findAllianceLastReqTimeByAllianceId(publicCache: PublicCache, aid: Long): (Long) {
    val aReqs = LinkedList<AllianceReq>()
    publicCache.allianceReqCache.mapByAllianceId.findByOneKeyFilter(aid) {
        aReqs.add(it)
        true
    }

    var t = 0L
    for (aReq in aReqs) {
        if (aReq.reqTime > t) {
            t = aReq.reqTime
        }
    }

    return t
}