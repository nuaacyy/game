package com.point18.slg2d.home.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.ONE_DAY_MILLS
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_JJC_REWARD
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.getJjcShopRefreshTime
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.JjcHomeDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.JjcFightEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.JjcFight
import pb4client.JjcFightRt
import pb4server.ArenaFightAskReq
import pb4server.Home2WorldAskResp
import java.util.Arrays.asList

// 购买竞技场挑战次数
class ArenaFightDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, JjcHomeDC, HomeMyTargetDC>(
    HomePlayerDC::class.java, JjcHomeDC::class.java, HomeMyTargetDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC, homeMyTargetDC: HomeMyTargetDC ->
            val defRank = (msg as JjcFight).defRank
            val defPlayerId = msg.defPlayerId
            val rt = dealArenaFight(
                session, defRank, defPlayerId, homePlayerDC, jjcHomeDC, homeMyTargetDC
            )
            if (rt != null) {
                session.sendMsg(MsgType.JjcFight_720, rt)
            }
        }
    }

    private fun dealArenaFight(
        session: PlayerActor, defRank: Int, defPlayerId: Long,
        homePlayerDC: HomePlayerDC, jjcHomeDC: JjcHomeDC, homeMyTargetDC: HomeMyTargetDC
    ): JjcFightRt? {
        val jjcInfo = jjcHomeDC.jjcHome
        val rtBuilder = JjcFightRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val nowTime = getNowTime()
        val nowRefreshTime = getJjcShopRefreshTime(nowTime)
        val lastRefreshTime = getJjcShopRefreshTime(jjcInfo.lastFightResetTime)

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

        if (jjcInfo.todayNum <= 0) {
            rtBuilder.rt = ResultCode.JJC_FIGHT_ERROR_NO_FIGHT_NUM.code
            return rtBuilder.build()
        }

        // 告知world服要挑战的对手，让world处理
        val askMsg = ArenaFightAskReq.newBuilder()
        askMsg.defRank = defRank
        askMsg.defPlayerId = defPlayerId
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setArenaFightAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { askResp, askErr ->

            try {
                when {
                    askErr != null -> {
                        rtBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.JjcFight_720, rtBuilder.build())
                        return@whenCompleteKt
                    }

                    askResp == null -> {
                        rtBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.JjcFight_720, rtBuilder.build())
                        return@whenCompleteKt
                    }

                    else -> {
                        if (askResp.arenaFightAskRt.rt != ResultCode.SUCCESS.code) {
                            return@whenCompleteKt
                        }
                        // todo 只要打架了,无论输赢,都有一定的竞技币,这个以后放到战斗结算做?
                        resHelper.addRes(
                            session,
                            ACTION_JJC_REWARD,
                            homePlayerDC.player,
                            pcs.basicProtoCache.arenaCoinReward
                        )
                        jjcInfo.todayNum = jjcInfo.todayNum - 1
                        homeMyTargetDC.targetInfo.jjcFightNum++
                        fireEvent(session, JjcFightEvent())
                        rtBuilder.rt = askResp.arenaFightAskRt.rt
                    }
                }

            } catch (e: Exception) {
                normalLog.error("ArenaFightAskReq Error!", e)
                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.JjcFight_720, rtBuilder.build())
                return@whenCompleteKt
            }
        }
        return null
    }
}