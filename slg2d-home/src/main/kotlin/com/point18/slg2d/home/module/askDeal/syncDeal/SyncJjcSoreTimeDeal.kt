package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal

class SyncJjcSoreTimeDeal : SyncMsgDeal, HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homeSyncDC ->
            val jjcScoreTime = data.toLongOrNull()
            if (jjcScoreTime == null) {
                return@prepare ResultCode.PARAMETER_ERROR.code
            }

            homeSyncDC.syncData.scoreTime = jjcScoreTime
            return@prepare ResultCode.SUCCESS.code
        }
    }
}