package com.point18.slg2d.home.common

import com.point18.slg2d.home.actor.PlayerActor
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.DealHeartAction
import com.point18.slg2d.common.constg.HomeHeartAction
import pb4server.DealHeartAskReq
import pb4server.Home2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

/**
 * 转发心跳处理至world
 */
fun forwardHeartDeal2World(
    session: PlayerActor,
    dealType: DealHeartAction,
    action: HomeHeartAction,
    actionId: Long,
    triggerTime: Long,
    onComplete: ((rt: Int) -> Unit)? = null
) {
    val msg = DealHeartAskReq.newBuilder()
    msg.dealType = dealType
    msg.action = action
    msg.actionId = actionId
    msg.triggerTime = triggerTime
    session.createACS<Home2WorldAskResp>()
        .ask(session.worldShardProxy,
            session.fillHome2WorldAskMsgHeader { it.setDealHeartAskReq(msg) },
            Home2WorldAskResp::class.java)
        .whenCompleteKt { rt, askErr ->

            try {
                when {
                    askErr != null -> {
                        normalLog.lzWarn { "转发心跳错误:{$askErr}" }
                        return@whenCompleteKt
                    }
                    rt == null -> {
                        normalLog.lzWarn { "转发心跳错误:{$askErr}" }
                        return@whenCompleteKt
                    }
                    else -> {
                        if (onComplete == null) {
                            return@whenCompleteKt
                        }

                        if (rt.dealHeartAskRt.rt != ResultCode.SUCCESS.code) {
                            normalLog.lzWarn { "转发心跳失败:{${rt.dealHeartAskRt.rt}}" }
                        }

                        onComplete(rt.dealHeartAskRt.rt)
                    }
                }

            } catch (e: Exception) {
                normalLog.lzWarn { "转发心跳失败捕获到异常:{$e}" }
            }
        }
}