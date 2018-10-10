package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.boss.BossM
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.CallBossAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp

class CallBossDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.callBossAskReq
        val playerId = req.playerId

        val result = BossM.dealCallBoss(areaCache, playerId, askMsg.extend1, askMsg.x, askMsg.y)

        val rt = CallBossAskRt.newBuilder()
        rt.rt = result.code

        resp.setCallBossAskRt(rt)
    }
}