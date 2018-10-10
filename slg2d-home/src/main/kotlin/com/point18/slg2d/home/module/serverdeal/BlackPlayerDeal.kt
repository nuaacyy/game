package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.H2HTell
import pb4server.Home2HomeTell

// 从对方的黑名单中删除
class BlackPlayerDeal : H2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        prepare(session) { homePlayerDC ->
            val myPlayerId = msg.blackTell.myPlayerId
            val player = homePlayerDC.player
            player.blackPlayers.remove(myPlayerId)
        }
    }

}