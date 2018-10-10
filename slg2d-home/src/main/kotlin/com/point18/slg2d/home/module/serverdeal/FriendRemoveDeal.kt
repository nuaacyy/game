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
import pb4server.FriendRemoveAskRt
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class FriendRemoveDeal : H2HAsk, HomeHelperPlus4<HomePlayerDC, FriendDC, VipDC, SkinDC>(
    HomePlayerDC::class.java, FriendDC::class.java, VipDC::class.java, SkinDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, friendDC, vipDC, skinDC ->
            val askMsg = req.friendRemoveAskReq
            val player = homePlayerDC.player
            val isOnline = session.isOnline()
            val rt = FriendRemoveAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val vipLv = vipDC.findVipByPlayerId().vipLv
            val skins = skinDC.findSkinsByPlayerId()
            var skinId = 1
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinId = skin.skinType
                    break
                }
            }

            val targetPlayerInfo = pb4server.FriendInfoVo.newBuilder()
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

            // 删除好友信息
            val friendInfo = friendDC.findMyFriendById(askMsg.myPlayerId)
            if (friendInfo != null) {
                friendDC.delFriendInfo(friendInfo)
            }

            if (isOnline) {
                // 推送好友信息
                val notice = createFriendApplySuccessNotifier(
                    FRIEND_DEL,
                    askMsg.myPlayerId,
                    askMsg.name,
                    askMsg.areaNo,
                    askMsg.vipLv,
                    askMsg.allianceShortName,
                    askMsg.photoProtoId,
                    askMsg.castleLv,
                    askMsg.skinType,
                    GROUP_ID_INIT,
                    askMsg.shortName
                )
                notice.notice(session)
            }
            resp.setFriendRemoveAskRt(rt)
        }
    }
}