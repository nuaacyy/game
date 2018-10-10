package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import pb4client.RefreshMoney
import pb4client.ResourceInfoRt

// 资源推送
data class RefreshResourceNotifier(
    val msg: pb4client.RefreshMoney.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.RefreshMoney_3000, this.msg.build())
    }
}

fun createRefreshResourceNotifier(
    player: HomePlayer,
    wood: Long,
    food: Long,
    iron: Long,
    stone: Long,
    decree: Int,
    resTime: Long
): RefreshResourceNotifier {
    // 向客户端推送临时计算的资源数量信息
    val refreshMoneyBuilder = RefreshMoney.newBuilder()
    val resourceInfoRtBuilder = ResourceInfoRt.newBuilder()
    resourceInfoRtBuilder.wood = wood
    resourceInfoRtBuilder.food = food
    resourceInfoRtBuilder.iron = iron
    resourceInfoRtBuilder.stone = stone
    resourceInfoRtBuilder.decree = decree

    resourceInfoRtBuilder.coin = player.coin
    resourceInfoRtBuilder.gold = player.gold
    resourceInfoRtBuilder.bindGold = player.bindGold
    resourceInfoRtBuilder.jjcCoin = player.jjcCoin
    resourceInfoRtBuilder.casinoCoin = player.casinoCoin

    resourceInfoRtBuilder.allianceCoin = player.allianceCoin
    resourceInfoRtBuilder.heroExpPool = player.heroExpPool
    resourceInfoRtBuilder.goldCoin = player.goldCoin
    resourceInfoRtBuilder.silverCoin = player.silverCoin

    // 推送时间
    resourceInfoRtBuilder.res4Time = com.point18.slg2d.common.commonfunc.getTimeSec(resTime)

    refreshMoneyBuilder.setRes(resourceInfoRtBuilder)
    return RefreshResourceNotifier(refreshMoneyBuilder)
}


