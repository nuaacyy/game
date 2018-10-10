package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BlackPlayerDC
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.W2HAsk
import pb4server.QueryFriendBlackAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class QueryFriendBlackDeal : W2HAsk,
    HomeHelperPlus2<FriendDC, BlackPlayerDC>(FriendDC::class.java, BlackPlayerDC::class.java) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val askMsg = req.queryFriendBlackAskReq
        val targetPlayerId = askMsg.targetPlayerId

        prepare(session) { friendDC: FriendDC, blackPlayerDC: BlackPlayerDC ->
            val rt = QueryFriendBlackAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            rt.isMyFriend = 0
            rt.isInMyBlack = 0

            val friendInfo = friendDC.findMyFriendById(targetPlayerId)
            if (friendInfo != null) {
                rt.isMyFriend = 1
            }

            val isInBlack = blackPlayerDC.findMyBlackById(targetPlayerId)
            if (isInBlack != null) {
                rt.isInMyBlack = 1
            }

            resp.setQueryFriendBlackAskRt(rt)
        }
    }
}








