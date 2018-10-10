package com.point18.slg2d.world.module.homeHeart

import com.point18.slg2d.world.wpm

class HomeHeartModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {
        wpm.hs.registerHeartHandler(HomeHeartHandler())
    }
}

var HomeHeartM = HomeHeartModule()