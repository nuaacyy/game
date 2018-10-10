package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.WONDER_WAR_ACTIVITY
import com.point18.slg2d.common.constg.WONDER_WAR_GATE
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import com.point18.slg2d.world.common.createWonderEnterTime
import com.point18.slg2d.world.msgnotice.ActivityEnterTimeChangeNotifier

// 结束奇观战
class GmStopWonderWar : GmCommand {
    // 活动ID
    private val wonderChallengeActivity: Int = 6001   // 奇观挑战
    private val wonderMarchActivity: Int = 6002   // 奇观进行曲

    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val nowTime = getNowTime()

        val bigWonder = areaCache.wonderCache.findBigWonder()
        if (bigWonder == null) {
            return 2
        }

        // 奇观都没开，不能关闭
        if (nowTime < bigWonder.warStartTime) {
            return 2
        }

        // 修改奇观的结束时间
        val wonders = areaCache.wonderCache.findAllWonders()
        for (wonder in wonders) {
            wonder.warFinishTime = nowTime + 10 * 1000
        }

        // 修改奇观挑战和奇观进行曲的结束时间
        val wonderChallengeActivity = areaCache.serverActivityCache.findServerActivityByActivityId(wonderChallengeActivity)
        if (wonderChallengeActivity != null) {
            wonderChallengeActivity.overTime = bigWonder.warFinishTime
        }
        val wonderMarchActivity = areaCache.serverActivityCache.findServerActivityByActivityId(wonderMarchActivity)
        if (wonderMarchActivity != null) {
            wonderMarchActivity.overTime = bigWonder.warFinishTime
        }

        // 通知新的奇观活动时间
        val notifier = ActivityEnterTimeChangeNotifier()
        notifier.append(createWonderEnterTime(areaCache, WONDER_WAR_GATE))
        notifier.append(createWonderEnterTime(areaCache, WONDER_WAR_ACTIVITY))
        for ((_, targetSession) in fetchAllOnlinePlayerSessions(areaCache)) {
            notifier.notice(targetSession)
        }

        return 1
    }
}

