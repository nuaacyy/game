package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.TaskHasFinish
import com.point18.slg2d.common.constg.TaskHasGetReward
import com.point18.slg2d.common.constg.taskCheckWhiteList
import com.point18.slg2d.world.msgnotice.createTaskOperationNotifier
import pb4server.TaskFinishOnWorldTell
import com.point18.slg2d.common.pc.pcs
import java.util.*

fun handleOverEvent(areaCache: AreaCache, event: Any, eventType: EventType, playerId: Long) {
    taskEffect(eventType, event, areaCache, playerId)
}

/**
任务进度变化
 */
fun taskEffect(eventType: EventType, event: Any, areaCache: AreaCache, playerId: Long) {
    val playerTasks = areaCache.taskCache.findAllTaskByPlayerId(playerId)
    val session = fetchOnlinePlayerSession(areaCache, playerId)
    val timeOverTasks = LinkedList<Long>()
    for (playerTask in playerTasks) {
        val taskProto = pcs.questCache.findSpecTaskProto(playerTask.taskProtoId)
        if (taskProto == null) {
            // commonfunc.NormalLog.Error("任务模板不存在：%v", playerTask.GetTaskProtoId())
            continue
        }
        if (taskProto.id == 0) {
            continue
        }
        if (playerTask.taskNowState == TaskHasFinish || playerTask.taskNowState == TaskHasGetReward) {
            continue
        }

        // 获取这个任务的检测条件,过滤白名单
        var checkType = 0
        for ((ct, _) in taskProto.completeCondMap) {
            checkType = ct
        }
        val whiteList = taskCheckWhiteList[checkType]
        if (whiteList == null || !whiteList.contains(eventType)) {
            // 说明这个事件根本就跟这个任务无关,过滤掉
            continue
        }

        if (playerTask.overTime != -1L && playerTask.overTime <= getNowTime()) {
            timeOverTasks.add(playerTask.id)
        }

        val (isAllFinish, isCheck) = checkIsFinish(areaCache, eventType, event, playerTask, playerId)

        if (isAllFinish) {
            // 任务完成,删除世界服的数据,转移保存到玩家服
            val tell = TaskFinishOnWorldTell.newBuilder()
            tell.taskProtoId = playerTask.taskProtoId
            areaCache.tellHome(areaCache.fillW2HTellMsgHeader(playerId) {
                it.setTaskFinishOnWorldTell(tell)
            })
            areaCache.taskCache.deleteTask(playerTask)
        } else {
            if (isCheck) {
                var overTime = -1
                if (playerTask.overTime != -1L) {
                    overTime = (playerTask.overTime / 1000).toInt()
                }

                // 给客户端推送任务变化
                if (session != null) {
                    val taskOperationNotifier = createTaskOperationNotifier(
                        3, playerTask.id, playerTask.taskProtoId, playerTask.taskNowState,
                        playerTask.taskFinish, overTime
                    )

                    taskOperationNotifier.notice(session)
                }
            }
        }

    }

    for (delTasks in timeOverTasks) {
        val delT = areaCache.taskCache.findTaskById(playerId, delTasks)
        if (delT != null) {
            areaCache.taskCache.deleteTask(delT)
        }
    }
}

//检测任务完成否
data class CheckIsFinish(val isOk: Boolean, val isChange: Boolean)

fun checkIsFinish(
    areaCache: AreaCache,
    eventType: EventType,
    event: Any,
    playerTask: Task,
    playerId: Long
): (CheckIsFinish) {
    val taskProto = pcs.questCache.findSpecTaskProto(playerTask.taskProtoId)
    if (taskProto == null) {
        //		commonfunc.NormalLog.Error("任务模板不存在：%v", playerTask.taskProtoId)
        return CheckIsFinish(false, false)
    }
    if (taskProto.id == 0) {
        return CheckIsFinish(false, false)
    }
    if (playerTask.taskNowState == TaskHasFinish || playerTask.taskNowState == TaskHasGetReward) {
        return CheckIsFinish(false, false)
    }

    var isAllFinish: Boolean
    for ((taskType, taskValue) in taskProto.completeCondMap) {
        val checkHandle = TaskM.checkHandles[taskType]
        if (checkHandle == null) {
            return CheckIsFinish(false, false)
        }
        val (returnIsAllFinish, nowValue) = checkHandle.check(
            areaCache,
            eventType,
            event,
            playerId,
            taskValue,
            playerTask.taskFinish
        )
        isAllFinish = returnIsAllFinish
        var isCheck = false
        if (playerTask.taskFinish != nowValue) {
            playerTask.taskFinish = nowValue
            isCheck = true
        }
        if (!isAllFinish) {
            return CheckIsFinish(false, isCheck)
        }
    }
    return CheckIsFinish(true, false)
}
