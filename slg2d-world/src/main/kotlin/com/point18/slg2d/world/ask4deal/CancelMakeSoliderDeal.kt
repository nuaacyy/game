package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.barracks.cancelMakeSolider
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.CancelMakeSoliderAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp

class CancelMakeSoliderDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder)  {
        val askMsg =req.cancelMakeSoliderAskReq
        val soliderId = askMsg.soliderId
        val playerId = req.playerId
        val rt = CancelMakeSoliderAskRt.newBuilder()
        val rst = cancelMakeSolider(areaCache, playerId, soliderId)
        rt.rt = rst.rt
        rt.cancelNum = rst.cancelNum
        resp.setCancelMakeSoliderAskRt(rt)
        return
    }
}