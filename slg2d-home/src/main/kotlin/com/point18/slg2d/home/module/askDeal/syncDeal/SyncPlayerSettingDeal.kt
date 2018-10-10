package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.PlayerSettingData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal

class SyncPlayerSettingDeal : SyncMsgDeal, HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homeSyncDC ->
            val playerSettingData: PlayerSettingData
            try {
                playerSettingData = toObj(data)
            } catch (e: Exception) {
                return@prepare ResultCode.PARAMETER_ERROR.code
            }

            homeSyncDC.syncData.refuseDisturbOpen = playerSettingData.refuseDisturbOpen
            homeSyncDC.syncData.refuseDisturbEnd = playerSettingData.refuseDisturbEnd
            homeSyncDC.syncData.cautionLv = playerSettingData.cautionLv
            homeSyncDC.syncData.switches = playerSettingData.switches

            return@prepare ResultCode.SUCCESS.code
        }
    }

}