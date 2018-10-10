package com.point18.slg2d.home.module.gm

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1

class GmStopHome : GmCommand, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun exec(session: PlayerActor, message: String) {

        System.exit(0)
    }

}