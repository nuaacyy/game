package com.point18.slg2d.home.module.bank

import com.point18.slg2d.common.constg.BANK_CONTENT
import com.point18.slg2d.common.constg.BANK_TITLE
import com.point18.slg2d.common.constg.SYS_MAIL
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.MailHelper
import com.point18.slg2d.home.dc.BankDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IHeartDeal
import java.util.*
import java.util.Arrays.asList

/**
银行的心跳结束的回调处理
 */

class BankHeartTriggerDeal(
    private val mailHelper: MailHelper = MailHelper()
) : IHeartDeal, HomeHelperPlus1<BankDC>(
    BankDC::class.java,
    asList(mailHelper)
) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { bankDC: BankDC ->
            val bank = bankDC.bank
            if (bank == null || bank.playerId != actionId) {
                //todo bank不存在的情况
                onComplete(ResultCode.PARAMETER_ERROR.code)
                return@prepare
            }

            bank.isMail = 1

            // 发个邮件给玩家通知
            // 计算利率
            val rateMoney = ((bank.useMoney + bank.useBindMoney).toDouble() * (bank.rate.toDouble() / 10000)).toLong()
            val allAddNum = bank.useMoney + bank.useBindMoney + rateMoney
            val messageParams = LinkedList<String>()
            messageParams += allAddNum.toString()
            val mailInfo = MailInfo(TEXT_READ_LAN, BANK_TITLE, LinkedList(), BANK_CONTENT, messageParams)
            mailHelper.sendMail(session, bank.playerId, mailInfo, SYS_MAIL)

            onComplete(ResultCode.SUCCESS.code)
        }

    }

}
