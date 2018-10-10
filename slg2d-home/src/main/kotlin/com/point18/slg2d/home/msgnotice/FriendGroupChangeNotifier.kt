package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.FriendGroupChange
import pb4client.GroupInfo

// 加好友信息变化
data class FriendGroupChangeNotifier(
    val msg: pb4client.FriendGroupChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.FriendGroupChange_3186, this.msg.build())
    }
}

fun createFriendGroupChangeNotifier(
    groupType:Int,
    groupId:Long,
    groupName:String
): FriendGroupChangeNotifier {
    val friendGroupChangeBuilder = FriendGroupChange.newBuilder()
    friendGroupChangeBuilder.type = groupType
    val groupInfoBuilder = GroupInfo.newBuilder()
    groupInfoBuilder.groupId = groupId
    groupInfoBuilder.groupName = groupName

    friendGroupChangeBuilder.setGroupInfo(groupInfoBuilder)

    return FriendGroupChangeNotifier(friendGroupChangeBuilder)
}

