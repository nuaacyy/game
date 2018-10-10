package com.point18.slg2d.home.module.realm

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.msgDealsAtHome
import pb4client.EnterGame
import xyz.ariane.util.lzWarn

@Volatile
var realmModule = RealmModule()

open class RealmModule {

    // 处理客户端断线
//    open fun handleClientDisconnect(session: PlayerActor) {
//        // 解除channel绑定
//        session.unbindChannelActor()
//
//        // 告诉世界客户端断开
//        val clientDisconnect = ClientDisconnectTell.newBuilder()
//        session.tellWorld(session.fillHome2WorldTellMsgHeader {
//            it.setClientDisconnectTell(clientDisconnect)
//        })
//
//        // 设置上次登出原因
//        session.lastLogoutReason = LogoutReason.NORMAL
//
//        // 账号中设置登出时间
//        session.db.getDCIfPresent(HomePlayerDC::class.java)?.player?.lastLeaveTime = getNowTime()
//    }

    /**
     * 处理客户端断线
     */
    fun dealDisconnect(session: PlayerActor): Boolean {
        val msgDeals = msgDealsAtHome
        val deal = msgDeals[MsgType.Offline_2999.msgType]
        if (deal == null) {
            normalLog.lzWarn { "找不到消息号为的 ${MsgType.Offline_2999} MsgDeal" }
            return false
        }

        // 交给目标方法处理
        // 需要注意的是，这里的EnterGame.getDefaultInstance()实际中并不会有任何用处。
        deal.dealPlayerReq(session, 0, EnterGame.getDefaultInstance())
        return true
    }
}