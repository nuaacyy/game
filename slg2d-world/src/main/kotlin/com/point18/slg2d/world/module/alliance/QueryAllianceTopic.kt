package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceQueryTopicRt
import com.point18.slg2d.common.resultcode.ResultCode

// 联盟邮件主题列表 890
class DealAllianceQueryTopic : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryTopic(session)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceQueryTopic_890, rt)
        }
    }

    fun queryTopic(session: PlayerSession): (AllianceQueryTopicRt?) {
        val rt = AllianceQueryTopicRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        com.point18.slg2d.world.common.allianceQueryTopic(session, player.allianceId)

        return null
    }
}


