package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.PublicCache
import pb4client.OccupyWonder
import pb4server.QueryOccupyWonderAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp

// todo jh 没用到
class QueryOccupyWonderSyncOp : World2PublicAskDealBase() {
    
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val rt = QueryOccupyWonderAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val alliance = publicCache.allianceCache.findAllianceById(req.publicId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setQueryOccupyWonderAskRt(rt)
            return
        }

        for ((worldId, wonderMap) in alliance.allianceOccupyInfo) {
            val occupyInfo = OccupyWonder.newBuilder()
            occupyInfo.worldId = worldId

            for ((wonderProtoId, _) in wonderMap) {
                occupyInfo.addWonderIds(wonderProtoId)
            }

            rt.addOccupyWonderInfo(occupyInfo)
        }

        resp.setQueryOccupyWonderAskRt(rt)
        return
    }
}