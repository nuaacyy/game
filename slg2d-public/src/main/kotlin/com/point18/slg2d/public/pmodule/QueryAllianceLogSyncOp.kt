package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class QueryAllianceLogSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.queryAllianceLogAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setQueryAllianceLogAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: QueryAllianceLogAskReq
    ): QueryAllianceLogAskRt.Builder {

        val rt = newQueryAllianceLogAskRtBuilder()

        // 获取所有日志记录
        val aLogs = publicCache.allianceLogCache.findLogsByAllianceId(allianceId)
        for ((_, aLog) in aLogs) {
            val args: LinkedList<String> = toObj(aLog.logInfo)
            val logBuilder = AllianceQueryLogInfo.newBuilder()
            logBuilder.dt = (aLog.logTime / 1000).toInt()
            logBuilder.typ = aLog.logType
            logBuilder.addAllLgs(args)
            rt.addL(logBuilder)
        }

        return rt
    }

    fun newQueryAllianceLogAskRtBuilder(): QueryAllianceLogAskRt.Builder {
        val rt = QueryAllianceLogAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}