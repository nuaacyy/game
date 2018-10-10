package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.AREA_ONLY_NAMED_QUERY
import com.point18.slg2d.common.worldentities.AreaOnlyEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.common.mapHelper
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*
import java.util.Arrays.asList

// 游戏区的汇总信息
class AreaOnly(
    var worldId: Long,
    var id: Long,

    var nowPlayerNum: Int,                  // 本区玩家进入数量
    var nowCreatePlayerOnArea: Int,         // 当前新玩家进入游戏是投在哪个小方块内
    var nowCreatePlayerCircleNum: Int,      // 当前新玩家进入游戏的投点已经是第几轮了  人数 >= 该值 * basic.bornUp + basic.bornFrist 的时候,NowCreatePlayerOnArea转换到下一个
    var everySmallMapPlayerNumMap: HashMap<Int, MapAreaPlayerInfo>, // 该大地图上的各个生态小方块内的玩家情况
    var lastWayForMapRefresh: Int, // 上次刷新大地图的时候用的那个人数方案(用来重启服务器之后的刷新,如果是0就表示是新服)  无使用，待删除
    var enterGamePlayerNumForMapRefreshMap: HashMap<Long, Int>, // 距离上次刷新大地图元素之后的拖动过大地图的不同玩家的map 无使用，待删除
    var initedMap: Boolean, //是否初始化地图
    var nextRefBossTime: Long, //下次刷新魔物的时间
    var nextJjcDayRewardTime: Long // 下次jjc每日奖励发放时间
) : EntityWrapper<AreaOnlyEntity> {
    constructor() : this(
        0, 0, 0, 0, 0,
        hashMapOf(), 0, hashMapOf(), false, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AreaOnlyEntity {
        return AreaOnlyEntity(
            worldId,
            id,
            nowPlayerNum,
            nowCreatePlayerOnArea,
            nowCreatePlayerCircleNum,
            toJson(everySmallMapPlayerNumMap),
            lastWayForMapRefresh,
            boolToInt(initedMap),
            nextRefBossTime,
            nextJjcDayRewardTime
        )
    }

    override fun wrap(entity: AreaOnlyEntity) {
        worldId = entity.worldId
        id = entity.id
        nowPlayerNum = entity.nowPlayerNum
        nowCreatePlayerOnArea = entity.nowCreatePlayerOnArea
        nowCreatePlayerCircleNum = entity.nowCreatePlayerCircleNum
        everySmallMapPlayerNumMap = toObj(entity.everySmallMapPlayerNum)
        lastWayForMapRefresh = entity.lastWayForMapRefresh
        enterGamePlayerNumForMapRefreshMap = hashMapOf()
        initedMap = intToBool(entity.initedMap)
        nextRefBossTime = entity.nextRefBossTime
        nextJjcDayRewardTime = entity.nextJjcDayRewardTime
    }
}

class MapAreaPlayerInfo(
    var playerNum: Int
) {
    constructor() : this(0)
}

class CacheAreaOnly(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private lateinit var areaOnly: AreaOnly

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.areaOnlyEntity =
                    session.getNamedQuery(AREA_ONLY_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
            if (worldInitData.areaOnlyEntity.isNotEmpty()) {
                return@execWithTransaction
            }

            val id = wpm.generateObjIdNew(areaCache)
            val nextJjcDayRewardTime = pcs.basicProtoCache.getNextJjcDayRewardTime(getNowTime(), getNowTime())
            val areaOnly = AreaOnly(
                worldId, id, 1, 0, 0,
                hashMapOf(), 0, hashMapOf(), false, 0, nextJjcDayRewardTime
            )
            worldInitData.areaOnlyEntity = asList(areaOnly.toEntity())
            session.save(areaOnly.toEntity())
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.areaOnlyEntity.forEach { entity ->
            try {
                val ao = db.wdb.recover(entity) { AreaOnly() }

                areaOnly = ao

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun findAreaOnly(): (AreaOnly) {
        return areaOnly
    }

    fun isInitedMap(): Boolean {
        return areaOnly.initedMap
    }

    fun initMap() {
        println("initMap - 尝试refreshMap")

        mapHelper.refreshAllMap(areaCache)
        areaOnly.initedMap = true
    }

}

fun putEverySmallMapPlayerNumMap(areaOnly: AreaOnly, smallMapId: Int, info: MapAreaPlayerInfo) {
    areaOnly.everySmallMapPlayerNumMap[smallMapId] = info
}



