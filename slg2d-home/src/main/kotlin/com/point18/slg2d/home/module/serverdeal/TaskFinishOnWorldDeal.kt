package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.TaskHasFinish
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HTell
import com.point18.slg2d.home.msgnotice.createTaskOperationNotifier
import pb4server.World2HomeTell

// 领取任务奖励
class TaskFinishOnWorldDeal : W2HTell, HomeHelperPlus1<HomeTaskDC>(
    HomeTaskDC::class.java
)  {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        val tell = msg.taskFinishOnWorldTell

        prepare(session) { homeTaskDC: HomeTaskDC ->
            val task = homeTaskDC.findTaskByProtoId(tell.taskProtoId)
            val taskProto = pcs.questCache.findSpecTaskProto(tell.taskProtoId)
            if (taskProto == null) {
                return@prepare
            }

            if (task != null) {
                task.onWorld = 0
                task.taskNowState = TaskHasFinish
                // 给客户端推送任务变化
                val taskOperationNotifier = createTaskOperationNotifier(
                    1, task.id, task.taskProtoId, task.taskNowState,
                    task.taskFinish, 0
                )

                taskOperationNotifier.notice(session)
            }
        }
    }
}
