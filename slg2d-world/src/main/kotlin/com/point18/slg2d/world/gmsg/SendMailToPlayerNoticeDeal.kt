package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.sendMail
import pb4server.Public2WorldTell
import java.util.*

class SendMailToPlayerNoticeDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.sendMailToPlayerNotic2GTell

        val attach = tell.attach
        val attachRes = LinkedList(resStringToResVoList(attach))
        
        val mailInfo = MailInfo(
            tell.readType,
            tell.title,
            LinkedList<String>(tell.messageParamList),
            tell.message,
            LinkedList<String>(tell.messageParamList)
        )
        for (tPlayerId in tell.playerIdsList) {
            val player = areaCache.playerCache.findPlayerById(tPlayerId)
            if (player != null) {
                sendMail(
                    areaCache,
                    tPlayerId,
                    mailInfo,
                    tell.sendPlayerId,
                    tell.sendPlayerName,
                    tell.sendPlayerNickName,
                    tell.sendAllianceId,
                    tell.sendAllianceName,
                    tell.sendAllianceShortName,
                    tell.mailType,
                    attachRes,
                    tell.sysId
                )

                if (tell.extend1 != "") {
                    player.allianceGiftGetMap[tell.extend1.toInt()] = 1
                    player.allianceGiftGetMap = player.allianceGiftGetMap
                }
            }
        }
    }
}

