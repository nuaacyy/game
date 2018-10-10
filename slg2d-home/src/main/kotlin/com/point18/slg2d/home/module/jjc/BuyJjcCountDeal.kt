package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.ONE_DAY_MILLS
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_USE_BUY_JJC_COUNT
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_BIND_GOLD
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.getJjcShopRefreshTime
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BuyJjcCountRt
import java.util.Arrays.asList

// 购买竞技场挑战次数
class BuyJjcCountDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, JjcHomeDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC ->
            val rt = buyJjcCount(session, homePlayerDC, jjcHomeDC)
            session.sendMsg(MsgType.BuyJjcCount_722, rt)
        }
    }

    private fun buyJjcCount(session: PlayerActor, homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC): BuyJjcCountRt {
        val rt = BuyJjcCountRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.buyTimes = 0
        rt.nextRefreshTime = 0
        val nowTime = getNowTime()
        // 获取挑战剩余次数、挑战冷却结束时间
        val jjcInfo = jjcHomeDC.jjcHome
        val homePlayer = homePlayerDC.player
        val nowRefreshTime = getJjcShopRefreshTime(nowTime)
        val lastRefreshTime = getJjcShopRefreshTime(jjcInfo.lastFightResetTime)

        rt.nextRefreshTime = nowRefreshTime / 1000 + 24 * 3600
        // 超过一天
        if ((lastRefreshTime - nowRefreshTime) >= ONE_DAY_MILLS) {

            // 挑战次数回复
            if (jjcInfo.todayNum < pcs.basicProtoCache.arenaFreeTimes) {
                jjcInfo.todayNum = pcs.basicProtoCache.arenaFreeTimes
            }

            //购买次数回复
            jjcInfo.lastFightResetTime = nowTime
            jjcInfo.todayBuyCountNum = 0
        }


        if (jjcInfo.todayNum >= pcs.basicProtoCache.arenaTimesLimit) {
            rt.rt = ResultCode.JJC_FIGHT_ERROR_ENOUGH_FIGHT_NUM.code
            return rt.build()
        }

        val c = pcs.diamondConsumeCache.getBuyJjcNumByTimes(jjcInfo.todayBuyCountNum + 1)

        val costs = ResVo(RES_BIND_GOLD, NOT_PROPS_SUB_TYPE, c.toLong())
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        // 扣除资源
        val action = ACTION_USE_BUY_JJC_COUNT
        resHelper.costRes(session, action, homePlayer, costs)

        // 次数增加
        jjcInfo.todayBuyCountNum += 1
        jjcInfo.todayNum = jjcInfo.todayNum + pcs.basicProtoCache.arenaBuyTimes
        rt.buyTimes = jjcInfo.todayNum
        return rt.build()
    }
}
