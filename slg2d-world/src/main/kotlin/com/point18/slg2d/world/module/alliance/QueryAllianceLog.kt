//查询联盟日志
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceQueryLogRt
import com.point18.slg2d.common.resultcode.ResultCode

//查询联盟日志 820
class DealQueryAllianceLog : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryLog(session)

        if (rt != null) {
            session.sendMsg(MsgType.AllianceQueryLog_820, rt)
        }

    }

    fun queryLog(session: PlayerSession): (AllianceQueryLogRt?) {
        val rt = AllianceQueryLogRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.queryAllianceLog(session, player.allianceId)

        return null
    }
}



