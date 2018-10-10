package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import xyz.ariane.util.tryCatch
import java.time.Clock

class GmFlushHomeDb : GmCommand, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun exec(session: PlayerActor, message: String) {
        val clock = Clock.systemDefaultZone()
        val now = clock.instant()
        tryCatch(normalLog) { session.db.wdb.forceCheckAll(now) }
    }
}