package com.point18.slg2d.home.module.innerCity

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.RefreshBuildEffectEvent
import com.point18.slg2d.home.module.innerCity.innerCityEffect.DefaultEffectAction

class RefreshEffectEventHandler : IEventHandler<RefreshBuildEffectEvent>, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java
) {

    override fun handleEvent(session: PlayerActor, event: RefreshBuildEffectEvent) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->
            val player = homePlayerDC.player
            val targetHelper = event.targetHelper

            //重算建筑实力
            targetHelper.targetAddVal(session, com.point18.slg2d.common.constg.BuildStrength, null)
            val buildings = innerCityDC.findInnerCityListFromCastleId(player.focusCastleId)
            for (building in buildings) {
                if (building.lv > 0) {
                    val buildingProto =
                        pcs.innerBuildingDataCache.fetchProtoByTypeLv(building.cityType, building.lv)
                    if (buildingProto == null) {
                        continue
                    }

                    val upEffs = buildingProto.upEff
                    for ((upEffKey, effValue) in upEffs) {
                        var updateEffectHandle = innerCityM.updateEffectHandleMap[upEffKey]
                        if (updateEffectHandle == null) {
                            updateEffectHandle = DefaultEffectAction()
                        }
                        updateEffectHandle.updateEffectH(
                            session,
                            upEffKey, effValue,
                            player, building,
                            innerCityDC
                        )
                    }
                }
            }

            event.effectHelper.syncEffect2World(session, com.point18.slg2d.common.constg.BUILDING_EFFECT)
        }
    }
}