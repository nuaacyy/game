package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession

class GmAddFriend : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        if (messages.size != 3) {
            return 2
        }

        val tarPlayerId = messages[2].toIntOrNull()
        if (tarPlayerId == null) {
            return 2
        }

        return 1
    }
}
