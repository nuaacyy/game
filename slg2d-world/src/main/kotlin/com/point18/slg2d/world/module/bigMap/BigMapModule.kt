package com.point18.slg2d.world.module.bigMap

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.baseg.TIMETASK
import com.point18.slg2d.world.wpm

var BigMapM = BigMapModule()

class BigMapModule : IModule {

    override fun moduleInit() {

        // 心跳
        wpm.hs.registerHeartHandler(RefBigMapHeartHandler())

        // 城堡状态变化心跳
        wpm.hs.registerHeartHandler(CastleStateChangeHeartHandler())

        // 事件
        wpm.es.register(TIMETASK, RefreshBigMapEventHandler())

    }
}