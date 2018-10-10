package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.NORMAL_LOTTERY
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.LotteryEvent

// 普通抽卡次数,领取任务之后才统计
class CheckNormalLotteryNowNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = nowFinish
        if (event is LotteryEvent) {
            if (event.LotteryType == NORMAL_LOTTERY) {
                value += event.LotteryNum
            }
        }

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



