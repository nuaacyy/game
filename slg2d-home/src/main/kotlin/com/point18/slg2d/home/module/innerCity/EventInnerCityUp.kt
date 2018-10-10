package com.point18.slg2d.home.module.innerCity

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.IEventHandler
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import com.point18.slg2d.home.module.innerCity.innerCityEffect.DefaultEffectAction

class InnerCityUpEventHandler : IEventHandler<BuildingUpFinishEvent>, HomeHelperPlus2<HomePlayerDC, InnerCityDC>(
    HomePlayerDC::class.java, InnerCityDC::class.java
) {

    override fun handleEvent(session: PlayerActor, event: BuildingUpFinishEvent) {
        prepare(session) { homePlayerDC, innerCityDC ->
            //
            // 下面的代码可能在异步情况下执行！
            //
            val buildingType = event.buildingType
            val buildingLv = event.buildingLv
            val buildingId = event.buildingId

            val targetHelper = event.targetHelper
            val refreshResHelper = event.refreshResHelper
            this.upEffect(session, homePlayerDC, innerCityDC, buildingType, buildingLv, buildingId, refreshResHelper)

            //重算建筑实力
            targetHelper.targetAddVal(session, com.point18.slg2d.common.constg.BuildStrength, null)

            event.effectHelper.syncEffect2World(session, com.point18.slg2d.common.constg.BUILDING_EFFECT)
        }

    }

    private fun upEffect(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC,
        buildingType: Int, buildingLv: Int, buildingId: Long, refreshResHelper: RefreshResourceHelper
    ) {
        val building = innerCityDC.findInnerCityFromId(buildingId)
        if (building == null) {
            return
        }

        val player = homePlayerDC.player

        if (buildingLv <= 0) {
            return
        }

        val buildingProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(buildingType, buildingLv)
        if (buildingProto == null) {
            return
        }

        val upEffs = buildingProto.upEff
        var isRefRes = false
        for ((upEffKey, effValue) in upEffs) {
            var updateEffectHandle = innerCityM.updateEffectHandleMap[upEffKey]
            if (updateEffectHandle == null) {
                updateEffectHandle = DefaultEffectAction()
            }
            updateEffectHandle.updateEffectH(session, upEffKey, effValue, player, building, innerCityDC)

            if (com.point18.slg2d.common.constg.isRefreshResAtOnce(upEffKey)) {
                isRefRes = true
            }
        }

        if (isRefRes) {
            refreshResHelper.refreshResource(session, com.point18.slg2d.common.constg.AllResYield)
        }
    }
}