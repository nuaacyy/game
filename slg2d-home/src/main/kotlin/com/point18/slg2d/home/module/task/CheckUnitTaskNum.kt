package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.TaskChapter
import com.point18.slg2d.common.constg.TaskHasGetReward
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

//  完成章节任务次数
class CheckUnitTaskNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val homeTaskDC = checkDep.homeTaskDC
        var finishNum = 0
        for (task in homeTaskDC.findAllTaskByPlayerId()) {
            if (task.taskNowState != TaskHasGetReward) {
                continue
            }
            val taskProto = pcs.questCache.findSpecTaskProto(task.taskProtoId)
            if (taskProto == null) {
                continue
            }

            if (taskProto.type == TaskChapter) {
                finishNum++
            }
        }

        if (finishNum >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, finishNum.toLong())
    }
}



