package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.askDeal.H2HTell
import com.point18.slg2d.home.msgnotice.createFriendChatMsgNotifier
import com.point18.slg2d.home.msgnotice.createNewChatWindowNotifier
import pb4server.Home2HomeTell

//
class FriendPrivateChatDeal : H2HTell, HomeHelperPlus4<HomePlayerDC, FriendChatRecordDC, FriendDC, BlackPlayerDC>(
    HomePlayerDC::class.java,
    FriendChatRecordDC::class.java,
    FriendDC::class.java,
    BlackPlayerDC::class.java
) {

    override fun dealHomeTell(session: PlayerActor, worldId: Long, playerId: Long, msg: Home2HomeTell) {
        val receiveMsg = msg.saveFriendChatRecordTell

        prepare(session) { homePlayerDC, friendChatRecordDC, friendDc, blackPlayerDC ->

            // 是不是在黑名单,在的话屏蔽
            val isBlackPlayer = blackPlayerDC.blackPlayers.firstOrNull { receiveMsg.friendId == it.blackPlayerId }
            if (isBlackPlayer != null) {
                return@prepare
            }

            val homePlayer = homePlayerDC.player
            val record = friendChatRecordDC.createFriendChatRecord(
                receiveMsg.lastTalkTime,
                receiveMsg.iconId,
                receiveMsg.recordString,
                receiveMsg.friendId,
                receiveMsg.msgType,
                receiveMsg.vipLv,
                receiveMsg.alliancePos,
                receiveMsg.allianceName,
                receiveMsg.allianceShortName,
                receiveMsg.playerName,
                receiveMsg.playerShortName,
                receiveMsg.kingdomPos,
                receiveMsg.wonderPos,
                receiveMsg.friendId,
                receiveMsg.areaNo
            )

            val chatSession = homePlayer.chatPlayerList.firstOrNull { it.chatRoomId == receiveMsg.friendId }
            if (chatSession == null) {
                homePlayer.chatPlayerList.add(MyChat(receiveMsg.friendId, 0))
            }

            // 推送新陌生人的聊天窗口  如果是好友就不要推
            val friends = friendDc.findFriendById()
            val myFriend = friends.firstOrNull { it.tarPlayerId == receiveMsg.friendId }
            if (myFriend == null) {
                val chatTimeRecord = homePlayer.chatPlayerList.firstOrNull { it.chatRoomId == receiveMsg.friendId }
                var readTime = 0L
                if (chatTimeRecord != null) {
                    readTime = chatTimeRecord.lastReadTime
                }
                createNewChatWindowNotifier(
                    receiveMsg.friendId,
                    0,
                    receiveMsg.playerName,
                    receiveMsg.iconId,
                    receiveMsg.power.toLong(),
                    receiveMsg.castleLv,
                    0,
                    receiveMsg.vipLv,
                    receiveMsg.areaNo,
                    receiveMsg.allianceShortName,
                    receiveMsg.lastTalkTime,
                    readTime,
                    receiveMsg.playerShortName
                ).notice(session)
            }

            // 把这条消息推出去
            val sendMsg = receiveMsg.recordString
            createFriendChatMsgNotifier(
                receiveMsg.lastTalkTime,
                receiveMsg.iconId,
                sendMsg,
                receiveMsg.friendId,
                receiveMsg.msgType,
                receiveMsg.vipLv,
                receiveMsg.alliancePos,
                receiveMsg.allianceName,
                receiveMsg.allianceShortName,
                receiveMsg.playerName,
                receiveMsg.playerShortName,
                receiveMsg.kingdomPos,
                receiveMsg.wonderPos,
                receiveMsg.friendId,
                receiveMsg.areaNo,
                record.id
            ).notice(session)
        }
    }
}
