package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.CastlePosChange

// 玩家城位置推送
class CastlePosChangeNotifier(
    val msg: CastlePosChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.CastlePosChange_3167, this.msg.build())
    }
}

fun createCastlePosChangeNotifier(posX: Int, posY: Int): CastlePosChangeNotifier {
    val castlePosChangeBuilder = CastlePosChange.newBuilder()
    castlePosChangeBuilder.posX = posX
    castlePosChangeBuilder.posY = posY
    return CastlePosChangeNotifier(castlePosChangeBuilder)
}


