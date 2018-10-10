package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Reinforce
import com.point18.slg2d.common.constg.Running
import com.point18.slg2d.common.constg.Stationed
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.addReinforce
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.QueryDetailReinforce
import pb4client.QueryDetailReinforceRt
import xyz.ariane.util.lzWarn

// 查询详细的增援
class QueryDetailReinforceDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryDetailReinforce(session, msg as QueryDetailReinforce)
        session.sendMsg(MsgType.QueryDetailReinforce_1257, rt.build())
    }

    private fun queryDetailReinforce(
            session: PlayerSession,
            queryMsg: QueryDetailReinforce
    ): QueryDetailReinforceRt.Builder {
        val rtBuilder = QueryDetailReinforceRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val targetPlayerId = queryMsg.playerId
        if (targetPlayerId != playerId) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val targetPlayer = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (targetPlayer == null) {
            normalLog.lzWarn { "找不到对方玩家数据:$targetPlayerId" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        val castle = areaCache.castleCache.findCastleById(targetPlayer.focusCastleId)
        if (castle == null) {
            normalLog.lzWarn { "找不到玩家城数据:${targetPlayer.focusCastleId}" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        addReinforce(areaCache, castle.x, castle.y, Running or Reinforce)
                .forEach { rtBuilder.addForces(it) }
        addReinforce(areaCache, castle.x, castle.y, Running or Stationed)
                .forEach { rtBuilder.addStationedForces(it) }
        return rtBuilder
    }
}



