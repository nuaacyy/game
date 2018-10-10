package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.queryOccupyWonder
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.QueryOccupyWonderRt

//查询占领的奇观
class DealQueryOccupyWonder : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryWonder(session)
        if (rt == null) {
            return
        }
        session.sendMsg(MsgType.QueryOccupyWonder_918, rt)
    }

    private fun queryWonder(session: PlayerSession): QueryOccupyWonderRt? {
        val rt = QueryOccupyWonderRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }
        queryOccupyWonder(session, player.allianceId)
        return null
    }
}




