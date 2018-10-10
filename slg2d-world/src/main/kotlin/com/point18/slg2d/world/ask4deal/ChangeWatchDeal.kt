package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.World2WorldAskDealBase
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class ChangeWatchDeal : World2WorldAskDealBase() {
    override fun dealWorldAsk(areaCache: AreaCache, req: World2WorldAskReq, resp: World2WorldAskResp.Builder) {
        val rtBuilder = ChangeWatchAskResp.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        resp.setChangeWatchAskResp(rtBuilder)
    }
}