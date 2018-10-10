@file:JvmName("ArenaRewardGetNotifierKt")

package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.ArenaRewardGet

data class ArenaRewardGetNotifier(
    val msg: ArenaRewardGet.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.ArenaRewardGet_3179, this.msg.build())
    }
}

fun createArenaRewardGetNotifier(gold: Long, jjcCoin : Long): ArenaRewardGetNotifier {
    val arenaRewardGet = ArenaRewardGet.newBuilder()
    arenaRewardGet.gold = gold
    arenaRewardGet.jjCoin = jjcCoin
    return ArenaRewardGetNotifier(arenaRewardGet)
}