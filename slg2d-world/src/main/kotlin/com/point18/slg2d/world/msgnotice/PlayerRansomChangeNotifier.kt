package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.MyPrisonInfo
import pb4client.PlayerRansomChange

// 玩家监狱信息变化
class PlayerRansomChangeNotifier(
    val msg: PlayerRansomChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.PlayerRansomChange_3181, this.msg.build())
    }
}

fun createPlayerRansomChangeNotifier(
    prison: Prison,
    castle: Castle,
    player: Player
): PlayerRansomChangeNotifier? {

    val playerRansomChangeNotifierBuilder = PlayerRansomChange.newBuilder()
    val myPrisonInfo = MyPrisonInfo.newBuilder()
    myPrisonInfo.playerId = prison.playerId
    myPrisonInfo.photoId = player.photoProtoId
    myPrisonInfo.allianceShortName = player.allianceShortName
    myPrisonInfo.playerName = player.name
    myPrisonInfo.x = castle.x
    myPrisonInfo.y = castle.y
    myPrisonInfo.areaNo = player.areaNo
    myPrisonInfo.ransom = prison.ransom
    myPrisonInfo.rewardGold = prison.rewardGold
    playerRansomChangeNotifierBuilder.prisonInfo = myPrisonInfo.build()

    return PlayerRansomChangeNotifier(playerRansomChangeNotifierBuilder)
}


