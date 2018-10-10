package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HAsk
import com.point18.slg2d.home.module.heartDealsAtHome
import pb4server.TriggerHeartAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp
import xyz.ariane.util.lzWarn

class TriggerHeartDeal : W2HAsk, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val msg = req.triggerHeartAskReq

        prepare(session) { _ ->
            val rt = TriggerHeartAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val deal = heartDealsAtHome[msg.action]
            if (deal == null) {
                normalLog.lzWarn { "找不到心跳:{${msg.action}}对应的处理" }
                rt.rt = ResultCode.PARAMETER_ERROR.code
                resp.setTriggerHeartAskRt(rt)
                return@prepare
            }

            // 注意，内部必须是同步执行，所以任何require都是不允许的！
            deal.dealHeart(session, msg.actionId) { rtCode ->
                rt.rt = rtCode
                resp.setTriggerHeartAskRt(rt)
            }
            return@prepare
        }
    }
}