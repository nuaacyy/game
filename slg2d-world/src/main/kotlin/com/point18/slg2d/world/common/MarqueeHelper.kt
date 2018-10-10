package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createNoticeInfoNotifier

// 跑马灯广播（个人）
fun sendMarqueeNotice2Player(
    areaCache: AreaCache,
    noticeType: Int,
    playerId: Long,
    lanId: String,
    readType: Int,
    vararg params: String
) {
    // 玩家个人
    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        val noticeNotifier = createNoticeInfoNotifier(noticeType, lanId, readType, *params)
        noticeNotifier.notice(session)
    }
}

// 跑马灯广播（全服）
fun sendMarqueeNotice2All(
    areaCache: AreaCache,
    noticeType: Int,
    lanId: String,
    readType: Int,
    vararg params: String
) {
    val noticeNotifier = createNoticeInfoNotifier(noticeType, lanId, readType, *params)

    for ((_, session) in fetchAllOnlinePlayerSessions(areaCache)) {
        noticeNotifier.notice(session)
    }
}
