package com.point18.slg2d.world.module.barracks

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.WONDER_OVER
import com.point18.slg2d.world.wpm

var BarracksM = BarracksModule()

class BarracksModule : IModule {

    override fun moduleInit() {
        wpm.hs.registerHeartHandler(MakeSoliderHeartHandler())

        wpm.hs.registerHeartHandler(CureSoliderHeartHandler())

        wpm.hs.registerHeartHandler(SoldierUpHeartHandler())

        // 注册奇观战结束事件
        wpm.es.register(WONDER_OVER, CureAllEventSoldierEventHandler())
    }

}
