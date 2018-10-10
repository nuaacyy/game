package com.point18.slg2d.world.module.wonder

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.QueryCountryNoticeRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

class QueryCountryNoticeDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rtBuilder = this.queryNotice(cache)
        val scMsg =
            ScMessageAtSend(MsgType.QueryCountryNotice_1462, cache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun queryNotice(areaCache: AreaCache): QueryCountryNoticeRt.Builder {
        val rtBuilder = QueryCountryNoticeRt.newBuilder()

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        rtBuilder.notice = wonder.notice
        rtBuilder.noticeCdTime = (wonder.lastNoticeTime / 1000).toInt() + pcs.basicProtoCache.noticeCd
        rtBuilder.rt = ResultCode.SUCCESS.code
        return rtBuilder
    }
}

