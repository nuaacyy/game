package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HAsk
import pb4server.*

class CheckJoinAllianceStateFromWorldDeal : W2HAsk,
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC ->
            val player = homePlayerDC.player

            val rt = CheckJoinAllianceStateRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            if (player.joinAllianceState != 0) {
                rt.rt = ResultCode.PLAYER_JOIN_ALLIANCE_BUSY.code
            } else if (player.allianceId != 0L) {
                rt.rt = ResultCode.ALLIANCE_PLAYER_HAS_ALLIANCE.code
            } else {
                player.joinAllianceState = 1
            }
            resp.setCheckJoinAllianceStateRt(rt)
        }
    }
}








