package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.LAST_HOME_TASK_CHECK
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.TASK_NAMED_QUERY
import com.point18.slg2d.common.worldentities.TaskEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class Task(
    var dbId: Long, // 毫无意义的ID 只作为存储数据库的主键 绝对不允许业务逻辑使用
    var worldId: Long,
    var id: Long,

    var taskProtoId: Int,              //任务模板ID
    var taskNowState: Int,              //当前状态 0-进行中 1-已完成 2-已领取奖励
    var taskFinish: Long,              //当前完成度
    var overTime: Long, // 结束时间
    var playerId: Long    //外键玩家ID
) : EntityWrapper<TaskEntity> {
    constructor() : this(0L, 0L, 0, 0, 0, 0, 0L, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): TaskEntity {
        return TaskEntity(
            worldId,
            dbId,
            id,
            taskProtoId,
            taskNowState,
            taskFinish,
            overTime,
            playerId
        )
    }

    override fun wrap(entity: TaskEntity) {
        dbId = entity.dbId
        worldId = entity.worldId
        id = entity.id
        taskProtoId = entity.taskProtoId
        taskNowState = entity.taskNowState
        taskFinish = entity.taskFinish
        overTime = entity.overTime
        playerId = entity.playerId
    }
}

class CacheTask(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val taskMap = TwoKeyIndex<Long, Long, Task>({ it.playerId }, { it.id })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.taskEntities =
                session.getNamedQuery(TASK_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.taskEntities.forEach { entity ->
            try {
                val task = db.wdb.recover(entity) { Task() }

                taskMap.insertByKey(task)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 根据玩家ID拿所有任务信息
    fun findAllTaskEntityListByPlayerId(playerId: Long): LinkedList<TaskEntity> {
        val tasks = LinkedList<TaskEntity>()
        taskMap.findByOneKeyFilter(playerId) { tasks.add(it.toEntity()) }
        return tasks
    }

    fun createTaskByMoveServer( b: TaskEntity) {
        val task = Task()
        task.wrap(b)
        val dbId = wpm.generateObjIdNew(areaCache)
        task.worldId = worldId
        task.dbId = dbId

        insert(areaCache, task)

        // 添加到缓存中
        taskMap.insertByKey(task)
    }

    // 移除某个玩家的所有数据
    fun clearTaskForMoveServer(playerId: Long) {
        val delList = findAllTaskByPlayerId(playerId)
        for (del in delList) {
            deleteTask(del)
        }
    }

    fun createTask(protoId: Int, state: Int, finish: Long, playerId: Long): Task? {
        val questProto = pcs.questCache.findSpecTaskProto(protoId)
        if (questProto == null) {
            return null
        }
        for ((checkType, _) in questProto.completeCondMap) {
            if (checkType <= LAST_HOME_TASK_CHECK) {
                return null
            }
        }

        val overTime = if (questProto.limitTime == 0) {
            // 不限时任务
            -1
        } else {
            getNowTime() + (questProto.limitTime * 1000)
        }

        val id = wpm.generateObjIdNew(areaCache)
        val dbId = wpm.generateObjIdNew(areaCache)
        val task = Task(
            dbId,
            worldId,
            id,
            protoId,
            state,
            finish,
            overTime,
            playerId
        )

        insert(areaCache, task)

        // 添加到缓存中
        taskMap.insertByKey(task)
        return task
    }



    // 删除一个任务
    fun deleteTask(task: Task) {
        if (task.id == 0L) {
            return
        }

        delete(areaCache, task)

        taskMap.deleteByKey(task)
    }

    // 根据ID拿任务信息
    fun findTaskById(playerId: Long, id: Long): Task? {
        return taskMap.findByKey(playerId, id)
    }

    // 根据玩家ID拿所有任务信息
    fun findAllTaskByPlayerId(playerId: Long): List<Task> {
        val tasks = LinkedList<Task>()
        taskMap.findByOneKeyFilter(playerId) { tasks.add(it) }
        return tasks
    }

    // 根据模版ID拿任务信息
    fun findTaskByProtoId(playerId: Long, taskProtoId: Int): Task? {
        var t: Task? = null
        taskMap.findByOneKeyFilter(playerId) {
            if (it.taskProtoId == taskProtoId) {
                t = it
            }
            it.taskProtoId != taskProtoId
        }
        return t
    }
}

