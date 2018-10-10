package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ReadMail
import pb4client.ReadMailRt

class ReadMailDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val mailId = (msg as ReadMail).mailId
        prepare(session) { mailDC ->
            val readMailRt = readMail(mailDC, mailId)
            session.sendMsg(MsgType.ReadMail_451, readMailRt.build())
        }
    }

    private fun readMail(dc: MailDC, mailId: Long): ReadMailRt.Builder {
        val rt = ReadMailRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val mail = dc.findById(mailId)
        if (mail == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt
        }
        mail.isRead = boolToInt(true)
        dc.checkMail(mail)

        return rt
    }
}
