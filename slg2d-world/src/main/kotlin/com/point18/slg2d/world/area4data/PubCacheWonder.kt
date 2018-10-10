package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.BIG_WONDER
import com.point18.slg2d.common.constg.NOT_IN_WONDER_WAR
import com.point18.slg2d.common.constg.PEACE
import com.point18.slg2d.common.constg.WAR_NORMAL
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.WONDER_NAMED_QUERY
import com.point18.slg2d.common.worldentities.WonderEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.common.isWonderPeace
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 奇观信息
class Wonder(
    var worldId: Long,
    var id: Long,

    var wonderProtoId: Int,                          // 奇观配置Id
    var status: Int,                            // 奇观显示状态 1、和平 2、争夺 3、争夺失败状态(冒烟) 4、争夺胜利状态(冒火)
    var statusOverTime: Long,                   // 奇观冒烟/冒火状态 结束时间
    var warStartTime: Long,                     // 开始争夺时间，和平时期指示下次争夺时间
    var occupyStartTime: Long,                  // 占领开始计算的时间
    var occupyOverTime: Long,                   // 占领时间
    var warFinishTime: Long,                    // 结束争夺时间，跟随部队占领
    var occupyGroupId: Long,                    // 占领的部队Id
    var belongToAllianceId: Long,               // 归属的联盟Id
    var notice: String,                         // 公告
    var officeMap: HashMap<Long, Int>,        // 玩家Id-官职Id
    var buffMap: HashMap<Int, LinkedList<KingBuff>>,        // 当前的王国buff <kingBuffProtoId, List[0]-通用 [1]-top1 [2]-top2 [3]-top3>
    var buffCoolTime: Long,                     // Buff冷却结束时间
    var pardonCount: Int,                       // 剩余赦免次数
    var lastNoticeTime: Long,                   // 上次发送公告时间
    var rankInfoMap: HashMap<Long, WonderRankVo>,           // 奇观占领时长（毫秒）信息<allianceId, rankVo>
    var awardInfoMap: HashMap<Int, LinkedList<Long>>,        // 赏赐信息<赏赐Id-[]玩家Id>
    var wonderWarStatus: Int,                    // 奇观战状态
    var initTime: Long                          // 奇观初始化时间
) : EntityWrapper<WonderEntity> {
    constructor() : this(
        0L, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, "", hashMapOf(),
        hashMapOf(), 0, 0, 0, hashMapOf(), hashMapOf(),
        0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): WonderEntity {
        return WonderEntity(
            worldId,
            id,
            wonderProtoId,
            status,
            statusOverTime,
            warStartTime,
            occupyStartTime,
            occupyOverTime,
            warFinishTime,
            occupyGroupId,
            belongToAllianceId,
            notice,
            toJson(officeMap),
            toJson(buffMap),
            buffCoolTime,
            pardonCount,
            lastNoticeTime,
            toJson(rankInfoMap),
            toJson(awardInfoMap),
            wonderWarStatus,
            initTime
        )
    }

    override fun wrap(entity: WonderEntity) {
        worldId = entity.worldId
        id = entity.id
        wonderProtoId = entity.wonderProtoId
        status = entity.status
        statusOverTime = entity.statusOverTime
        warStartTime = entity.warStartTime
        occupyStartTime = entity.occupyStartTime
        occupyOverTime = entity.occupyOverTime
        warFinishTime = entity.warFinishTime
        occupyGroupId = entity.occupyGroupId
        belongToAllianceId = entity.belongToAllianceId
        notice = entity.notice
        officeMap = toObj(entity.officeInfo)
        buffMap = toObj(entity.buffInfo)
        buffCoolTime = entity.buffCoolTime
        pardonCount = entity.pardonCount
        lastNoticeTime = entity.lastNoticeTime
        rankInfoMap = toObj(entity.rankInfo)
        awardInfoMap = toObj(entity.awardInfo)
        wonderWarStatus = entity.wonderWarStatus
        initTime = entity.initTime
    }
}

data class KingBuff(
    var id: Long,           // 唯一Id
    var protoId: Int,       // Buff配置Id
    var startTime: Long,    // Buff开始时间
    var endTime: Long       // Buff结束时间（即Buff冷却开始时间）
)

data class WonderRankVo(
    val allianceId: Long,                   // 联盟Id
    val allianceShortName: String,          // 联盟名称
    val allianceName: String,               // 联盟简称
    val flagColor: Int,                     // 联盟旗帜的颜色
    val flagStyle: Int,                     // 联盟旗帜的样式
    val flagEffect: Int,                    // 联盟旗帜图案
    var score: Long                         // 积分记录
) {
    constructor() : this(0, "", "", 0, 0, 0, 0)
}

//奇观缓存
class CacheWonder(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val wonderMap = OneKeyIndex { wonder: Wonder -> wonder.wonderProtoId } //奇观缓存

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.wonderEntities =
                    session.getNamedQuery(WONDER_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
            val initTime = getNowTime()
            for ((_, wonderProto) in pcs.wonderLocationProtoCache.wonderLocationProtoMap) {
                if (worldInitData.wonderEntities.firstOrNull { it.wonderProtoId == wonderProto.id } != null) {
                    continue
                }

                //创建奇观
                val id = wpm.generateObjIdNew(areaCache)
                val wonder = Wonder(
                    worldId,
                    id,
                    wonderProto.id,
                    PEACE,
                    0,
                    0,
                    maxTime.time,
                    maxTime.time,
                    0,
                    0L,
                    0L,
                    "",
                    hashMapOf(),
                    hashMapOf(),
                    zeroTime.time,
                    pcs.basicProtoCache.pardom,
                    zeroTime.time,
                    hashMapOf(),
                    hashMapOf(),
                    NOT_IN_WONDER_WAR,
                    initTime
                )
                session.save(wonder.toEntity())
                val entities = LinkedList(worldInitData.wonderEntities)
                entities.add(wonder.toEntity())
                worldInitData.wonderEntities = entities
            }
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.wonderEntities.forEach { entity ->
            try {
                val wonder = db.wdb.recover(entity) { Wonder() }

                wonderMap.insertByKey(wonder)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
        /** 根据basic配置设置时间 **/
        val startWeek = pcs.basicProtoCache.wonderWarOpen[0]
        val endWeek = pcs.basicProtoCache.wonderWarClose[0]
        val startHour = pcs.basicProtoCache.wonderWarOpen[1]
        val endHour = pcs.basicProtoCache.wonderWarClose[1]
        val startProtectTime = pcs.wonderLocationProtoCache.startWonderProtectTime()

        // 根据配置计算奇观开始结束时间与当前状态
        var warStartTime = getWeekDateZeroTime(0, startWeek) + startHour * 3600 * 1000
        var warFinishTime = getWeekDateZeroTime(0, endWeek) + endHour * 3600 * 1000
        var bigWonder = findBigWonder()
        if (bigWonder == null) {
            normalLog.error("找不到大奇观")
            return
        }
        val initTime = bigWonder.initTime

        var ifReSetTime = false

        wonderMap.map {
            if (getNowTime() < it.warStartTime - startProtectTime) {
                // 在奇观开始保护时间前可以根据配置重置
                ifReSetTime = true
            }
            if (it.warStartTime == 0L || ifReSetTime) {
                // 根据配置重置时间
                val baseStartZeroTime = getWeekDateZeroTime(initTime, startWeek) // 开服那周奇观开始日期零点时间
                val startZeroTime = getWeekDateZeroTime(warStartTime, startWeek) // 奇观当前的开始日期的零点时间
                if (baseStartZeroTime == startZeroTime) {
                    // 初始开始化时间与开服时间同周，延后一周 //todo jh 延迟时间要配置化
                    warStartTime += 7 * 24 * 3600 * 1000
                    warFinishTime += 7 * 24 * 3600 * 1000
                }
                val status = if (getNowTime() in warStartTime..warFinishTime) {
                    WAR_NORMAL
                } else {
                    PEACE
                }
                it.warStartTime = warStartTime
                it.warFinishTime = warFinishTime
                it.status = status
            }
            true
        }

        // 把这个区的大奇观状态记录下来
        bigWonder = findBigWonder()
        if (bigWonder != null) {
            areaCache.worldActor.wonder = bigWonder.status
        }
    }

    //查找结束占领的奇观
    fun findOverOccupiedWonder(): List<Wonder> {
        val overWarWonders = LinkedList<Wonder>()
        wonderMap.map {
            if (isWonderPeace(it)) {
                return@map true
            }
            if (getNowTime() < it.occupyOverTime) {
                return@map true
            }
            overWarWonders.add(it)
        }
        return overWarWonders
    }

    //查找奇观
    fun findWonder(wounderId: Int): Wonder? {
        return wonderMap.findByKey(wounderId)
    }

    //查找所有奇观
    fun findAllWonders(): List<Wonder> {
        val wonders = LinkedList<Wonder>()
        wonderMap.map { wonders.add(it) }
        return wonders
    }

    //查找大奇观
    fun findBigWonder(): Wonder? {
        val bigWonder = wonderMap.findByKey(BIG_WONDER)
        if (bigWonder == null) {
            assert(false) { "无大奇观数据" }
        }
        return bigWonder
    }
}