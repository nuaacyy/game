package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.Wonder
import com.point18.slg2d.world.common.findOfficeByPlayerId
import pb4client.WonderInfoInit

class WonderInfoInitNotifier(
    val msg: WonderInfoInit.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.WonderInfoInit_3304, this.msg.build())
    }
}

// 初始化客户端数据的推送
fun createWonderInfoInitNotifier(areaCache: AreaCache, playerId: Long, wonder: Wonder): WonderInfoInitNotifier {
    val notifierBuilder = WonderInfoInit.newBuilder()

    val posId = findOfficeByPlayerId(areaCache, playerId)

    notifierBuilder.currentPos = posId
    notifierBuilder.buffCoolTime = getTimeSec(wonder.buffCoolTime)

    return WonderInfoInitNotifier(notifierBuilder)
}
