package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.barracks.cancelCureSolider
import com.point18.slg2d.common.constg.EventCure
import com.point18.slg2d.world.common.isWonderWar
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.CancelCureSoliderAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.SoliderVo
import com.point18.slg2d.common.resultcode.ResultCode

class CancelCureSoliderDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.cancelCureSoliderAskReq
        val playerId = req.playerId
        val eventCure = askMsg.eventCure

        val rt = CancelCureSoliderAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (eventCure == EventCure && !isWonderWar(areaCache)) { // 非奇迹争夺战没有活动伤兵营
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setCancelCureSoliderAskRt(rt)
            return
        }

        val rst = cancelCureSolider(areaCache, playerId, eventCure)
        rt.rt = rst.rt
        for (each in rst.cancelMap){
            val tmp = SoliderVo.newBuilder()
            tmp.soliderId = each.key
            tmp.soliderNum = each.value
            rt.addCancelMap(tmp)
        }
        resp.setCancelCureSoliderAskRt(rt)
        return
    }
}