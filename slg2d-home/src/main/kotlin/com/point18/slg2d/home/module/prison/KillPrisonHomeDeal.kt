package com.point18.slg2d.home.module.prison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_KILL_PRISON
import com.point18.slg2d.common.constg.COST_COIN_TO_KILL_PRISON
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
import pb4client.KillPrison
import pb4client.KillPrisonRt
import pb4server.Home2WorldAskResp
import pb4server.KillPrisonAskReq
import java.util.*

class KillPrisonHomeDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
                val prisonPlayerId = (msg as KillPrison).prisonPlayerId
                val costOrNot = msg.costOrNot
                val rt = killPrison(session, prisonPlayerId, costOrNot, homePlayerDC)
                if (rt != null) {
                    session.sendMsg(MsgType.KillPrison_1352, rt)
                }
            }
    }

    private fun killPrison(session: PlayerActor, prisonPlayerId: Long, costOrNot: Int, homePlayerDC: HomePlayerDC): KillPrisonRt? {
        val rtDeal = KillPrisonRt.newBuilder()
        rtDeal.rt = ResultCode.SUCCESS.code
        rtDeal.prisonPlayerId = prisonPlayerId

        val cost = LinkedList<ResVo>()
        // 花费道具杀领主
        if (costOrNot == COST_COIN_TO_KILL_PRISON) {
            cost += pcs.basicProtoCache.fastKill
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
        }
        val action = ACTION_KILL_PRISON
        val costResWithoutNoticeResult =
            resHelper.costResWithoutNotice(session, action, homePlayerDC.player, cost)

        val askMsg = KillPrisonAskReq.newBuilder()
        askMsg.costOrNot = costOrNot
        askMsg.prisonPlayerId = prisonPlayerId
        session.createACS<Home2WorldAskResp>()
            .ask(session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setKillPrisonAskReq(askMsg) },
                Home2WorldAskResp::class.java)
            .whenCompleteKt { askResp, askErr ->

                try {
                    when {
                        askErr != null -> {
                            resHelper.addResWithoutNotice(session, ACTION_KILL_PRISON, homePlayerDC.player, cost)
                            rtDeal.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.KillPrison_1352, rtDeal.build())
                            return@whenCompleteKt
                        }

                        askResp == null -> {
                            resHelper.addResWithoutNotice(session, ACTION_KILL_PRISON, homePlayerDC.player, cost)
                            rtDeal.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.KillPrison_1352, rtDeal.build())
                            return@whenCompleteKt
                        }

                        else -> {
                            if (askResp.killPrisonAskRt.rt != ResultCode.SUCCESS.code) {
                                // 失败了就把资源加回去
                                rtDeal.rt = askResp.killPrisonAskRt.rt
                                resHelper.addResWithoutNotice(session, ACTION_KILL_PRISON, homePlayerDC.player, cost)
                                session.sendMsg(MsgType.KillPrison_1352, rtDeal.build())
                            } else {
                                rtDeal.rt = askResp.killPrisonAskRt.rt
                                costResWithoutNoticeResult.noticeCostRes(session, homePlayerDC.player)
                                session.sendMsg(MsgType.KillPrison_1352, rtDeal.build())
                            }
                            return@whenCompleteKt
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("KillPrisonAskReq Error!", e)
                    rtDeal.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.KillPrison_1352, rtDeal.build())
                }
            }

        return null
    }
}