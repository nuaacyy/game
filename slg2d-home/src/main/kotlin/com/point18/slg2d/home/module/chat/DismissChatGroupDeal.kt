package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.roomChannelOf
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createRoomDelMemberNotifier
import pb4client.DelGroupChat
import pb4client.DelGroupChatRt
import pb4server.ChatRoomDismissTell
import pb4server.DissmissChatRoomAskReq
import pb4server.Home2PublicAskResp

class DismissChatGroupDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val roomId = (msg as DelGroupChat).groupId

            val rt = dismissChat(session, roomId, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.DelGroupChat_317, rt)
            }
        }
    }

    private fun dismissChat(session: PlayerActor, roomId: Long, homePlayerDC: HomePlayerDC): DelGroupChatRt? {

        // 去public 删除  返回里面有的人,通知他们改数据库和取消订阅
        val askMsg = DissmissChatRoomAskReq.newBuilder()
        askMsg.roomId = roomId

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setDissmissChatRoomAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->
            val rt = DelGroupChatRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || prt == null) {
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else {
                rt.rt = prt.dissmissChatRoomAskRt.rt
                session.sendMsg(MsgType.DelGroupChat_317, rt.build())

                if (prt.dissmissChatRoomAskRt.rt == ResultCode.SUCCESS.code) {
                    session.unsubscribeChannel(roomChannelOf(roomId))
                    createRoomDelMemberNotifier(roomId).notice(session)
                    val player = homePlayerDC.player

                    prt.dissmissChatRoomAskRt.membersList.remove(session.playerId)
                    val tellMsg = ChatRoomDismissTell.newBuilder()

                    tellMsg.chatRoomId = roomId
                    tellMsg.roomName = prt.dissmissChatRoomAskRt.roomName
                    tellMsg.roomPlayerId = prt.dissmissChatRoomAskRt.roomPlayerId
                    tellMsg.actionPlayerName = player.name
                    tellMsg.actionPlayerShort = player.allianceNickName
                    tellMsg.actPlayerAlliance = player.allianceShortName


                    for (eachPlayerId in prt.dissmissChatRoomAskRt.membersList) {
                        val home2homeTell = session.fillHome2HomeTellMsgHeader(
                            eachPlayerId
                        ) { it.setChatRoomDismissTell(tellMsg) }
                        session.tellHome(home2homeTell)
                    }
                }
            }
        }
        return null
    }
}