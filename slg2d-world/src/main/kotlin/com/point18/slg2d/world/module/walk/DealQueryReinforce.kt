package com.point18.slg2d.world.module.walk

import com.point18.slg2d.world.area4data.*
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.common.getResearchEffectValue
import pb4client.QueryReinfore
import pb4client.QueryReinforeRt
import com.point18.slg2d.common.resultcode.ResultCode

// 查询增援信息
class QueryReinforceDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryReinforce(session, msg as QueryReinfore)
        session.sendMsg(MsgType.QueryReinforce_1256, rt.build())
    }

    private fun queryReinforce(session: PlayerSession, queryMsg: QueryReinfore): QueryReinforeRt.Builder {
        val rtBuilder = QueryReinforeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.canReinforceNum = 0
        rtBuilder.nowReinforceNum = 0

        val areaCache = session.areaCache
        val targetPlayerId = queryMsg.playerId
        val targetPlayer = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (targetPlayer == null) {
            normalLog.lzWarn { "找不到对方玩家数据:$targetPlayerId" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        val canReinforceNum =
            getResearchEffectValue(areaCache, NO_FILTER, targetPlayer, ReinforceTroopsMaxAdd)
        rtBuilder.canReinforceNum = canReinforceNum
        val castle = areaCache.castleCache.findCastleById(targetPlayer.focusCastleId)
        if (castle == null) {
            normalLog.lzWarn { "找不到玩家城数据:${targetPlayer.focusCastleId}" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        val groups =
            areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(castle.x, castle.y, Running or Reinforce)
        var nowReinforceNum = 0
        for (group in groups) {
            if (group.runningState == Reinforce) {
                //增援部队已到
                nowReinforceNum += areaCache.walkForceGroupCache.getSoliderNumInGroup(group.id)
                continue
            }
            val walk = areaCache.walkCache.findWalkByGroupId(group.id) ?: continue

            if (walk.marchState != WalkReinforce) {
                continue
            }
            nowReinforceNum += areaCache.walkForceGroupCache.getSoliderNumInGroup(group.id)
        }
        rtBuilder.nowReinforceNum = nowReinforceNum
        return rtBuilder
    }
}


