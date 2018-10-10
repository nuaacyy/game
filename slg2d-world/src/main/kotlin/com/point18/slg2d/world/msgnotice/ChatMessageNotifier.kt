package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.ChatInfo
import pb4client.NewChatMessage
import pb4client.Notice

// 推送聊天室消息
class ChatMessageNotifier(
    val msg: NewChatMessage.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.NewChatMessage_3080, this.msg.build())
    }
}

// trueCountry是指玩家现实中的国家
fun createChatMessageNotifier(
    chatType: Int,
    isSystem: Int,
    trueCountry: Int,
    allianceName: String,
    allianceShortName: String,
    alliancePositions: String,
    playerId: Long,
    playerName: String,
    playerIcon: Int,
    office: Int,
    sendTime: Int,
    lanId: String,
    readType: Int,
    messageType: Int,
    vipLv: Int,
    areaNo: Int,
    id: Long = 0,
    playerShortName: String = "",
    vararg lanParams: String
): ChatMessageNotifier {
    val newChatMessageBuilder = NewChatMessage.newBuilder()
    val chatInfoBuilder = ChatInfo.newBuilder()
    chatInfoBuilder.id = id
    chatInfoBuilder.type = chatType
    chatInfoBuilder.isSystem = isSystem
    chatInfoBuilder.country = trueCountry
    chatInfoBuilder.allianceName = allianceName
    chatInfoBuilder.allianceShortName = allianceShortName
    chatInfoBuilder.alliancePositions = alliancePositions
    chatInfoBuilder.player = playerName
    chatInfoBuilder.playerId = playerId
    chatInfoBuilder.playerShortName = playerShortName
    chatInfoBuilder.playerIcon = playerIcon
    chatInfoBuilder.sendTime = sendTime
    chatInfoBuilder.messageType = messageType
    chatInfoBuilder.redBagState = 0
    chatInfoBuilder.office = office
    chatInfoBuilder.vipLv = vipLv
    chatInfoBuilder.areaNo = areaNo
    val noticeBuilder = Notice.newBuilder()
    noticeBuilder.readType = readType
    noticeBuilder.noticeLanId = lanId
    lanParams.forEach { noticeBuilder.addNoticeParams(it) }
    chatInfoBuilder.setMessage(noticeBuilder)
    newChatMessageBuilder.setChatInfo(chatInfoBuilder)

    return ChatMessageNotifier(newChatMessageBuilder)
}


