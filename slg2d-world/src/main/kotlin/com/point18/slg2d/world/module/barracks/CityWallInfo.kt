package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.CASTLE_FIRE
import pb4client.QueryWallInfoRt
import com.point18.slg2d.common.resultcode.ResultCode

class QueryWallInfo : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = queryWallInfo(session)
        session.sendMsg(MsgType.QueryWallInfo_1540, rt)
    }

    private fun queryWallInfo(session: PlayerSession): QueryWallInfoRt {
        val dealRt = QueryWallInfoRt.newBuilder()
        dealRt.rt = ResultCode.SUCCESS.code
        dealRt.fireEndTime = 0

        val player = session.player

        val castles = session.areaCache.castleCache.findCastleFromPlayerId(session.playerId)
        if (castles.size != 1) {
            dealRt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            return dealRt.build()
        }
        val castle = castles[0]

        if (castle.castleState == CASTLE_FIRE) {
            dealRt.fireEndTime = (castle.castleStatusEndTime / 1000).toInt()
        }
        return dealRt.build()
    }
}