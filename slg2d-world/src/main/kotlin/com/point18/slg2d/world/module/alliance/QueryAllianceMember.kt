//查询联盟概要信息
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.common.queryAllianceMember
import pb4client.AllianceQueryPlayer
import pb4client.AllianceQueryPlayerRt
import com.point18.slg2d.common.resultcode.ResultCode

//查询联盟成员信息 816
class DealQueryAllianceMember : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val allianceId = (msg as AllianceQueryPlayer).allianceId
        val rt = this.queryPlayer(session, allianceId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceQueryPlayer_816, rt)
        }
    }

    fun queryPlayer(session: PlayerSession, allianceId: Long): (AllianceQueryPlayerRt?) {
        val rt = AllianceQueryPlayerRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.allianceId = allianceId

        queryAllianceMember(session, allianceId)

        return null

    }
}



