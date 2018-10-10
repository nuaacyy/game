package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.KillActivityBossReport

// 击杀活动魔物推送
class KillActivityBossNotifier(
    val msg: KillActivityBossReport.Builder
) {
    fun notice(areaCache: AreaCache) {
        val sessions = fetchAllOnlinePlayerSessions(areaCache)
        for (session in sessions.values) {
            session.sendMsg(MsgType.KillActivityBossReport_3190, this.msg.build())
        }
    }
}

fun createKillActivityBossNotifier(
    playerName: String,
    allianceShortName: String,
    activityBossId: Int
): KillActivityBossNotifier {
    val msgBuilder = KillActivityBossReport.newBuilder()
    msgBuilder.playerName = playerName
    msgBuilder.allianceShortName = allianceShortName
    msgBuilder.activityBossId = activityBossId

    return KillActivityBossNotifier(msgBuilder)
}


