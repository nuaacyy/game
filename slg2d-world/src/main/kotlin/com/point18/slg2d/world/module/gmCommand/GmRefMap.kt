package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import java.time.Instant

// 刷新大地图
class GmRefMap : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {

        session.areaCache.mapCellCache.nextResRefTime = Instant.now().toEpochMilli() + 1000
        session.areaCache.mapCellCache.nowResRefId = 1
        return 1
    }
}

