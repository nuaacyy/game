package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.FIELD_LENGTH_ALLIANCE_REPLY_MESSAGE
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import pb4client.AllianceTopicReply
import pb4client.AllianceTopicReplyRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.allianceTopicReply

// 对联盟邮件主题进行回复 892
class DealAllianceTopicReply : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val topicId = (msg as AllianceTopicReply).topicId
        val message = msg.message
        val rt = this.replyTopic(session, topicId, message)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceTopicReply_892, rt)
        }
    }

    fun replyTopic(session: PlayerSession, topicId: Long, message: String): (AllianceTopicReplyRt?) {
        val rt = AllianceTopicReplyRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.topicId = topicId

        val player = session.player

        // 回复消息长度验证
        val res = pcs.wordCache.check(message, pcs.basicProtoCache.allianceTopicMessageLength, WORD_CHECK_MESSAGE)
        when (res.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.KEYWORDS.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_REPLY_MESSAGE_LENGTH_EXCEED.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_REPLY_MESSAGE_LENGTH_EXCEED.code)
                return rt.build()
            }
        }

        if (message.length > FIELD_LENGTH_ALLIANCE_REPLY_MESSAGE) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        allianceTopicReply(session, player.allianceId, topicId, message)

        return null
    }
}

