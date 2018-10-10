package com.point18.slg2d.home.module.giftBag

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.BUILDING_UP_FINISH
import com.point18.slg2d.common.constg.KING_LV_CHANGE
import com.point18.slg2d.home.module.registerEvent

var giftBagM = GiftBagModule()

class GiftBagModule : IModule {

    override fun moduleInit() {
        registerEvent(BUILDING_UP_FINISH, BuildingUpTriggerEventHandler())
        registerEvent(KING_LV_CHANGE, KingUpTriggerEventHandler())
    }
}