package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.FRIEND_DEL
import com.point18.slg2d.common.constg.GROUP_ID_INIT
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.askDeal.H2HAsk
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4server.FindPlayerInBlackAskRt
import pb4server.FriendInfoVo
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class FindPlayerDeal : H2HAsk, HomeHelperPlus4<HomePlayerDC, VipDC, SkinDC, FriendDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java, FriendDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, vipDC, skinDC, friendDC ->
            val askMsg = req.findPlayerInBlackAskReq
            val targetId = askMsg.myPlayerId
            val player = homePlayerDC.player
            player.blackPlayers.add(targetId)
            val vipLv = vipDC.findVipByPlayerId().vipLv
            val skins = skinDC.findSkinsByPlayerId()
            var skinId = 1
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinId = skin.skinType
                    break
                }
            }
            val rt = FindPlayerInBlackAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val isFriend = friendDC.findMyFriendById(targetId)

            if (isFriend != null) {
                friendDC.delFriendInfo(isFriend)
                // 推送好友变化消息
                val notice = createFriendApplySuccessNotifier(
                    FRIEND_DEL,
                    targetId,
                    player.name,
                    player.areaNo,
                    vipLv,
                    player.allianceShortName,
                    player.photoProtoId,
                    player.castleLv,
                    skinId,
                    GROUP_ID_INIT,
                    player.allianceNickName
                )
                notice.notice(session)
            }

            val targetPlayerInfo = FriendInfoVo.newBuilder()
            targetPlayerInfo.myPlayerId = player.playerId
            targetPlayerInfo.worldId = player.worldId
            targetPlayerInfo.name = player.name
            targetPlayerInfo.photoProtoId = player.photoProtoId
            targetPlayerInfo.power = player.power
            targetPlayerInfo.castleLv = player.castleLv
            targetPlayerInfo.skinType = skinId
            targetPlayerInfo.vipLv = vipLv
            targetPlayerInfo.areaNo = player.areaNo
            targetPlayerInfo.allianceShortName = player.allianceShortName
            targetPlayerInfo.allianceNickName = player.allianceNickName
            rt.targetPlayer = targetPlayerInfo.build()

            resp.setFindPlayerInBlackAskRt(rt)
        }
    }
}
