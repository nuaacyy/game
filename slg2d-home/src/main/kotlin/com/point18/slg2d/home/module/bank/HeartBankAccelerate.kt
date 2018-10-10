package com.point18.slg2d.home.module.bank

import com.point18.slg2d.common.constg.BANK_ACCELERATE_CONTENT
import com.point18.slg2d.common.constg.BANK_ACCELERATE_TITLE
import com.point18.slg2d.common.constg.SYS_MAIL
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.MailHelper
import com.point18.slg2d.home.dc.BankAccelerateDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.IHeartDeal
import java.util.*
import java.util.Arrays.asList

/**
银行的心跳结束的回调处理
 */

class BankAccelerateHeartTriggerDeal(
    private val mailHelper: MailHelper = MailHelper()
) : IHeartDeal, HomeHelperPlus1<BankAccelerateDC>(
    BankAccelerateDC::class.java,
    asList(mailHelper)
) {

    override fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit) {
        prepare(session) { bankAccelerateDC: BankAccelerateDC ->
            val bankAccelerate = bankAccelerateDC.bankAccelerate
            if (bankAccelerate == null || bankAccelerate.playerId != actionId) {
                //todo bank不存在的情况
                onComplete(ResultCode.PARAMETER_ERROR.code)
                return@prepare
            }
            bankAccelerate.isMail = 1
            // 发个邮件给玩家通知

            val messageParams = LinkedList<String>()
            for (prop in bankAccelerate.props) {
                messageParams += prop.key.toString()
                messageParams += prop.value.toString()
            }

            val mailInfo =
                MailInfo(TEXT_READ_LAN, BANK_ACCELERATE_TITLE, LinkedList(), BANK_ACCELERATE_CONTENT, messageParams)
            mailHelper.sendMail(
                session,
                bankAccelerate.playerId,
                mailInfo,
                SYS_MAIL
            )

            onComplete(ResultCode.SUCCESS.code)
        }

    }

}
