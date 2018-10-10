package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.FriendApplySuccess
import pb4client.FriendInfo

// 加好友信息变化
data class FriendApplySuccessNotifier(
    val msg: pb4client.FriendApplySuccess.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.FriendApplySuccess_3185, this.msg.build())
    }
}

fun createFriendApplySuccessNotifier(
    type: Int,
    playerId: Long,
    name: String,
    areaNo: Int,
    vipLv: Int,
    allianceShortName: String,
    photoProtoId: Int,
    castleLv: Int,
    skinType: Int,
    groupID: Long,
    shortName: String
): FriendApplySuccessNotifier {
    val friendApplySuccessBuilder = FriendApplySuccess.newBuilder()

    friendApplySuccessBuilder.type = type
    val nowTime = getNowTime()

    val friendInfoBuilder = FriendInfo.newBuilder()
    friendInfoBuilder.playerId = playerId
    friendInfoBuilder.name = name
    friendInfoBuilder.photoId = photoProtoId
    friendInfoBuilder.townState = vipLv
    friendInfoBuilder.fightValue = 0
    friendInfoBuilder.castleLv = castleLv
    friendInfoBuilder.skinType = skinType
    friendInfoBuilder.vipLv = vipLv
    friendInfoBuilder.areaNo = areaNo
    friendInfoBuilder.allianceShortName = allianceShortName
    friendInfoBuilder.groupId = groupID
    friendInfoBuilder.lastTalkTime = nowTime / 1000
    friendInfoBuilder.lastReadTime = nowTime / 1000
    friendInfoBuilder.msgNum = 0
    friendInfoBuilder.shortName = shortName
    friendApplySuccessBuilder.setFriendInfo(friendInfoBuilder)

    return FriendApplySuccessNotifier(friendApplySuccessBuilder)
}

