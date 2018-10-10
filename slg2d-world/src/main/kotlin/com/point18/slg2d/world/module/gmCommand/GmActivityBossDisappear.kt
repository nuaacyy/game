package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.constg.FOUR_DRAGON_ACTIVITY
import com.point18.slg2d.common.constg.FOUR_DRAGON_GATE
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import com.point18.slg2d.world.common.createActivityBossEnterTime
import com.point18.slg2d.world.module.boss.bossDisappear
import com.point18.slg2d.world.msgnotice.ActivityEnterTimeChangeNotifier

// 关闭四天龙
class GmActivityBossDisappear : GmCommand {

    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val bossAreas = areaCache.activityBossAreaCache.findAllActivityBossArea()
        for (bossArea in bossAreas) {
            bossDisappear(areaCache, bossArea)
        }

        val notifier = ActivityEnterTimeChangeNotifier()
        notifier.append(createActivityBossEnterTime(areaCache, FOUR_DRAGON_GATE))
        notifier.append(createActivityBossEnterTime(areaCache, FOUR_DRAGON_ACTIVITY))
        for ((_, targetSession) in fetchAllOnlinePlayerSessions(areaCache)) {
            notifier.notice(targetSession)
        }

        return 1
    }
}

