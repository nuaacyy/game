package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceTopicSign
import pb4client.AllianceTopicSignRt
import com.point18.slg2d.common.resultcode.ResultCode

// 收藏/取消收藏联盟主题
class DealAllianceTopicSign : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val topicId = (msg as AllianceTopicSign).topicId
        val rt = this.allianceTopicSign(session, topicId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceTopicSign_917, rt)
        }
    }

    fun allianceTopicSign(session: PlayerSession, topicId: Long): (AllianceTopicSignRt?) {
        val rt = AllianceTopicSignRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.topicId = topicId
        val player = session.player

        com.point18.slg2d.world.common.allianceTopicSign(session, player.allianceId, topicId)

        return null
    }
}


