package com.point18.slg2d.home.module.giftBag

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.QuotaBagDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.QueryQuotaBagRt
import pb4client.QuotaBagInfo

class QueryQuotaBagInfoDeal : HomeClientMsgDeal, HomeHelperPlus1<QuotaBagDC>(QuotaBagDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { quotaBagDC: QuotaBagDC ->
            val rt = deal(quotaBagDC)
            session.sendMsg(MsgType.QueryQuotaBagInfo_1584, rt.build())
        }
    }

    private fun deal(quotaBagDC: QuotaBagDC): QueryQuotaBagRt.Builder {
        val rt = QueryQuotaBagRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val quotaBagInfo = QuotaBagInfo.newBuilder()

        val quotaBag = quotaBagDC.quotaBag
        // 是否有触发礼包或者触发的礼包是否过期
        if (quotaBag.quotaBagId == 0 || quotaBag.isOverdue()) {
            rt.quotaBagInfo = quotaBagInfo.setQuotaBagId(0).build()
            return rt
        }

        quotaBagInfo.quotaBagId = quotaBag.quotaBagId
        quotaBagInfo.endTime = quotaBag.endTime
        quotaBagInfo.rewardId = quotaBag.rewardId
        quotaBagInfo.degree = quotaBag.degree
        rt.quotaBagInfo = quotaBagInfo.build()
        return rt
    }
}