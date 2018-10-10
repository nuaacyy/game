package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4client.DelGroupChatMember
import pb4client.DelGroupChatMemberRt
import pb4server.ChatRoomChangeInfoTell
import pb4server.ChatRoomKickOutTell
import pb4server.Home2PublicAskResp
import pb4server.KickOutChatRoomAskReq

class KickOutMemberDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC ->
            val roomId = (msg as DelGroupChatMember).groupId
            val removeId = msg.playerId
            val rt = kickOutOne(session, roomId, removeId, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.DelGroupChatMember_316, rt)
            }
        }
    }

    private fun kickOutOne(session: PlayerActor, roomId: Long, removeId: Long, homePlayerDC: HomePlayerDC): DelGroupChatMemberRt? {
        val rt = DelGroupChatMemberRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.playerId = removeId
        val player = homePlayerDC.player

        // 没有这个聊天室就不推送
        val chatRoom = player.chatRoomList.firstOrNull { it.chatRoomId == roomId }
        if (chatRoom == null) {
            rt.rt = ResultCode.SUCCESS.code
            return rt.build()
        }

        val askMsg = KickOutChatRoomAskReq.newBuilder()
        askMsg.roomId = roomId
        askMsg.playerIdRemove = removeId

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setKickOutChatRoomAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->

            if (askErr != null || prt == null) {
                println("return数据失败")
            } else if (prt.kickOutChatRoomAskRt.rt != ResultCode.SUCCESS.code) {
                rt.rt = prt.kickOutChatRoomAskRt.rt
                session.sendMsg(MsgType.DelGroupChatMember_316, rt.build())
            } else {
                rt.rt = prt.kickOutChatRoomAskRt.rt

                // 告诉旧群主
                createRoomNotifierExtend(
                    prt.kickOutChatRoomAskRt.chatRoomId,
                    prt.kickOutChatRoomAskRt.roomName,
                    prt.kickOutChatRoomAskRt.unreadNum,
                    prt.kickOutChatRoomAskRt.iconProtoIdsList,
                    prt.kickOutChatRoomAskRt.memberNum,
                    prt.kickOutChatRoomAskRt.roomPlayerId,
                    chatRoom.lastReadTime,
                    prt.kickOutChatRoomAskRt.lastSendTime
                ).notice(session)

                //  告诉home去取消订阅这个channel 并且记录下这个频道
                val tellHomeMsg = ChatRoomKickOutTell.newBuilder()
                tellHomeMsg.chatRoomId = prt.kickOutChatRoomAskRt.chatRoomId
                tellHomeMsg.roomName = prt.kickOutChatRoomAskRt.roomName
                tellHomeMsg.roomPlayerId = prt.kickOutChatRoomAskRt.roomPlayerId
                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                    removeId
                ) { it.setChatRoomKickOutTell(tellHomeMsg) }
                session.tellHome(home2homeTell)

                // 通知人数变化
//                    prt.kickOutChatRoomAskRt.memberIdsList.remove(session.playerId)
                for (eachMember in prt.kickOutChatRoomAskRt.memberIdsList) {

                    val tellMsg = ChatRoomChangeInfoTell.newBuilder()
                    tellMsg.chatRoomId = prt.kickOutChatRoomAskRt.chatRoomId
                    tellMsg.unreadNum = prt.kickOutChatRoomAskRt.unreadNum
                    tellMsg.addAllIconProtoIds(prt.kickOutChatRoomAskRt.iconProtoIdsList)
                    tellMsg.memberNum = prt.kickOutChatRoomAskRt.memberNum
                    tellMsg.roomPlayerId = prt.kickOutChatRoomAskRt.roomPlayerId
                    tellMsg.lastSendTime = prt.kickOutChatRoomAskRt.lastSendTime

                    val home2Home = session.fillHome2HomeTellMsgHeader(
                        eachMember
                    ) { it.setChatRoomChangeInfoTell(tellMsg) }
                    session.tellHome(home2Home)
                }

                session.sendMsg(MsgType.DelGroupChatMember_316, rt.build())
            }
        }
        return null
    }
}