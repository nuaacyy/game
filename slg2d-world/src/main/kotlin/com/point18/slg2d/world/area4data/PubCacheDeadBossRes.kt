package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.worldentities.DEAD_BOSS_NAMED_QUERY
import com.point18.slg2d.common.worldentities.DeadBossResEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 死亡BOSS资源地信息
class DeadBossRes(
        var worldId: Long,
        var id: Long,

        var resId: Int,            // 资源点配置Id
        var x: Int,                 // 所在坐标X
        var y: Int,                 // 所在坐标Y
        var overTime: Long,         // 消失时间
        var nowResNum: Int,          //当前资源量
        var groupId: Long,   // 屯田部队ID
        var farmStartTime: Long,  //采集开始时间
        var farmEndTime: Long   // 采集结束时间
) : EntityWrapper<DeadBossResEntity> {

    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): DeadBossResEntity {
        return DeadBossResEntity(
                worldId,
                id,
                resId,
                x,
                y,
                overTime,
                nowResNum,
                groupId,
                farmStartTime,
                farmEndTime
        )
    }

    override fun wrap(entity: DeadBossResEntity) {
        worldId = entity.worldId
        id = entity.id
        resId = entity.resId
        x = entity.x
        y = entity.y
        overTime = entity.overTime
        nowResNum = entity.nowResNum
        groupId = entity.groupId
        farmStartTime = entity.farmStartTime
        farmEndTime = entity.farmEndTime
    }

}


class CacheDeadBossRes(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val deadBossResByXy =
            TwoKeyIndex({ DeadBossRes: DeadBossRes -> DeadBossRes.x }, { DeadBossRes: DeadBossRes -> DeadBossRes.y })

    private val farmOverPq: Queue<DeadBossRes> = PriorityQueue { c1, c2 ->
        when {
            c1.farmEndTime > c2.farmEndTime -> 1
            c1.farmEndTime == c2.farmEndTime -> 0
            else -> -1
        }
    }

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.deadBossResEntities =
                    session.getNamedQuery(DEAD_BOSS_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.deadBossResEntities.forEach { entity ->
            try {
                val deadBossRes = db.wdb.recover(entity) { DeadBossRes() }

                this.deadBossResByXy.insertByKey(deadBossRes)
                if (deadBossRes.groupId != 0L) {
                    this.farmOverPq.add(deadBossRes)
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    fun createDeadBossRes(
            resId: Int,
            x: Int,
            y: Int,
            overTime: Long,
            nowResNum: Int
    ): DeadBossRes {
        val id = wpm.generateObjIdNew(areaCache)
        val deadBossRes = DeadBossRes(worldId, id, resId, x, y, overTime, nowResNum, 0, 0, 0)

        insert(areaCache, deadBossRes)
        deadBossResByXy.insertByKey(deadBossRes)

        return deadBossRes
    }

    fun deleteDeadBossRes(deadBossRes: DeadBossRes?) {
        if (deadBossRes == null || deadBossRes.id == 0L) {
            return
        }

        delete(areaCache, deadBossRes)

        // 从缓存中删除
        deadBossResByXy.deleteByKey(deadBossRes)
        //有采集部队，额外删除心跳
        farmOverPq.remove(deadBossRes)
    }

    // 根据XY找到一个尸体资源地
    fun findDeadBossResByXy(x: Int, y: Int): (DeadBossRes?) {
        return deadBossResByXy.findByKey(x, y)
    }

    // 找出所有到点的尸体资源地
    fun findAllTimeOverDeadBossRes(): (LinkedList<DeadBossRes>) {
        val allRes = LinkedList<DeadBossRes>()
        val nowTime = getNowTime()

        deadBossResByXy.map {
            if (it.overTime in 1..(nowTime - 1)) {
                allRes.add(it)
            }
            true
        }

        return allRes
    }

    fun peekDeadBossResOver(): (DeadBossRes?) {
        val farmOverH = farmOverPq.peek()
        if (farmOverH == null) {
            return null
        }

        val nowTime = getNowTime()
        if (farmOverH.farmEndTime < nowTime) {
            return farmOverH
        }

        return null
    }

    fun popDeadBossResOver() {
        farmOverPq.poll()
    }

    fun updateDeadBossRes(deadBossRes: DeadBossRes) {
        farmOverPq.remove(deadBossRes)
        farmOverPq.add(deadBossRes)
    }
}
