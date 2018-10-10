package com.point18.slg2d.home.module.blackPlayer

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BlackPlayerDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.OffBlack
import pb4client.OffBlackRt
import pb4server.BlackTell
import pb4server.DelMulticastBlackList
import pb4server.MulticastEnvelopeMsg

// 从黑名单中移除
class OffBlackDeal : HomeClientMsgDeal, HomeHelperPlus2<BlackPlayerDC, HomePlayerDC>(
    BlackPlayerDC::class.java, HomePlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { blackPlayerDC: BlackPlayerDC, homePlayerDC: HomePlayerDC ->
            val blackPlayerId = (msg as OffBlack).blackPlayerId
            val offBlackRt = offBlack(session, blackPlayerId, blackPlayerDC, homePlayerDC)
            session.sendMsg(MsgType.OffBlack_322, offBlackRt)
        }
    }

    private fun offBlack(session: PlayerActor, blackPlayerId: Long, blackPlayerDC: BlackPlayerDC, homePlayerDC: HomePlayerDC): OffBlackRt {
        val rt = OffBlackRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.blackPlayerId = blackPlayerId

        val player = homePlayerDC.player

        val blackPlayer = blackPlayerDC.findMyBlackById(blackPlayerId)

        if (blackPlayer == null) {
            rt.rt = ResultCode.BLACK_IS_NOT_EXIST.code
            return rt.build()
        }

        val tellHomeMsg = BlackTell.newBuilder()
        tellHomeMsg.myPlayerId = player.playerId

        val home2homeTell = session.fillHome2HomeTellMsgHeader(
            blackPlayer.blackPlayerId
        ) { it.setBlackTell(tellHomeMsg) }
        session.tellHome(home2homeTell)

        blackPlayerDC.delBlackPlayer(blackPlayer)
        kickOutBlackList(session, blackPlayerId)

        return rt.build()
    }
}

fun kickOutBlackList(session: PlayerActor, blackId: Long) {
    session.tellMsg {
        val multicastEnvelopeMsg = MulticastEnvelopeMsg.newBuilder()
        val delMulticastBlackList = DelMulticastBlackList.newBuilder()
        delMulticastBlackList.blackPlayerId = blackId
        multicastEnvelopeMsg.setDelMulticastBlackList(delMulticastBlackList)
        multicastEnvelopeMsg.build()
    }
}