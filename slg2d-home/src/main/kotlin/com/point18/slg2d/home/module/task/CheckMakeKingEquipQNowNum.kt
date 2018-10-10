package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData
import com.point18.slg2d.home.module.event.GetKingEquipEvent

class CheckMakeKingEquipQNowNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        var value = nowFinish
        if (event is GetKingEquipEvent) {
            val equipProto = pcs.equipCache.equipProtoMap[event.equipProtoId]
            if (equipProto != null && equipProto.quality >= checkValue[0]) {
                value += 1
            }
        }

        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value)
    }
}



