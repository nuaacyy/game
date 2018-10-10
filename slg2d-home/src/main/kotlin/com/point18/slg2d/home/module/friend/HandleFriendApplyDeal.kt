package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus7
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.blackPlayer.kickOutBlackList
import com.point18.slg2d.home.module.event.GetFriendEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createFriendApplyChangeNotifier
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4client.HandleFriendApply
import pb4client.HandleFriendApplyRt
import pb4server.BlackTell
import pb4server.FriendAcceptAskReq
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp
import xyz.ariane.util.lzWarn

// 处理好友请求
class HandleFriendApplyDeal : HomeClientMsgDeal,
    HomeHelperPlus7<HomePlayerDC, FriendDC, FriendApplyDC, VipDC, SkinDC, HomeMyTargetDC, BlackPlayerDC>(
        HomePlayerDC::class.java, FriendDC::class.java, FriendApplyDC::class.java, VipDC::class.java,
        SkinDC::class.java, HomeMyTargetDC::class.java, BlackPlayerDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, friendDC: FriendDC,
                             friendApplyDC: FriendApplyDC, vipDC: VipDC, skinDC: SkinDC,
                             homeMyTargetDC: HomeMyTargetDC, blackPlayerDC: BlackPlayerDC ->
            val tarPlayerId = (msg as HandleFriendApply).tarPlayerId
            val type = msg.type
            val rt = this.handleFriend(
                session, tarPlayerId, type,
                homePlayerDC, friendDC, friendApplyDC, vipDC, skinDC,
                homeMyTargetDC, blackPlayerDC
            )
            if (rt != null) {
                session.sendMsg(MsgType.HandleFriendApply_710, rt)
            }
        }
    }

    private fun handleFriend(
        session: PlayerActor, tarPlayerId: Long, type: Int, homePlayerDC: HomePlayerDC,
        friendDC: FriendDC, friendApplyDC: FriendApplyDC, vipDC: VipDC, skinDC: SkinDC,
        homeMyTargetDC: HomeMyTargetDC, blackPlayerDC: BlackPlayerDC
    ): HandleFriendApplyRt? {
        val rtBuilder = HandleFriendApplyRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        // type; 1：同意，0：不同意

        val player = homePlayerDC.player

        val friendLimit = pcs.basicProtoCache.friendsNumLimit
        val friends = friendDC.findFriendById()

        // 查找vip等级和皮肤
        val vipLv = vipDC.findVipByPlayerId().vipLv
        val skins = skinDC.findSkinsByPlayerId()
        var skinId = 1 // 默认皮肤为：1
        for (skin in skins) {
            if (skin.isUse == 1) {
                skinId = skin.skinType
                break
            }
        }

        // 是否是一键操作：tarplayerId等于0为一键操作，不为0不是一键操作
        if (tarPlayerId.toInt() != 0) {

            if (type != APPLY_AGREE && type != APPLY_DISAGREE) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder.build()
            }

            // 如果是同意操作
            if (type == APPLY_AGREE) {
                var friendSize = 0
                friendSize = friends.size

                // 看是否是好友
                val isMyFriend = friendDC.findMyFriendById(tarPlayerId)
                if (isMyFriend != null) {
                    rtBuilder.rt = ResultCode.IS_FRIEND.code
                    return rtBuilder.build()
                }
                // 好友上限
                if (friendSize + 1 > friendLimit) {
                    rtBuilder.rt = ResultCode.NO_MORE_FRIEND.code
                    return rtBuilder.build()
                }
            }

            // 是否在申请列表
            val apply = friendApplyDC.findMyFriendApplyById(tarPlayerId)
            if (apply == null) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder.build()
            }

            askHome(
                session, player, vipLv, skinId, type, tarPlayerId, apply.applyTime,
                homeMyTargetDC, friendDC, friendApplyDC, blackPlayerDC
            )

            return rtBuilder.build()

        } else {
            // 查询申请列表
            val applyInfoList = friendApplyDC.findPlayerFriendApply()

            // 好友上限
            if (type == APPLY_AGREE) {
                if (friends.size + applyInfoList.size > friendLimit) {
                    rtBuilder.rt = ResultCode.NO_MORE_FRIEND.code
                    return rtBuilder.build()
                }
            }

            // 同意好友的通知
            for (apply in applyInfoList) {
                if (apply.applyState == FRIEND_APPLY_WAIT_STATE) {
                    askHome(
                        session, player, vipLv, skinId, type, apply.applyPlayerId, apply.applyTime,
                        homeMyTargetDC, friendDC, friendApplyDC, blackPlayerDC
                    )
                }
            }

            return rtBuilder.build()
        }
    }

    private fun askHome(
        session: PlayerActor, player: HomePlayer, vipLv: Int, skinId: Int, type: Int, tarPlayerId: Long, applyTime: Long,
        homeMyTargetDC: HomeMyTargetDC, friendDC: FriendDC, friendApplyDC: FriendApplyDC, blackPlayerDC: BlackPlayerDC
    ) {

        // 通知好友接受请求
        val askMsg = FriendAcceptAskReq.newBuilder()
        askMsg.worldId = player.worldId
        askMsg.myPlayerId = player.playerId
        askMsg.photoProtoId = player.photoProtoId
        askMsg.name = player.name
        askMsg.areaNo = player.areaNo
        askMsg.vipLv = vipLv
        askMsg.allianceShortName = player.allianceShortName
        askMsg.castleLv = player.castleLv
        askMsg.skinType = skinId
        askMsg.type = type
        askMsg.allianceNickname = player.allianceNickName

        val askMsgBuilder = Home2HomeAskReq.newBuilder()
        askMsgBuilder.playerId = tarPlayerId
        askMsgBuilder.setFriendAcceptAskReq(askMsg)

        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            askMsgBuilder.build(),
            Home2HomeAskResp::class.java
        ).whenCompleteKt { askRes, err ->
            if (err != null || askRes == null) {
                normalLog.lzWarn { "加好友失败" }
                return@whenCompleteKt
            }

            val rt = HandleFriendApplyRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            // 对方添加成功
            if (askRes.friendAcceptAskRt.rt == ResultCode.SUCCESS.code) {

                val tarPlayerInfo = askRes.friendAcceptAskRt.targetPlayer
                // 加入好友列表
                // 查询要添加的好友信息
                val friendApply = friendApplyDC.findMyFriendApplyById(tarPlayerId)

                if (friendApply == null) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    session.sendMsg(MsgType.HandleFriendApply_710, rt.build())
                    return@whenCompleteKt
                }

                // 推送给客户端
                var typeState = FRIEND_APPLY_OK_STATE
                if (type == APPLY_DISAGREE) {
                    typeState = FRIEND_APPLY_REMOVE_STATE
                } else {
                    val friend = friendDC.createFriend(
                        tarPlayerInfo.myPlayerId,
                        tarPlayerInfo.worldId,
                        tarPlayerInfo.name,
                        tarPlayerInfo.photoProtoId,
                        tarPlayerInfo.power,
                        tarPlayerInfo.castleLv,
                        tarPlayerInfo.skinType,
                        tarPlayerInfo.vipLv,
                        tarPlayerInfo.areaNo,
                        tarPlayerInfo.allianceShortName,
                        FRIEND_FALSE,
                        tarPlayerInfo.allianceNickName
                    )

                    val nowTime = getNowTime()
                    player.chatPlayerList.add(MyChat(tarPlayerId, nowTime))

                    homeMyTargetDC.targetInfo.haveFriendNum++
                    fireEvent(session, GetFriendEvent())

                    // 修改添加好友是否真正成功的状态
                    friend.isRealFriend = FRIEND_TRUE

                    // 是否在黑名单
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

                    // 通知好友变化：添加成功
                    val notice = createFriendApplySuccessNotifier(
                        FRIEND_ADD,
                        tarPlayerInfo.myPlayerId,
                        tarPlayerInfo.name,
                        tarPlayerInfo.areaNo,
                        tarPlayerInfo.vipLv,
                        tarPlayerInfo.allianceShortName,
                        tarPlayerInfo.photoProtoId,
                        tarPlayerInfo.castleLv,
                        tarPlayerInfo.skinType,
                        GROUP_ID_INIT,
                        tarPlayerInfo.allianceNickName
                    )
                    notice.notice(session)
                }

                friendApply.applyTime = getNowTime()
                friendApply.applyState = typeState

                // 申请信息变化
                val applyNotice = createFriendApplyChangeNotifier(
                    tarPlayerInfo.myPlayerId,
                    tarPlayerInfo.name,
                    tarPlayerInfo.areaNo,
                    tarPlayerInfo.vipLv,
                    tarPlayerInfo.allianceShortName,
                    tarPlayerInfo.photoProtoId,
                    typeState,
                    tarPlayerInfo.castleLv,
                    tarPlayerInfo.skinType,
                    applyTime,
                    tarPlayerInfo.allianceNickName
                )
                applyNotice.notice(session)
            }
        }
    }
}


