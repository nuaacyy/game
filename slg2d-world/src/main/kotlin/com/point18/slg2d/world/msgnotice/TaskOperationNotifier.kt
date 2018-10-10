package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.Task
import pb4client.TaskOperation

// 任务操作
class TaskOperationNotifier(
    val msg: TaskOperation.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.TaskOperation_3035, this.msg.build())
    }
}

fun createTaskOperationNotifier(
    operationType: Int,
    taskId: Long,
    taskProtoId: Int,
    taskState: Int,
    taskFinish: Long,
    overTime: Int
): TaskOperationNotifier {
    val taskOperationBuilder = TaskOperation.newBuilder()
    taskOperationBuilder.operationType = operationType
    val taskBuilder = Task.newBuilder()
    taskBuilder.taskId = taskId
    taskBuilder.taskProtoId = taskProtoId
    taskBuilder.taskState = taskState
    taskBuilder.taskFinish = taskFinish
    taskBuilder.overTime = overTime
    taskOperationBuilder.setTask(taskBuilder)
    return TaskOperationNotifier(taskOperationBuilder)
}
