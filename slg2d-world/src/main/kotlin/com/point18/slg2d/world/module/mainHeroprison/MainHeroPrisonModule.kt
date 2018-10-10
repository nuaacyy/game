package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.common.constg.DEF_COVER_ON
import com.point18.slg2d.common.constg.PRISON_AFTER_PVP
import com.point18.slg2d.common.constg.RESCUE_PRISON
import com.point18.slg2d.world.wpm

class MainHeroPrisonModule : com.point18.slg2d.common.baseg.IModule {

    override fun moduleInit() {
        // 注册事件处理
        wpm.es.register(PRISON_AFTER_PVP, PrisonEventHandler())
        wpm.es.register(RESCUE_PRISON, RescuePrisonEventHandler())

        wpm.es.register(DEF_COVER_ON, DefendCoverOnEventHandler())

        wpm.hs.registerHeartHandler(PlayerPrisonBuffHeartHandler())

        // 领主状态心跳
        wpm.hs.registerHeartHandler(MainHeroPrisonHeartHandler())

    }
}

var mainHeroPrisonM: MainHeroPrisonModule = MainHeroPrisonModule()




