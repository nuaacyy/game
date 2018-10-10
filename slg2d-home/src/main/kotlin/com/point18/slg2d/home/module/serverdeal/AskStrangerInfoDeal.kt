package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.askDeal.H2HAsk
import pb4server.AskStrangerInfoAskRt
import pb4server.FriendInfoVo
import pb4server.Home2HomeAskReq
import pb4server.Home2HomeAskResp

class AskStrangerInfoDeal : H2HAsk, HomeHelperPlus3<HomePlayerDC, VipDC, SkinDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, vipDC, skinDC ->
            val askMsg = req.askStrangerInfoAskReq
            val rt = AskStrangerInfoAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val player = homePlayerDC.player

            val vip = vipDC.findVipByPlayerId()
            val skins = skinDC.findSkinsByPlayerId()
            var skinType = 1
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinType = skin.skinType
                    break
                }
            }

            val targetPlayerInfo = FriendInfoVo.newBuilder()
            targetPlayerInfo.myPlayerId = player.playerId
            targetPlayerInfo.worldId = player.worldId
            targetPlayerInfo.name = player.name
            targetPlayerInfo.photoProtoId = player.photoProtoId
            targetPlayerInfo.power = player.power
            targetPlayerInfo.castleLv = player.castleLv
            targetPlayerInfo.skinType = skinType
            targetPlayerInfo.vipLv = vip.vipLv
            targetPlayerInfo.areaNo = player.areaNo
            targetPlayerInfo.allianceShortName = player.allianceShortName
            targetPlayerInfo.allianceNickName = player.allianceNickName

            rt.targetPlayer = targetPlayerInfo.build()
            resp.setAskStrangerInfoAskRt(rt)
        }
    }
}