package com.point18.slg2d.home.module.barracks

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_FIRE_FIGHTING
import com.point18.slg2d.common.constg.CASTLE_FIRE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.WallFireFightRt
import pb4server.FireFightAskReq
import pb4server.Home2WorldAskResp
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

// 城墙灭火
class CastleFireDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, HomeSyncDC>(
    HomePlayerDC::class.java, HomeSyncDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC ->
            val rt = castleFireFight(session, homePlayerDC, homeSyncDC)
            if (rt != null) {
                session.sendMsg(MsgType.WallFireFight_1542, rt)
            }
        }
    }

    private fun castleFireFight(session: PlayerActor, homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC): WallFireFightRt? {
        val rtBuilder = WallFireFightRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val homePlayer = homePlayerDC.player

        var costOrNot = true
        val cost = LinkedList<ResVo>()

        // 是否到自动灭火
        val syncData = homeSyncDC.syncData
        if (syncData.castleState == CASTLE_FIRE && syncData.castleStatusEndTime < getNowTime() && syncData.castleStatusEndTime != 0L) {
            costOrNot = false
        }

        // 不是自动灭火需要资源消耗
        if (costOrNot) {
            // 资源检测
            val costPropsId = pcs.basicProtoCache.cityMisfireProps
            cost += costPropsId
            val checkCostFirst = resHelper.checkRes(session, cost)

            // 有道具扣道具,没道具扣钻石
            if (!checkCostFirst) {
                val (ok, needRes) = com.point18.slg2d.common.pc.props2GoldCost(cost[0])
                if (ok != ResultCode.SUCCESS) {
                    rtBuilder.rt = ok.code
                    return rtBuilder.build()
                }
                cost.clear()
                cost += needRes

                // 再次检测
                if (!resHelper.checkRes(session, cost)) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return rtBuilder.build()
                }
            }
        }

        val costResWithoutNoticeResult = resHelper.costResWithoutNotice(
            session,
            ACTION_FIRE_FIGHTING,
            homePlayer,
            cost
        )

        // 去world服 灭火
        val askMsg = FireFightAskReq.newBuilder()
        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.fireFightAskReq = askMsg.build() },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { rt, err ->

            try {
                when {
                    err != null -> {
                        val dealRt = WallFireFightRt.newBuilder()
                        dealRt.rt = ResultCode.ASK_ERROR1.code
                        resHelper.addResWithoutNotice(session, ACTION_FIRE_FIGHTING, homePlayer, cost)
                        normalLog.lzWarn { "是否着火 错误消息$err" }
                        session.sendMsg(MsgType.WallFireFight_1542, dealRt.build())
                    }
                    rt == null -> {
                        val dealRt = WallFireFightRt.newBuilder()
                        dealRt.rt = ResultCode.ASK_ERROR2.code
                        resHelper.addResWithoutNotice(session, ACTION_FIRE_FIGHTING, homePlayer, cost)
                        normalLog.lzWarn { "是否着火 错误消息$err" }
                        session.sendMsg(MsgType.WallFireFight_1542, dealRt.build())
                    }
                    else -> {
                        val dealRt = WallFireFightRt.newBuilder()
                        dealRt.rt = ResultCode.SUCCESS.code
                        if (rt.fireFightAskRt.rt != ResultCode.SUCCESS.code) {
                            resHelper.addResWithoutNotice(session, ACTION_FIRE_FIGHTING, homePlayer, cost)
                            dealRt.rt = rt.fireFightAskRt.rt
                            session.sendMsg(MsgType.WallFireFight_1542, dealRt.build())
                        } else {
                            // 成功灭火
                            dealRt.rt = rt.fireFightAskRt.rt
                            costResWithoutNoticeResult.noticeCostRes(session, homePlayer)
                            session.sendMsg(MsgType.WallFireFight_1542, dealRt.build())
                        }
                    }
                }

            } catch (e: Exception) {
                normalLog.error("FireFightAskReq Error!", e)
                val dealRt = WallFireFightRt.newBuilder()
                dealRt.rt = ResultCode.ASK_ERROR3.code
                resHelper.addResWithoutNotice(session, ACTION_FIRE_FIGHTING, homePlayer, cost)
                session.sendMsg(MsgType.WallFireFight_1542, dealRt.build())
            }
        }

        return null
    }

}
