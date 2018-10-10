package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.UseSoliderAddAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class UseSoliderAddDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.useSoliderAddAskReq
        val playerId = req.playerId

        askMsg.solidersAddList.forEach {
            com.point18.slg2d.world.common.addSoliderByUseProps(areaCache, playerId, it.soliderId, askMsg.useNum * it.soliderNum)
        }

        val rt = UseSoliderAddAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        resp.setUseSoliderAddAskRt(rt)
    }
}