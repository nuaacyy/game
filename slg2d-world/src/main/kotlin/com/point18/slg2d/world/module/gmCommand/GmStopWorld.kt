package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession

class GmStopWorld : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        System.exit(0)
        return 1
    }
}