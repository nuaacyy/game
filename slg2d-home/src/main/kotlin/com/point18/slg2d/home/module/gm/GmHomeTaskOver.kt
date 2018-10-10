package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.constg.TaskHasFinish
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.msgnotice.createTaskOperationNotifier

class GmHomeTaskOver : GmCommand, HomeHelperPlus1<HomeTaskDC>(HomeTaskDC::class.java) {

    override fun exec(session: PlayerActor, message: String) {
        prepare(session) { homeTaskDC: HomeTaskDC ->
            // 格式1： -gm add 类型 数量
            // 格式2： -gm changeCD building
            val messages = message.split(" ")
            if (messages.size == 1) {
                return@prepare
            }

            if (messages.size != 3) {
                return@prepare
            }

            val taskId = messages[2].toIntOrNull()
            if (taskId == null) {
                return@prepare
            }

            val playerTask = homeTaskDC.findTaskByProtoId(taskId)
            if (playerTask == null) {
                return@prepare
            }
            val taskProto = pcs.questCache.findSpecTaskProto(taskId)
            if(taskProto == null) {
                return@prepare
            }
            var value = 0
            for ((_, taskValue) in taskProto.completeCondMap) {
                value = taskValue[taskValue.size - 1]
            }
            playerTask.taskNowState = TaskHasFinish
            playerTask.taskFinish = value.toLong()
            // 给客户端推送任务变化
            val taskOperationNotifier = createTaskOperationNotifier(
                1, playerTask.id, playerTask.taskProtoId, playerTask.taskNowState,
                playerTask.taskFinish, 0
            )

            taskOperationNotifier.notice(session)

        }

    }
}