package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllMails
import pb4client.AllMailsRt
import pb4client.MailInfo
import pb4client.MailTitleAndCon

class AllMailDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java
    ) {

    // 邮件箱查询
    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        //最大邮件id
        val reqMsg = msg as AllMails

        prepare(session) { mailDC ->
            val allMailRt = allMail(mailDC, reqMsg.mailType, reqMsg.page)
            session.sendMsg(MsgType.AllMails_450, allMailRt.build())
        }
    }

    private fun allMail(dc: MailDC, mailType: Int, page: Int): AllMailsRt.Builder {
        val rt = AllMailsRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val mails = dc.findMailsByPage(mailType, page, pcs.basicProtoCache.emailUpDateNumb)
        if (mails == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        for (mail in mails) {
            val mailInfo = MailInfo.newBuilder()
            mailInfo.mailId = mail.id
            mailInfo.type = mail.type
            mailInfo.playerId = mail.sendPlayerId
            mailInfo.playerName = mail.sendPlayerName
            mailInfo.playerNickName = mail.sendPlayerNickName
            mailInfo.allianceId = mail.sendAllianceId
            mailInfo.allianceShortName = mail.sendAllianceShortName

            val tmpMailTitleAndCon = MailTitleAndCon.newBuilder()

            tmpMailTitleAndCon.readType = mail.mailInfo.readType
            tmpMailTitleAndCon.message = mail.mailInfo.message
            tmpMailTitleAndCon.title = mail.mailInfo.title

            for (each in mail.mailInfo.titleParam) {
                tmpMailTitleAndCon.addTitleParam(each)
            }
            for (each in mail.mailInfo.messageParam) {
                tmpMailTitleAndCon.addMessageParam(each)
            }
            mailInfo.setMailTitleAndCon(tmpMailTitleAndCon)

            mailInfo.attach = mail.attach
            mailInfo.isRead = mail.isRead
            mailInfo.isDraw = mail.isDraw
            mailInfo.sendTime = com.point18.slg2d.common.commonfunc.getTimeSec(mail.sendTime)

            // 过期时间
            val expireAt = mail.sendTime + pcs.basicProtoCache.mailExpireDuration * 3600
            mailInfo.expireTime = com.point18.slg2d.common.commonfunc.getTimeSec(expireAt)
            mailInfo.isSign = mail.isSign
            rt.addMailInfo(mailInfo)
        }

        return rt
    }

}
