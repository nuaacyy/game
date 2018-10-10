package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HTell
import pb4server.World2HomeTell

class AllianceInfoChangeDeal : W2HTell, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        prepare(session) { homePlayerDC ->
            val tell = msg.allianceInfoChangeTell
            val allianceName = tell.allianceName
            val allianceShortName = tell.allianceShortName
            val flagColor = tell.flagColor
            val flagStyle = tell.flagStyle
            val flagEffect = tell.flagEffect
            val player = homePlayerDC.player

            player.allianceName = allianceName
            player.allianceShortName = allianceShortName
            player.flagColor = flagColor
            player.flagStyle = flagStyle
            player.flagEffect = flagEffect
        }
    }

}