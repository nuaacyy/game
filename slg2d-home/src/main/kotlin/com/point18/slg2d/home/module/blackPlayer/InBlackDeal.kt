package com.point18.slg2d.home.module.blackPlayer

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FRIEND_DEL
import com.point18.slg2d.common.constg.GROUP_ID_INIT
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BlackPlayerDC
import com.point18.slg2d.home.dc.FriendChatRecordDC
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4client.FriendInfo
import pb4client.InBlack
import pb4client.InBlackRt
import pb4server.*
import xyz.ariane.util.lzWarn

// 创建分组
class InBlackDeal : HomeClientMsgDeal, HomeHelperPlus4<BlackPlayerDC, FriendDC, HomePlayerDC, FriendChatRecordDC>(
    BlackPlayerDC::class.java, FriendDC::class.java, HomePlayerDC::class.java, FriendChatRecordDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { blackPlayerDC: BlackPlayerDC, friendDC: FriendDC, homePlayerDC: HomePlayerDC,
                             friendChatRecordDC: FriendChatRecordDC ->
            val blackPlayerId = (msg as InBlack).blackPlayerId
            val inBlackRt = inBlack(session, blackPlayerId,homePlayerDC, blackPlayerDC, friendChatRecordDC, friendDC)
            if (inBlackRt != null) {
                session.sendMsg(MsgType.InBlack_321, inBlackRt)
            }
        }
    }

    private fun inBlack(
        session: PlayerActor, blackPlayerId: Long, homePlayerDC: HomePlayerDC,
        blackPlayerDC: BlackPlayerDC, friendChatRecordDC: FriendChatRecordDC, friendDC: FriendDC
    ): InBlackRt? {
        val rt = InBlackRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val blackPlayerBuilder = FriendInfo.newBuilder()

        val player = homePlayerDC.player

        val blackPlayer = blackPlayerDC.findMyBlackById(blackPlayerId)

        if (blackPlayer != null) {
            rt.rt = ResultCode.BLACK_IS_EXIST.code
            return rt.build()
        }

        val askMsg = FindPlayerInBlackAskReq.newBuilder()
        askMsg.myPlayerId = player.playerId
        val askMsgBuilder = Home2HomeAskReq.newBuilder()
        askMsgBuilder.playerId = blackPlayerId
        askMsgBuilder.setFindPlayerInBlackAskReq(askMsg)

        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            askMsgBuilder.build(),
            Home2HomeAskResp::class.java
        ).whenCompleteKt { players, err ->
            if (err != null || players == null) {
                normalLog.lzWarn { "玩家查找失败:{$players}" }
                return@whenCompleteKt
            }

            val rtBuilder = InBlackRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code

            val hisChatRoom = player.chatPlayerList.firstOrNull { it.chatRoomId == blackPlayerId }
            if (hisChatRoom != null) {
                player.chatPlayerList.remove(hisChatRoom)
            }

            friendChatRecordDC.delRecordByFriendId(blackPlayerId)

            val matePlayers = players.findPlayerInBlackAskRt.targetPlayer
            blackPlayerDC.createBlackPlayer(
                blackPlayerId,
                matePlayers.worldId,
                matePlayers.name,
                matePlayers.photoProtoId,
                0,
                matePlayers.castleLv,
                matePlayers.skinType,
                matePlayers.vipLv,
                matePlayers.areaNo,
                matePlayers.allianceShortName,
                matePlayers.shortName
            )

            blackPlayerBuilder.playerId = blackPlayerId
            blackPlayerBuilder.name = matePlayers.name
            blackPlayerBuilder.photoId = matePlayers.photoProtoId
            blackPlayerBuilder.fightValue = 0
            blackPlayerBuilder.townState = 0
            blackPlayerBuilder.areaNo = matePlayers.areaNo
            blackPlayerBuilder.allianceShortName = matePlayers.allianceShortName
            blackPlayerBuilder.vipLv = matePlayers.vipLv
            blackPlayerBuilder.groupId = GROUP_ID_INIT
            blackPlayerBuilder.castleLv = matePlayers.castleLv
            blackPlayerBuilder.lastTalkTime = 0
            blackPlayerBuilder.lastReadTime = 0
            blackPlayerBuilder.shortName = matePlayers.allianceShortName

            rtBuilder.setBlackPlayerInfo(blackPlayerBuilder)

            val isFriend = friendDC.findMyFriendById(blackPlayerId)

            if (isFriend != null) {
                friendDC.delFriendInfo(isFriend)
                // 推送
                val notice = createFriendApplySuccessNotifier(
                    FRIEND_DEL,
                    matePlayers.myPlayerId,
                    matePlayers.name,
                    matePlayers.areaNo,
                    matePlayers.vipLv,
                    matePlayers.allianceShortName,
                    matePlayers.photoProtoId,
                    matePlayers.castleLv,
                    matePlayers.skinType,
                    GROUP_ID_INIT,
                    matePlayers.shortName
                )
                notice.notice(session)
            }

            // 加入广播黑名单
            fillBlackList(session, blackPlayerId)

            session.sendMsg(MsgType.InBlack_321, rtBuilder.build())
        }
        return null
    }

    private fun fillBlackList(session: PlayerActor, blackId: Long) {
        session.tellMsg {
            val multicastEnvelopeMsg = MulticastEnvelopeMsg.newBuilder()
            val addMulticastBlackList = AddMulticastBlackList.newBuilder()
            addMulticastBlackList.blackPlayerId = blackId
            multicastEnvelopeMsg.setAddMulticastBlackList(addMulticastBlackList)
            multicastEnvelopeMsg.build()
        }
    }
}