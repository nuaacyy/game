package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal
import com.point18.slg2d.home.module.event.JjcRankChangeEvent
import com.point18.slg2d.home.module.fireEvent

class SyncJjcRankDeal : SyncMsgDeal, HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homeSyncDc ->
            val jjcRank = data.toIntOrNull() ?: return@prepare ResultCode.PARAMETER_ERROR.code

            val oldJjcRank = homeSyncDc.syncData.jjcRank
            var diff = 0
            if (jjcRank < oldJjcRank) {
                // 排名上升了
                diff = oldJjcRank - jjcRank
            }
            homeSyncDc.syncData.jjcRank = jjcRank
            fireEvent(session, JjcRankChangeEvent(diff, jjcRank))
            return@prepare ResultCode.SUCCESS.code
        }
    }
}