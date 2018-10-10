package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.FOUR_DRAGON_ACTIVITY
import com.point18.slg2d.common.constg.FOUR_DRAGON_GATE
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import com.point18.slg2d.world.common.createActivityBossEnterTime
import com.point18.slg2d.world.msgnotice.ActivityEnterTimeChangeNotifier

// 开启四天龙
class GmActivityBossAppear : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val bossAreas = areaCache.activityBossAreaCache.findAllActivityBossArea()
        for (bossArea in bossAreas) {
            bossArea.startTime = getNowTime() + 20 * 1000
            bossArea.refreshTime = bossArea.startTime
            bossArea.finishTime = bossArea.startTime + 3600 * 1000
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