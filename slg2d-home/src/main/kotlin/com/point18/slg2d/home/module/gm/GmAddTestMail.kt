package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.MailHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import java.util.*
import java.util.Arrays.asList

class GmAddTestMail(
    private val mailHelper: MailHelper = MailHelper()
) : GmCommand, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(mailHelper)
) {

    override fun exec(session: PlayerActor, message: String) {
        val paras = message.split(" ")
        if (paras.size < 4) {
            return
        }
        val mailType = paras[2].toIntOrNull()
        val sendNum = paras[3].toIntOrNull()
        if (mailType == null || sendNum == null || sendNum < 1) {
            return
        }


        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            SYSTEM_MAIL,
            LinkedList()
        )
        val allProps =
            pcs.equipCache.equipProtoList.filter { it.mainType == PROP_RES || it.mainType == PROP_QUICK || it.mainType == PROP_USE }
        for (i in 1..sendNum) {
            val attach = LinkedList<ResVo>()
            val propNum = getRandInt(3)
            for (j in 1..propNum) {
                val prop = allProps[getRandInt(allProps.count())]
                val num = getRandInt(3) + 1
                attach += ResVo(RES_PROPS, prop.id.toLong(), num.toLong())
            }
            mailHelper.sendMail(session, session.playerId, mailInfo, mailType, attach)
        }
    }

}