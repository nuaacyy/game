package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.LotteryChange
import pb4client.LotteryInfo

data class LotteryChangeNotifier(
    val msg: pb4client.LotteryChange.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.LotteryChange_3182, this.msg.build())
    }
}

fun createLotteryChangeNotifier(
    protoId: Int, nextRefreshTime: Long, freeTimes: Int, todayDiscount: Int,
    lackTimes: Int, start: Long, end: Long
): LotteryChangeNotifier {
    val lotteryChange = LotteryChange.newBuilder()
    val lotteryInfo = LotteryInfo.newBuilder()
    lotteryInfo.protoId = protoId
    lotteryInfo.nextRefreshTime = nextRefreshTime
    lotteryInfo.freeTimes = freeTimes
    lotteryInfo.todayDiscount = todayDiscount
    lotteryInfo.lackTimes = lackTimes
    lotteryInfo.startTime = start
    lotteryInfo.endTime = end
    lotteryChange.lotteryInfo = lotteryInfo.build()
    return LotteryChangeNotifier(lotteryChange)
}

