package com.point18.slg2d.world.module.mail

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.playerIsHavePos
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.common.constg.TEXT_READ_INFO
import com.point18.slg2d.common.pc.*
import pb4client.MailTitleAndCon
import pb4client.SendAllianceMail
import pb4client.SendAllianceMailRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.sendAllianceMail
import java.util.*

class SendAllianceMailDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val dealMailRt = sendAllianceMail(session, (msg as SendAllianceMail).mailTitleAndCon)
        if (dealMailRt != null) {
            session.sendMsg(MsgType.SendAllianceMail_458, dealMailRt.build())
        }
    }
}

// 发送联盟邮件
fun sendAllianceMail(session: PlayerSession, mailTitleAndCon: MailTitleAndCon): SendAllianceMailRt.Builder? {
    val rtBuilder = SendAllianceMailRt.newBuilder()

    val player = session.player

    //校验权限
    if (!playerIsHavePos(player, ALLIANCE_POSITION_BOSS)) {
        rtBuilder.rt = ResultCode.ALLIANCE_POSITION_NO_ENERGH.code
        return rtBuilder
    }

    //校验邮件内容
    if (mailTitleAndCon.readType != TEXT_READ_INFO) {
        rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
        return rtBuilder
    }
    val titleRst =
        pcs.wordCache.check(
            mailTitleAndCon.title,
            pcs.basicProtoCache.allianceTopicTitleLength,
            WORD_CHECK_MESSAGE
        )
    when (titleRst.wordCheckResult) {
        WORD_CHECK_RESULT_LENGTH_SHORT -> {
            rtBuilder.rt = ResultCode.NAME_NIL.code
            return rtBuilder
        }
        WORD_CHECK_RESULT_LENGTH_EXCEED -> {
            rtBuilder.rt = ResultCode.NAME_LONG.code
            return rtBuilder
        }
        WORD_CHECK_RESULT_FORBIDDEN -> {
            rtBuilder.rt = ResultCode.KEYWORDS.code
            return rtBuilder
        }
    }
    val contentRst = pcs.wordCache.check(
        mailTitleAndCon.message,
        pcs.basicProtoCache.allianceTopicMessageLength,
        WORD_CHECK_MESSAGE
    )
    when (contentRst.wordCheckResult) {
        WORD_CHECK_RESULT_LENGTH_SHORT -> {
            rtBuilder.rt = ResultCode.NAME_NIL.code
            return rtBuilder
        }
        WORD_CHECK_RESULT_LENGTH_EXCEED -> {
            rtBuilder.rt = ResultCode.NAME_LONG.code
            return rtBuilder
        }
        WORD_CHECK_RESULT_FORBIDDEN -> {
            rtBuilder.rt = ResultCode.KEYWORDS.code
            return rtBuilder
        }
    }

    //转发联盟服
    sendAllianceMail(
        session,
        player.allianceId,
        mailTitleAndCon.readType,
        mailTitleAndCon.title,
        LinkedList(mailTitleAndCon.titleParamList),
        mailTitleAndCon.message,
        LinkedList(mailTitleAndCon.messageParamList)
    )

    return null
}
