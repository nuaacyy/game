package com.point18.slg2d.home.module.changePlayerName

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.ACTION_NAME_CHANGE
import com.point18.slg2d.common.constg.UPDATE_INFO_BY_HOME_PLAYER_NAME
import com.point18.slg2d.common.constg.UpdateName
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
import pb4client.ChangePlayerName
import pb4client.ChangePlayerNameRt
import pb4server.FriendRefreshNoticeTell
import pb4server.Home2WorldAskResp
import pb4server.UpdateInfoByHomeAskReq
import java.util.Arrays.asList

//改名
class ChangePlayerNameDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus4<HomePlayerDC, VipDC, SkinDC, FriendDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java, FriendDC::class.java,
    asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC ->
            val reqMsg = msg as ChangePlayerName
            val changePlayerNameRt = changePlayerName(
                session, reqMsg.name, homePlayerDC,
                vipDC, skinDC, friendDC
            )
            if (changePlayerNameRt != null) {
                session.sendMsg(MsgType.ChangePlayerName_27, changePlayerNameRt)
            }
        }
    }

    private fun changePlayerName(
        session: PlayerActor, playerName: String, homePlayerDC: HomePlayerDC,
        vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC
    ): ChangePlayerNameRt? {
        val rtBuilder = ChangePlayerNameRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.name = playerName

        //查找自己
        val player = homePlayerDC.player

        //检测是否以系统前缀King开头
        if (playerName.startsWith(pcs.basicProtoCache.namePrefix)) {
            rtBuilder.rt = ResultCode.KEYWORDS.code
            return rtBuilder.build()
        }
        //检测是否合法
        val rst = pcs.wordCache.check(
            playerName,
            pcs.basicProtoCache.playerNameLength,
            com.point18.slg2d.common.pc.WORD_CHECK_LETTER_NUMBER_NOCHINESE
        )
        when (rst.wordCheckResult) {
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rtBuilder.rt = ResultCode.NAME_NIL.code
                return rtBuilder.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rtBuilder.rt = ResultCode.NAME_LONG.code
                return rtBuilder.build()
            }
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rtBuilder.rt = ResultCode.KEYWORDS.code
                return rtBuilder.build()
            }
        }
        // todo 检测重名
//        val searchPlayer = areaData.findPlayerByName(session.areaCache, playerName)
//        if (searchPlayer != null) {
//            rtBuilder.rt = ResultCode.USE_NAME.code
//            return rtBuilder.build()
//        }

        // 判断资源
        var costs = pcs.basicProtoCache.changeNameCostForRes
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            val (ok, needRes) = props2GoldCost(costs[0])
            if (ok != ResultCode.SUCCESS) {
                rtBuilder.rt = (ResultCode.LESS_RESOUCE.code)
                return rtBuilder.build()
            }

            //校验需要的资源
            if (!resHelper.checkRes(session, needRes)) {
                rtBuilder.rt = (ResultCode.LESS_RESOUCE.code)
                return rtBuilder.build()
            }

            costs = needRes
        }
        //预先扣除资源
        val costRst =
            resHelper.costResWithoutNotice(session, ACTION_NAME_CHANGE, player, costs)

        // 把变化后的城池等级同步到世界去
        val askMsg = UpdateInfoByHomeAskReq.newBuilder()
        val updateInfoByHomeVo = pb4server.UpdateInfoByHomeVo.newBuilder()
        updateInfoByHomeVo.updateType = UPDATE_INFO_BY_HOME_PLAYER_NAME
        updateInfoByHomeVo.updateValue = toJson(playerName)
        askMsg.addUpdates(updateInfoByHomeVo)
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setUpdateInfoByHomeAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->
            try {
                when {
                    askErr != null -> {
                        resHelper.addResWithoutNotice(
                            session,
                            ACTION_NAME_CHANGE,
                            player,
                            costs
                        )
                        val changePlayerNameRt = ChangePlayerNameRt.newBuilder()
                        changePlayerNameRt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.ChangePlayerName_27, changePlayerNameRt.build())
                    }
                    askResp == null -> {
                        resHelper.addResWithoutNotice(
                            session,
                            ACTION_NAME_CHANGE,
                            player,
                            costs
                        )
                        val changePlayerNameRt = ChangePlayerNameRt.newBuilder()
                        changePlayerNameRt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.ChangePlayerName_27, changePlayerNameRt.build())
                    }
                    else -> {
                        val changePlayerNameRt = ChangePlayerNameRt.newBuilder()
                        changePlayerNameRt.rt = ResultCode.SUCCESS.code
                        changePlayerNameRt.name = rst.newString
                        if (askResp.updateInfoByHomeAskRt.rt != ResultCode.SUCCESS.code) {
                            resHelper.addResWithoutNotice(
                                session,
                                ACTION_NAME_CHANGE,
                                player,
                                costs
                            )
                            changePlayerNameRt.rt = askResp.updateInfoByHomeAskRt.rt
                        } else {
                            costRst.noticeCostRes(session, player)

                            player.name = playerName

                            if (player.allianceId != 0L) {
                                // 有联盟,改了名字去联盟服修改
                                val updateInfoMap = hashMapOf<Int, String>()
                                updateInfoMap[UpdateName] = playerName
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
                            tellHomeMsg.name = playerName
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

                        session.sendMsg(MsgType.ChangePlayerName_27, changePlayerNameRt.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("UpdateInfoByHomeAskReq Error!", e)
                resHelper.addResWithoutNotice(
                    session,
                    ACTION_NAME_CHANGE,
                    player,
                    costs
                )
                val changePlayerNameRt = ChangePlayerNameRt.newBuilder()
                changePlayerNameRt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.ChangePlayerName_27, changePlayerNameRt.build())
            }
        }

        return null
    }
}



