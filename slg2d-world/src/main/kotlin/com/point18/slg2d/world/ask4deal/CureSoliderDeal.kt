package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.module.barracks.cureSolider
import com.point18.slg2d.common.constg.EventCure
import com.point18.slg2d.world.common.isWonderWar
import pb4server.CureSoliderAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class CureSoliderDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.cureSoliderAskReq
        val playerId = req.playerId
        val cureType = askMsg.cureType
        val trapOrSolider = askMsg.trapOrSolider
        val cureMap = hashMapOf<Int, Int>()
        askMsg.cureMapList.forEach {
            cureMap[it.soliderId] = it.soliderNum
        }
        val effectMap = hashMapOf<Int, Int>()
        askMsg.effectMapList.forEach {
            effectMap[it.effectId] = it.effectValue
        }
        val eventCure = askMsg.eventCure

        val rt = CureSoliderAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (eventCure == EventCure && !isWonderWar(areaCache)) { // 非奇迹争夺战没有活动伤兵营
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setCureSoliderAskRt(rt)
            return
        }

        rt.rt = cureSolider(areaCache, playerId, cureType, trapOrSolider, cureMap, effectMap, eventCure)
        resp.setCureSoliderAskRt(rt)
    }
}