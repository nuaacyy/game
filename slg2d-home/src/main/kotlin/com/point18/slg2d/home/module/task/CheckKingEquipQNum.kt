package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 君主穿戴某品质装备的数量
class CheckKingEquipQNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val newEquipDC = checkDep.equipDC
        val equips = newEquipDC.findKingEquipsByPlayerId(checkDep.bagDC, session.playerId)

        var value = 0
        for (e in equips) {
            val equipProto = pcs.equipCache.equipProtoMap[e.equipProtoId]
            if (equipProto != null && equipProto.quality >= checkValue[0]) {
                value++
            }
        }


        if (value >= checkValue[1]) {
            return AllCheckReturn(true, checkValue[1].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }
}



