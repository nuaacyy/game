package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.askDeal.W2HTell
import pb4server.FriendRefreshNoticeTell
import pb4server.World2HomeTell

// 通知好友信息变化
class FriendChangeToHomeDeal : W2HTell, HomeHelperPlus4<HomePlayerDC, VipDC, SkinDC, FriendDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java, FriendDC::class.java
) {

    override fun dealWorldTell(session: PlayerActor, playerId: Long, msg: World2HomeTell) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC ->
            // 告诉好友
            val player = homePlayerDC.player
            val vipLv = vipDC.getVipLv()
            var skinType = 1
            val skins = skinDC.findSkinsByPlayerId()
            for (skin in skins) {
                if (skin.isUse == 1) {
                    skinType = skin.skinType
                    break
                }
            }
            val tellHomeMsg = FriendRefreshNoticeTell.newBuilder()
            tellHomeMsg.myPlayerId = player.playerId
            tellHomeMsg.photoProtoId = player.photoProtoId
            tellHomeMsg.name = player.name
            tellHomeMsg.areaNo = player.areaNo
            tellHomeMsg.vipLv = vipLv
            tellHomeMsg.allianceShortName = player.allianceShortName
            tellHomeMsg.state = 0
            tellHomeMsg.castleLv = player.castleLv
            tellHomeMsg.skinType = skinType
            tellHomeMsg.shortName = player.allianceNickName
            val myFriends = friendDC.findFriendById()

            for (friend in myFriends) {
                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                    friend.tarPlayerId
                ) { it.setFriendRefreshNoticeTell(tellHomeMsg) }
                session.tellHome(home2homeTell)
            }
        }
    }
}
