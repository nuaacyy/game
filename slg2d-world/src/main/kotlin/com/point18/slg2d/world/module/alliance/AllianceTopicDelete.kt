package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceTopicDelete
import pb4client.AllianceTopicDeleteRt
import com.point18.slg2d.common.resultcode.ResultCode

// 联盟邮件主题删除 894
class DealAllianceTopicDelete : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val topicId = (msg as AllianceTopicDelete).topicId
        val rt = this.deleteTopic(session, topicId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceTopicDelete_894, rt)
        }
    }

    private fun deleteTopic(session: PlayerSession, topicId: Long): AllianceTopicDeleteRt? {
        val rt = AllianceTopicDeleteRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player
        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.allianceTopicDelete(session, player.allianceId, topicId)

        return null

    }
}



