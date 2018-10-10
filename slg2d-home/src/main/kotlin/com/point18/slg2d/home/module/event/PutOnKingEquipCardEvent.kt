package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.KING_EQUIP_ON_CARD
import com.point18.slg2d.home.module.EventData

data class PutOnKingEquipCardEvent(
    val cardId: Int
) : EventData(KING_EQUIP_ON_CARD)

