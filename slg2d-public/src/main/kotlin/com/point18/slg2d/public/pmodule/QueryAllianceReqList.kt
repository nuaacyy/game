package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.findAllianceReqsByAllianceId
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class QueryAllianceReqListSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.queryAllianceReqListAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setQueryAllianceReqListAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: QueryAllianceReqListAskReq
    ): QueryAllianceReqListAskRt.Builder {
        val rt = newQueryAllianceReqListAskRtBuilder()

        // 获取待查询联盟ID
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        rt.canAddPower = alce.canAddPower
        rt.powerLimit = alce.powerLimit
        for (reqVo in findAllianceReqsByAllianceId(publicCache, allianceId)) {
            val p = AllianceQueryReqListInfoVo.newBuilder()
            p.id = reqVo.playerId
            p.name = reqVo.playerName
            p.photoProtoId = reqVo.playerPhoto
            p.fightValue = reqVo.playerFightValue
            rt.addPlayers(p)
        }
        return rt
    }

    fun newQueryAllianceReqListAskRtBuilder(): QueryAllianceReqListAskRt.Builder {
        val rt = QueryAllianceReqListAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.canAddPower = 0
        rt.powerLimit = 0
        return rt
    }
}