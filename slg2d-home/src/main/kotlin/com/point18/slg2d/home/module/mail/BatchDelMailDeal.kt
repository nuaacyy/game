package com.point18.slg2d.home.module.mail

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.intToBool
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.MailDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.BatchDelMail
import pb4client.BatchDelMailRt
import java.util.*

class BatchDelMailDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<MailDC>(
        MailDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        //邮件类型
        val reqMsg = msg as BatchDelMail
        prepare(session) { mailDC ->
            val delMailRt =
                batchDelMail(mailDC, reqMsg.mailType, intToBool(reqMsg.selectAll), LinkedList(reqMsg.mailIdsList))
            session.sendMsg(MsgType.BatchDelMail_456, delMailRt.build())
        }
    }

    // 删除邮件
    private fun batchDelMail(
        dc: MailDC,
        mailType: Int,
        selectAll: Boolean,
        mailIds: LinkedList<Long>
    ): BatchDelMailRt.Builder {
        val rt = BatchDelMailRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val mailIdMap = hashMapOf<Long, Int>()
        mailIds.forEach { mailIdMap[it] = 1 }

        val mailList = dc.findMailsByType(mailType) { return@findMailsByType intToBool(it.isDraw) }
        mailList.forEach {
            if (!selectAll && !mailIdMap.containsKey(it.id)) {
                return@forEach
            } else if (selectAll && mailIdMap.containsKey(it.id)) {
                return@forEach
            }
            dc.deleteMail(it)
        }

        dc.newMailNum().forEach {
            rt.addNewMailNum(it)
        }

        return rt
    }
}
