package com.point18.slg2d.home.module.innerCity.innerCityEffect

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.InnerCity
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.dc.TimeBoxInfo
import com.point18.slg2d.home.module.innerCity.UpdateEffectHandle

class AddTimeBoxEffectAction : UpdateEffectHandle {
    override fun updateEffectH(
        session: PlayerActor,
        upEffKey: Int,
        effValue: Int,
        player: HomePlayer,
        building: InnerCity,
        innerCityDC: InnerCityDC
    ) {
        val effBuildMap = pcs.innerBuildingDataCache.fetchSpecBuildingMap(upEffKey) ?: return
        var allValue = 0
        for (effBuildType in effBuildMap) {
            val builds = innerCityDC.findInnerCityListFromCastleIdAndType(player.focusCastleId, effBuildType)
            for (build in builds) {
                if (build.lv > 0) {
                    val value = pcs.innerBuildingDataCache.getEffValue(effBuildType, build.lv, upEffKey)
                    allValue += value
                }
            }
        }

        val diffNum = allValue - player.timeBoxNumMap.size
        if (diffNum > 0) {
            // 新增X个槽位
            for (i in 1..diffNum) {
                val vo = TimeBoxInfo(
                    0,
                    1,
                    0,
                    0
                )
                player.putTimeBoxNumMap(player.timeBoxNumMap.size + 1, vo)
            }
        }
    }

}
