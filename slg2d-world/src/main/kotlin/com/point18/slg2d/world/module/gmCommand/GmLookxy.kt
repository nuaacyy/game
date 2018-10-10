package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.PlayerSession

class GmLookxy : GmCommand {

    override fun exec(session: PlayerSession, message: String): Int {
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        if (messages.size != 4) {
            return 2
        }
        val xx = messages[2].toIntOrNull()
        if (xx == null) {
            return 2
        }

        val yy = messages[3].toIntOrNull()
        if (yy == null) {
            return 2
        }
        val add = pcs.wonderLocationProtoCache.findInWonderType(xx, yy)
        println("坐标属于遗迹的位置为:$add")
        return 1
    }

}
