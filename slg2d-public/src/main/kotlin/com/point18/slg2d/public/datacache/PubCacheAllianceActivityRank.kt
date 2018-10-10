package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_ACTIVITY_RANK_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceActivityRankEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.ALLIANCE_GENERAL_PID
import java.io.Serializable
import java.time.Duration

class AllianceActivityRank(
    var id: Long,
    var publicId: Long,
    var overTime: Long,  // 过期时间
    var rankInfoMap: MutableMap<Int, AllianceActivityRankVo>

) : EntityWrapper<AllianceActivityRankEntity> {
    constructor() : this(0, 0, 0, mutableMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceActivityRankEntity {
        return AllianceActivityRankEntity(
            id,
            publicId,
            overTime,
            toJson(rankInfoMap)
        )
    }

    override fun wrap(entity: AllianceActivityRankEntity) {
        id = entity.id
        publicId = entity.publicId
        overTime = entity.overTime
        rankInfoMap = toObj(entity.rankInfo)
    }

}

data class AllianceActivityRankVo(
    val allianceId: Long,// 联盟Id
    val allianceName: String,// 联盟名
    val allianceShortName: String,// 联盟简称
    val score: Int,    // 积分记录
    val flagColor: Int,             // 联盟旗帜的颜色
    val flagStyle: Int,            // 联盟旗帜的样式
    val flagEffect: Int            // 联盟旗帜图案
)

class CacheAllianceActivityRank(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceActivityRankMap = OneKeyIndex { it: AllianceActivityRank -> it.id }// 缓存

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceActivityRanks =
                    session.getNamedQuery(ALLIANCE_ACTIVITY_RANK_NAMED_QUERY)
                        .setLong("publicId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceActivityRanks.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceActivityRank()
                }

                this.allianceActivityRankMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }


    fun createAllianceActivityRank(
        overTime: Long,
        rankInfos: MutableMap<Int, AllianceActivityRankVo>
    ): (AllianceActivityRank) {
        val activityRank = AllianceActivityRank()

        val id = ppm.generateObjIdNew()
        activityRank.id = id
        activityRank.publicId = ALLIANCE_GENERAL_PID
        activityRank.overTime = overTime
        activityRank.rankInfoMap = rankInfos
        insert(publicCache, activityRank)

        // 存入缓存
        allianceActivityRankMap.insertByKey(activityRank)

        return activityRank
    }

    // 根据活动ID拿活动信息
    fun findActivityRankById(id: Long): (AllianceActivityRank?) {
        return allianceActivityRankMap.findByKey(id) ?: return null
    }

    // 删除数据
    fun deleteAllianceActivityRankById(del: AllianceActivityRank) {
        allianceActivityRankMap.deleteByKey(del)
        delete(publicCache, del)
    }
}
