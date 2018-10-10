package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_MAIL_REWARD
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.Mail
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.DrawAllMail
import pb4client.DrawAllMailRt
import java.util.*

class DrawAllMailDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, MailDC>(
        HomePlayerDC::class.java,
        MailDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val mailType = (msg as DrawAllMail).mailType
        prepare(session) { homePlayerDC, mailDC ->
            val drawMailRt = drawAllMail(session, mailType, homePlayerDC, mailDC)
            session.sendMsg(MsgType.DrawAllMail_454, drawMailRt.build())
        }
    }

    private fun drawAllMail(session: PlayerActor, mailType: Int, homePlayerDC: HomePlayerDC, mailDC: MailDC): DrawAllMailRt.Builder {
        val rt = DrawAllMailRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val mailList = mailDC.findMailsByType(mailType) { !intToBool(it.isDraw) }

        // 计算总奖励
        val reward = margetMailReward(mailType, mailDC)

        // 修改所有邮件状态
        mailList.forEach { updateAllMailState(it, mailDC) }

        // 发送奖励
        if (reward.isNotEmpty()) {
            val action = ACTION_MAIL_REWARD
            resHelper.addRes(session, action, homePlayerDC.player, reward)
        }

        // 填充客户端消息
        mailDC.newMailNum().forEach {
            rt.addNewMailNum(it)
        }

        return rt
    }

    // 合并邮件的奖励
    private fun margetMailReward(mailType: Int, mailDC: MailDC): LinkedList<com.point18.slg2d.common.pc.ResVo> {
        val mailList = mailDC.findMailsByType(mailType) { !intToBool(it.isDraw) }
        val reward = LinkedList<ResVo>()
        for (mail in mailList) {
            if (mail.playerId != mailDC.playerId) {
                continue
            }
            val attach = mail.attach

            if (attach == "") {
                continue
            }

            if (intToBool(mail.isDraw)) {
                // 已领取
                continue
            }

            // 发资源
            val addResVo = resStringToResVoList(attach)

            // 增加附件资源
            if (addResVo != null) {
                reward += addResVo
            }
        }

        return com.point18.slg2d.common.pc.resVoCombine(reward)
    }

    // 批量修改邮件状态
    private fun updateAllMailState(mail: Mail, mailDC: MailDC): Int {
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

        // 设置为已经领取
        mail.isDraw = boolToInt(true)
        mail.isRead = boolToInt(true)
        mailDC.checkMail(mail)

        return ResultCode.SUCCESS.code
    }

}
