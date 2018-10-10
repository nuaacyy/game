package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.ArenaRankChange

// 赦免次数变化推送
class ArenaRankChangeNotifier(
    val msg: ArenaRankChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.ArenaRankChange_3178, this.msg.build())
    }
}

fun createArenaRankChangeNotifier(rank: Int): ArenaRankChangeNotifier {
    val arenaRankChange = ArenaRankChange.newBuilder()
    arenaRankChange.changeInfo = rank
    return ArenaRankChangeNotifier(arenaRankChange)
}


