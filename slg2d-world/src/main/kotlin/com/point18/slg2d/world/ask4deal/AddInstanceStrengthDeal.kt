package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.addStrength
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.AddInstanceStrengthAskReq
import pb4server.AddInstanceStrengthAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class AddInstanceStrengthDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.addInstanceStrengthAskReq
        val rt = dealAddInstanceStrength(areaCache, req.playerId, askMsg)
        resp.setAddInstanceStrengthAskRt(rt)
    }

    private fun dealAddInstanceStrength(
        areaCache: AreaCache,
        playerId: Long,
        askMsg: AddInstanceStrengthAskReq
    ): AddInstanceStrengthAskRt.Builder {
        val rt = AddInstanceStrengthAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            return rt
        }
        rt.rt = addStrength(areaCache, player, askMsg.addValue).code

        return rt
    }
}