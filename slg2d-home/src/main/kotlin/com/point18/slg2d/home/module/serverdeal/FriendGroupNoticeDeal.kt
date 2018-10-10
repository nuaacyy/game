package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.FRIEND_CHANGE
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.askDeal.H2HAsk
import com.point18.slg2d.home.msgnotice.createFriendApplySuccessNotifier
import pb4server.FriendGroupNoticeAskRt
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class FriendGroupNoticeDeal : H2HAsk, HomeHelperPlus3<HomePlayerDC, VipDC, SkinDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, vipDC, skinDC ->
            val askMsg = req.friendGroupNoticeAskReq
            val groupId = askMsg.groupId
            val isOnline = session.isOnline()
            val rt = FriendGroupNoticeAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            // 推送好友信息
            if (isOnline) {
                val player = homePlayerDC.player
                val vipLv = vipDC.findVipByPlayerId().vipLv
                val skins = skinDC.findSkinsByPlayerId()
                var skinId = 1
                for (skin in skins) {
                    if (skin.isUse == 1) {
                        skinId = skin.skinType
                        break
                    }
                }

                val notice = createFriendApplySuccessNotifier(
                    FRIEND_CHANGE,
                    player.playerId,
                    player.name,
                    player.areaNo,
                    vipLv,
                    player.allianceShortName,
                    player.photoProtoId,
                    player.castleLv,
                    skinId,
                    groupId,
                    player.allianceNickName
                )
                notice.notice(session)
            }
            resp.setFriendGroupNoticeAskRt(rt)
        }
    }
}