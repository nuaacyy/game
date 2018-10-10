package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.GET_KING_EQUIP
import com.point18.slg2d.home.module.EventData

data class GetKingEquipEvent(
    val equipProtoId: Int
) : EventData(GET_KING_EQUIP)

