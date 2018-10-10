package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.LAST_HOME_TASK_CHECK
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.constg.TaskChapter
import com.point18.slg2d.common.constg.TaskgoAlong
import com.point18.slg2d.common.homeentities.HOME_TASK_NAMED_QUERY
import com.point18.slg2d.common.homeentities.HomeTaskEntity
import com.point18.slg2d.common.homeentities.HomeTaskPK
import com.point18.slg2d.common.pc.QuestProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.hpm
import pb4server.CreateTaskToWorldTell
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class HomeTaskDC : AbstractDataContainer<List<HomeTaskEntity>>() {

    private val homeTask = mutableMapOf<Long, HomeTask>()
    private val homeTaskMapByProtoId = mutableMapOf<Int, HomeTask>()

    override fun initImpl(data: List<HomeTaskEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val task = wdb.recover(it) { HomeTask() }

            homeTask[task.id] = task
            homeTaskMapByProtoId[task.taskProtoId] = task
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<HomeTaskEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HOME_TASK_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<HomeTaskEntity>()
            list
        }
        return data
    }

    /**
     * 创建任务
     */
    fun createTask(taskProto: QuestProto, finish: Long): HomeTask {
        // 限时时间
        var overTime = 0L
        if (taskProto.limitTime == 0) {
            // 不限时任务
            overTime = -1
        } else {
            overTime = getNowTime() + (taskProto.limitTime * 1000)
        }

        var isWorld = 0
        for ((checkType, _) in taskProto.completeCondMap) {
            if (checkType > LAST_HOME_TASK_CHECK) {
                // 世界任务
                isWorld = 1
                break
            }
        }

        val protoId = taskProto.id
        val id = hpm.generateObjIdNew()
        val task = HomeTask(
            id,
            protoId,
            TaskgoAlong,
            finish,
            overTime,
            isWorld,
            playerId
        )

        wdb.save(task)

        // 添加到缓存中
        homeTask[task.id] = task
        homeTaskMapByProtoId[task.taskProtoId] = task

        return task
    }

    // 删除一个任务
    fun deleteTask(task: HomeTask) {
        if (task.id == 0L) {
            return
        }

        wdb.delete(task)
        homeTask.remove(task.id)
        homeTaskMapByProtoId.remove(task.taskProtoId)
    }

    // 根据ID拿任务信息
    fun findTaskById(id: Long): HomeTask? {
        return homeTask[id]
    }

    // 根据任务模版ID拿任务信息
    fun findTaskByProtoId(protoId: Int): HomeTask? {
        return homeTaskMapByProtoId[protoId]
    }

    // 拿所有任务信息
    fun findAllTaskByPlayerId(): LinkedList<HomeTask> {
        val tasks = LinkedList<HomeTask>()
        for ((_, t) in homeTask) {
            tasks += t
        }
        return tasks
    }

    // 拿某类型的所有任务,并且删除该类型的过期任务
    // *******************  这个方法会删除掉过期的任务 根据需求来决定到底是不是用这个哦   *********************
    fun findTaskByTaskType(taskType: Int): LinkedList<HomeTask> {
        val tasks = LinkedList<HomeTask>()
        val delTasks = LinkedList<Int>()
        for ((_, t) in homeTask) {
            val taskProto = pcs.questCache.findSpecTaskProto(t.taskProtoId)
            if (taskProto == null || taskProto.type != taskType) {
                continue
            }

            if (t.overTime != -1L && t.overTime <= getNowTime()) {
                delTasks += t.taskProtoId
                continue
            }

            tasks += t
        }

        for (del in delTasks) {
            val delT = findTaskByProtoId(del)
            if (delT != null) {
                deleteTask(delT)
            }
        }
        return tasks
    }
}

// 玩家home的任务
class HomeTask(
    var id: Long,

    var taskProtoId: Int,              //任务模板ID
    var taskNowState: Int,              //当前状态 0-进行中 1-已完成 2-已领取奖励
    var taskFinish: Long,              //当前完成度
    var overTime: Long, // 任务结束时间  -1表示不限时
    var onWorld: Int, // 是否正在world服进行中 home这边留了底  0-否 1-是

    var playerId: Long
) : EntityWrapper<HomeTaskEntity> {
    constructor() : this(0, 0, 0, 0, 0L, 0, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = HomeTaskPK(playerId, id)

    override fun toEntity(): HomeTaskEntity {
        return HomeTaskEntity(
            id,
            taskProtoId,
            taskNowState,
            taskFinish,
            overTime,
            onWorld,
            playerId
        )
    }

    override fun wrap(entity: HomeTaskEntity) {
        id = entity.id
        taskProtoId = entity.taskProtoId
        taskNowState = entity.taskNowState
        taskFinish = entity.taskFinish
        overTime = entity.overTime
        onWorld = entity.onWorld
        playerId = entity.playerId
    }
}