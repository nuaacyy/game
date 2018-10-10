package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.playerActivity.dealTimeOverActivity
import com.point18.slg2d.world.common.gmOverAllianceActivity

// 让当前的个人活动全都结束
class GMActivityOver : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val timeOverActivity = session.areaCache.serverActivityCache.findAllActivity()

        for (activity in timeOverActivity) {
            dealTimeOverActivity(session.areaCache, activity)
        }

        gmOverAllianceActivity(session.areaCache)
        return 1
    }
}
