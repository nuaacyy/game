package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendChatRecordDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DelStrangerChat
import pb4client.DelStrangerChatRt

class DelStrangerChatDeal : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, FriendChatRecordDC>(
    HomePlayerDC::class.java, FriendChatRecordDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, friendChatRecordDC: FriendChatRecordDC ->
            val playerIdDel = (msg as DelStrangerChat).playerId
            val rt = delStrangerChat(
                session, playerIdDel, homePlayerDC, friendChatRecordDC
            )
            session.sendMsg(MsgType.DelStrangerChat_296, rt)
        }
    }

    private fun delStrangerChat(
        session: PlayerActor, playerIdDel: Long, homePlayerDC: HomePlayerDC, friendChatRecordDC: FriendChatRecordDC
    ): DelStrangerChatRt {

        val rt = DelStrangerChatRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 删记录
        friendChatRecordDC.delRecordByFriendId(playerIdDel)
        friendChatRecordDC.deleteOutOfDate()

        // 删窗口
        homePlayerDC.player.focusChatPlayerId = 0
        homePlayerDC.player.chatPlayerList.removeIf { it.chatRoomId == playerIdDel }

        return rt.build()
    }
}