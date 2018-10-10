package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getWeekDateZeroTime
import com.point18.slg2d.common.constg.BOSS_DISAPPEAR
import com.point18.slg2d.common.constg.BOSS_WAIT_REFRESH
import com.point18.slg2d.common.constg.NOT_SEND_ADVANCE_MAIL
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.ACTIVITY_BOSS_AREA_NAMED_QUERY
import com.point18.slg2d.common.worldentities.ActivityBossAreaEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 活动boss区域
class ActivityBossArea(
    var worldId: Long,
    var id: Long,

    var locationId: Int,                // 底座模板Id MonsterActivityLocation表的Id
    var activityBossId: Int,        // monsterActivity表的id（随刷新改变，中间冷却时为上个bossId）
    var startTime: Long,            // 开始时间
    var refreshTime: Long,          // 刷新时间
    var finishTime: Long,           // 结束时间
    var status: Int,                // boss状态  0-未刷 1-有boss 2-刷新冷却
    var advanceMail: Int,            // 预告邮件
    var initTime: Long              // 初始化时间

) : EntityWrapper<ActivityBossAreaEntity> {
    constructor() : this(
        0L, 0, 0, 0,
        0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): ActivityBossAreaEntity {
        return ActivityBossAreaEntity(
            worldId,
            id,
            locationId,
            activityBossId,
            startTime,
            refreshTime,
            finishTime,
            status,
            advanceMail,
            initTime
        )
    }

    override fun wrap(entity: ActivityBossAreaEntity) {
        worldId = entity.worldId
        id = entity.id
        locationId = entity.locationId
        activityBossId = entity.activityBossId
        startTime = entity.startTime
        refreshTime = entity.refreshTime
        finishTime = entity.finishTime
        status = entity.status
        advanceMail = entity.advanceMail
        initTime = entity.initTime
    }
}

// 活动boss区域缓存
class CacheActivityBossArea(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val activityBossAreaMap = OneKeyIndex<Int, ActivityBossArea> { it.locationId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.activityBossAreaEntities =
                    session.getNamedQuery(ACTIVITY_BOSS_AREA_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
            val nowTime = getNowTime()
            for ((_, locationProto) in pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap) {
                if (worldInitData.activityBossAreaEntities.firstOrNull { it.locationId == locationProto.id } != null) {
                    continue
                }

                // 创建活动Boss区域
                val id = wpm.generateObjIdNew(areaCache)
                val activityBossArea = ActivityBossArea(
                    worldId,
                    id,
                    locationProto.id,
                    0,
                    0L,
                    0L,
                    0L,
                    0,
                    NOT_SEND_ADVANCE_MAIL,
                    nowTime
                )
                session.save(activityBossArea.toEntity())
                val entities = LinkedList(worldInitData.activityBossAreaEntities)
                entities.add(activityBossArea.toEntity())
                worldInitData.activityBossAreaEntities = entities
            }
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.activityBossAreaEntities.forEach { entity ->
            try {
                val activityBossArea = db.wdb.recover(entity) { ActivityBossArea() }

                activityBossAreaMap.insertByKey(activityBossArea)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
        /** 根据basic配置设置时间 **/
        val startWeek = pcs.basicProtoCache.monsterActivityOpen[0]
        val startHour = pcs.basicProtoCache.monsterActivityOpen[1]
        val continueSec = pcs.basicProtoCache.monsterActivityContinue
        val advanceSec = pcs.basicProtoCache.monsterActivityAdvance

        var startTime = getWeekDateZeroTime(0, startWeek) + startHour * 3600 * 1000
        var finishTime = startTime + continueSec * 1000

        // 筛选出最早的初始化时间作为开服时间
        val initTime = getInitAreaTime()

        var ifReSetTime = false
        activityBossAreaMap.map {
            if (getNowTime() < it.startTime - advanceSec * 1000) {
                // 在通知开始前可以根据配置重置
                ifReSetTime = true
            }
            if (it.startTime == 0L || ifReSetTime) {
                // 根据配置重置时间
                val baseStartZeroTime = getWeekDateZeroTime(initTime, startWeek) // 开服那周开始日期零点时间
                val startZeroTime = getWeekDateZeroTime(startTime, startWeek) // 当前的开始日期的零点时间
                if (baseStartZeroTime == startZeroTime) {
                    // 初始开始化时间与开服时间同周，延后一周
                    startTime += 7 * 24 * 3600 * 1000
                    finishTime += 7 * 24 * 3600 * 1000
                }
                it.startTime = startTime
                it.refreshTime = it.startTime
                it.finishTime = finishTime
            }
            true
        }
    }

    fun findAllActivityBossArea(): List<ActivityBossArea> {
        val areas = LinkedList<ActivityBossArea>()
        activityBossAreaMap.map { areas.add(it) }
        return areas
    }

    fun findActivityBossAreaByLocationId(locationId: Int): ActivityBossArea? {
        return activityBossAreaMap.findByKey(locationId)
    }

    //查找需要出现活动boss的活动boss区域
    fun findBossAreaAppear(): List<ActivityBossArea> {
        val areas = LinkedList<ActivityBossArea>()
        val nowTime = getNowTime()
        activityBossAreaMap.map {
            if (nowTime < it.startTime) {
                return@map true
            }
            if (nowTime > it.finishTime) {
                return@map true
            }
            if (it.status != BOSS_DISAPPEAR) {
                // 已经有活动魔物，不需要再次创建
                return@map true
            }
            areas.add(it)
        }
        return areas
    }

    fun findBossAreaRefresh(): List<ActivityBossArea> {
        val areas = LinkedList<ActivityBossArea>()
        val nowTime = getNowTime()
        activityBossAreaMap.map {
            if (nowTime < it.refreshTime) {
                return@map true
            }
            if (nowTime > it.finishTime) {
                return@map true
            }
            if (it.status != BOSS_WAIT_REFRESH) {
                return@map true
            }
            areas.add(it)
        }
        return areas
    }

    //查找活动boss消失的活动boss区域
    fun findBossAreaDisappear(): List<ActivityBossArea> {
        val areas = LinkedList<ActivityBossArea>()
        val nowTime = getNowTime()
        activityBossAreaMap.map {
            if (nowTime < it.finishTime) {
                return@map true
            }
            areas.add(it)
        }
        return areas
    }

    //获取初始化时间
    fun getInitAreaTime(): Long {
        var initTime = 0L
        activityBossAreaMap.map {
            initTime = it.initTime
            false
        }
        return initTime
    }
}