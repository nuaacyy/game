package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import pb4client.AlliancePublishTopic
import pb4client.AlliancePublishTopicRt
import com.point18.slg2d.common.resultcode.ResultCode

// 发布联盟邮件主题 891
class DealAlliancePublishTopic : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val t = (msg as AlliancePublishTopic).type
        val title = msg.title
        val message = msg.message
        val rt = this.publishTopic(session, t, title, message)
        if (rt != null) {
            session.sendMsg(MsgType.AlliancePublishTopic_891, rt)
        }
    }

    fun publishTopic(session: PlayerSession, t: Int, title: String, message: String): AlliancePublishTopicRt? {
        val rt = AlliancePublishTopicRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        // 标题验证
        val res1 = pcs.wordCache.check(title, pcs.basicProtoCache.allianceTopicTitleLength, WORD_CHECK_NAME)
        when (res1.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.CHECK_WORD_ERR.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_TOPIC_TITLE_LENGTH_EXCEED.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_TOPIC_TITLE_LENGTH_EXCEED.code)
                return rt.build()
            }

        }

        // 内容验证
        val res2 = pcs.wordCache.check(message, pcs.basicProtoCache.allianceTopicMessageLength, WORD_CHECK_MESSAGE)
        when (res2.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.CHECK_WORD_ERR.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_TOPIC_MESSAGE_LENGTH_EXCEED.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_TOPIC_TITLE_LENGTH_EXCEED.code)
                return rt.build()
            }

        }

        if (message.length > com.point18.slg2d.common.constg.FIELD_LENGTH_ALLIANCE_REPLY_MESSAGE) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.alliancePublishTopic(session, player.allianceId, t, title, message)

        return null
    }

}


