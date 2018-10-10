package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession

class GmOpenSendAllianceGift : GmCommand {

    override fun exec(session: PlayerSession, message: String): Int {
        val player = session.player

        player.openAllianceSendGift = 1

        return 1
    }

}
