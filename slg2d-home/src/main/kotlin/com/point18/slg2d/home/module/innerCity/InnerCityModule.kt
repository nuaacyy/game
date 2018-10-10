package com.point18.slg2d.home.module.innerCity

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.InnerCity
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.module.innerCity.innerCityEffect.*
import com.point18.slg2d.home.module.registerEvent
import com.point18.slg2d.home.module.registerHeart

// 升级效果接口
interface UpdateEffectHandle {
    fun updateEffectH(
        session: PlayerActor,
        upEffKey: Int,
        effValue: Int,
        player: HomePlayer,
        building: InnerCity,
        innerCityDC: InnerCityDC
    )
}

var innerCityM = InnerCityModule()

class InnerCityModule : IModule {
    var updateEffectHandleMap: HashMap<Int, UpdateEffectHandle> = hashMapOf()

    // 特殊策略的注册与检测
    private fun innerCitySpecialActionCheckAndInit() {
        // 只有特殊策略才需要注册
        // 警戒塔策略
        this.updateEffectHandleMap[ShowWarnResearchLv1] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv2] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv3] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv4] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv5] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv6] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv7] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv8] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv9] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv10] = PreWarnEffectAction()
        this.updateEffectHandleMap[ShowWarnResearchLv11] = PreWarnEffectAction()
        this.updateEffectHandleMap[CanHelpNum] = AddAllianceCanHelperNumEffectAction()
        this.updateEffectHandleMap[TimeBoxNumAdd] = AddTimeBoxEffectAction()
        this.updateEffectHandleMap[AddCityLv] = AddCastleLv()
        this.updateEffectHandleMap[CanFightRelicLv] = MaxEffectAction()
    }

    override fun moduleInit() {
        updateEffectHandleMap = hashMapOf()

        registerEvent(BUILDING_UP_FINISH, InnerCityUpEventHandler())

        registerEvent(REFRESH_INNER_CITY_EFFECT, RefreshEffectEventHandler())

        // 注册效果
        innerCitySpecialActionCheckAndInit()

        // 注册心跳
        registerHeart(InnerCityDestroy, InnerCityDestroyHeartTriggerDeal())
        registerHeart(InnerCityUpgrade, InnerCityUpgradeHeartTriggerDeal())
    }
}
