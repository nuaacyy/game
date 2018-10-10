package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.baseg.TIMETASK
import com.point18.slg2d.common.baseg.TimeEvent
import com.point18.slg2d.common.constg.RefreshTower
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.wpm

class GmTimeTaskForWeekRank : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        wpm.es.fire(
            session.areaCache, 0, TIMETASK, TimeEvent(
                RefreshTower
            )
        )
        return 1
    }
}


