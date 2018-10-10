package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.TaskHasGetReward
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

//  完成普通任务
class CheckMainTask : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homeTaskDC = checkDep.homeTaskDC
        val task = homeTaskDC.findTaskByProtoId(checkValue[0])
        if (task == null || task.taskNowState != TaskHasGetReward) {
            return AllCheckReturn(false, 0)
        }
        return AllCheckReturn(true, checkValue[0].toLong())

    }
}



