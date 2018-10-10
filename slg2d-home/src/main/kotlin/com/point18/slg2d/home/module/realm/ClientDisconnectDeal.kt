package com.point18.slg2d.home.module.realm

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.LogoutReason
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4server.ClientDisconnectTell

class ClientDisconnectDeal() : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            // 解除channel绑定
            session.unbindChannelActor()

            // 告诉世界客户端断开
            val clientDisconnect = ClientDisconnectTell.newBuilder()
            session.tellWorld(session.fillHome2WorldTellMsgHeader {
                it.setClientDisconnectTell(clientDisconnect)
            })

            // 设置上次登出原因
            session.lastLogoutReason = LogoutReason.NORMAL

            // 账号中设置登出时间
            homePlayerDC.player.lastLeaveTime = getNowTime()
        }
    }

}
