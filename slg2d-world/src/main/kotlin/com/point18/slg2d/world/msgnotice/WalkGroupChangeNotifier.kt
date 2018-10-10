package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.NoticeWalkGroupChange
import pb4client.WalkGroup

class WalkGroupChangeNotifier(
    val msg: NoticeWalkGroupChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.WalkGroupChange_3142, this.msg.build())
    }
}

fun createWalkGroupChangeNotifier(
    changeType: Int,
    walkGroupType: Int,
    walkGroup: WalkGroup.Builder
): WalkGroupChangeNotifier {
    val noticeWalkGroupChangeBuilder = NoticeWalkGroupChange.newBuilder()
    noticeWalkGroupChangeBuilder.changeType = changeType
    noticeWalkGroupChangeBuilder.walkGroupType = walkGroupType
    noticeWalkGroupChangeBuilder.setWalkGroup(walkGroup)
    return WalkGroupChangeNotifier(noticeWalkGroupChangeBuilder)
}


