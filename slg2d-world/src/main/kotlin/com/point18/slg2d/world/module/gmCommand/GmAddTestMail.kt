package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.common.sendMail
import java.util.*
import java.util.Arrays.asList

class GmAddTestMail : GmCommand {

    override fun exec(session: PlayerSession, message: String): Int {
        val paras = message.split(" ")
        if (paras.size < 4) {
            return 2
        }

        val mailType = paras[2].toIntOrNull()
        val sendNum = paras[3].toIntOrNull()
        if (mailType == null || sendNum == null || sendNum < 1) {
            return 2
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
        val player = session.player
        val playerId = session.playerId
        val areaCache = session.areaCache
        for (i in 1..sendNum) {
            val attach = LinkedList<ResVo>()
            val propNum = getRandInt(3)
            for (j in 1..propNum) {
                val prop = allProps[getRandInt(allProps.count())]
                val num = getRandInt(3) + 1
                attach += ResVo(RES_PROPS, prop.id.toLong(), num.toLong())
            }
            sendMail(areaCache, playerId, mailInfo, player, mailType, attach)
        }
        return 1
    }
}