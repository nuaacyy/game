package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.FriendApply
import pb4client.FriendApplyChange

// 加好友信息变化
data class FriendApplyChangeNotifier(
    val msg: pb4client.FriendApplyChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.FriendApplyChange_3172, this.msg.build())
    }
}

fun createFriendApplyChangeNotifier(
    playerId:Long,
    name:String,
    areaNo:Int,
    vipLv:Int,
    allianceShortName: String,
    photoProtoId: Int,
    state: Int,
    castleLv: Int,
    skinType: Int,
    applyTime: Long,
    shortName: String
): FriendApplyChangeNotifier {
    val friendApplyChangeBuilder = FriendApplyChange.newBuilder()

    val friendApplyBuilder = FriendApply.newBuilder()
    friendApplyBuilder.applyPlayerId = playerId
    friendApplyBuilder.applyPlayerName = name
    friendApplyBuilder.applyPlayerAreaNo = areaNo
    friendApplyBuilder.applyPlayerVipLv = vipLv
    friendApplyBuilder.applyPlayerAllianceShortName = allianceShortName
    friendApplyBuilder.applyPlayerPhotoId = photoProtoId
    friendApplyBuilder.applyState = state
    friendApplyBuilder.castleLv = castleLv
    friendApplyBuilder.skinType = skinType
    friendApplyBuilder.applyTime = (applyTime/1000).toInt()
    friendApplyBuilder.shortName = shortName

    friendApplyChangeBuilder.setFriendApply(friendApplyBuilder)

    return FriendApplyChangeNotifier(friendApplyChangeBuilder)
}

