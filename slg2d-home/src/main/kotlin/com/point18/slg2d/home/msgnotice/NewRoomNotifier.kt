package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.EnterGameChatRoomInfo
import java.util.*

// 新增聊天室
data class NewRoomNotifier(
    val msg: pb4client.EnterGameChatRoomInfo.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.EnterChatRoomInfo_3188, this.msg.build())
    }
}

fun createRoomNotifierExtend(
    chatRoomId: Long,
    roomName:String,
    unreadNum:Int,
    iconProtoIds: List<Int>,
    memberNum:Int,
    playerId:Long,
    lastReadTime:Long = 0,
    lastChatTime :Long = 0
): NewRoomNotifier {
    // 给新成员推 新聊天组信息
    val newRoomBuilder = EnterGameChatRoomInfo.newBuilder()
    val talkInfoBuilder = pb4client.ChatRoom.newBuilder()
    talkInfoBuilder.chatRoomId = chatRoomId
    talkInfoBuilder.chatRoomName = roomName
    val icons = LinkedList<Int>()
    for (eachIcon in iconProtoIds) {
        icons.add(eachIcon)
        if (icons.size >= 4) {
            break
        }
    }
    talkInfoBuilder.addAllMemberIcons(icons)
    talkInfoBuilder.memberNum = memberNum
    talkInfoBuilder.playerId =  playerId
    talkInfoBuilder.lastReadTime = lastReadTime/1000
    talkInfoBuilder.lastTalkTime = lastChatTime/1000
    newRoomBuilder.chatRoomInfo = talkInfoBuilder.build()
    return NewRoomNotifier(newRoomBuilder)
}
