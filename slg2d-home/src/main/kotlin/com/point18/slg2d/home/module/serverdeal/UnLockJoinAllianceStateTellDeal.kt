package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.askDeal.W2HTell
import pb4server.FriendRefreshNoticeTell
import pb4server.World2HomeTell

// 解除玩家在home服的入帮状态
class UnLockJoinAllianceStateTellDeal : W2HTell, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            homePlayerDC.player.joinAllianceState = 0
        }
    }
}
