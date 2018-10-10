package com.point18.slg2d.world.module.walk

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.QueryAllianceMemberPos
import pb4client.QueryAllianceMemberPosRt
import com.point18.slg2d.common.resultcode.ResultCode

// 查询公会成员的城池位置
class QueryAllianceMemberPosDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryPos(session, msg as QueryAllianceMemberPos)

        session.sendMsg(MsgType.QueryAllianceMemberPos_1258, rt.build())
    }

    private fun queryPos(session: PlayerSession, queryMsg: QueryAllianceMemberPos): QueryAllianceMemberPosRt.Builder {
        val rtBuilder = QueryAllianceMemberPosRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val targetPlayerId = queryMsg.targetPlayerId
        val selfPlayer = session.player

        val targetPlayer = areaCache.playerCache.findPlayerById(targetPlayerId)
        if (targetPlayer == null) {
            // 找不到目标玩家
            rtBuilder.rt = ResultCode.NotInSameMap.code
            return rtBuilder
        }

        if (selfPlayer.allianceId != targetPlayer.allianceId) {
            // 不是一个公会的
            rtBuilder.rt = ResultCode.NOT_IN_SAME_ALLIANCE.code
            return rtBuilder
        }

        val targetCastle = areaCache.castleCache.findCastleById(targetPlayer.focusCastleId)
        if (targetCastle == null) {
            // 找不到城池
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        rtBuilder.posX = targetCastle.x
        rtBuilder.posY = targetCastle.y
        return rtBuilder
    }
}


