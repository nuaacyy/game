package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeTaskDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HTell
import com.point18.slg2d.home.module.gm.gmM
import pb4server.World2HomeTell

class UseGmDeal : W2HTell, HomeHelperPlus1<HomeTaskDC>(
    HomeTaskDC::class.java
) {
    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        val tell = msg.useGmReqTell
        val gmWrap = gmM.cmds[tell.gmType]
        if (gmWrap != null) {
            gmWrap.execGm(session, tell.message)
        }
    }
}
