package com.point18.slg2d.home.module.prison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_SET_MAX_LV_PRISON_BUFF
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GainMaxPrisonLvBuffRt
import pb4server.Home2WorldAskResp
import pb4server.MaxPrisonBuffAskReq
import java.util.*

class GainMaxPrisonBuffDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            val rt = gainPrisonBuff(session, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.GainMaxPrisonLvBuff_1360, rt)
            }
        }
    }

    private fun gainPrisonBuff(session: PlayerActor, homePlayerDC: HomePlayerDC): GainMaxPrisonLvBuffRt? {
        val dealRt = GainMaxPrisonLvBuffRt.newBuilder()
        dealRt.rt = ResultCode.SUCCESS.code

        val cost = LinkedList<ResVo>()
        cost += pcs.basicProtoCache.fakeLordCost
        val checkResRt = resHelper.checkRes(session, cost)
        if (!checkResRt) {
            dealRt.rt = (ResultCode.LESS_RESOUCE.code)
            return dealRt.build()
        }
        val costNotice = resHelper.costResWithoutNotice(
            session, ACTION_SET_MAX_LV_PRISON_BUFF, homePlayerDC.player, cost
        )

        val askMsg = MaxPrisonBuffAskReq.newBuilder()
        session.createACS<Home2WorldAskResp>()
            .ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setMaxPrisonBuffAskReq(askMsg) },
                Home2WorldAskResp::class.java
            )
            .whenCompleteKt { askResp, askErr ->

                try {
                    when {
                        askErr != null -> {
                            resHelper.addResWithoutNotice(
                                session,
                                ACTION_SET_MAX_LV_PRISON_BUFF,
                                homePlayerDC.player,
                                cost
                            )
                            dealRt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.GainMaxPrisonLvBuff_1360, dealRt.build())
                            return@whenCompleteKt
                        }

                        askResp == null -> {
                            resHelper.addResWithoutNotice(
                                session,
                                ACTION_SET_MAX_LV_PRISON_BUFF,
                                homePlayerDC.player,
                                cost
                            )
                            dealRt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.GainMaxPrisonLvBuff_1360, dealRt.build())
                            return@whenCompleteKt
                        }

                        else -> {
                            if (askResp.maxPrisonBuffAskRt.rt != ResultCode.SUCCESS.code) {
                                resHelper.addResWithoutNotice(
                                    session,
                                    ACTION_SET_MAX_LV_PRISON_BUFF,
                                    homePlayerDC.player,
                                    cost
                                )
                                dealRt.rt = askResp.maxPrisonBuffAskRt.rt
                                session.sendMsg(MsgType.GainMaxPrisonLvBuff_1360, dealRt.build())
                            } else {
                                costNotice.noticeCostRes(session, homePlayerDC.player)
                                dealRt.rt = askResp.maxPrisonBuffAskRt.rt
                                session.sendMsg(MsgType.GainMaxPrisonLvBuff_1360, dealRt.build())
                            }
                            return@whenCompleteKt
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("MaxPrisonBuffAskReq Error!", e)
                    dealRt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.GainMaxPrisonLvBuff_1360, dealRt.build())
                }
            }

        return null
    }

}