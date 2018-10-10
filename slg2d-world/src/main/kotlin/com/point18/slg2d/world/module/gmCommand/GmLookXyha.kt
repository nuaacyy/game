package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.getFarmInfoByGridId
import com.point18.slg2d.common.pc.pcs

class GmLookXyha : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val xyMap = getFarmInfoByGridId(0, pcs.basicProtoCache.resourceArea)

        for ((_, info) in xyMap) {
            println("XY:${info.x},${info.y}")
        }
        return 1
    }
}
