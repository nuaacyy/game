package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.FriendInfo
import pb4client.SendStrangerInfo

data class NewChatWindowNotifier(
    val msg: pb4client.SendStrangerInfo.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.SendStrangerInfo_3189, this.msg.build())
    }
}

fun createNewChatWindowNotifier(
    playerId: Long,
    worldId:Long,
    name:String,
    photoProtoId:Int,
    power:Long,
    castleLv:Int,
    skinId:Int,
    vipLv:Int,
    areaNo:Int,
    allianceShortName:String,
    lastTalkTime:Long,
    lastReadTime:Long,
    playerShortName:String
): NewChatWindowNotifier {
    val newBuilder = SendStrangerInfo.newBuilder()
    val content = FriendInfo.newBuilder()
    content.photoId = photoProtoId
    content.playerId = playerId
    content.fightValue = power
    content.castleLv = castleLv
    content.vipLv = vipLv
    content.areaNo = areaNo
    content.allianceShortName = allianceShortName
    content.groupId = 0
    content.name = name
    content.lastReadTime = lastReadTime/1000
    content.lastTalkTime = lastTalkTime/1000
    content.shortName = playerShortName
    newBuilder.strangerInfos = content.build()
    return NewChatWindowNotifier(newBuilder)
}