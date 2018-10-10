package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.AllResYield
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.BarrackData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal
import java.util.Arrays.asList

class SyncBarracksDeal(
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper()
) : SyncMsgDeal,
    HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java, asList(refreshResHelper)) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homeSyncDc: HomeSyncDC ->
            val barrackMap: HashMap<Int, BarrackData>
            try {
                barrackMap = toObj(data)
            } catch (e: Exception) {
                return@prepare ResultCode.PARAMETER_ERROR.code
            }

            homeSyncDc.syncData.barracksMap = barrackMap
            refreshResHelper.refreshResource(session, AllResYield)

            return@prepare ResultCode.SUCCESS.code
        }
    }
}