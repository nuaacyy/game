package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedFightInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BlackPlayerDC
import com.point18.slg2d.home.dc.FriendChatRecordDC
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.*
import pb4server.ChatInfoVo
import pb4server.GetAllianceChatAskReq
import pb4server.GetGroupChatAskReq
import pb4server.Home2PublicAskResp
import java.util.*

class GetMessageDeal : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, FriendChatRecordDC, BlackPlayerDC>(
    HomePlayerDC::class.java, FriendChatRecordDC::class.java, BlackPlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, friendChatRecordDC: FriendChatRecordDC, blackPlayerDC: BlackPlayerDC ->
            // 要拉取的类型 世界的？联盟的？
            val type = (msg as GetChatInfo).chatType
            val id = msg.lastEndId
            val roomId = msg.roomId
            val player = homePlayerDC.player
            val rt = getOldMsg(session, type, id, player, msg, roomId, blackPlayerDC, friendChatRecordDC)
            if (rt != null) {
                session.sendMsg(MsgType.GetChatInfo_319, rt)
            }
        }
    }

    private fun getOldMsg(
        session: PlayerActor, type: Int, chatId: Long,
        player: HomePlayer, receiveReq: GetChatInfo, roomId: Long,
        blackPlayerDC: BlackPlayerDC, friendChatRecordDC: FriendChatRecordDC
    ): GetChatInfoRt? {
        val rt = GetChatInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val blackList = LinkedList<Long>()
        blackPlayerDC.blackPlayers.forEach {
            blackList.add(it.blackPlayerId)
        }

        // 要拉取的类型 世界的？联盟的？
        when (type) {
            CHAT_TYPE_WORLD -> {
                // 世界频道
                val tellMsg = pb4server.GetChatHistoryTell.newBuilder()
                tellMsg.chatId = chatId
                tellMsg.addAllBlackList(blackList)
                session.tellWorld(session.fillHome2WorldTellMsgHeader {
                    it.setGetChatHistoryTell(tellMsg)
                })
            }

            CHAT_TYPE_PRIVATE -> {
                rt.chatType = CHAT_TYPE_PRIVATE
                val recordList = LinkedList<ChatInfo>()
                if (receiveReq.playerId == 0L) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt.build()
                }
                val findResList = friendChatRecordDC.findRecordByFriendId(receiveReq.playerId)

                if (chatId == 0L) {
                    var startIndex = findResList.lastIndex - 10
                    if (startIndex < 0) {
                        startIndex = 0
                    }
                    for (index in startIndex..findResList.lastIndex) {
                        if (recordList.size >= 10) {
                            break
                        }
                        val eachRecord = findResList.getOrNull(index) ?: continue
                        val tmpChatInfo = ChatInfo.newBuilder()
                        tmpChatInfo.type = CHAT_TYPE_PRIVATE //  4群聊  5私聊(私聊有聊天室Id)
                        tmpChatInfo.country = 24 // 真实国家
                        tmpChatInfo.allianceName = eachRecord.allianceName // 联盟名称
                        tmpChatInfo.allianceShortName = eachRecord.allianceShortName // 联盟简称
                        tmpChatInfo.alliancePositions = eachRecord.alliancePos.toString() // 所属联盟官位
                        tmpChatInfo.player = eachRecord.playerName // 说话者名字
                        tmpChatInfo.playerShortName = eachRecord.playerShortName // 玩家昵称
                        tmpChatInfo.playerIcon = eachRecord.iconId // 头像模板id
                        tmpChatInfo.sendTime = (eachRecord.talkTime / 1000).toInt() // 发送时间
                        tmpChatInfo.id = eachRecord.id // 唯一ID
                        tmpChatInfo.playerId = eachRecord.talkPlayerId // 说话玩家ID (这个可以是自己,也可以是别人)
                        tmpChatInfo.messageType = eachRecord.msgType
                        tmpChatInfo.office = eachRecord.wonderPos // 官职Id
                        tmpChatInfo.vipLv = eachRecord.vipLv // vip等级
                        tmpChatInfo.areaNo = eachRecord.areaNo // 服务器编号
                        tmpChatInfo.talkToId = eachRecord.friendId // 对谁说话ID
                        tmpChatInfo.isSystem = 0

                        val noticeBuilder = Notice.newBuilder()
                        noticeBuilder.readType = TEXT_READ_INFO
                        noticeBuilder.noticeLanId = eachRecord.record

                        if (eachRecord.msgType == FIGHT_INFO_SHARE) {
                            noticeBuilder.noticeLanId = ""
                            val fightInfo = toObj<SimplifiedFightInfo>(eachRecord.record)
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
                            tmpChatInfo.easyFightInfo = easyFightInfo.build()
                        } else if (eachRecord.msgType == LOCATION_SHARE) {
                            val location = toObj<LocationShareInfo>(eachRecord.record)
                            tmpChatInfo.x = location.x
                            tmpChatInfo.y = location.y
                            noticeBuilder.noticeLanId = location.locationName
                        }

                        tmpChatInfo.message = noticeBuilder.build() // 内容

                        recordList.add(tmpChatInfo.build())
                    }
                    rt.addAllChatInfos(recordList)
                    return rt.build()
                }

                val target = findResList.firstOrNull { it.id == chatId }
                if (target != null) {
                    val targetIndex = findResList.indexOf(target)
                    val endIndex = targetIndex - 1
                    if (endIndex < 0) {
                        return rt.build()
                    }
                    var startIndex = endIndex - 10
                    if (startIndex < 0) {
                        startIndex = 0
                    }

                    for (index in startIndex..endIndex) {
                        if (recordList.size >= 10) {
                            break
                        }
                        val eachRecord = findResList.getOrNull(index) ?: continue
                        val tmpChatInfo = ChatInfo.newBuilder()
                        tmpChatInfo.type = CHAT_TYPE_PRIVATE                  //  4群聊  5私聊(私聊有聊天室Id)
                        tmpChatInfo.country = 24                            // 真实国家
                        tmpChatInfo.allianceName = eachRecord.allianceName  // 联盟名称
                        tmpChatInfo.allianceShortName = eachRecord.allianceShortName // 联盟简称
                        tmpChatInfo.alliancePositions = eachRecord.alliancePos.toString() // 所属联盟官位
                        tmpChatInfo.player = eachRecord.playerName          // 说话者名字
                        tmpChatInfo.playerShortName = eachRecord.playerShortName // 玩家昵称
                        tmpChatInfo.playerIcon = eachRecord.iconId          // 头像模板id
                        tmpChatInfo.sendTime = (eachRecord.talkTime / 1000).toInt() // 发送时间
                        tmpChatInfo.id = eachRecord.id                      // 唯一ID
                        tmpChatInfo.playerId = eachRecord.talkPlayerId      // 说话玩家ID (这个可以是自己,也可以是别人)
                        tmpChatInfo.messageType = eachRecord.msgType
                        tmpChatInfo.office = eachRecord.wonderPos                            // 官职Id
                        tmpChatInfo.vipLv = eachRecord.vipLv                // vip等级
                        tmpChatInfo.areaNo = eachRecord.areaNo              // 服务器编号
                        tmpChatInfo.talkToId = eachRecord.friendId          // 对谁说话ID
                        tmpChatInfo.isSystem = 0
                        val noticeBuilder = Notice.newBuilder()
                        noticeBuilder.readType = TEXT_READ_INFO
                        noticeBuilder.noticeLanId = eachRecord.record

                        if (eachRecord.msgType == FIGHT_INFO_SHARE) {
                            noticeBuilder.noticeLanId = ""
                            val fightInfo = toObj<SimplifiedFightInfo>(eachRecord.record)
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
                            tmpChatInfo.easyFightInfo = easyFightInfo.build()
                        } else if (eachRecord.msgType == LOCATION_SHARE) {
                            val location = toObj<LocationShareInfo>(eachRecord.record)
                            tmpChatInfo.x = location.x
                            tmpChatInfo.y = location.y
                            noticeBuilder.noticeLanId = location.locationName
                        }

                        tmpChatInfo.message = noticeBuilder.build() // 内容

                        recordList.add(tmpChatInfo.build())
                    }
                }
                rt.addAllChatInfos(recordList)
                return rt.build()
            }

            CHAT_TYPE_GROUP -> {
                // 群聊频道
                if (roomId == 0L) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt.build()
                }

                val askMsg = GetGroupChatAskReq.newBuilder()
                askMsg.chatId = chatId
                askMsg.roomId = roomId

                session.createACS<Home2PublicAskResp>().ask(
                    session.publicShardProxy,
                    session.fillHome2PublicAskMsgHeader(roomId) { it.setGetGroupChatAskReq(askMsg) },
                    Home2PublicAskResp::class.java
                ).whenCompleteKt { prt, askErr ->
                    if (askErr != null || prt == null) {
                        rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                    } else if (prt.getGroupChatAskRt.rt != ResultCode.SUCCESS.code) {
                        rt.rt = prt.getGroupChatAskRt.rt
                    } else {
                        rt.rt = prt.getGroupChatAskRt.rt
                        val tmpChatInfoList = LinkedList<ChatInfo>()
                        for (eachRecord in prt.getGroupChatAskRt.chatRecordListList) {
                            if (blackList.contains(eachRecord.playerId)) {
                                continue
                            }
                            val chatInfo = genChatInfoPb(eachRecord, CHAT_TYPE_GROUP)
                            tmpChatInfoList.add(chatInfo)
                        }

                        rt.chatType = CHAT_TYPE_GROUP
                        rt.addAllChatInfos(tmpChatInfoList)
                        session.sendMsg(MsgType.GetChatInfo_319, rt.build())
                    }
                }

            }

            CHAT_TYPE_ALLIANCE -> {
                val allianceId = player.allianceId
                if (allianceId == 0L) {
                    rt.rt = ResultCode.ALLIANCE_QUERY_NOT_EXIST.code
                    return rt.build()
                }

                // 联盟频道
                val askMsg = GetAllianceChatAskReq.newBuilder()
                askMsg.chatId = chatId

                session.createACS<Home2PublicAskResp>().ask(
                    session.publicShardProxy,
                    session.fillHome2PublicAskMsgHeader(allianceId) { it.setGetAllianceChatAskReq(askMsg) },
                    Home2PublicAskResp::class.java
                ).whenCompleteKt { prt, askErr ->
                    if (askErr != null || prt == null) {
                        rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                    } else {
                        rt.rt = prt.getAllianceChatAskRt.rt
                        val getChatInfoRt = genPbGetChatInfoRt(prt.getAllianceChatAskRt.getChatInfoRt, blackList)
                        session.sendMsg(MsgType.GetChatInfo_319, getChatInfoRt.build())
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

    private fun genChatInfoPb(chatRecord: ChatInfoVo, chatType: Int): ChatInfo {
        val chatInfo = ChatInfo.newBuilder()
        chatInfo.isSystem = chatRecord.isSystem // 是否是系统发布:0-玩家发布  1-系统发布
        chatInfo.country = chatRecord.country // 真实国家
        chatInfo.allianceName = chatRecord.allianceName// 联盟名称
        chatInfo.allianceShortName = chatRecord.allianceShortName // 联盟简称
        chatInfo.alliancePositions = chatRecord.alliancePositions// 所属联盟官位
        chatInfo.player = chatRecord.player // 说话者名字
        chatInfo.playerShortName = chatRecord.playerShortName // 玩家昵称
        chatInfo.playerIcon = chatRecord.playerIcon// 头像模板id
        chatInfo.sendTime = chatRecord.sendTime// 发送时间
        chatInfo.id = chatRecord.id // 唯一ID
        chatInfo.playerId = chatRecord.playerId // 玩家ID
        chatInfo.messageType = chatRecord.messageType //  1-普通消息 3-表情  4-战报分享  5-集结
        chatInfo.office = chatRecord.office //官职Id
        chatInfo.vipLv = chatRecord.vipLv // vip等级
        chatInfo.areaNo = chatRecord.areaNo // 服务器编号
        chatInfo.chatRoomId = chatRecord.chatRoomId // 聊天室Id
        chatInfo.type = chatType
        val tmpMsg = Notice.newBuilder()
        tmpMsg.readType = TEXT_READ_INFO
        tmpMsg.noticeLanId = chatRecord.message.noticeLanId

        if (chatRecord.messageType == FIGHT_INFO_SHARE) {
            tmpMsg.noticeLanId = ""
            val fightInfo = toObj<SimplifiedFightInfo>(chatRecord.message.noticeLanId)
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
            chatInfo.easyFightInfo = easyFightInfo.build()
        } else if (chatRecord.messageType == LOCATION_SHARE) {
            val location = toObj<LocationShareInfo>(chatRecord.message.noticeLanId)
            chatInfo.x = location.x
            chatInfo.y = location.y
            tmpMsg.noticeLanId = location.locationName

        }
        chatInfo.message = tmpMsg.build() // 内容
        return chatInfo.build()
    }

    private fun genPbGetChatInfoRt(vo: pb4server.GetChatInfoRtVo, blackList: LinkedList<Long>): GetChatInfoRt.Builder {
        val builder = GetChatInfoRt.newBuilder()

        builder.rt = vo.rt
        builder.chatType = vo.chatType
        for (each in vo.chatInfosList) {
            if (blackList.contains(each.playerId)) {
                continue
            }
            val chatInfo = genPbChatInfo(each)
            builder.addChatInfos(chatInfo)
        }

        return builder
    }

    private fun genPbChatInfo(vo: pb4server.ChatInfoVo): ChatInfo {
        val builder = ChatInfo.newBuilder()

        builder.type = vo.type
        builder.isSystem = vo.isSystem
        builder.country = vo.country
        builder.allianceName = vo.allianceName
        builder.allianceShortName = vo.allianceShortName
        builder.alliancePositions = vo.alliancePositions
        builder.player = vo.player
        builder.playerShortName = vo.playerShortName
        builder.playerIcon = vo.playerIcon
        builder.sendTime = vo.sendTime
        builder.id = vo.id
        builder.playerId = vo.playerId
        builder.messageType = vo.messageType
        builder.redBagState = 0
        builder.office = vo.office
        builder.vipLv = vo.vipLv
        builder.areaNo = vo.areaNo
        builder.chatRoomId = vo.chatRoomId
        builder.talkToId = vo.talkToId
        builder.x = vo.x
        builder.y = vo.y

        val notice = Notice.newBuilder()
        notice.noticeLanId = vo.message.noticeLanId
        notice.readType = vo.message.readType
        builder.message = notice.build()

        val fightInfo = SimpleFightReport.newBuilder()
        fightInfo.reportType = vo.easyFightInfo.reportType
        fightInfo.mainPlayer = vo.easyFightInfo.mainPlayer
        fightInfo.mainPlayerAlliance = vo.easyFightInfo.mainPlayerAlliance
        fightInfo.atkOrDef = vo.easyFightInfo.atkOrDef
        fightInfo.targetName = vo.easyFightInfo.targetName
        fightInfo.allianceOrLv = vo.easyFightInfo.allianceOrLv
        fightInfo.reportId = vo.easyFightInfo.reportId
        fightInfo.mainIconId = vo.easyFightInfo.mainIconId
        fightInfo.iconId = vo.easyFightInfo.iconId
        fightInfo.monsterId = vo.easyFightInfo.monsterId
        fightInfo.world = vo.easyFightInfo.world
        builder.easyFightInfo = fightInfo.build()

        val simpleMassInfo = SimpleMassInfo.newBuilder()
        simpleMassInfo.massId = vo.massInfo.massId
        simpleMassInfo.massName = vo.massInfo.massName
        builder.massInfo = simpleMassInfo.build()

        return builder.build()
    }

}



