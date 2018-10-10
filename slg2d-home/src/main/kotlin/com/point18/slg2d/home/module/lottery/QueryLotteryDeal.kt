package com.point18.slg2d.home.module.lottery

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowMTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.DrawHeroProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.LotteryDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.LotteryInfo
import pb4client.QueryLotteryRt
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

class QueryLotteryDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, LotteryDC>(
        HomePlayerDC::class.java,
        LotteryDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, lotteryDC ->
            val rt = queryLottery(session, homePlayerDC, lotteryDC)
            session.sendMsg(MsgType.QueryLottery_1545, rt)
        }
    }

    private fun queryLottery(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        lotteryDC: LotteryDC
    ): QueryLotteryRt {
        val rt = QueryLotteryRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // todo 验证玩家
        val player = homePlayerDC.player
        var lotteryPools = lotteryDC.findDrawHeroByPlayerId(player.playerId)
        // 从数据库找
        val now = getNowMTime()
        val lotteryInfoList = LinkedList<LotteryInfo>()

        // 应该有的抽奖proto
        val newLotteryList = LinkedList<DrawHeroProto>()
        for ((_, eachProto) in pcs.drawHeroProtoCache.dropBagMap) {
            if (eachProto.actEndTime > now && eachProto.actStartTime < now) {
                newLotteryList.add(eachProto)
                continue
            }
            if (eachProto.actEndTime == 0L && eachProto.actStartTime == 0L) {
                newLotteryList.add(eachProto)
            }
        }


        for (eachActivity in newLotteryList) {
            val act = lotteryPools.filter { it.protoId == eachActivity.id }

            if (act.size == 1) {
                continue
            }

            if (act.size > 1) {
                normalLog.error("抽奖池有相同的，错误！")
                continue
            }

            // 如果抽奖没有创建
            if (eachActivity.id == 1) {
                lotteryDC.createDrawHero(
                    eachActivity.id, eachActivity.actEndTime,
                    pcs.basicProtoCache.lowDropFreeNum, session.playerId
                )
            } else if (eachActivity.id == 2) {
                lotteryDC.createDrawHero(
                    eachActivity.id, eachActivity.actEndTime,
                    1, session.playerId
                )
            } else {
                lotteryDC.createDrawHero(
                    eachActivity.id, eachActivity.actEndTime,
                    0, session.playerId
                )
            }
        }

        lotteryPools = lotteryDC.findDrawHeroByPlayerId(session.playerId)

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

            // 普通
            if (eachLottery.protoId == 1) {
                // 免费次数同时刷新
                val lastFreeDrawTime = getDiscountRefreshTime(eachLottery.freeRefreshTime)
                val nowFreeDrawTime = getDiscountRefreshTime(now)
                if (lastFreeDrawTime - nowFreeDrawTime > 24 * 3600 * 1000) {
                    eachLottery.freeRefreshTime = now
                    eachLottery.restFreeDrawTimes = pcs.basicProtoCache.lowDropFreeNum
                    eachLottery.lastFreeDrawTime = 0
                }
            }

            // 高级
            if (eachLottery.protoId == 2) {
                // 刷新cd
                if (now - eachLottery.lastFreeDrawTime > pcs.basicProtoCache.highDropFreeCd * 1000) {
                    eachLottery.restFreeDrawTimes = 1
                    eachLottery.lastFreeDrawTime = 0
                }
            }

            // 活动

            // 是否到每天6点定时刷新折扣 和普通的次数
            val lastRefreshDiscountTime = getDiscountRefreshTime(eachLottery.lastUseDiscountTime)
            val nowRefreshDiscountTime = getDiscountRefreshTime(now)
            if (nowRefreshDiscountTime - lastRefreshDiscountTime > 24 * 3600 * 1000) {
                eachLottery.lastUseDiscountTime = now
                eachLottery.discountToday = 1
            }

            if (eachLottery.lastFreeDrawTime != 0L && eachLottery.protoId == 1 && eachLottery.restFreeDrawTimes > 0) {
                tmpLotteryInfo.nextRefreshTime = eachLottery.lastFreeDrawTime / 1000 + pcs.basicProtoCache.lowDropFreeCd
            } else if (eachLottery.lastFreeDrawTime != 0L && eachLottery.protoId == 2 && eachLottery.restFreeDrawTimes > 0) {
                tmpLotteryInfo.nextRefreshTime = eachLottery.lastFreeDrawTime / 1000 +
                        pcs.basicProtoCache.highDropFreeCd
            } else {
                tmpLotteryInfo.nextRefreshTime = 0
            }

            tmpLotteryInfo.protoId = eachLottery.protoId
            tmpLotteryInfo.freeTimes = eachLottery.restFreeDrawTimes
            tmpLotteryInfo.todayDiscount = eachLottery.discountToday
            tmpLotteryInfo.lackTimes = 10 - (eachLottery.drawSum % 10).toInt()
            tmpLotteryInfo.endTime = proto.actEndTime / 1000
            tmpLotteryInfo.startTime = proto.actStartTime / 1000

            lotteryInfoList.add(tmpLotteryInfo.build())
        }

        rt.addAllInfos(lotteryInfoList)
        return rt.build()
    }
}

//获取刷新时间
fun getDiscountRefreshTime(assignTime: Long): Long {
    val refreshHour = pcs.basicProtoCache.getVipRewardRefreshHour
    val refreshMinute = pcs.basicProtoCache.getVipRewardRefreshMinute

    val instance = Instant.ofEpochMilli(assignTime)
    val localDate = ZonedDateTime.ofInstant(instance, ZoneId.systemDefault())
//    val zoneDateTime = ZonedDateTime.of(localDate,ZoneId.systemDefault())
//    zoneDateTime.toInstant()

    val localYear = localDate.year
    val localMouth = localDate.month
    val localDay = localDate.dayOfMonth
    val localHour = localDate.hour
    val localMinute = localDate.minute
    val localSec = localDate.second

    val refreshTime1 = ZonedDateTime.of(
        localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault()
    ).minusHours(24)

    val refreshTime2 = ZonedDateTime.of(
        localYear, localMouth.value, localDay
        , refreshHour, refreshMinute, 0, 0, ZoneId.systemDefault()
    )

    if (localDate.isBefore(refreshTime2)) {
        return refreshTime1.toInstant().toEpochMilli()
    } else {
        return refreshTime2.toInstant().toEpochMilli()
    }

}