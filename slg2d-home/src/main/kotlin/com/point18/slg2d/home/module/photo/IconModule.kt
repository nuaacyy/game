package com.point18.slg2d.home.module.photo

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.GET_NEW_HERO
import com.point18.slg2d.home.module.registerEvent

var iconM = IconModule()

class IconModule : IModule {
    override fun moduleInit() {
        registerEvent(GET_NEW_HERO, GainHeroEventHandler())
    }
}
