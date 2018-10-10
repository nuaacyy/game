package com.point18.slg2d.world.msgnotice

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceTopicReplyChange

// 联盟邮件新主题或新回复通知
class AllianceTopicReplyChgNotifier(
    val msg: AllianceTopicReplyChange.Builder
) {
    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.AllianceTopicReplyChange_3100, this.msg.build())
    }
}

fun createAllianceTopicReplyChgNotifier(topicId: Long): AllianceTopicReplyChgNotifier {
    val allianceTopicReplyChangeBuilder = AllianceTopicReplyChange.newBuilder()
    allianceTopicReplyChangeBuilder.topicId = topicId
    return AllianceTopicReplyChgNotifier(allianceTopicReplyChangeBuilder)
}


