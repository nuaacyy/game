package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.UpdateOffice
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.updateAllianceMemberInfo
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal

class SyncOfficeDeal : SyncMsgDeal, HomeHelperPlus2<HomePlayerDC, HomeSyncDC>(
    HomePlayerDC::class.java, HomeSyncDC::class.java
) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC ->
            val player = homePlayerDC.player
            val officeInfoData = toObj<Map<Long, Int>>(data)

            for ((worldId, officeId) in officeInfoData) {
                if (officeId == 0) {
                    homeSyncDC.syncData.officeMap.remove(worldId)
                } else {
                    homeSyncDC.syncData.officeMap[worldId] = officeId
                }
            }

            // 同步到Pub
            if (player.allianceId != 0L) {
                val updateInfoMap = hashMapOf<Int, String>()
                updateInfoMap[UpdateOffice] = data
                updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
            }

            return@prepare ResultCode.SUCCESS.code
        }
    }
}