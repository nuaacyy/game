package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.APPLY_AGREE
import com.point18.slg2d.common.constg.FRIEND_ADD
import com.point18.slg2d.common.constg.FRIEND_TRUE
import com.point18.slg2d.common.constg.GROUP_ID_INIT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.module.askDeal.H2HAsk
import com.point18.slg2d.home.module.event.GetFriendEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4server.FriendAcceptAskRt
import pb4server.FriendInfoVo
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class FriendAcceptDeal : H2HAsk, HomeHelperPlus6<HomePlayerDC, FriendDC, VipDC, SkinDC, BlackPlayerDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, FriendDC::class.java, VipDC::class.java, SkinDC::class.java,
    BlackPlayerDC::class.java, HomeMyTargetDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, friendDC, vipDC, skinDC, blackPlayerDC, homeMyTargetDC ->
            val askMsg = req.friendAcceptAskReq
            val rt = FriendAcceptAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val player = homePlayerDC.player

            val myFriends = friendDC.findFriendById()

            // 好友数量限制
            if (myFriends.size >= pcs.basicProtoCache.friendsNumLimit) {
                rt.rt = ResultCode.NO_MORE_FRIEND.code
            }

            val vip = vipDC.findVipByPlayerId()
            val skins = skinDC.findSkinsByPlayerId()
            val isOnline = session.isOnline()
            var skinId = 1
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinId = skin.skinType
                    break
                }
            }

            val blackPlayer = blackPlayerDC.findMyBlackById(askMsg.myPlayerId)
            if (blackPlayer != null) {
                blackPlayerDC.delBlackPlayer(blackPlayer)
            }

            val targetPlayerInfo = FriendInfoVo.newBuilder()
            targetPlayerInfo.myPlayerId = player.playerId
            targetPlayerInfo.worldId = player.worldId
            targetPlayerInfo.name = player.name
            targetPlayerInfo.photoProtoId = player.photoProtoId
            targetPlayerInfo.power = player.power
            targetPlayerInfo.castleLv = player.castleLv
            targetPlayerInfo.skinType = skinId
            targetPlayerInfo.vipLv = vip.vipLv
            targetPlayerInfo.areaNo = player.areaNo
            targetPlayerInfo.allianceShortName = player.allianceShortName
            targetPlayerInfo.allianceNickName = player.allianceNickName
            rt.targetPlayer = targetPlayerInfo.build()

            if (askMsg.type == APPLY_AGREE) {
                // 添加好友信息
                friendDC.createFriend(
                    askMsg.myPlayerId,
                    askMsg.worldId,
                    askMsg.name,
                    askMsg.photoProtoId,
                    0,
                    askMsg.castleLv,
                    askMsg.skinType,
                    askMsg.vipLv,
                    askMsg.areaNo,
                    askMsg.allianceShortName,
                    FRIEND_TRUE,
                    askMsg.allianceNickname
                )

                val nowTime = getNowTime()
                player.chatPlayerList.add(MyChat(askMsg.myPlayerId, nowTime))

                homeMyTargetDC.targetInfo.haveFriendNum++
                fireEvent(session, GetFriendEvent())

                // 推送好友信息
                if (isOnline) {
                    val notice = createFriendApplySuccessNotifier(
                        FRIEND_ADD,
                        askMsg.myPlayerId,
                        askMsg.name,
                        askMsg.areaNo,
                        askMsg.vipLv,
                        askMsg.allianceShortName,
                        askMsg.photoProtoId,
                        askMsg.castleLv,
                        askMsg.skinType,
                        GROUP_ID_INIT,
                        askMsg.allianceNickname
                    )
                    notice.notice(session)
                }
            }
            resp.setFriendAcceptAskRt(rt)
        }
    }
}