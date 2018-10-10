package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.ClearWonderAwardRecordAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class ClearWonderAwardRecordOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val rt = ClearWonderAwardRecordAskRt.newBuilder()

        val alliance = publicCache.allianceCache.findAllianceById(req.publicId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setClearWonderAwardRecordAskRt(rt)
            return
        }
        alliance.wonderAwardMap = mutableMapOf()
        resp.setClearWonderAwardRecordAskRt(rt)
        return
    }
}