package com.point18.slg2d.world.module.fog

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NotWin
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.BattleSolider
import pb4client.QueryFogArmy
import pb4client.QueryFogArmyRt
import com.point18.slg2d.common.resultcode.ResultCode

class QueryFogArmyDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        msg as QueryFogArmy
        val rt = queryFogArmy(session.areaCache, session.playerId, msg.fogId)
        session.sendMsg(MsgType.QueryFogArmy_1575, rt.build())
    }

    private fun queryFogArmy(areaCache: AreaCache, playerId: Long, fogId: Int): QueryFogArmyRt.Builder {
        val rtBuilder = QueryFogArmyRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        val fog = areaCache.fogCache.findFogById(playerId, fogId)
        if (fog == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        if (fog.state != NotWin) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        for ((soldierId, soliderNum) in fog.soliderMap) {
            val soliderBuilder = BattleSolider.newBuilder()
            soliderBuilder.protoId = soldierId
            soliderBuilder.num = soliderNum
            rtBuilder.addSoldiers(soliderBuilder)
        }
        return rtBuilder
    }
}