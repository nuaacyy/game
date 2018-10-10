package com.point18.slg2d.home.module.mail

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC

//处理邮件过期
fun dealMailExpire(session: PlayerActor) {
    session.db.require(MailDC::class.java).handleKt { mailDc ->
        val nowTime = getNowTime()
        var index = 0
        while (true) {
            if (++index > 100) {
                break
            }
            val mail = mailDc.mailQueue.peek() ?: break
            if (mail.sendTime + pcs.basicProtoCache.mailExpireDuration * 3600 > nowTime) {
                break
            }
            mailDc.deleteMail(mail)
        }
    }
}