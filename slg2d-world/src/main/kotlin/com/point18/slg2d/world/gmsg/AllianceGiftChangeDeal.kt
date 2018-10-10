package com.point18.slg2d.world.gmsg

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.msgnotice.createAllianceGiftChangeNotifier
import pb4server.Public2WorldTell

class AllianceGiftChangeDeal : Public2WorldTellDealBase() {
    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val tell = msg.allianceGiftChangeNotic2GTell

            val allianceBigGiftVo = pb4client.AllianceBigGiftVo.newBuilder()
            allianceBigGiftVo.bigGiftId = tell.allianceBigGiftVo.bigGiftId
            allianceBigGiftVo.bigGiftExp = tell.allianceBigGiftVo.bigGiftExp
            allianceBigGiftVo.giftLv = tell.allianceBigGiftVo.giftLv
            allianceBigGiftVo.giftExp = tell.allianceBigGiftVo.giftExp

            val msgNotifier = createAllianceGiftChangeNotifier(allianceBigGiftVo)

            // 告诉联盟成员playerM的信息变化
            msgNotifier.notice(session)
        }
    }
}
