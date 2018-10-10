package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import xyz.ariane.util.tryCatch
import com.point18.slg2d.common.commonfunc.normalLog
import java.time.Clock

class GmFlushWorldDb : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val clock= Clock.systemDefaultZone()
        val now = clock.instant()
        tryCatch(normalLog) { session.areaCache.db.wdb.forceCheckAll(now) }
        return 1
    }
}