package com.point18.slg2d.home.module.innerCity.innerCityEffect

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.InnerCity
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.module.innerCity.UpdateEffectHandle

// 默认策略
open class DefaultEffectAction : UpdateEffectHandle {
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

        val updateMap = hashMapOf<Int, Int>()
        updateMap[upEffKey] = allValue
        player.putInnerBuildingEffectInfoMap(updateMap)
    }
}
