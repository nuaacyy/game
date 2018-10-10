package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceTopicGetReply
import pb4client.AllianceTopicGetReplyRt
import com.point18.slg2d.common.resultcode.ResultCode

// 玩家滚动回复列表时: 请求历史回复内容 893
class DealAllianceTopicGetReply : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val topicId = (msg as AllianceTopicGetReply).topicId
        val lastId = msg.replyId
        val rt = this.getReply(session, topicId, lastId)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceTopicGetReply_893, rt)
        }
    }

    fun getReply(session: PlayerSession, topicId: Long, lastId: Long): (AllianceTopicGetReplyRt?) {
        val rt = AllianceTopicGetReplyRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        com.point18.slg2d.world.common.allianceTopicGetReply(session, player.allianceId, topicId, lastId)

        return null
    }

}


