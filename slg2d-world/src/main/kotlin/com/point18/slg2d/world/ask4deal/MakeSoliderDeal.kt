package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.isSolider
import com.point18.slg2d.common.constg.isTrap
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.module.barracks.dealMakeSolider
import com.point18.slg2d.world.module.barracks.dealMakeTrap
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.MakeSoliderAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class MakeSoliderDeal :  Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg =req.makeSoliderAskReq
        val soliderId = askMsg.soliderId
        val makeType = askMsg.makeType
        val makeNum = askMsg.makeNum
        val effectMap = askMsg.effectMapList
        val playerId = req.playerId

        val rt = MakeSoliderAskRt.newBuilder()

        val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
        if (soliderProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            resp.setMakeSoliderAskRt(rt)
            return
        }

        if (isSolider(soliderProto.armyType)) {
            val tmpMap = hashMapOf<Int,Int>()
            for (each in effectMap){
                tmpMap[each.effectId] = each.effectValue
            }
            rt.rt = dealMakeSolider(areaCache, playerId, soliderId, makeType, makeNum, tmpMap)
            resp.setMakeSoliderAskRt(rt)
            return
        }
        if (isTrap(soliderProto.armyType)) {
            val tmpMap = hashMapOf<Int,Int>()
            for (each in effectMap){
                tmpMap[each.effectId] = each.effectValue
            }
            rt.rt = dealMakeTrap(areaCache, playerId, soliderId, makeType, makeNum, tmpMap)
            resp.setMakeSoliderAskRt(rt)
            return
        }

        rt.rt = ResultCode.SUCCESS.code
        resp.setMakeSoliderAskRt(rt)
        return
    }
}