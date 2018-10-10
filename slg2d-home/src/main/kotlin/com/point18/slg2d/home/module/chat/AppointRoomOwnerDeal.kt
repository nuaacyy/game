package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4client.ChatRoomAppoint
import pb4client.ChatRoomAppointRt
import pb4server.ChatRoomChangeInfoTell
import pb4server.Home2PublicAskResp
import pb4server.RoomOwnerChangeAskReq

class AppointRoomOwnerDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val roomId = (msg as ChatRoomAppoint).roomId
            val newOwnerId = msg.playerId
            val rt = appointSomeone(session, roomId, newOwnerId, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.AppointRoomOwner_309, rt)
            }
        }
    }

    private fun appointSomeone(
        session: PlayerActor, roomId: Long, newOwnerId: Long, homePlayerDC: HomePlayerDC
    ): ChatRoomAppointRt? {
        val rt = ChatRoomAppointRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        // 没有这个聊天室就不推送
        val chatRoom = player.chatRoomList.firstOrNull { it.chatRoomId == roomId }
        if (chatRoom == null) {
            rt.rt = ResultCode.SUCCESS.code
            return rt.build()
        }

        val askMsg = RoomOwnerChangeAskReq.newBuilder()
        askMsg.roomId = roomId
        askMsg.newOwner = newOwnerId
        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setRoomOwnerChangeAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->

            if (askErr != null || prt == null) {
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
            } else if (prt.roomOwnerChangeAskRt.rt != ResultCode.SUCCESS.code) {
                rt.rt = prt.roomOwnerChangeAskRt.rt
                session.sendMsg(MsgType.AppointRoomOwner_309, rt.build())
            } else {
                session.sendMsg(MsgType.AppointRoomOwner_309, rt.build())

                // 成功了就告诉新群主
                val tellMsg = ChatRoomChangeInfoTell.newBuilder()
                tellMsg.chatRoomId = prt.roomOwnerChangeAskRt.chatRoomId
                tellMsg.chatRoomId = prt.roomOwnerChangeAskRt.chatRoomId
                tellMsg.unreadNum = prt.roomOwnerChangeAskRt.unreadNum
                tellMsg.addAllIconProtoIds(prt.roomOwnerChangeAskRt.iconProtoIdsList)
                tellMsg.memberNum = prt.roomOwnerChangeAskRt.memberNum
                tellMsg.roomPlayerId = prt.roomOwnerChangeAskRt.roomPlayerId
                tellMsg.lastSendTime = prt.roomOwnerChangeAskRt.lastSendTime

                val home2Home = session.fillHome2HomeTellMsgHeader(
                    prt.roomOwnerChangeAskRt.roomPlayerId
                ) { it.setChatRoomChangeInfoTell(tellMsg) }
                session.tellHome(home2Home)

                // 告诉旧群主
                createRoomNotifierExtend(
                    prt.roomOwnerChangeAskRt.chatRoomId,
                    prt.roomOwnerChangeAskRt.roomName,
                    prt.roomOwnerChangeAskRt.unreadNum,
                    prt.roomOwnerChangeAskRt.iconProtoIdsList,
                    prt.roomOwnerChangeAskRt.memberNum,
                    prt.roomOwnerChangeAskRt.roomPlayerId,
                    chatRoom.lastReadTime / 1000,
                    prt.roomOwnerChangeAskRt.lastSendTime / 1000
                ).notice(session)
            }
        }

        return null
    }

}