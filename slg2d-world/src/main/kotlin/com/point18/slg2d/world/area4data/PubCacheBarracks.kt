package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.EventCure
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.BARRACKS_NAMED_QUERY
import com.point18.slg2d.common.worldentities.BarracksEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 兵营(造兵+治疗)功能表
class Barracks(
    var worldId: Long,
    var id: Long,

    var soldierId: Int,                 // 士兵模板ID
    /** 兵营总数 **/
    var soldierNum: Int,                // 士兵数量
    /** 造兵 **/
    var needTime: Long,                // 造兵开始时间
    var overTime: Long,                 // 造兵结束时间
    var nowMakeNum: Int,                // 正在造的士兵数量
    /** 治疗伤兵 **/
    var canCureNum: Int,                // 当前可治疗的士兵数量
    var nowCureNum: Int,                // 当前正在治疗的士兵数量
    var cureNeedTime: Long,            // 治疗开始时间
    var cureOverTime: Long,             // 治疗结束时间
    var cureQueue: Int,                 // 所属治疗队列
    /** 治疗活动伤兵 **/
    var canEventCureNum: Int,           // 当前可治疗(活动)的士兵/陷阱数量
    var nowEventCureNum: Int,           // 当前正在治疗(活动)的士兵/陷阱数量
    var eventCureNeedTime: Long,        // 治疗(活动)士兵/陷阱需要总时间
    var eventCureOverTime: Long,        // 治疗(活动)士兵/陷阱结束时间
    var eventCureQueue: Int,            // 所属(活动)治疗队列
    /** 晋升兵种 **/
    var upNum: Int,                     // 当前正在晋升的数量
    var upToSoliderId: Int,             // 正在晋升到的兵种ID
    var upNeedTime: Long,               // 晋升需要总时间
    var upOverTime: Long,               // 晋升结束时间

    var playerId: Long                  // 玩家ID
) : EntityWrapper<BarracksEntity> {
    constructor() : this(
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): BarracksEntity {
        return BarracksEntity(
            worldId,
            id,
            soldierId,
            soldierNum,
            needTime,
            overTime,
            nowMakeNum,
            canCureNum,
            nowCureNum,
            cureNeedTime,
            cureOverTime,
            cureQueue,
            canEventCureNum,
            nowEventCureNum,
            eventCureNeedTime,
            eventCureOverTime,
            eventCureQueue,
            upNum,
            upToSoliderId,
            upNeedTime,
            upOverTime,
            playerId
        )
    }

    override fun wrap(entity: BarracksEntity) {
        worldId = entity.worldId
        id = entity.id
        soldierId = entity.soldierId
        soldierNum = entity.soldierNum
        overTime = entity.overTime
        nowMakeNum = entity.nowMakeNum
        canCureNum = entity.canCureNum
        nowCureNum = entity.nowCureNum
        cureOverTime = entity.cureOverTime
        cureQueue = entity.cureQueue
        canEventCureNum = entity.canEventCureNum
        nowEventCureNum = entity.nowEventCureNum
        eventCureOverTime = entity.eventCureOverTime
        eventCureQueue = entity.eventCureQueue
        upNum = entity.upNum
        upToSoliderId = entity.upToSoliderId
        upOverTime = entity.upOverTime
        playerId = entity.playerId
        needTime = entity.startTime
        cureNeedTime = entity.cureStartOverTime
        eventCureNeedTime = entity.eventCureStartTime
        upNeedTime = entity.upStartTime
    }

}

class CacheBarracks(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    val allBarracksMap = OneKeyIndex { it: Barracks -> it.id }     // 缓存
    private val barracksMap = OneKeyIndexSlice({ it: Barracks -> it.playerId },
        { ita: Barracks, itb: Barracks -> ita.id == itb.id })// 玩家兵营表 Key:玩家ID

    // 造兵队列
    private val makeSoliderPq: Queue<Barracks> = PriorityQueue { c1, c2 ->
        when {
            c1.overTime > c2.overTime -> 1
            c1.overTime == c2.overTime -> 0
            else -> -1
        }
    }

    // 伤兵营队列
    private val cureSoliderPq: Queue<Barracks> = PriorityQueue { c1, c2 ->
        when {
            c1.cureOverTime > c2.cureOverTime -> 1
            c1.cureOverTime == c2.cureOverTime -> 0
            else -> -1
        }
    }

    // 伤兵营(活动)队列
    private val eventCureSoliderPq: Queue<Barracks> = PriorityQueue { c1, c2 ->
        when {
            c1.eventCureOverTime > c2.eventCureOverTime -> 1
            c1.eventCureOverTime == c2.eventCureOverTime -> 0
            else -> -1
        }
    }

    // 升阶队列
    private val soliderUpPq: Queue<Barracks> = PriorityQueue { c1, c2 ->
        when {
            c1.upOverTime > c2.upOverTime -> 1
            c1.upOverTime == c2.upOverTime -> 0
            else -> -1
        }
    }

    override fun init() {

    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.barracksEntities =
                session.getNamedQuery(BARRACKS_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.barracksEntities.forEach { entity ->
            try {
                val barrack = db.wdb.recover(entity) { Barracks() }

                this.allBarracksMap.insertByKey(barrack)
                this.barracksMap.insertByKey(barrack)

                if (barrack.overTime != zeroTime.time) {
                    this.makeSoliderPq.add(barrack)
                }
                if (barrack.cureOverTime != zeroTime.time) {
                    this.cureSoliderPq.add(barrack)
                }
                if (barrack.eventCureOverTime != zeroTime.time) {
                    this.eventCureSoliderPq.add(barrack)
                }
                if (barrack.upOverTime != zeroTime.time) {
                    this.soliderUpPq.add(barrack)
                }

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {

    }

    // 查询玩家所有的兵营信息list格式
    fun findBarrackEntityListByPlayerId(playerId: Long): (LinkedList<BarracksEntity>) {
        val barracksWraps = LinkedList<BarracksEntity>()

        barracksMap.findByKey(playerId) {
            barracksWraps.add(it.toEntity())
            true
        }

        return barracksWraps
    }

    fun createBarracksByMoveServer(b: BarracksEntity) {
        val barrack = Barracks()
        barrack.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        barrack.worldId = worldId
        barrack.id = id

        insert(areaCache, barrack)

        // 添加到缓存中
        barracksMap.insertByKey(barrack)
        allBarracksMap.insertByKey(barrack)

        if (barrack.overTime != zeroTime.time) {
            this.makeSoliderPq.add(barrack)
        }
        if (barrack.cureOverTime != zeroTime.time) {
            this.cureSoliderPq.add(barrack)
        }
        if (barrack.eventCureOverTime != zeroTime.time) {
            this.eventCureSoliderPq.add(barrack)
        }
        if (barrack.upOverTime != zeroTime.time) {
            this.soliderUpPq.add(barrack)
        }
    }

    // 暂时移除某个玩家的优先级队列数据,如果迁服失败再重新添回来
    fun stopBarracksForMoveServer(playerId: Long) {
        val delList = findBarracksByPlayerId(playerId)
        for (barrack in delList) {
            if (barrack.overTime != zeroTime.time) {
                this.makeSoliderPq.remove(barrack)
            }
            if (barrack.cureOverTime != zeroTime.time) {
                this.cureSoliderPq.remove(barrack)
            }
            if (barrack.eventCureOverTime != zeroTime.time) {
                this.eventCureSoliderPq.remove(barrack)
            }
            if (barrack.upOverTime != zeroTime.time) {
                this.soliderUpPq.remove(barrack)
            }

        }
    }

    // 迁服失败,把数据重新添加到优先级队列
    fun reviveBarracksForMoveServer(playerId: Long) {
        val delList = findBarracksByPlayerId(playerId)
        for (barrack in delList) {
            if (barrack.overTime != zeroTime.time) {
                this.makeSoliderPq.add(barrack)
            }
            if (barrack.cureOverTime != zeroTime.time) {
                this.cureSoliderPq.add(barrack)
            }
            if (barrack.eventCureOverTime != zeroTime.time) {
                this.eventCureSoliderPq.add(barrack)
            }
            if (barrack.upOverTime != zeroTime.time) {
                this.soliderUpPq.add(barrack)
            }

        }
    }

    // 移除某个玩家的所有数据
    fun clearBarracksForMoveServer(playerId: Long) {
        val delList = findBarracksByPlayerId(playerId)
        for (barrack in delList) {
            delete(areaCache, barrack)

            // 从缓存中删除
            barracksMap.deleteByKey(barrack)
            allBarracksMap.deleteByKey(barrack)
        }
    }

    fun createBarracks(soldierId: Int, playerId: Long): Barracks {
        val id = wpm.generateObjIdNew(areaCache)
        val barracks = Barracks(
            worldId, id, soldierId, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, playerId
        )

        insert(areaCache, barracks)

        // 添加到缓存中
        barracksMap.insertByKey(barracks)
        allBarracksMap.insertByKey(barracks)

        return barracks
    }

    // 查询玩家所有的兵营信息list格式
    fun findBarracksByPlayerId(playerId: Long): (LinkedList<Barracks>) {
        val barracksWraps = LinkedList<Barracks>()

        barracksMap.findByKey(playerId) {
            barracksWraps.add(it)
            true
        }

        return barracksWraps
    }

    // 查询玩家所有的兵营信息map格式
    fun findBarracksMapByPlayerId(playerId: Long): (HashMap<Int, Barracks>) {
        val barracksWraps = hashMapOf<Int, Barracks>()

        barracksMap.findByKey(playerId) {
            barracksWraps[it.soldierId] = it
            true
        }

        return barracksWraps
    }

    // 查找某玩家的某个兵种的情况
    fun findBarracksByPlayerIdAndSoldierId(playerId: Long, soldierId: Int): (Barracks?) {
        var soldierInfo: Barracks? = null

        barracksMap.findByKey(playerId) {
            if (it.soldierId == soldierId) {
                soldierInfo = it
            }
            it.soldierId != soldierId

        }

        return soldierInfo
    }

    //查找造兵/陷阱队列数量
    fun findMakeQueueNum(playerId: Long, check: (Int) -> Boolean): (Int) {
        var num = 0

        barracksMap.findByKey(playerId) {
            if (it.overTime != 0L || it.upOverTime != 0L) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[it.soldierId]
                if (soliderProto == null) {
                    //Assert
                    return@findByKey true
                }
                if (check(soliderProto.armyType)) {
                    num += 1
                }
            }
            true
        }

        return num
    }

    // 查找某治疗队列所有士兵
    fun findCureSoliderListByCureQueue(
        playerId: Long,
        eventCure: Int,
        cureQueue: Int
    ): (LinkedList<Barracks>) {
        val queue = LinkedList<Barracks>()

        if (eventCure == EventCure) {
            barracksMap.findByKey(playerId) {
                if (it.eventCureQueue == cureQueue) {
                    queue.add(it)
                }
                true
            }
        } else {
            barracksMap.findByKey(playerId) {
                if (it.cureQueue == cureQueue) {
                    queue.add(it)
                }
                true
            }
        }

        return queue
    }

    // 查找当前正在治疗的兵或者陷阱队列数量
    fun findCureSoliderNum(playerId: Long, check: (Int) -> Boolean): (Int) {
        var num = 0

        barracksMap.findByKey(playerId) {
            val soliderProto = pcs.soliderCache.soliderProtoMap[it.soldierId]
            if (soliderProto == null) {
                //Assert
                return@findByKey true
            }

            if (it.cureQueue != 0 && check(soliderProto.armyType)) {
                num++
            } else if (it.eventCureQueue != 0 && check(soliderProto.armyType)) {
                num++
            }

            true
        }

        return num
    }

    fun peekMakeSoliderFinish(): (Barracks?) {
        val makeSoliderH = makeSoliderPq.peek()
        if (makeSoliderH == null) {
            return null
        }

        if (makeSoliderH.overTime < getNowTime()) {
            return makeSoliderH
        }

        return null
    }

    fun popMakeSolider() {
        makeSoliderPq.poll()
    }

    // 更改造兵时间
    fun makeSoliderUpdate(barracks: Barracks, overTime: Long) {
        val oldTime = barracks.overTime
        if (oldTime != 0L) {
            makeSoliderPq.remove(barracks)
        } else {
            // 当前是0 行为新开始 设置总时间
            barracks.needTime = overTime - getNowTime()
        }
        barracks.overTime = overTime
        if (overTime != 0L) {
            makeSoliderPq.add(barracks)
        } else {
            // 等于0 行为结束 清掉总时间
            barracks.needTime = 0
        }
    }

    fun peekSoliderUpFinish(): (Barracks?) {
        val soliderUpH = soliderUpPq.peek()
        if (soliderUpH == null) {
            return null
        }

        val nowTime = getNowTime()
        if (soliderUpH.upOverTime < nowTime) {
            return soliderUpH
        }

        return null
    }

    fun popSoliderUp() {
        soliderUpPq.poll()
    }

    // 更改晋升时间
    fun soliderUpUpdate(barracks: Barracks, overTime: Long) {
        val oldTime = barracks.upOverTime
        if (oldTime != 0L) {
            soliderUpPq.remove(barracks)
        } else {
            // 当前是0 行为新开始 设置总时间
            barracks.upNeedTime = overTime - getNowTime()
        }
        barracks.upOverTime = overTime
        if (overTime != 0L) {
            soliderUpPq.add(barracks)
        } else {
            barracks.upNeedTime = 0
        }
    }

    fun peekCureSoliderFinish(): (Barracks?) {
        val cureSoliderH = cureSoliderPq.peek()
        if (cureSoliderH == null) {
            return null
        }

        val nowTime = getNowTime()
        if (cureSoliderH.cureOverTime < nowTime) {
            return cureSoliderH
        }

        return null
    }

    fun peekEventCureSoliderFinish(): (Barracks?) {
        val cureSoliderH = eventCureSoliderPq.peek()
        if (cureSoliderH == null) {
            return null
        }

        val nowTime = getNowTime()
        if (cureSoliderH.eventCureOverTime < nowTime) {
            return cureSoliderH
        }

        return null
    }

    fun popCureSolider() {
        cureSoliderPq.poll()
    }

    fun popEventCureSolider() {
        eventCureSoliderPq.poll()
    }

    // 更新伤兵营时间
    fun cureSoliderUpdate(barracks: Barracks, overTime: Long) {
        val oldTime = barracks.cureOverTime
        if (oldTime != 0L) {
            cureSoliderPq.remove(barracks)
        } else {
            // 当前是0 行为新开始 设置总时间
            barracks.cureNeedTime = overTime - getNowTime()
        }
        barracks.cureOverTime = overTime
        if (overTime != 0L) {
            cureSoliderPq.add(barracks)
        } else {
            // 当前是0 行为新开始 设置总时间
            barracks.cureNeedTime = 0
        }
    }

    // 更新伤兵营(活动)时间
    fun eventCureSoliderUpdate(barracks: Barracks, overTime: Long) {
        val oldTime = barracks.eventCureOverTime
        if (oldTime != 0L) {
            eventCureSoliderPq.remove(barracks)
        } else {
            // 当前是0 行为新开始 设置总时间
            barracks.eventCureNeedTime = overTime - getNowTime()
        }
        barracks.eventCureOverTime = overTime
        if (overTime != 0L) {
            eventCureSoliderPq.add(barracks)
        } else {
            // 当前是0 行为新开始 设置总时间
            barracks.eventCureNeedTime = 0
        }
    }
}