package com.point18.slg2d.home.module.prison

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_GO_RANSOM
import com.point18.slg2d.common.constg.NOT_PROPS_SUB_TYPE
import com.point18.slg2d.common.constg.RES_COIN
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GoRansom
import pb4client.GoRansomRt
import pb4server.GiveRansomAskReq
import pb4server.Home2WorldAskResp
import java.util.*

class GiveRansomDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            val num = (msg as GoRansom).num
            val rt = giveRansom(session, num, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.GoRansom_1356, rt)
            }
        }
    }

    private fun giveRansom(session: PlayerActor, num: Long, homePlayerDC: HomePlayerDC): GoRansomRt? {
        val rt = GoRansomRt.newBuilder()
        if (num == 0L) {
            rt.rt = (ResultCode.NO_SET_RANSOM.code)
            return rt.build()
        }

        // 检测资源
        val costs = ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, num)
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }
        val costNotice = resHelper.costResWithoutNotice(
            session, ACTION_GO_RANSOM, homePlayerDC.player, costs
        )

        val askMsg = GiveRansomAskReq.newBuilder()
        askMsg.num = num

        session.createACS<Home2WorldAskResp>()
            .ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setGiveRansomAskReq(askMsg) },
                Home2WorldAskResp::class.java
            )
            .whenCompleteKt { askResp, askErr ->

                try {
                    when {
                        askErr != null -> {
                            resHelper.addResWithoutNotice(session, ACTION_GO_RANSOM, homePlayerDC.player, costs)
                            rt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.GoRansom_1356, rt.build())
                        }

                        askResp == null -> {
                            resHelper.addResWithoutNotice(session, ACTION_GO_RANSOM, homePlayerDC.player, costs)
                            rt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.GoRansom_1356, rt.build())
                        }

                        else -> {
                            if (askResp.giveRansomAskRt.rt != ResultCode.SUCCESS.code) {
                                resHelper.addResWithoutNotice(session, ACTION_GO_RANSOM, homePlayerDC.player, costs)
                                rt.rt = askResp.giveRansomAskRt.rt
                                session.sendMsg(MsgType.GoRansom_1356, rt.build())
                            } else {
                                costNotice.noticeCostRes(session, homePlayerDC.player)
                                rt.rt = askResp.giveRansomAskRt.rt
                                session.sendMsg(MsgType.GoRansom_1356, rt.build())
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("GiveRansomAskReq Error!", e)
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.GoRansom_1356, rt.build())
                }

            }
        return null
    }
}