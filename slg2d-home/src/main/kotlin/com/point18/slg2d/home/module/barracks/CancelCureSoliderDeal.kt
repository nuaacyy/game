package com.point18.slg2d.home.module.barracks

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.EventCure
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.CancelCureSolider
import pb4client.CancelCureSoliderRt
import pb4server.CancelCureSoliderAskReq
import pb4server.Home2WorldAskResp
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

// 取消治疗兵
class CancelCureSoliderDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val askMsg = CancelCureSoliderAskReq.newBuilder()
            val eventCure = (msg as CancelCureSolider).eventCure

            askMsg.eventCure = eventCure
            session.createACS<Home2WorldAskResp>().ask(
                session.worldShardProxy,
                session.fillHome2WorldAskMsgHeader { it.setCancelCureSoliderAskReq(askMsg) },
                Home2WorldAskResp::class.java
            ).whenCompleteKt { askRt, err ->
                try {
                    when {
                        err != null -> {
                            normalLog.lzWarn { "请求world取消治疗兵失败:{$err}" }
                            val rt = CancelCureSoliderRt.newBuilder()
                            rt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.CancelCureSolider_1085, rt.build())
                            return@whenCompleteKt
                        }
                        askRt == null -> {
                            normalLog.lzWarn { "请求world取消治疗兵失败:{$err}" }
                            val rt = CancelCureSoliderRt.newBuilder()
                            rt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.CancelCureSolider_1085, rt.build())
                            return@whenCompleteKt
                        }
                        else -> {
                            if (askRt.cancelCureSoliderAskRt.rt != ResultCode.SUCCESS.code) {
                                normalLog.lzWarn { "请求world取消治疗兵失败:{$askRt.rt}" }
                            } else {
                                val allNeedCost = LinkedList<ResVo>()
                                for (each in askRt.cancelCureSoliderAskRt.cancelMapList) {
                                    val soliderProto = pcs.soliderCache.soliderProtoMap[each.soliderId]
                                    if (soliderProto == null) {
                                        continue
                                    }

                                    // 重新计算需要总资源量
                                    var cureCancelMap: List<ResVo>
                                    if (eventCure == EventCure) {
                                        cureCancelMap = soliderProto.cureCancleActivityMap
                                    } else {
                                        cureCancelMap = soliderProto.cureCancleMap
                                    }
                                    val (ok, newRes) = resVoAddX(cureCancelMap, each.soliderNum)
                                    if (!ok) {
                                        continue
                                    }
                                    allNeedCost += newRes
                                }

                                resHelper.addRes(
                                    session,
                                    com.point18.slg2d.common.constg.ACTION_CANCEL_MAKE_SOLIDER,
                                    homePlayerDC.player,
                                    allNeedCost
                                )
                            }
                            val rt = CancelCureSoliderRt.newBuilder()
                            rt.rt = askRt.cancelCureSoliderAskRt.rt
                            session.sendMsg(MsgType.CancelCureSolider_1085, rt.build())
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("CancelCureSoliderAskReq Error!", e)
                    val rt = CancelCureSoliderRt.newBuilder()
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.CancelCureSolider_1085, rt.build())
                }
            }
        }
    }
}