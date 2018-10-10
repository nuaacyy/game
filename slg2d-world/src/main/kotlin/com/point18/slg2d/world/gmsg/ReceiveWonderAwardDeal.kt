package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.constg.RECEIVE_ALLIANCE_AWARD_CONTENT
import com.point18.slg2d.common.constg.RECEIVE_ALLIANCE_AWARD_TITLE
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.sendMail
import pb4server.Public2WorldTell
import java.util.*
import java.util.Arrays.asList

class ReceiveWonderAwardDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.receiveWonderAwardNotic2GTell

        val awardId = tell.awardId

        val awardProto = pcs.kingAwardProtoCache.kingAwardProtoMap[awardId]
        if (awardProto == null) {
            return
        }

        //发送邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            RECEIVE_ALLIANCE_AWARD_TITLE,
            LinkedList(),
            RECEIVE_ALLIANCE_AWARD_CONTENT,
            LinkedList(asList(awardProto.name))
        )
        sendMail(areaCache, playerId, mailInfo)
    }
}

