package com.point18.slg2d.home.module.photo

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_ICON
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.updateAllianceMemberInfo
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ChangeIconRt
import pb4server.FriendRefreshNoticeTell
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq

// 领取更换头像
class ChangeIconDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus5<
            HomePlayerDC,
            IconDC,
            VipDC,
            SkinDC,
            FriendDC
            >(
        HomePlayerDC::class.java,
        IconDC::class.java,
        VipDC::class.java,
        SkinDC::class.java,
        FriendDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val iconId = (msg as pb4client.ChangeIcon).iconId
        prepare(session) { homePlayerDC, iconDC, vipDC, skinDC, friendDC ->
            val rt = dealChangeIcon(
                session,
                iconId,
                homePlayerDC,
                iconDC,
                vipDC,
                skinDC,
                friendDC)
            session.sendMsg(MsgType.ChangeIcon_1511, rt)
        }
    }

    fun dealChangeIcon(
        session: PlayerActor,
        iconId: Int,
        playerDc: HomePlayerDC,
        iconDC: IconDC,
        vipDC: VipDC,
        skinDC: SkinDC,
        friendDC: FriendDC
    ): (ChangeIconRt) {
        val dealRt = ChangeIconRt.newBuilder()
        dealRt.rt = com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code
        dealRt.iconId = iconId

        val player = playerDc.player

        // 直接验证是否是模板默认头像，是的话直接给
        val iconDefault = pcs.lordHeadIconProtoCache.lordHeadIconMap[iconId]
        if (iconDefault == null) {
            dealRt.rt = com.point18.slg2d.common.resultcode.ResultCode.NO_PROTO.code
            return dealRt.build()
        }

        if (iconDefault.type != 0) {
            val icon = iconDC.iconsMap[iconId]
            if (icon == null) {
                dealRt.rt = com.point18.slg2d.common.resultcode.ResultCode.PHOTO_NO_EXISTS.code
                return dealRt.build()
            }
        }

        // 各种验证通过了，允许更换头像
        player.photoProtoId = iconId

        tellPlayer(session, playerDc, vipDC, skinDC, friendDC)

        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_ICON
        updateInfoByHomeVo.updateValue = toJson(iconId)
        askMsg.addUpdates(updateInfoByHomeVo)

        session.createACS<Home2WorldAskResp>()
            .ask(session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
                Home2WorldAskResp::class.java)
            .whenCompleteKt { rt, askErr ->
                try {
                    when {
                        askErr != null -> {
                            // TODO
                        }

                        rt == null -> {
                            // TODO
                        }

                        else -> {
                            // TODO
                        }
                    }

                } catch (e: Throwable) {
                    // TODO
                }
            }

        if (player.allianceId != 0L) {
            val updateInfoMap = hashMapOf<Int, String>()
            updateInfoMap[com.point18.slg2d.common.constg.UpdatePhotoProtoId] = iconId.toString()
            updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
        }

        dealRt.iconId = iconId
        return dealRt.build()
    }

    fun tellPlayer(session: PlayerActor, playerDc: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC) {
        // tell home 向好友推送
        val player = playerDc.player
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
