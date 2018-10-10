package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ReadAllMail
import pb4client.ReadAllMailRt

/**
 * 一键已读
 */
class ReadAllMailDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val mailType = (msg as ReadAllMail).mailType
        prepare(session) { mailDC ->
            val readAllMailRt = readAllMail(mailDC, mailType)
            session.sendMsg(MsgType.ReadAllMail_452, readAllMailRt.build())
        }
    }

    private fun readAllMail(dc: MailDC, mailType: Int): ReadAllMailRt.Builder {
        val rt = ReadAllMailRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val mailList = dc.findMailsByType(mailType) { return@findMailsByType intToBool(it.isDraw) }
        mailList.forEach {
            it.isRead = boolToInt(true)
            dc.checkMail(it)
        }

        dc.newMailNum().forEach {
            rt.addNewMailNum(it)
        }

        return rt
    }
}
