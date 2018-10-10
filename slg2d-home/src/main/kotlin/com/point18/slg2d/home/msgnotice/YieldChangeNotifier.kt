package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.YieldChange

// 向客户端推送资源产量
data class YieldChangeNotifier(
    val msg: pb4client.YieldChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.YieldChange_3009, this.msg.build())
    }
}

fun createYieldChangeNotifier(wood: Int, iron: Int, stone: Int, food: Int, coin: Int, calcTime: Int): YieldChangeNotifier {
    val yieldChangeBuilder = YieldChange.newBuilder()
    yieldChangeBuilder.addWood = wood
    yieldChangeBuilder.addIron = iron
    yieldChangeBuilder.addStone = stone
    yieldChangeBuilder.addFood = food
    yieldChangeBuilder.addCoin = coin
    yieldChangeBuilder.calcTime = calcTime
    return YieldChangeNotifier(yieldChangeBuilder)
}


