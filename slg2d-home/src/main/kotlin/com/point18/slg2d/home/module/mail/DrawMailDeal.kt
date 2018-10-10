package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_MAIL_REWARD
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.Mail
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DrawMail
import pb4client.DrawMailRt
import java.util.*

class DrawMailDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, MailDC>(
        HomePlayerDC::class.java,
        MailDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        //邮件id
        val mailId = (msg as DrawMail).mailId
        prepare(session) { homePlayerDC, mailDC ->
            val rt = drawMail(session, mailDC, mailId, homePlayerDC)
            val drawMailRt = DrawMailRt.newBuilder()
            drawMailRt.rt = rt
            session.sendMsg(MsgType.DrawMail_453, drawMailRt.build())
        }
    }

    private fun drawMail(session: PlayerActor, mailDC: MailDC, mailId: Long, homePlayerDC: HomePlayerDC): Int {
        val mail = mailDC.findById(mailId)

        if (mail == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        return drawMail(session, resHelper, mail, mailDC, homePlayerDC)
    }
}

fun drawMail(session: PlayerActor, resHelper: ResHelper, mail: Mail, mailDC: MailDC, homePlayerDC: HomePlayerDC): Int {
    val action = ACTION_MAIL_REWARD

    if (mail.playerId != mailDC.playerId) {
        return ResultCode.PARAMETER_ERROR.code
    }

    val attach = mail.attach

    if (attach == "") {
        return ResultCode.PARAMETER_ERROR.code
    }

    if (intToBool(mail.isDraw)) {
        // 已领取
        return ResultCode.PARAMETER_ERROR.code
    }

    // 发资源
    val addResVo = resStringToResVoList(attach)

    // 增加附件资源
    if (addResVo != null) {
        resHelper.addRes(session, action, homePlayerDC.player, addResVo)
    }

    // 设置为已经领取
    mail.isDraw = boolToInt(true)
    mailDC.checkMail(mail)

    return ResultCode.SUCCESS.code
}
