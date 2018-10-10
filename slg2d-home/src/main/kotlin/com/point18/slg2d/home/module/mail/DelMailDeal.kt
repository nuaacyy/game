package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DelMail
import pb4client.DelMailRt

class DelMailDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val reqMsg = msg as DelMail
        prepare(session) { mailDC ->
            val delMailRt = delMail(mailDC, reqMsg.mailId)
            session.sendMsg(MsgType.DelMail_455, delMailRt.build())
        }

    }

    // 删除邮件
    fun delMail(dc: MailDC, mailId: Long): DelMailRt.Builder {
        val rt = DelMailRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // 查找邮件
        val mail = dc.findById(mailId)

        if (mail == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        if (!intToBool(mail.isDraw)) {
            // 有附件未领取
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt
        }

        // 删除邮件
        dc.deleteMail(mail)

        return rt
    }
}
