package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.pc.pcs
import java.time.LocalDateTime
import java.time.ZoneId

// 停止刷新大地图
class GmStopRefMap : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"))
        var nextRefTime = LocalDateTime.of(now.year, now.month, now.dayOfMonth, pcs.basicProtoCache.timeZone, 0, 0, 0)
        if (now.hour >= pcs.basicProtoCache.timeZone) {
            // 起服的时候已经过了今天的,把时间改到明天
            nextRefTime = nextRefTime.plusHours(24)
        }

        session.areaCache.mapCellCache.nextResRefTime = nextRefTime.atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli()
        session.areaCache.mapCellCache.nowResRefId = 1
        return 1
    }
}


