package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_SEND_NOTICE_TO_LEADER
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.SendNoticeToLeaderOfAllianceRt
import pb4server.Home2WorldAskResp
import pb4server.SendNoticeToLeaderAskReq
import java.util.*
import java.util.Arrays.asList

class SendNoticeToLeaderOfAllianceDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(resHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = askSendNoticeToLeader(session, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.SendNoticeToLeaderOfAlliance_1460, rt)
            }
        }
    }

    private fun askSendNoticeToLeader(session: PlayerActor, homePlayerDC: HomePlayerDC): SendNoticeToLeaderOfAllianceRt? {
        val player = homePlayerDC.player
        val cost = LinkedList<ResVo>()
        cost += pcs.basicProtoCache.noticePrice
        val costResWithoutNoticeResult = resHelper.costResWithoutNotice(
            session,
            ACTION_SEND_NOTICE_TO_LEADER,
            player,
            cost
        )

        val askMsg = SendNoticeToLeaderAskReq.newBuilder()

        session.createACS<Home2WorldAskResp>().ask(
            session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setSendNoticeToLeaderAskReq(askMsg) },
            Home2WorldAskResp::class.java
        ).whenCompleteKt { rt, askErr ->
            try {
                when {
                    askErr != null -> {
                        val rtBuilder = SendNoticeToLeaderOfAllianceRt.newBuilder()
                        resHelper.addResWithoutNotice(session, ACTION_SEND_NOTICE_TO_LEADER, player, cost)
                        rtBuilder.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.SendNoticeToLeaderOfAlliance_1460, rtBuilder.build())
                    }
                    rt == null -> {
                        val rtBuilder = SendNoticeToLeaderOfAllianceRt.newBuilder()
                        resHelper.addResWithoutNotice(session, ACTION_SEND_NOTICE_TO_LEADER, player, cost)
                        rtBuilder.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.SendNoticeToLeaderOfAlliance_1460, rtBuilder.build())
                    }
                    else -> {
                        val rtBuilder = SendNoticeToLeaderOfAllianceRt.newBuilder()
                        rtBuilder.rt = ResultCode.SUCCESS.code
                        if (rt.sendNoticeToLeaderAskRt.rt != ResultCode.SUCCESS.code) {
                            resHelper.addResWithoutNotice(session, ACTION_SEND_NOTICE_TO_LEADER, player, cost)
                            rtBuilder.rt = rt.sendNoticeToLeaderAskRt.rt
                            session.sendMsg(MsgType.SendNoticeToLeaderOfAlliance_1460, rtBuilder.build())
                        } else {
                            costResWithoutNoticeResult.noticeCostRes(session, player)
                            rtBuilder.rt = rt.sendNoticeToLeaderAskRt.rt
                            session.sendMsg(MsgType.SendNoticeToLeaderOfAlliance_1460, rtBuilder.build())
                        }
                    }
                }

            } catch (e: Exception) {
                normalLog.error("SendNoticeToLeaderAskReq Error!", e)
                val rtBuilder = SendNoticeToLeaderOfAllianceRt.newBuilder()
                resHelper.addResWithoutNotice(session, ACTION_SEND_NOTICE_TO_LEADER, player, cost)
                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.SendNoticeToLeaderOfAlliance_1460, rtBuilder.build())
            }
        }
        return null
    }
}
