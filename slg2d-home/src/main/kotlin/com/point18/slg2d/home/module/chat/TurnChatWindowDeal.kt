package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.MyChat
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ChangeChatWindow
import pb4client.ChangeChatWindowRt

class TurnChatWindowDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC ->
            msg as ChangeChatWindow
            val roomIdNew = msg.roomIdNew
            val playerIdNew = msg.playerIdNew
            val rt = openStrangerChat(session, roomIdNew, playerIdNew, homePlayerDC)
            session.sendMsg(MsgType.ChangeChatWindow_298, rt)
        }
    }

    private fun openStrangerChat(
        session: PlayerActor, roomIdNew: Long, playerIdNew: Long, homePlayerDC: HomePlayerDC
    ): ChangeChatWindowRt {
        // 去home找这个player的信息
        val rt = ChangeChatWindowRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 切换聊天框框时候只能切换一个
        if (roomIdNew != 0L && playerIdNew != 0L) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val nowTime = getNowTime()

        val player = homePlayerDC.player
        val playerRooms = player.chatRoomList
        val privateChat = player.chatPlayerList
        // 记录下之前那个聊天室的最后聊天时间
        if (player.focusChatRoomId != 0L) {
            val oldRoom = playerRooms.firstOrNull { it.chatRoomId == player.focusChatRoomId }
            if (oldRoom != null) {
                oldRoom.lastReadTime = nowTime
            }
        }

        if (player.focusChatPlayerId != 0L) {
            val privateChats = privateChat.firstOrNull { it.chatRoomId == player.focusChatPlayerId }
            if (privateChats != null) {
                privateChats.lastReadTime = nowTime
            }
        }

        if (roomIdNew != 0L) {
            val nowRoom = playerRooms.firstOrNull { it.chatRoomId == roomIdNew }
            if (nowRoom == null) {
                rt.rt = ResultCode.NO_CHAT_ROOM.code
                return rt.build()
            }
            player.focusChatRoomId = roomIdNew
            nowRoom.lastReadTime = nowTime
        }



        if (playerIdNew != 0L) {
            var nowRoom = privateChat.firstOrNull { it.chatRoomId == playerIdNew }
            if (nowRoom == null) {
                nowRoom = MyChat(playerIdNew, nowTime)
                privateChat.add(nowRoom)
            }
            nowRoom.lastReadTime = nowTime
            player.focusChatPlayerId = playerIdNew
        }

        return rt.build()
    }
}