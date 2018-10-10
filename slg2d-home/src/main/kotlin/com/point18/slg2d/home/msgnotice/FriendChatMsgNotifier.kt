package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.CHAT_TYPE_PRIVATE
import com.point18.slg2d.common.constg.FIGHT_INFO_SHARE
import com.point18.slg2d.common.constg.LOCATION_SHARE
import com.point18.slg2d.common.constg.TEXT_READ_INFO
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedFightInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.ChatInfo
import pb4client.Notice
import pb4client.PrivateChatInfo
import pb4client.SimpleFightReport

// 新的私聊信息
data class FriendChatMsgNotifier(
    val msg: PrivateChatInfo
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.PrivateChatInfo_3079, this.msg)
    }
}

fun createFriendChatMsgNotifier(
    lastTalkTime: Long, iconId: Int, recordString: String,
    friendId: Long, msgType: Int, vipLv: Int, alliancePos: Int,
    allianceName: String, allianceShortName: String, playerName: String,
    playerShortName: String, kingdomPos: Int, wonderPos: Int,
    talkerId: Long, areaNo: Int, chatId: Long
): FriendChatMsgNotifier {
    val privateChatInfoBuilder = PrivateChatInfo.newBuilder()
    val chatInfoWithPerson = ChatInfo.newBuilder()

    chatInfoWithPerson.type = CHAT_TYPE_PRIVATE //  4群聊  5私聊(私聊有聊天室Id)
    chatInfoWithPerson.country = 24 // 真实国家
    chatInfoWithPerson.allianceName = allianceName // 联盟名称
    chatInfoWithPerson.allianceShortName = allianceShortName // 联盟简称
    chatInfoWithPerson.alliancePositions = alliancePos.toString() // 所属联盟官位
    chatInfoWithPerson.player = playerName // 说话者名字
    chatInfoWithPerson.playerShortName = playerShortName // 玩家昵称
    chatInfoWithPerson.playerIcon = iconId // 头像模板id
    chatInfoWithPerson.sendTime = (lastTalkTime / 1000).toInt() // 发送时间
    chatInfoWithPerson.id = chatId // 唯一ID
    chatInfoWithPerson.playerId = talkerId // 说话玩家ID (这个可以是自己,也可以是别人)
    chatInfoWithPerson.messageType = msgType // 消息类型  1-普通消息  2-红包消息（暂时被砍） 3-表情  4-战报分享  5-集结  6-喇叭
    chatInfoWithPerson.office = wonderPos //官职Id
    chatInfoWithPerson.vipLv = vipLv // vip等级
    chatInfoWithPerson.areaNo = areaNo // 服务器编号
    chatInfoWithPerson.talkToId = friendId // 对谁说话ID
    chatInfoWithPerson.isSystem = 0
    val noticeBuilder = Notice.newBuilder()
    noticeBuilder.readType = TEXT_READ_INFO
    noticeBuilder.noticeLanId = recordString

    if (msgType == FIGHT_INFO_SHARE) {
        noticeBuilder.noticeLanId = ""
        val fightInfo = toObj<SimplifiedFightInfo>(recordString)
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
        chatInfoWithPerson.easyFightInfo = easyFightInfo.build()
    } else if (msgType == LOCATION_SHARE) {
        val location = toObj<LocationShareInfo>(recordString)
        chatInfoWithPerson.x =  location.x
        chatInfoWithPerson.y  = location.y
        noticeBuilder.noticeLanId = location.locationName

    }

    chatInfoWithPerson.message = noticeBuilder.build() // 内容
    privateChatInfoBuilder.message = chatInfoWithPerson.build()

    return FriendChatMsgNotifier(privateChatInfoBuilder.build())
}