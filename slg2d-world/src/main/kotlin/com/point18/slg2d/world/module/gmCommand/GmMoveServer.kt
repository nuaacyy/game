package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.MoveServerHelper

class GmMoveServer : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        val player = session.player

        if (messages.size != 5) {
            return 2
        }

        val worldId = messages[2].toLongOrNull()
        if (worldId == null) {
            return 2
        }

        val x = messages[3].toIntOrNull()
        if (x == null) {
            return 2
        }

        val y = messages[4].toIntOrNull()
        if (y == null) {
            return 2
        }

        val ms = MoveServerHelper()
        ms.moveServer(session.areaCache, player, worldId, x, y)
        return 1
    }
}