package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.world.common.addDecree
import pb4server.AddDecreeAskReq
import pb4server.AddDecreeAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class AddDecreeDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.addDecreeAskReq
        val rt = dealAddDecree(areaCache, req.playerId, askMsg)
        resp.setAddDecreeAskRt(rt)
    }

    private fun dealAddDecree(areaCache: AreaCache, playerId: Long, askMsg: AddDecreeAskReq): AddDecreeAskRt.Builder {
        val rt = AddDecreeAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        rt.rt = addDecree(areaCache, player, askMsg.addNum, intToBool(askMsg.useProp)).code

        return rt
    }

}