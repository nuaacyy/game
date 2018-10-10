package com.point18.slg2d.world.module.task

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.common.baseg.EventType
import com.point18.slg2d.world.event.JjcFightWin

// 完成竞技场胜利次数
class CheckJJCWinNum : AllCheck {
    override fun check(
        areaCache: AreaCache,
        ventType: EventType,
        event: Any,
        playerId: Long,
        checkValue: List<Int>,
        nowValue: Long
    ): AllCheckReturn {
        if (checkValue.size != 1) {
            return AllCheckReturn(false, 0)
        }

        // 如果事件是竞技场胜利
        var value = nowValue
        if(event is JjcFightWin){
            value++
        }

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, value)
        }
        return AllCheckReturn(false, value)
    }
}