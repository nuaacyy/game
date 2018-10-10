package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.Lottery
import pb4client.LotteryInfo
import pb4client.LotteryInfoInit
import java.util.*

// 抽卡信息初始化推送
data class LotteryInfoInitNotifier(
    val msg: LotteryInfoInit.Builder
) {
    fun notice(session: PlayerActor) {
        session.sendMsg(MsgType.LotteryInfoInit_3354, this.msg.build())
    }
}

fun createLotteryInfoInitNotifier(lotteryPools: List<Lottery>): LotteryInfoInitNotifier {

    val notifierBuilder = LotteryInfoInit.newBuilder()

    // 从数据库找
    val now = getNowTime()
    val lotteryInfoList = LinkedList<LotteryInfo>()

    for (eachLottery in lotteryPools) {
        // 验证模板
        val proto = pcs.drawHeroProtoCache.dropBagMap[eachLottery.protoId]
        if (proto == null) {
            continue
        }

        // 通过模板验证是否在活动时间
        if (now > proto.actEndTime || now < proto.actStartTime) {
            if (proto.actEndTime != 0L && proto.actStartTime != 0L) {
                continue
            }
        }

        val tmpLotteryInfo = LotteryInfo.newBuilder()

        if (eachLottery.lastFreeDrawTime != 0L && eachLottery.protoId == 1 && eachLottery.restFreeDrawTimes > 0) {
            tmpLotteryInfo.nextRefreshTime = eachLottery.lastFreeDrawTime / 1000 + pcs.basicProtoCache.lowDropFreeCd
        } else if (eachLottery.lastFreeDrawTime != 0L && eachLottery.protoId == 2 && eachLottery.restFreeDrawTimes > 0) {
            tmpLotteryInfo.nextRefreshTime = eachLottery.lastFreeDrawTime / 1000 + pcs.basicProtoCache.highDropFreeCd
        } else {
            tmpLotteryInfo.nextRefreshTime = 0
        }

        tmpLotteryInfo.protoId = eachLottery.protoId
        tmpLotteryInfo.freeTimes = eachLottery.restFreeDrawTimes
        tmpLotteryInfo.todayDiscount = eachLottery.discountToday
        tmpLotteryInfo.lackTimes = 10 - (eachLottery.drawSum % 10).toInt()
        tmpLotteryInfo.startTime = proto.actStartTime / 1000
        tmpLotteryInfo.endTime = proto.actEndTime / 1000

        lotteryInfoList.add(tmpLotteryInfo.build())
    }

    notifierBuilder.addAllLotteryInfos(lotteryInfoList)

    return LotteryInfoInitNotifier(notifierBuilder)
}