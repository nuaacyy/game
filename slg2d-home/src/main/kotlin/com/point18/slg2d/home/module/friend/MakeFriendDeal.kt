package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.blackPlayer.kickOutBlackList
import pb4client.MakeFriend
import pb4client.MakeFriendRt
import pb4server.BlackTell
import pb4server.FriendNoticeAskReq
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import xyz.ariane.util.lzWarn

// 添加好友
class MakeFriendDeal : HomeClientMsgDeal,
    HomeHelperPlus5<HomePlayerDC, FriendDC, BlackPlayerDC, VipDC, SkinDC>(
        HomePlayerDC::class.java, FriendDC::class.java, BlackPlayerDC::class.java,
        VipDC::class.java, SkinDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, friendDC: FriendDC,
                             blackPlayerDC: BlackPlayerDC, vipDC: VipDC, skinDC: SkinDC ->
            val tarPlayerId = (msg as MakeFriend).tarPlayerId

            val rt = MakeFriendRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val playerId = session.playerId
            val player = homePlayerDC.player

            val myFriends = friendDC.findFriendById()

            // 是否是自己
            if (tarPlayerId == playerId) {
                rt.rt = ResultCode.MAKE_FRIEND_ERR.code
                session.sendMsg(MsgType.MakeFriend_701, rt.build())
                return@prepare
            }

            // 是否已经是好友
            for (myFriend in myFriends) {
                if (myFriend.tarPlayerId == tarPlayerId) {
                    rt.rt = ResultCode.IS_FRIEND.code
                    session.sendMsg(MsgType.MakeFriend_701, rt.build())
                    return@prepare
                }
            }

            // 好友数量限制
            if (myFriends.size >= pcs.basicProtoCache.friendsNumLimit) {
                rt.rt = ResultCode.NO_MORE_FRIEND.code
                session.sendMsg(MsgType.MakeFriend_701, rt.build())
                return@prepare
            }

            // 是否在你的黑名单中
            val blackPlayer = blackPlayerDC.findMyBlackById(tarPlayerId)
            if (blackPlayer != null) {
                blackPlayerDC.delBlackPlayer(blackPlayer)

                val tellHomeMsg = BlackTell.newBuilder()
                tellHomeMsg.myPlayerId = player.playerId

                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                    blackPlayer.blackPlayerId
                ) { it.setBlackTell(tellHomeMsg) }
                session.tellHome(home2homeTell)
                kickOutBlackList(session, blackPlayer.blackPlayerId)
            }

            // 查询要添加的好友信息
            val vip = vipDC.findVipByPlayerId()
            val skins = skinDC.findSkinsByPlayerId()
            var skinId = 1
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinId = skin.skinType
                    break
                }
            }

            val askMsg = FriendNoticeAskReq.newBuilder()
            askMsg.targetPlayerId = tarPlayerId
            askMsg.myPlayerId = playerId
            askMsg.photoProtoId = player.photoProtoId
            askMsg.name = player.name
            askMsg.areaNo = player.areaNo
            askMsg.vipLv = vip.vipLv
            askMsg.allianceShortName = player.allianceShortName
            askMsg.state = 0
            askMsg.castleLv = player.castleLv
            askMsg.skinType = skinId
            askMsg.shortName = player.allianceNickName

            val askMsgBuilder = Home2HomeAskReq.newBuilder()
            askMsgBuilder.playerId = tarPlayerId
            askMsgBuilder.setFriendNoticeAskReq(askMsg)

            session.createACS<Home2HomeAskResp>().ask(
                session.homeShardProxy,
                askMsgBuilder.build(),
                Home2HomeAskResp::class.java
            ).whenCompleteKt { askRes, err ->
                if (err != null || askRes == null) {
                    normalLog.lzWarn { "好友申请失败" }
                    return@whenCompleteKt
                }
                println(askRes.friendNoticeAskRt)
                rt.rt = askRes.friendNoticeAskRt.rt
                session.sendMsg(MsgType.MakeFriend_701, rt.build())
            }
        }
    }
}
