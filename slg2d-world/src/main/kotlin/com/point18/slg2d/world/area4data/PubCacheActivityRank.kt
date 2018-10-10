package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.worldentities.ACTIVITY_RANK_NAMED_QUERY
import com.point18.slg2d.common.worldentities.ActivityRankEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class ActivityRank(
    var worldId: Long,
    var id: Long,

    var overTime: Long,  // 排行榜过期时间
    var rankInfoMap: HashMap<Int, ActivityRankVo>
) : EntityWrapper<ActivityRankEntity> {
    constructor() : this(0, 0, 0, hashMapOf())

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ActivityRankEntity {

        return ActivityRankEntity(
            worldId,
            id,
            overTime,
            toJson(rankInfoMap)
        )
    }

    override fun wrap(entity: ActivityRankEntity) {
        worldId = entity.worldId
        id = entity.id
        overTime = entity.overTime
        rankInfoMap = toObj(entity.rankInfo)
    }
}

class ActivityRankVo(
    var playerId: Long, // 玩家ID
    var playerName: String, // 玩家名
    var playerNickName: String, // 玩家昵称
    var allianceShortName: String, // 联盟简称
    var score: Int,    // 积分记录
    var photoId: Int    // 头像
) {
    constructor() : this(0, "", "", "", 0, 0)
}

class CacheActivityRank(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private var activityRankMap = OneKeyIndex<Long, ActivityRank> { it.id }

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.activityRanks =
                session.getNamedQuery(ACTIVITY_RANK_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.activityRanks.forEach { entity ->
            try {
                val rank = db.wdb.recover(entity) { ActivityRank() }

                activityRankMap.insertByKey(rank)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun createActivityRank(
        overTime: Long,
        rankInfoMap: HashMap<Int, ActivityRankVo>
    ): ActivityRank {
        val id = wpm.generateObjIdNew(areaCache)
        val activityRank = ActivityRank(worldId, id, overTime, rankInfoMap)

        insert(areaCache, activityRank)

        // 存入缓存
        activityRankMap.insertByKey(activityRank)

        return activityRank
    }

    // 根据ID拿活动排行榜信息
    fun findActivityRankById(id: Long): (ActivityRank?) {
        return activityRankMap.findByKey(id)
    }

    // 删除数据
    fun deleteActivityRankById(del: ActivityRank) {
        delete(areaCache, del)

        activityRankMap.deleteByKey(del)
    }

}