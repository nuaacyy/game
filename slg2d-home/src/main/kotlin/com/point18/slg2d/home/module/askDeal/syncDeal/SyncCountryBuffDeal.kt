package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.EffectData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal

class SyncCountryBuffDeal : SyncMsgDeal, HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homeSyncDC ->
            val effectData: EffectData
            try {
                effectData = toObj(data)
            } catch (e: Exception) {
                return@prepare ResultCode.PARAMETER_ERROR.code
            }

            homeSyncDC.syncData.effectMap[effectData.syncEffectType] = effectData.effectMap
            return@prepare ResultCode.SUCCESS.code
        }
    }

}