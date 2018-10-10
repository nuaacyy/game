package com.point18.slg2d.home.module.prison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_BUY_RESURGENCE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.props2GoldCost
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ResurgenceAtOnceRt
import pb4server.Home2WorldAskResp
import pb4server.ResurgenceAskReq
import java.util.*

class ResurgenceAtOnceHomeDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
                val rt = resurgenceAtOnce(session, homePlayerDC)
                if (rt != null) {
                    session.sendMsg(MsgType.ResurgenceAtOnce_1357, rt)
                }
            }
    }

    private fun resurgenceAtOnce(session: PlayerActor, homePlayerDC: HomePlayerDC): ResurgenceAtOnceRt? {

        val rtDeal = ResurgenceAtOnceRt.newBuilder()
        rtDeal.rt = ResultCode.SUCCESS.code

        val cost = LinkedList<ResVo>()
        cost += pcs.basicProtoCache.fastReborn
        val checkResRt = resHelper.checkRes(session, cost)
        if (!checkResRt) {
            val (ok, needRes) = props2GoldCost(cost[0])
            if (ok != ResultCode.SUCCESS) {
                rtDeal.rt = ResultCode.LESS_RESOUCE.code
                return rtDeal.build()
            }

            //校验需要的资源
            if (!resHelper.checkRes(session, needRes)) {
                rtDeal.rt = ResultCode.LESS_RESOUCE.code
                return rtDeal.build()
            }

            cost.clear()
            cost += needRes
        }

        val action = ACTION_BUY_RESURGENCE
        val costResWithoutNoticeResult =
            resHelper.costResWithoutNotice(session, action, homePlayerDC.player, cost)

        val askMsg = ResurgenceAskReq.newBuilder()
        session.createACS<Home2WorldAskResp>()
            .ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setResurgenceAskReq(askMsg) },
                Home2WorldAskResp::class.java
            )
            .whenCompleteKt { askResp, askErr ->

                try {
                    when {
                        askErr != null -> {
                            resHelper.addResWithoutNotice(session, ACTION_BUY_RESURGENCE, homePlayerDC.player, cost)
                            rtDeal.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.ResurgenceAtOnce_1357, rtDeal.build())
                            return@whenCompleteKt
                        }

                        askResp == null -> {
                            resHelper.addResWithoutNotice(session, ACTION_BUY_RESURGENCE, homePlayerDC.player, cost)
                            rtDeal.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.ResurgenceAtOnce_1357, rtDeal.build())
                            return@whenCompleteKt
                        }

                        else -> {
                            if (askResp.resurgenceAskRt.rt != ResultCode.SUCCESS.code) {
                                // 失败了就把资源加回去
                                rtDeal.rt = askResp.resurgenceAskRt.rt
                                resHelper.addResWithoutNotice(session, ACTION_BUY_RESURGENCE, homePlayerDC.player, cost)
                                session.sendMsg(MsgType.ResurgenceAtOnce_1357, rtDeal.build())
                            } else {
                                rtDeal.rt = askResp.resurgenceAskRt.rt
                                costResWithoutNoticeResult.noticeCostRes(session, homePlayerDC.player)
                                session.sendMsg(MsgType.ResurgenceAtOnce_1357, rtDeal.build())
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("ResurgenceAskReq Error!", e)
                    rtDeal.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.ResurgenceAtOnce_1357, rtDeal.build())
                }
            }

        return null
    }
}