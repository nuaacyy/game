package com.point18.slg2d.world.module.farm

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.world.wpm

var FarmM = FarmModule()

class FarmModule : IModule {

    override fun moduleInit() {
        //尸体资源地消失心跳
        wpm.hs.registerHeartHandler(DeadBossResHeartHandler())

        //尸体资源地采集心跳
        wpm.hs.registerHeartHandler(DeadBossResFarmHeartHandler())

        //普通资源地采集心跳
        wpm.hs.registerHeartHandler(CommonResFarmHeartHandler())
    }
}