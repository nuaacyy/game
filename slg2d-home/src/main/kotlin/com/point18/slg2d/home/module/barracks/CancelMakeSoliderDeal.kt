package com.point18.slg2d.home.module.barracks

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ACTION_CANCEL_MAKE_SOLIDER
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.CancelMakeSolider
import pb4client.CancelMakeSoliderRt
import pb4server.CancelMakeSoliderAskReq
import pb4server.Home2WorldAskResp
import xyz.ariane.util.lzWarn
import java.util.Arrays.asList

// 取消造兵
class CancelMakeSoliderDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val soliderId = (msg as CancelMakeSolider).soliderId

            val rt = CancelMakeSoliderRt.newBuilder()
            val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
            if (soliderProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                session.sendMsg(MsgType.CancelMakeSolider_1082, rt.build())
                return@prepare
            }
            
            val askMsg = CancelMakeSoliderAskReq.newBuilder()
            askMsg.soliderId = soliderId
            session.createACS<Home2WorldAskResp>().ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setCancelMakeSoliderAskReq(askMsg) },
                Home2WorldAskResp::class.java
            ).whenCompleteKt { askRt, err ->

                try {
                    when {
                        err != null -> {
                            normalLog.lzWarn { "请求world取消造兵失败:{$err}" }
                            val rt = CancelMakeSoliderRt.newBuilder()
                            rt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.CancelMakeSolider_1082, rt.build())
                            return@whenCompleteKt
                        }
                        askRt == null -> {
                            val rt = CancelMakeSoliderRt.newBuilder()
                            rt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.CancelMakeSolider_1082, rt.build())
                            return@whenCompleteKt
                        }
                        else -> {
                            if (askRt.cancelMakeSoliderAskRt.rt != ResultCode.SUCCESS.code) {
                                normalLog.lzWarn { "请求world取消造兵失败:{$askRt.rt}" }
                            } else {
                                val (_, newRes)
                                    = resVoAddX(
                                    soliderProto.trainCancleMap,
                                    askRt.cancelMakeSoliderAskRt.cancelNum
                                )
                                resHelper.addRes(
                                    session,
                                    ACTION_CANCEL_MAKE_SOLIDER,
                                    homePlayerDC.player,
                                    newRes
                                )
                            }
                            rt.rt = askRt.cancelMakeSoliderAskRt.rt
                            session.sendMsg(MsgType.CancelMakeSolider_1082, rt.build())
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("CancelMakeSoliderAskReq Error!", e)
                    val rt = CancelMakeSoliderRt.newBuilder()
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.CancelMakeSolider_1082, rt.build())
                    return@whenCompleteKt
                }
            }
        }
    }
}
