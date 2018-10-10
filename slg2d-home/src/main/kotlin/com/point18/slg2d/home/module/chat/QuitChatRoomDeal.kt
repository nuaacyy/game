package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.QuitChatRoom
import pb4client.QuitChatRoomRt
import pb4server.ChatRoomChangeInfoTell
import pb4server.ChatRoomKickOutTell
import pb4server.Home2PublicAskResp
import pb4server.QuitOneRoomAskReq

class QuitChatRoomDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) {
            val roomId = (msg as QuitChatRoom).roomId
            quitOneRoom(session, roomId)
        }
    }

    private fun quitOneRoom(session: PlayerActor, roomId: Long) {

        val askMsg = QuitOneRoomAskReq.newBuilder()
        askMsg.roomId = roomId

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setQuitOneRoomAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->

            val rt = QuitChatRoomRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            if (askErr != null || prt == null) {

            } else if (prt.quitOneRoomAskRt.rt != ResultCode.SUCCESS.code) {
                rt.rt = prt.quitOneRoomAskRt.rt
                session.sendMsg(MsgType.QuitChatRoom_312, rt.build())
            } else {
                rt.rt = prt.quitOneRoomAskRt.rt

                //  告诉home去订阅这个channel 并且取消订阅这个频道
                val tellHomeMsg = ChatRoomKickOutTell.newBuilder()
                tellHomeMsg.chatRoomId = prt.quitOneRoomAskRt.chatRoomId
                tellHomeMsg.roomName = prt.quitOneRoomAskRt.roomName
                tellHomeMsg.roomPlayerId = prt.quitOneRoomAskRt.roomPlayerId
                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                    session.playerId
                ) { it.setChatRoomKickOutTell(tellHomeMsg) }
                session.tellHome(home2homeTell)
                session.sendMsg(MsgType.QuitChatRoom_312, rt.build())

                // 通知人数变化
                for (eachMember in prt.quitOneRoomAskRt.memberIdsList) {

                    val tellMsg = ChatRoomChangeInfoTell.newBuilder()
                    tellMsg.chatRoomId = prt.quitOneRoomAskRt.chatRoomId
                    tellMsg.chatRoomId = prt.quitOneRoomAskRt.chatRoomId
                    tellMsg.unreadNum = prt.quitOneRoomAskRt.unreadNum
                    tellMsg.addAllIconProtoIds(prt.quitOneRoomAskRt.iconProtoIdsList)
                    tellMsg.memberNum = prt.quitOneRoomAskRt.memberNum
                    tellMsg.roomPlayerId = prt.quitOneRoomAskRt.roomPlayerId
                    tellMsg.lastSendTime = prt.quitOneRoomAskRt.lastSendTime

                    val home2Home = session.fillHome2HomeTellMsgHeader(
                        eachMember
                    ) { it.setChatRoomChangeInfoTell(tellMsg) }
                    session.tellHome(home2Home)

                }
            }
        }

    }
}