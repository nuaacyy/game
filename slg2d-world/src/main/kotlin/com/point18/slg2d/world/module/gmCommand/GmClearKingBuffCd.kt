package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.world.area4data.PlayerSession

//清除王国buff cd
class GmClearKingBuffCd : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            return 2
        }

        val nowTime = getNowTime()
        wonder.buffCoolTime = nowTime
        return 1
    }

}
