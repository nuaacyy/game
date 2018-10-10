package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.maxTime
import com.point18.slg2d.common.constg.WONDER_WAR_ACTIVITY
import com.point18.slg2d.common.constg.WONDER_WAR_GATE
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import com.point18.slg2d.world.common.createWonderEnterTime
import com.point18.slg2d.world.msgnotice.ActivityEnterTimeChangeNotifier

// 开启奇观战（非立即开始）
class GmStartWonderWar : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val wonders = areaCache.wonderCache.findAllWonders()
        if (wonders.size > 0) {
            val nowTime = getNowTime()
            for (wonder in wonders) {
                if (nowTime > wonder.warStartTime) {
                    return 2
                }
            }

            val startHour = pcs.basicProtoCache.wonderWarOpen[1] + pcs.basicProtoCache.wonderWarOpen[0] * 24
            val endHour = pcs.basicProtoCache.wonderWarClose[1] + pcs.basicProtoCache.wonderWarClose[0] * 24
            val continueHour = endHour - startHour //活动持续时间

            for (wonder in wonders) {
                wonder.warStartTime = nowTime + 10 * 1000 // 10秒后开启
                wonder.occupyStartTime = maxTime.time
                wonder.occupyOverTime = maxTime.time
                wonder.warFinishTime = wonder.warStartTime + continueHour * 3600 * 1000
            }
        }

        val notifier = ActivityEnterTimeChangeNotifier()
        notifier.append(createWonderEnterTime(areaCache, WONDER_WAR_GATE))
        notifier.append(createWonderEnterTime(areaCache, WONDER_WAR_ACTIVITY))
        for ((_, targetSession) in fetchAllOnlinePlayerSessions(areaCache)) {
            notifier.notice(targetSession)
        }

        return 1
    }
}

