package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.GmOverAllianceActivityAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class GmOverAllianceActivityOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val rt = dealMsg(publicCache)

        resp.setGmOverAllianceActivityAskRt(rt)
    }

    fun dealMsg(publicCache: PublicCache): GmOverAllianceActivityAskRt.Builder {
        val rtBuilder = GmOverAllianceActivityAskRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val timeOverActivity = publicCache.allianceActivityCache.findAllAllianceActivityForGm()

        for (activity in timeOverActivity) {
            dealTimeOverActivity(publicCache, activity)
        }

        return rtBuilder
    }

}
