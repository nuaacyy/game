package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.MailSign
import pb4client.MailSignRt

class SignMailDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val mailId = (msg as MailSign).mailId
        prepare(session) { mailDC ->
            val delMailRt = signMail(mailDC, mailId)
            session.sendMsg(MsgType.SignMail_457, delMailRt)
        }
    }

    // 标记邮件
    private fun signMail(dc: MailDC, mailId: Long): MailSignRt {
        val rt = MailSignRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        // 查找邮件
        val mail = dc.findById(mailId)

        if (mail == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        dc.delFromCache(mail)
        mail.isSign = boolToInt(true)
        dc.add2Cache(mail)
        return rt.build()
    }
}
