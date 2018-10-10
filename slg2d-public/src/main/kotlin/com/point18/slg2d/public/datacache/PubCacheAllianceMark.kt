package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_MARK_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceMarkEntity
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration

class AllianceMark(
    var id: Long,

    var allianceId: Long,  //联盟ID
    var type: Int,               //类型：1-联盟标记；2-集结请求
    var playerId: Long,  //玩家ID
    var x: Int,             //坐标点X
    var y: Int,             //坐标点Y
    var title: String,        //标题
    var description: String,       //描述
    var markTime: Long,  //标记时间
    var pltAreaNo: Int            // 服务器编号

) : EntityWrapper<AllianceMarkEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0, "", "", 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceMarkEntity {
        return AllianceMarkEntity(
            id,
            allianceId,
            type,
            playerId,
            x,
            y,
            title,
            description,
            markTime,
            pltAreaNo

        )
    }

    override fun wrap(entity: AllianceMarkEntity) {
        id = entity.id
        allianceId = entity.allianceId
        type = entity.type
        playerId = entity.playerId
        x = entity.x
        y = entity.y
        title = entity.title
        description = entity.description
        markTime = entity.markTime
        pltAreaNo = entity.pltAreaNo
    }

}

class CacheAllianceMark(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceMarkMap = mutableMapOf<Long, MutableMap<Long, AllianceMark>>() // 公会标记缓存

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceMarks =
                session.getNamedQuery(ALLIANCE_MARK_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceMarks.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceMark()

                }

                this.saveAllianceMark2Cache(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceMark(
        publicCache: PublicCache,
        aid: Long,
        t: Int,
        pid: Long,
        x: Int,
        y: Int,
        pltAreaNo: Int,
        title: String,
        desp: String
    ): AllianceMark {
        val id = ppm.generateObjIdNew()
        val aMark = AllianceMark(
            id,
            aid,
            t,
            pid,
            x,
            y,
            title,
            desp,
            getNowTime(),
            pltAreaNo
        )

        insert(publicCache, aMark)

        // 保存到缓存
        saveAllianceMark2Cache(aMark)

        return aMark
    }

    fun saveAllianceMark2Cache(aMark: AllianceMark) {
        val aMarks = this.allianceMarkMap.getOrPut(aMark.allianceId) {
            mutableMapOf()
        }

        aMarks[aMark.id] = aMark
        this.allianceMarkMap[aMark.allianceId] = aMarks
    }

    // 删除联盟标记
    fun deleteAllianceMark(mark: AllianceMark?) {
        if (mark == null || mark.id == 0L) {
            return
        }

        delete(publicCache, mark)

        // 从缓存移除
        val aMarks = allianceMarkMap[mark.allianceId]
            ?: error("${mark.allianceId},${mark.id}")
        // 从联盟成员列表中将玩家移除
        aMarks.remove(mark.id)
        allianceMarkMap[mark.allianceId] = aMarks
    }

    // 根据联盟ID查找所有的标记
    fun findMarksByAllianceId(aid: Long): (MutableMap<Long, AllianceMark>) {
        val aMarks = allianceMarkMap[aid]
        if (aid == 0L || aMarks == null) {
            return mutableMapOf()
        }
        return aMarks
    }

    // 根据联盟ID与唯一ID查找标记
    fun findMarksByAllianceIdAndId(aid: Long, id: Long): (AllianceMark?) {
        val aMarkss = allianceMarkMap[aid]
        if (aid == 0L || aMarkss == null) {
            return null
        }
        return aMarkss[id]
    }

    // 根据联盟ID查找最近一次标记的时间点
    fun findLastMarkTimeAllianceId(aid: Long): (Long) {
        val aMarks = allianceMarkMap[aid]
        var t = 0L
        if (aid == 0L || aMarks == null) {
            return 0
        }
        for ((_, mark) in aMarks) {
            if (mark.markTime > t) {
                t = mark.markTime
            }
        }
        return t
    }

    // 根据类型、联盟ID、坐标查找
    fun findMarkByAidXY(t: Int, aid: Long, x: Int, y: Int, pltAreaNo: Int): (AllianceMark?) {

        val aMarks = allianceMarkMap[aid]
        if (aid == 0L || aMarks == null) {
            return null
        }

        for ((_, aMark) in aMarks) {
            if (aMark.type == t && aMark.x == x && aMark.y == y && aMark.pltAreaNo == pltAreaNo) {
                return aMark
            }
        }
        return null
    }
}



