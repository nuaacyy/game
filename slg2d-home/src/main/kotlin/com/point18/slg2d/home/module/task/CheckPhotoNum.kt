package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.constg.ICON_TYPE_DEFAULT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.EventData

// 玩家头像数量
class CheckPhotoNum : AllCheck {

    override fun check(
        session: PlayerActor,
        checkDep: CheckDep,
        checkValue: List<Int>,
        event: EventData,
        nowFinish: Long
    ): AllCheckReturn {
        val iconDC = checkDep.iconDC
        var value = iconDC.iconsMap.size
        val basicPhoto = pcs.lordHeadIconProtoCache.iconTypeMap[ICON_TYPE_DEFAULT]
        if (basicPhoto != null) {
            value += basicPhoto.size
        }

        if (value >= checkValue[0]) {
            return AllCheckReturn(true, checkValue[0].toLong())
        }

        return AllCheckReturn(false, value.toLong())
    }
}



