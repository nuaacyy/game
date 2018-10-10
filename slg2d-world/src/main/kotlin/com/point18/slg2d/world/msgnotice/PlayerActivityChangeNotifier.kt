package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.PlayerActivity
import pb4client.PlayerActivityChange

// 玩家活动信息发生变化
class PlayerActivityChangeNotifier(
    val msg: PlayerActivityChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.PlayerActivityChange_3155, this.msg.build())
    }
}

fun createPlayerActivityChangeNotifier(
    activityId: Int,
    score: Int,
    rank: Int,
    isActivityOver: Int
): PlayerActivityChangeNotifier {
    val playerActivityChangeBuilder = PlayerActivityChange.newBuilder()
    val playerActivityBuilder = PlayerActivity.newBuilder()
    playerActivityBuilder.activityId = activityId
    playerActivityBuilder.score = score
    playerActivityBuilder.rank = rank
    playerActivityBuilder.isActivityOver = isActivityOver
    playerActivityChangeBuilder.setPlayerActivity(playerActivityBuilder)
    return PlayerActivityChangeNotifier(playerActivityChangeBuilder)
}


