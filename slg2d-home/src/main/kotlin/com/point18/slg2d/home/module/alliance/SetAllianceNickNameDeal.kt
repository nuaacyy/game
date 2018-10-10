package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.ACTION_USE_ALLIANCE_NICKNAME
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_PLAYER_NICK_NAME
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.updateAllianceMemberInfo
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceNickName
import pb4client.AllianceNickNameRt
import pb4server.FriendRefreshNoticeTell
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.Arrays.asList

// 设置联盟昵称
class SetAllianceNickNameDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, VipDC, SkinDC, FriendDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java, FriendDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC ->
            val nickName = (msg as AllianceNickName).nickName
            val rt = this.setAllianceNickNameDeal(
                session, session.playerId, nickName,
                homePlayerDC, vipDC, skinDC, friendDC
            )
            if (rt != null) {
                session.sendMsg(MsgType.AllianceNickName_897, rt)
            }
        }
    }

    private fun setAllianceNickNameDeal(
        session: PlayerActor, playerId: Long, nickName: String,
        homePlayerDC: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC
    ): AllianceNickNameRt? {
        val rt = AllianceNickNameRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.nickName = nickName

        // 判断内容
        val res1 = pcs.wordCache.check(nickName, pcs.basicProtoCache.allianceNickNameLength, WORD_CHECK_NAME)
        when (res1.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.WAIJIAO_INFO_ERROR.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.WAIJIAO_INFO_TOO_SHORT.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.WAIJIAO_INFO_TOO_LANG.code)
                return rt.build()
            }
        }

        // 我的帮
        val player = homePlayerDC.player
        // 判断资源
        var costs = pcs.basicProtoCache.nickNameCostForProps

        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            val (ok, needRes) = props2GoldCost(costs[0])
            if (ok != ResultCode.SUCCESS) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }

            //校验需要的资源
            if (!resHelper.checkRes(session, needRes)) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }

            costs = needRes
        }

        // 扣除资源
        val action = ACTION_USE_ALLIANCE_NICKNAME
        val costRst = resHelper.costResWithoutNotice(session, action, player, costs)

        // 把变化后的城池等级同步到世界去

        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_PLAYER_NICK_NAME
        updateInfoByHomeVo.updateValue = toJson(nickName)
        askMsg.addUpdates(updateInfoByHomeVo)
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askRt, askErr ->
            try {
                when {
                    askErr != null -> {
                        val rtBuilder = AllianceNickNameRt.newBuilder()
                        resHelper.addResWithoutNotice(session, action, player, costs)
                        rtBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.AllianceNickName_897, rtBuilder.build())
                    }
                    askRt == null -> {
                        val rtBuilder = AllianceNickNameRt.newBuilder()
                        resHelper.addResWithoutNotice(session, action, player, costs)
                        rtBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.AllianceNickName_897, rtBuilder.build())
                    }
                    else -> {
                        val rtBuilder = AllianceNickNameRt.newBuilder()
                        rtBuilder.rt = ResultCode.SUCCESS.code
                        rtBuilder.nickName = res1.newString
                        if (askRt.updateInfoByHomeAskRt.rt != ResultCode.SUCCESS.code) {
                            resHelper.addResWithoutNotice(session, action, player, costs)
                            rtBuilder.rt = askRt.updateInfoByHomeAskRt.rt
                        } else {
                            costRst.noticeCostRes(session, player)

                            player.allianceNickName = nickName

                            if (player.allianceId != 0L) {
                                // 有联盟,改了名字去联盟服修改
                                val updateInfoMap = hashMapOf<Int, String>()
                                updateInfoMap[com.point18.slg2d.common.constg.UpdateAllianceNickName] = nickName
                                updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
                            }

                            // 告诉好友
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
                            tellHomeMsg.shortName = nickName

                            val myFriends = friendDC.findFriendById()

                            for (friend in myFriends) {
                                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                                    friend.tarPlayerId
                                ) { it.setFriendRefreshNoticeTell(tellHomeMsg) }
                                session.tellHome(home2homeTell)
                            }
                        }

                        session.sendMsg(MsgType.AllianceNickName_897, rtBuilder.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("UpdateInfoByHomeAskReq Error!", e)
                val rtBuilder = AllianceNickNameRt.newBuilder()
                resHelper.addResWithoutNotice(session, action, player, costs)
                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.AllianceNickName_897, rtBuilder.build())
            }
        }


        return null
    }

}


