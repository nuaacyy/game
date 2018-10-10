package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedFightInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.ChatEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createFriendChatMsgNotifier
import pb4client.*
import pb4server.*
import xyz.ariane.util.tellNoSender

class SendMessageDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus6<HomePlayerDC, VipDC, HomeMyTargetDC, FriendChatRecordDC, HomeSyncDC, BattleReportDC>(
    HomePlayerDC::class.java, VipDC::class.java, HomeMyTargetDC::class.java, FriendChatRecordDC::class.java,
    HomeSyncDC::class.java, BattleReportDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, homeMyTargetDC: HomeMyTargetDC,
                             friendChatRecordDC: FriendChatRecordDC, homeSyncDC: HomeSyncDC,
                             battleReportDC: BattleReportDC ->
            val msgReceive = msg as SendChatMsg
            val type = msgReceive.type
            val chatPlayerId = msgReceive.playerId
            val message = msgReceive.message
            val messageType = msgReceive.messageType
            val fightInfoId = msgReceive.easyFightInfoId
            val massId = msgReceive.massId
            val player = homePlayerDC.player
            val vip = vipDC.vipInfo
            val syncHomePlayer = homeSyncDC.syncData
            val roomId = msgReceive.roomId
            val x = msgReceive.x
            val y = msgReceive.y
            val rt = sendChatMsg(
                session, type, chatPlayerId, message, messageType, fightInfoId,
                massId, player, vip, roomId, x, y, syncHomePlayer,
                homeMyTargetDC, battleReportDC, friendChatRecordDC
            )
            if (rt != null) {
                session.sendMsg(MsgType.SendChat_301, rt)
            }
        }
    }

    private fun sendChatMsg(
        session: PlayerActor, type: Int, chatPlayerId: Long,
        message: String, messageType: Int, easyFightId: Long,
        massId: Long, homePlayer: HomePlayer, vip: Vip,
        roomId: Long, x: Int, y: Int, homeSync: HomeSync,
        homeMyTargetDC: HomeMyTargetDC, battleReportDC: BattleReportDC,
        friendChatRecordDC: FriendChatRecordDC
    ): SendChatMsgRt? {
        val rt = SendChatMsgRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 检测类型
        if (type != CHAT_TYPE_WORLD && type != CHAT_TYPE_ALLIANCE && type != CHAT_TYPE_PRIVATE && type != CHAT_TYPE_GROUP) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (messageType != NORMAL_MESSAGE_NOTICE && messageType != CATTON_DISPLAY &&
            messageType != FIGHT_INFO_SHARE && messageType != TRMPET && messageType != LOCATION_SHARE) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 私聊时聊天对象不能是自己
        if (chatPlayerId == session.playerId && type == CHAT_TYPE_PRIVATE) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        //验证长度和敏感字符
        var msgNewString = message

        if (messageType != CATTON_DISPLAY) {
            var checkLength = pcs.basicProtoCache.chatMessageLength
            if (messageType == LOCATION_SHARE) {
                checkLength = pcs.basicProtoCache.markTextLength
            }

            val checkMsg = pcs.wordCache.check(
                message,
                checkLength,
                com.point18.slg2d.common.pc.WORD_CHECK_MESSAGE
            )

            if (
                checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_SHORT ||
                checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_EXCEED
            ) {
                rt.rt = ResultCode.CHAT_MSG_LENGTH_OVER.code
                return rt.build()
            }
            msgNewString = checkMsg.newString
        }

        val now = getNowTime()
        // 判断发的频道，世界，联盟，群聊
        when (type) {
            // 世界频道  只有世界有喇叭 其他没有喇叭
            CHAT_TYPE_WORLD -> {
                val askMsg = WorldChatAskReq.newBuilder()
                askMsg.message = msgNewString
                askMsg.messageType = messageType
                askMsg.easyFightId = easyFightId
                askMsg.playerName = homePlayer.name
                askMsg.playerShortName = homePlayer.allianceNickName
                askMsg.massId = massId
                askMsg.massName = ""
                askMsg.iconProtoId = homePlayer.photoProtoId
                askMsg.areaNo = homePlayer.areaNo
                askMsg.pltAreaId = homePlayer.worldId
                askMsg.vipLv = vip.vipLv
                askMsg.allianceName = homePlayer.allianceName
                askMsg.allianceShortName = homePlayer.allianceShortName
                askMsg.allianceId = homePlayer.allianceId
                askMsg.x = x
                askMsg.y = y

                // 世界喇叭cd消耗 和快捷相关
                var cost = listOf<ResVo>()
                if (messageType == TRMPET) {
                    val worldCD = getNowMTime() - homePlayer.boardcastLast
                    if (worldCD < pcs.basicProtoCache.hornChatSpaceTime * 1000) {
                        rt.rt = ResultCode.WAIT_TALK.code
                        return rt.build()
                    }
                    cost = pcs.basicProtoCache.hornCost
                    var checkResPass = resHelper.checkRes(session, cost)
                    if (!checkResPass) {
                        val (ok, needRes) = props2GoldCost(cost[0])

                        if (ok != ResultCode.SUCCESS || needRes.size == 0) {
                            rt.rt = ok.code
                            return rt.build()
                        }

                        checkResPass = resHelper.checkRes(session, needRes)
                        if (!checkResPass) {
                            rt.rt = ResultCode.LESS_RESOUCE.code
                            return rt.build()
                        }
                    }
                }

                // 世界说话cd相关
                if (messageType != TRMPET) {
                    val worldCD = getNowMTime() - homePlayer.worldTalkLast
                    if (worldCD < pcs.basicProtoCache.worldChatSpaceTime * 1000) {
                        rt.rt = ResultCode.WAIT_TALK.code
                        return rt.build()
                    }
                }

                // 战报分享需要拿战报
                var fightInfo = ""
                if (messageType == FIGHT_INFO_SHARE) {
                    val battleReport = battleReportDC.findBattleReportById(easyFightId)
                    if (battleReport == null) {
                        rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                        return rt.build()
                    }

                    val fightInfoTmp = toSimpleFightInfo(
                        session.playerId,
                        battleReport.id,
                        battleReport.reportType,
                        battleReport.reportContent,
                        homePlayer.name,
                        homePlayer.allianceShortName,
                        homePlayer.photoProtoId,
                        session.worldId
                    )

                    if (fightInfoTmp == null) {
                        rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                        return rt.build()
                    }

                    fightInfo = fightInfoTmp
                    askMsg.message = fightInfo
                }

                session.createACS<Home2WorldAskResp>().ask(
                    session.worldShardProxy,
                    session.fillHome2WorldAskMsgHeader { it.setWorldChatAskReq(askMsg) },
                    Home2WorldAskResp::class.java
                ).whenCompleteKt { wrt, askErr ->
                    try {
                        when {
                            askErr != null -> {
                                rt.rt = ResultCode.ASK_ERROR1.code
                                session.sendMsg(MsgType.SendChat_301, rt.build())
                            }
                            wrt == null -> {
                                rt.rt = ResultCode.ASK_ERROR2.code
                                session.sendMsg(MsgType.SendChat_301, rt.build())

                            }
                            else -> {
                                rt.rt = ResultCode.SUCCESS.code
                                if (wrt.worldChatAskRt.rt != ResultCode.SUCCESS.code) {
                                    rt.rt = wrt.worldChatAskRt.rt
                                } else {
                                    rt.rt = wrt.worldChatAskRt.rt
                                    if (messageType == TRMPET) {
                                        resHelper.costRes(session, ACTION_CHAT, homePlayer, cost)
                                        homePlayer.boardcastLast = getNowTime()
                                    } else {
                                        homePlayer.worldTalkLast = getNowTime()
                                    }

                                    // 组装聊天消息
                                    val countryPositionId = homeSync.officeMap[session.worldId] ?: 0

                                    val newChatMessageBuilder = NewChatMessage.newBuilder()
                                    val chatInfoBuilder = ChatInfo.newBuilder()
                                    chatInfoBuilder.id = wrt.worldChatAskRt.chatId
                                    chatInfoBuilder.type = CHAT_TYPE_WORLD
                                    chatInfoBuilder.isSystem = 1
                                    chatInfoBuilder.country = 24
                                    chatInfoBuilder.allianceName = homePlayer.allianceName
                                    chatInfoBuilder.allianceShortName = homePlayer.allianceShortName
                                    chatInfoBuilder.alliancePositions = homePlayer.getMaxAlliancePos().toString()
                                    chatInfoBuilder.player = homePlayer.name
                                    chatInfoBuilder.playerId = homePlayer.playerId
                                    chatInfoBuilder.playerShortName = homePlayer.allianceNickName
                                    chatInfoBuilder.playerIcon = homePlayer.photoProtoId
                                    chatInfoBuilder.sendTime = (now / 1000).toInt()
                                    chatInfoBuilder.messageType = messageType
                                    chatInfoBuilder.redBagState = 0
                                    chatInfoBuilder.office = countryPositionId
                                    chatInfoBuilder.vipLv = vip.vipLv
                                    chatInfoBuilder.areaNo = homePlayer.areaNo
                                    val noticeBuilder = Notice.newBuilder()
                                    noticeBuilder.readType = TEXT_READ_INFO
                                    noticeBuilder.noticeLanId = msgNewString

                                    if (messageType == FIGHT_INFO_SHARE) {
                                        noticeBuilder.readType = TEXT_READ_INFO
                                        noticeBuilder.noticeLanId = ""
                                        val simplifiedFightInfo = toObj<SimplifiedFightInfo>(fightInfo)
                                        val easyFightInfo = SimpleFightReport.newBuilder()
                                        easyFightInfo.reportType = simplifiedFightInfo.reportType
                                        easyFightInfo.mainPlayer = simplifiedFightInfo.mainPlayer
                                        easyFightInfo.mainPlayerAlliance = simplifiedFightInfo.mainPlayerAlliance
                                        easyFightInfo.atkOrDef = simplifiedFightInfo.atkOrDef
                                        easyFightInfo.targetName = simplifiedFightInfo.targetName
                                        easyFightInfo.allianceOrLv = simplifiedFightInfo.allianceOrLv
                                        easyFightInfo.reportId = simplifiedFightInfo.reportId
                                        easyFightInfo.mainIconId = simplifiedFightInfo.mainIconId
                                        easyFightInfo.iconId = simplifiedFightInfo.iconId
                                        easyFightInfo.monsterId = simplifiedFightInfo.monster
                                        easyFightInfo.world = simplifiedFightInfo.worldId
                                        chatInfoBuilder.easyFightInfo = easyFightInfo.build()
                                    } else if (messageType == LOCATION_SHARE) {
                                        val location = toObj<LocationShareInfo>(wrt.worldChatAskRt.locationInfo)

                                        chatInfoBuilder.x = location.x
                                        chatInfoBuilder.y = location.y
                                        location.areaNo = location.areaNo
                                        noticeBuilder.readType = TEXT_READ_INFO
                                        noticeBuilder.noticeLanId = location.locationName
                                    }

                                    chatInfoBuilder.message = noticeBuilder.build()
                                    newChatMessageBuilder.chatInfo = chatInfoBuilder.build()

                                    // home将消息广播到世界频道
                                    println("home将消息广播到世界频道")
                                    val multicastMSG = MulticastEnvelopeMsg.newBuilder()
                                    multicastMSG.msgType = MsgType.NewChatMessage_3080.msgType
                                    multicastMSG.newChatMsg = newChatMessageBuilder.build()
                                    multicastMSG.channel = worldChannelOf(session.worldId)
                                    hpm.multicastServiceRouter.tellNoSender(
                                        multicastMSG.build()
                                    )

                                    homeMyTargetDC.targetInfo.worldTalkNum++
                                    fireEvent(session, ChatEvent(CHAT_TYPE_WORLD))
                                    session.sendMsg(MsgType.SendChat_301, rt.build())
                                }
                            }
                        }

                    } catch (e: Exception) {
                        normalLog.error("WorldChatAskReq Error!", e)
                        rt.rt = ResultCode.ASK_ERROR3.code
                        session.sendMsg(MsgType.SendChat_301, rt.build())
                    }
                }
            }

            // 私聊
            CHAT_TYPE_PRIVATE -> {
                // 1  两个玩家的私聊消息存库
                if (homePlayer.blackPlayers.contains(chatPlayerId)) {
                    rt.rt = ResultCode.IN_HIS_BLACK_LIST.code
                    return rt.build()
                }
                when (messageType) {
                    // 战报分享
                    FIGHT_INFO_SHARE -> {
                        val battleReport = battleReportDC.findBattleReportById(easyFightId)
                        if (battleReport == null) {
                            rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                            return rt.build()
                        }

                        val fightInfo = toSimpleFightInfo(
                            session.playerId,
                            battleReport.id,
                            battleReport.reportType,
                            battleReport.reportContent,
                            homePlayer.name,
                            homePlayer.allianceShortName,
                            homePlayer.photoProtoId,
                            session.worldId
                        )

                        if (fightInfo == null) {
                            rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                            return rt.build()
                        }

                        sendPrivateChatMsg(session, chatPlayerId, homePlayer, fightInfo,
                            messageType, easyFightId, vip.vipLv, rt, friendChatRecordDC, homeSync)
                    }

                    NORMAL_MESSAGE_NOTICE, CATTON_DISPLAY -> {
                        sendPrivateChatMsg(session, chatPlayerId, homePlayer, msgNewString,
                            messageType, easyFightId, vip.vipLv, rt, friendChatRecordDC, homeSync)
                    }

                    LOCATION_SHARE -> {
                        val locationString = toJson(LocationShareInfo(homePlayer.areaNo, x, y, msgNewString))
                        sendPrivateChatMsg(session, chatPlayerId, homePlayer, locationString,
                            messageType, easyFightId, vip.vipLv, rt, friendChatRecordDC, homeSync)
                    }

                }
            }

            // 联盟频道
            CHAT_TYPE_ALLIANCE -> {
                // 验证是否加入了联盟
                if (homePlayer.allianceId <= 0L) {
                    rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
                    return rt.build()
                }

                // cd验证
                val chatCd = getNowMTime() - homePlayer.allianceTalkLast
                if (chatCd < pcs.basicProtoCache.groupChatSpaceTime * 1000) {
                    rt.rt = ResultCode.WAIT_TALK.code
                    return rt.build()
                }

                when (messageType) {
                    FIGHT_INFO_SHARE -> {

                        val battleReport = battleReportDC.findBattleReportById(easyFightId)
                        if (battleReport == null) {
                            rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                            return rt.build()
                        }

                        val fightInfo = toSimpleFightInfo(
                            session.playerId,
                            battleReport.id,
                            battleReport.reportType,
                            battleReport.reportContent,
                            homePlayer.name,
                            homePlayer.allianceShortName,
                            homePlayer.photoProtoId,
                            session.worldId
                        )

                        if (fightInfo == null) {
                            rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                            return rt.build()
                        }

                        sendAllianceMsg(session, homePlayer, messageType, fightInfo, vip.vipLv,
                            rt, homeMyTargetDC, homeSync)

                    }

                    NORMAL_MESSAGE_NOTICE, CATTON_DISPLAY -> {
                        sendAllianceMsg(session, homePlayer, messageType, msgNewString, vip.vipLv, rt, homeMyTargetDC, homeSync)
                    }

                    LOCATION_SHARE -> {
                        val locationString = toJson(LocationShareInfo(homePlayer.areaNo, x, y, msgNewString))
                        sendAllianceMsg(session, homePlayer, messageType, locationString, vip.vipLv, rt, homeMyTargetDC, homeSync)
                    }

                    else -> {
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        return rt.build()
                    }
                }

            }

            // 群聊
            CHAT_TYPE_GROUP -> {
                // 验证是否加入了聊天室并且更改阅读时间
                val homeRoomInfo = homePlayer.chatRoomList.firstOrNull { it.chatRoomId == roomId }
                if (homeRoomInfo == null) {
                    rt.rt = ResultCode.NO_CHAT_ROOM.code
                    return rt.build()
                }
                homeRoomInfo.lastReadTime = now

                when (messageType) {
                    FIGHT_INFO_SHARE -> {

                        val battleReport = battleReportDC.findBattleReportById(easyFightId)
                        if (battleReport == null) {
                            rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                            return rt.build()
                        }

                        val fightInfo = toSimpleFightInfo(
                            session.playerId,
                            battleReport.id,
                            battleReport.reportType,
                            battleReport.reportContent,
                            homePlayer.name,
                            homePlayer.allianceShortName,
                            homePlayer.photoProtoId,
                            session.worldId
                        )

                        if (fightInfo == null) {
                            rt.rt = ResultCode.FIGHT_INFO_NOT_FOUND.code
                            return rt.build()
                        }

                        sendChatRoomMsg(session, roomId, homePlayer, fightInfo,
                            messageType, easyFightId, vip.vipLv, rt, homeSync)

                    }

                    NORMAL_MESSAGE_NOTICE, CATTON_DISPLAY -> {
                        sendChatRoomMsg(session, roomId, homePlayer, msgNewString, messageType, easyFightId, vip.vipLv, rt, homeSync)
                    }

                    LOCATION_SHARE -> {
                        val locationString = toJson(LocationShareInfo(homePlayer.areaNo, x, y, msgNewString))
                        sendChatRoomMsg(session, roomId, homePlayer, locationString, messageType, easyFightId, vip.vipLv, rt, homeSync)
                    }

                    else -> {
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        return rt.build()
                    }
                }
            }

            else -> {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
        }

        return null
    }

}

fun sendAllianceMsg(
    session: PlayerActor, homePlayer: HomePlayer, messageType: Int, msgNewString: String, vipLv: Int,
    rt: SendChatMsgRt.Builder, homeMyTargetDC: HomeMyTargetDC, homeSync: HomeSync
) {
    val countryPositionId = homeSync.officeMap[session.worldId] ?: 0

    val askMsg = SendAllianceChatAskReq.newBuilder()
    askMsg.playerShortName = homePlayer.allianceNickName
    askMsg.playerName = homePlayer.name
    askMsg.messageType = messageType
    askMsg.message = msgNewString
    askMsg.iconProtoId = homePlayer.photoProtoId
    askMsg.vipLv = vipLv
    askMsg.pltAreaId = homePlayer.worldId
    askMsg.areaNo = homePlayer.areaNo
    askMsg.allianceName = homePlayer.allianceName
    askMsg.allianceShortName = homePlayer.allianceShortName

    session.createACS<Home2PublicAskResp>().ask(
        session.publicShardProxy,
        session.fillHome2PublicAskMsgHeader(homePlayer.allianceId) { it.setSendAllianceChatAskReq(askMsg) },
        Home2PublicAskResp::class.java
    ).whenCompleteKt { prt, askErr ->
        if (askErr != null || prt == null) {
            ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
        } else if (prt.sendAllianceChatAskRt.rt != ResultCode.SUCCESS.code) {
            rt.rt = prt.sendAllianceChatAskRt.rt
            session.sendMsg(MsgType.SendChat_301, rt.build())
        } else {
            rt.rt = prt.sendAllianceChatAskRt.rt
            val now = getNowTime()
            // 组装聊天消息
            val newChatMessageBuilder = NewChatMessage.newBuilder()
            val chatInfoBuilder = ChatInfo.newBuilder()
            chatInfoBuilder.id = prt.sendAllianceChatAskRt.chatId
            chatInfoBuilder.type = CHAT_TYPE_ALLIANCE
            chatInfoBuilder.isSystem = 1
            chatInfoBuilder.country = 24
            chatInfoBuilder.allianceName = homePlayer.allianceName
            chatInfoBuilder.allianceShortName = homePlayer.allianceShortName
            chatInfoBuilder.alliancePositions = homePlayer.getMaxAlliancePos().toString()
            chatInfoBuilder.player = homePlayer.name
            chatInfoBuilder.playerId = homePlayer.playerId
            chatInfoBuilder.playerShortName = homePlayer.allianceNickName
            chatInfoBuilder.playerIcon = homePlayer.photoProtoId
            chatInfoBuilder.sendTime = (now / 1000).toInt()
            chatInfoBuilder.messageType = messageType
            chatInfoBuilder.office = countryPositionId
            chatInfoBuilder.vipLv = vipLv
            chatInfoBuilder.areaNo = homePlayer.areaNo
            val noticeBuilder = Notice.newBuilder()
            noticeBuilder.readType = TEXT_READ_INFO
            noticeBuilder.noticeLanId = msgNewString

            if (messageType == FIGHT_INFO_SHARE) {
                noticeBuilder.readType = TEXT_READ_INFO
                noticeBuilder.noticeLanId = ""
                val fightInfo = toObj<SimplifiedFightInfo>(msgNewString)
                val easyFightInfo = SimpleFightReport.newBuilder()
                easyFightInfo.reportType = fightInfo.reportType
                easyFightInfo.mainPlayer = fightInfo.mainPlayer
                easyFightInfo.mainPlayerAlliance = fightInfo.mainPlayerAlliance
                easyFightInfo.atkOrDef = fightInfo.atkOrDef
                easyFightInfo.targetName = fightInfo.targetName
                easyFightInfo.allianceOrLv = fightInfo.allianceOrLv
                easyFightInfo.reportId = fightInfo.reportId
                easyFightInfo.mainIconId = fightInfo.mainIconId
                easyFightInfo.iconId = fightInfo.iconId
                easyFightInfo.monsterId = fightInfo.monster
                easyFightInfo.world = fightInfo.worldId
                chatInfoBuilder.easyFightInfo = easyFightInfo.build()
            } else if (messageType == LOCATION_SHARE) {
                val location = toObj<LocationShareInfo>(msgNewString)
                chatInfoBuilder.x = location.x
                chatInfoBuilder.y = location.y
                noticeBuilder.noticeLanId = location.locationName
            }

            chatInfoBuilder.setMessage(noticeBuilder)
            newChatMessageBuilder.setChatInfo(chatInfoBuilder)

            // home将消息广播到联盟频道
            println("home将消息广播到联盟频道")
            val multicastMSG = MulticastEnvelopeMsg.newBuilder()
            multicastMSG.msgType = MsgType.NewChatMessage_3080.msgType
            multicastMSG.newChatMsg = newChatMessageBuilder.build()
            multicastMSG.channel = allianceChannelOf(homePlayer.allianceId)
            hpm.multicastServiceRouter.tellNoSender(
                multicastMSG.build()
            )

            homePlayer.allianceTalkLast = now
            homeMyTargetDC.targetInfo.allianceTalkNum++

            fireEvent(session, ChatEvent(CHAT_TYPE_ALLIANCE))
            session.sendMsg(MsgType.SendChat_301, rt.build())
        }
    }
}

fun sendChatRoomMsg(
    session: PlayerActor, roomId: Long, homePlayer: HomePlayer, message: String,
    messageType: Int, easyFightId: Long, vipLv: Int, rt: SendChatMsgRt.Builder, homeSync: HomeSync
) {
    val countryPositionId = homeSync.officeMap[session.worldId] ?: 0

    val askMsg = SendRoomMsgAskReq.newBuilder()
    askMsg.roomId = roomId
    askMsg.message = message
    askMsg.messageType = messageType
    askMsg.playerName = homePlayer.name
    askMsg.playerShortName = homePlayer.allianceNickName
    askMsg.easyFightId = easyFightId
    askMsg.massId = 0
    askMsg.massName = ""
    askMsg.iconProtoId = homePlayer.photoProtoId
    askMsg.areaNo = homePlayer.areaNo
    askMsg.vipLv = vipLv
    askMsg.allianceName = homePlayer.allianceName
    askMsg.allianceShortName = homePlayer.allianceShortName
    askMsg.alliancePos = homePlayer.getMaxAlliancePos()
    askMsg.wonderPos = countryPositionId

    session.createACS<Home2PublicAskResp>().ask(
        session.publicShardProxy,
        session.fillHome2PublicAskMsgHeader(roomId) { it.setSendRoomMsgAskReq(askMsg) },
        Home2PublicAskResp::class.java
    ).whenCompleteKt { prt, askErr ->
        if (askErr != null || prt == null) {
            rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
        } else if (prt.sendRoomMsgAskRt.rt != ResultCode.SUCCESS.code) {
            rt.rt = prt.sendRoomMsgAskRt.rt
            session.sendMsg(MsgType.SendChat_301, rt.build())
        } else {
            val now = getNowTime()
            rt.rt = prt.sendRoomMsgAskRt.rt
            // 组装聊天消息
            val newChatMessageBuilder = GroupChatInfo.newBuilder()
            val chatInfoBuilder = ChatInfo.newBuilder()
            chatInfoBuilder.id = prt.sendRoomMsgAskRt.chatId
            chatInfoBuilder.type = CHAT_TYPE_GROUP
            chatInfoBuilder.isSystem = 1
            chatInfoBuilder.country = 24
            chatInfoBuilder.allianceName = homePlayer.allianceName
            chatInfoBuilder.allianceShortName = homePlayer.allianceShortName
            chatInfoBuilder.alliancePositions = homePlayer.getMaxAlliancePos().toString()
            chatInfoBuilder.player = homePlayer.name
            chatInfoBuilder.playerId = homePlayer.playerId
            chatInfoBuilder.playerShortName = homePlayer.allianceNickName
            chatInfoBuilder.playerIcon = homePlayer.photoProtoId
            chatInfoBuilder.sendTime = (now / 1000).toInt()
            chatInfoBuilder.messageType = messageType
            chatInfoBuilder.redBagState = 0
            chatInfoBuilder.office = countryPositionId
            chatInfoBuilder.vipLv = vipLv
            chatInfoBuilder.areaNo = homePlayer.areaNo
            chatInfoBuilder.chatRoomId = roomId
            val noticeBuilder = Notice.newBuilder()
            noticeBuilder.readType = TEXT_READ_INFO
            noticeBuilder.noticeLanId = message

            if (messageType == FIGHT_INFO_SHARE) {
                noticeBuilder.noticeLanId = ""
                val fightInfo = toObj<SimplifiedFightInfo>(message)
                val easyFightInfo = SimpleFightReport.newBuilder()
                easyFightInfo.reportType = fightInfo.reportType
                easyFightInfo.mainPlayer = fightInfo.mainPlayer
                easyFightInfo.mainPlayerAlliance = fightInfo.mainPlayerAlliance
                easyFightInfo.atkOrDef = fightInfo.atkOrDef
                easyFightInfo.targetName = fightInfo.targetName
                easyFightInfo.allianceOrLv = fightInfo.allianceOrLv
                easyFightInfo.reportId = fightInfo.reportId
                easyFightInfo.mainIconId = fightInfo.mainIconId
                easyFightInfo.iconId = fightInfo.iconId
                easyFightInfo.monsterId = fightInfo.monster
                easyFightInfo.world = fightInfo.worldId
                chatInfoBuilder.easyFightInfo = easyFightInfo.build()
            } else if (messageType == LOCATION_SHARE) {
                val location = toObj<LocationShareInfo>(message)
                chatInfoBuilder.x = location.x
                chatInfoBuilder.y = location.y
                noticeBuilder.noticeLanId = location.locationName
            }

            chatInfoBuilder.message = noticeBuilder.build()
            newChatMessageBuilder.message = chatInfoBuilder.build()

            // home将消息广播
            println("home将room消息广播")
            val multicastMSG = MulticastEnvelopeMsg.newBuilder()
            multicastMSG.msgType = MsgType.GroupChatInfo_3076.msgType
            multicastMSG.groupChatMsg = newChatMessageBuilder.build()
            multicastMSG.channel = roomChannelOf(roomId)
            hpm.multicastServiceRouter.tellNoSender(
                multicastMSG.build()
            )

            session.sendMsg(MsgType.SendChat_301, rt.build())
        }

    }
}

fun sendPrivateChatMsg(
    session: PlayerActor, chatPlayerId: Long, homePlayer: HomePlayer, message: String,
    messageType: Int, easyFightId: Long, vipLv: Int, rt: SendChatMsgRt.Builder,
    friendChatRecordDC: FriendChatRecordDC, homeSync: HomeSync
) {
    val countryPositionId = homeSync.officeMap[session.worldId] ?: 0

    val now = getNowTime()
    val chatSaveRt = friendChatRecordDC.createFriendChatRecord(
        now,
        homePlayer.photoProtoId,
        message,
        chatPlayerId,
        messageType,
        vipLv,
        homePlayer.getMaxAlliancePos(),
        homePlayer.allianceName,
        homePlayer.allianceShortName,
        homePlayer.name,
        homePlayer.allianceNickName,
        0,
        countryPositionId,
        homePlayer.playerId,
        homePlayer.areaNo
    )

    // tell home 让对方也存一份并且推送
    val tellHome = SaveFriendChatRecordTell.newBuilder()
    tellHome.lastTalkTime = now
    tellHome.iconId = homePlayer.photoProtoId
    tellHome.recordString = message
    tellHome.friendId = homePlayer.playerId
    tellHome.msgType = messageType
    tellHome.vipLv = vipLv
    tellHome.alliancePos = homePlayer.getMaxAlliancePos()
    tellHome.allianceName = homePlayer.allianceName
    tellHome.allianceShortName = homePlayer.allianceShortName
    tellHome.playerName = homePlayer.name
    tellHome.playerShortName = homePlayer.allianceNickName
    tellHome.kingdomPos = 1
    tellHome.wonderPos = countryPositionId
    tellHome.areaNo = homePlayer.areaNo
    tellHome.castleLv = homePlayer.castleLv
    tellHome.power = homePlayer.power

    val home2Home = session.fillHome2HomeTellMsgHeader(
        chatPlayerId
    ) { it.setSaveFriendChatRecordTell(tellHome) }
    session.tellHome(home2Home)

    // 把这条消息推给自己
    createFriendChatMsgNotifier(
        now,
        homePlayer.photoProtoId,
        message,
        chatPlayerId,
        messageType,
        vipLv,
        homePlayer.getMaxAlliancePos(),
        homePlayer.allianceName,
        homePlayer.allianceShortName,
        homePlayer.name,
        homePlayer.allianceNickName,
        1, // todo 什么大帝国王的功能
        countryPositionId,
        homePlayer.playerId,
        homePlayer.areaNo,
        chatSaveRt.id
    ).notice(session)

    // 更改阅读时间
    val chatInfoHome = homePlayer.chatPlayerList.firstOrNull { it.chatRoomId == chatPlayerId }
    if (chatInfoHome == null) {
        homePlayer.chatPlayerList.add(MyChat(chatPlayerId, now))
    } else {
        chatInfoHome.lastReadTime = now
    }

    // 3 返回成功
    session.sendMsg(MsgType.SendChat_301, rt.build())
}

fun toSimpleFightInfo(
    myPlayerId: Long, reportId: Long, reportType: Int, reportContent: ByteArray,
    playerName: String, playerAlliance: String, icon: Int, worldId: Long
): String? {
    var message: String? = null
    when (reportType) {
        FIGHT_PLAYER_REPORT -> {
            val pvpTroopsFightReport = PvpFightReport.parseFrom(reportContent)
            val atkFightInfo = pvpTroopsFightReport.atkFightInfoList.firstOrNull()
            val defFightInfo = pvpTroopsFightReport.defFightInfoList.firstOrNull()
            if (atkFightInfo == null || defFightInfo == null) {
                return null
            }

            if (myPlayerId == atkFightInfo.fightPlayerInfo.id) {
                val info = SimplifiedFightInfo(
                    reportType,
                    atkFightInfo.fightPlayerInfo.name,
                    atkFightInfo.fightPlayerInfo.allianceShortName,
                    ATK_SIDE,
                    defFightInfo.fightPlayerInfo.name,
                    defFightInfo.fightPlayerInfo.allianceShortName,
                    reportId,
                    atkFightInfo.fightPlayerInfo.photo,
                    defFightInfo.fightPlayerInfo.photo,
                    0,
                    worldId
                )
                message = toJson(info)
            } else {
                val info = SimplifiedFightInfo(
                    reportType,
                    defFightInfo.fightPlayerInfo.name,
                    defFightInfo.fightPlayerInfo.allianceShortName,
                    DEF_SIDE,
                    atkFightInfo.fightPlayerInfo.name,
                    atkFightInfo.fightPlayerInfo.allianceShortName,
                    reportId,
                    defFightInfo.fightPlayerInfo.photo,
                    atkFightInfo.fightPlayerInfo.photo,
                    0,
                    worldId
                )
                message = toJson(info)
            }
        }

        STATION_DEF_REPORT -> {
            val stationDefReport = pb4client.StationDefReport.parseFrom(reportContent)

            val info = SimplifiedFightInfo(
                reportType,
                stationDefReport.atkPlayerName,
                stationDefReport.atkAllianceShortName,
                0,  // todo
                stationDefReport.defPlayerName,
                stationDefReport.defAllianceShortName,
                reportId,
                icon,
                1,
                0,
                worldId
            )
            message = toJson(info)
        }

        JJC_FIGHT_REPORT -> {
            val jjcFightReport = pb4client.JjcFightReport.parseFrom(reportContent)

            val info = SimplifiedFightInfo(
                reportType,
                playerName,
                playerAlliance,
                jjcFightReport.fightType,
                jjcFightReport.enemyName,
                jjcFightReport.enemyAllianceName,
                reportId,
                icon,
                jjcFightReport.enemyPhoto,
                0,
                worldId
            )
            message = toJson(info)
        }

        ATK_MONSTER_REPORT -> {
            val hunterFightReport = HunterFightReport.parseFrom(reportContent)
            val monsterId = hunterFightReport.hunterFightInfo.monsterId
            val monster = pcs.monsterProtoCache.findMonsterProto(monsterId)
            if (monster == null) {
                return message
            }

            val info = SimplifiedFightInfo(
                reportType,
                playerName,
                playerAlliance,
                ATK_SIDE,  // todo
                "",
                "",
                reportId,
                icon,
                0,
                monsterId,
                worldId
            )
            message = toJson(info)
        }

        ATK_ALLIANCE_MONSTER_REPORT -> {
            val allianceHunterFightReport = HunterFightReport.parseFrom(reportContent)
            val monsterId = allianceHunterFightReport.hunterFightInfo.monsterId
            val monster = pcs.monsterProtoCache.findMonsterProto(monsterId)
            if (monster == null) {
                return message
            }

            val info = SimplifiedFightInfo(
                reportType,
                playerName,
                playerAlliance,
                ATK_SIDE,  // todo
                "",
                "",
                reportId,
                icon,
                0,
                monsterId,
                worldId
            )
            message = toJson(info)
        }

        ATK_WORLD_MONSTER_REPORT -> {
            val worldHunterFightReport = HunterFightReport.parseFrom(reportContent)
            val monsterId = worldHunterFightReport.hunterFightInfo.monsterId
            val monster = pcs.monsterProtoCache.findMonsterProto(monsterId)
            if (monster == null) {
                return message
            }

            val info = SimplifiedFightInfo(
                reportType,
                playerName,
                playerAlliance,
                ATK_SIDE,  // todo
                "",
                "",
                reportId,
                icon,
                0,
                monsterId,
                worldId
            )
            message = toJson(info)
        }

        SCOUT_REPORT -> {
            val scoutReport = pb4client.ScoutReport.parseFrom(reportContent)
            if (scoutReport.cellType != 4 && scoutReport.result != 1) {
                // 侦察玩家并且成功才能继续下去,不然跳出
                return message
            }

            val info = SimplifiedFightInfo(
                reportType,
                "",
                "",
                0,  // todo
                scoutReport.defForce.playerInfo.playerInfo.name,
                scoutReport.defForce.playerInfo.playerInfo.allianceShortName,
                reportId,
                0,
                scoutReport.defForce.playerInfo.playerInfo.photo,
                0,
                worldId
            )
            message = toJson(info)
        }

        FIGHT_RELIC_REPORT -> {
            val massRuinsFightReport = MassRuinsFightReport.parseFrom(reportContent)
        }

        HUNTER_CALL_REPORT -> {
        }

        FIGHT_GROUP_REPORT -> {
        }

        TRANSPORT_REPORT -> {
        }

        BE_SCOUT_REPORT -> {
        }

        FARM_REPORT -> {
        }

        else -> {
            return null
        }
    }

    return message
}