package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.allianceChannelOf
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import pb4client.AllianceQuitRt
import pb4server.*
import java.util.*

// 删除帮助信息
fun removeAllianceHelp(session: PlayerActor, allianceId: Long, helpId: Long) {

    val askMsg = RemoveAllianceHelpAskReq.newBuilder()
    askMsg.helpId = helpId

    session.createACS<Home2PublicAskResp>()
        .ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setRemoveAllianceHelpAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        )
        .whenCompleteKt { askResp, askErr ->
            try {
                when {
                    askErr != null -> {
                    }
                    askResp == null -> {

                    }
                    else -> {
                        val rt = askResp.removeAllianceHelpAskRt
                        if (rt.rt != ResultCode.SUCCESS.code) {

                        } else {

                        }
                    }
                }

            } catch (e: Exception) {
            }

        }
}

// 更新联盟成员属性
fun updateAllianceMemberInfo(
    session: PlayerActor,
    allianceId: Long,
    playerId: Long,
    updateInfoMap: MutableMap<Int, String>
) {
    if (allianceId == 0L) {
        return
    }

    val askMsg = UpdateAllianceMemberInfoInHomeAskReq.newBuilder()
    for ((t, v) in updateInfoMap) {
        val updateAllianceMemberInfoVo = UpdateAllianceMemberInfoAskVo.newBuilder()
        updateAllianceMemberInfoVo.updateType = t
        updateAllianceMemberInfoVo.info = v
        askMsg.addUpdates(updateAllianceMemberInfoVo)
    }


    session.createACS<Home2PublicAskResp>()
        .ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setUpdateAllianceMemberInfoInHomeAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        )
        .whenCompleteKt { rt, askErr ->

            try {
                when {
                    askErr != null -> {
                    }
                    rt == null -> {

                    }
                    else -> {
                        val updateAllianceMemberInfoInHomeAskRt = rt.updateAllianceMemberInfoInHomeAskRt
                        if (updateAllianceMemberInfoInHomeAskRt.rt != ResultCode.SUCCESS.code) {

                        } else {

                        }
                    }
                }

            } catch (e: Exception) {
            }
        }
}

// 玩家离帮
fun allianceMemberQuit(
    session: PlayerActor,
    allianceId: Long,
    player: HomePlayer,
    isRook: Int,
    innerCityDC: InnerCityDC,
    vipDC: VipDC,
    skinDC: SkinDC,
    friendDC: FriendDC
) {
    val askMsg = AllianceMemberQuitAskReq.newBuilder()
    askMsg.isRook = isRook

    session.createACS<Home2PublicAskResp>()
        .ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setAllianceMemberQuitAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        )
        .whenCompleteKt { askResp, askErr ->

            try {
                when {
                    askErr != null -> {
                        val rtMsgBuilder = AllianceQuitRt.newBuilder()
                        rtMsgBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.AllianceQuit_811, rtMsgBuilder.build())
                    }
                    askResp == null -> {
                        val rtMsgBuilder = AllianceQuitRt.newBuilder()
                        rtMsgBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.AllianceQuit_811, rtMsgBuilder.build())
                    }
                    else -> {
                        val rtMsgBuilder = AllianceQuitRt.newBuilder()
                        val rt = askResp.allianceMemberQuitAskRt
                        rtMsgBuilder.rt = askResp.allianceMemberQuitAskRt.rt
                        if (rt.rt == ResultCode.SUCCESS.code) {
                            // 从联盟成员中剔除玩家，并更新玩家信息及联盟信息
                            sendAllianceInfoChange(session, 0, LinkedList(), "", "")

                            // todo 取消订阅联盟频道
                            session.unsubscribeChannel(allianceChannelOf(player.allianceId))

                            // 触发退出同盟事件
                            val oldPos = LinkedList<Int>()
                            for ((p, _) in player.alliancePosMap) {
                                oldPos += p
                            }

                            playerLeaveAlliance(player, innerCityDC)

                            // 通知世界服执行清玩家联盟数据
                            val tell = LeaveAllianceBySelfTell.newBuilder()
                            tell.addAllOldPos(oldPos)
                            session.tellWorld(session.fillHome2WorldTellMsgHeader {
                                it.setLeaveAllianceBySelfTell(tell)
                            })

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
                            tellHomeMsg.allianceShortName = ""
                            tellHomeMsg.state = 0
                            tellHomeMsg.castleLv = player.castleLv
                            tellHomeMsg.skinType = skinType
                            tellHomeMsg.shortName = ""
                            val myFriends = friendDC.findFriendById()

                            for (friend in myFriends) {
                                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                                    friend.tarPlayerId
                                ) { it.setFriendRefreshNoticeTell(tellHomeMsg) }
                                session.tellHome(home2homeTell)
                            }
                        }

                        session.sendMsg(MsgType.AllianceQuit_811, rtMsgBuilder.build())
                    }
                }

            } catch (e: Exception) {
                normalLog.error("AllianceMemberQuitAskReq Error!", e)
                val rtMsgBuilder = AllianceQuitRt.newBuilder()
                rtMsgBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.AllianceQuit_811, rtMsgBuilder.build())
            }
        }
}

// 玩家离开联盟成功之后 HOME服的数据修改处理
fun playerLeaveAlliance(player: HomePlayer, innerCityDC: InnerCityDC) {

    player.allianceId = 0
    player.joinAllianceState = 0
    player.allianceName = ""
    player.allianceShortName = ""
    player.allianceRnum = 0
    player.flagColor = 0
    player.flagEffect = 0
    player.flagStyle = 0
    player.clearWrapPosition()

    for ((rid, re) in player.researchInfoMap) {
        re.helpId = 0
        player.researchInfoMap[rid] = re
    }

    val innerCityBuildings =
        innerCityDC.findInnerCityListFromCastleId(player.focusCastleId)
    for (b in innerCityBuildings) {
        b.helpId = 0
    }
}

// 接收联盟礼物
fun receiveAllianceGift(
    session: PlayerActor,
    allianceId: Long,
    giftMap: HashMap<Int, Int>
) {

    val askMsg = ReceiveAllianceGiftAskReq.newBuilder()
    for ((gId, gNum) in giftMap) {
        val receiveAllianceGiftAskReqVo = ReceiveAllianceGiftAskReqVo.newBuilder()
        receiveAllianceGiftAskReqVo.giftId = gId
        receiveAllianceGiftAskReqVo.giftNum = gNum
        askMsg.addGiftMap(receiveAllianceGiftAskReqVo)
    }

    session.createACS<Home2PublicAskResp>()
        .ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setReceiveAllianceGiftAskReq(askMsg)
            },
            Home2PublicAskResp::class.java
        )
        .whenCompleteKt { askResp, askErr ->
            try {
                when {
                    askErr != null -> {
                        // 失败了 执行回滚内容
                    }
                    askResp == null -> {

                    }
                    else -> {

                    }
                }
            } catch (e: Exception) {
            }
        }
}


