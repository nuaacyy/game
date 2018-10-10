package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.constg.CaveStart
import com.point18.slg2d.world.area4data.Cave
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.CaveInfo
import pb4client.CaveSoldier
import pb4client.NoticeCaveChg

class CaveChangeNotifier(
    val msg: NoticeCaveChg.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.CaveChange_3144, this.msg.build())
    }
}

fun createCaveChangeNotifier(
    isStart: Int, player: Player?,
    cave: Cave?, caveSoldiers: List<CaveSoldier>?, holdKing: Int
): CaveChangeNotifier {

    val noticeCaveChgBuilder = NoticeCaveChg.newBuilder()
    noticeCaveChgBuilder.caveStarted = isStart

    if (isStart == CaveStart && caveSoldiers != null && cave != null && player != null) {
        val caveInfoBuilder = CaveInfo.newBuilder()
        caveInfoBuilder.startTime = getTimeSec(cave.hideStartTime)
        caveInfoBuilder.endTime = getTimeSec(cave.hideOverTime)
        caveSoldiers.forEach { caveInfoBuilder.addSoldiers(it) }
        caveInfoBuilder.holdKingId = 0
        if (holdKing != 0) {
            caveInfoBuilder.holdKingId = player.mainHeroId
        }
        noticeCaveChgBuilder.setInfo(caveInfoBuilder)
    }
    return CaveChangeNotifier(noticeCaveChgBuilder)
}


