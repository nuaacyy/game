package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.playerLeaveAlliance
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.W2HTell
import pb4server.World2HomeTell

class LeaveAllianceDeal : W2HTell, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java
) {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        prepare(session) { homePlayerDC, innerCityDC ->
            playerLeaveAlliance(homePlayerDC.player, innerCityDC)
        }
    }
}