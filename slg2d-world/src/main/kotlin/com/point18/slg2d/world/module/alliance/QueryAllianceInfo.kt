//查询联盟概要信息
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.RELATION_SHIP_NEUTRAL
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceQueryInfo
import pb4client.AllianceQueryInfoRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.queryAllianceInfo

//查询联盟概要信息 809
class DealQueryAllianceInfo : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val allianceId = (msg as AllianceQueryInfo).allianceId
        val rt = this.queryInfo(session, allianceId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceQueryInfo_809, rt)
        }
    }

    fun queryInfo(session: PlayerSession, allianceId: Long): (AllianceQueryInfoRt?) {
        val rt = AllianceQueryInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.relationShipId = RELATION_SHIP_NEUTRAL

        val player = session.player

        queryAllianceInfo(session, allianceId)

        return null
    }
}



