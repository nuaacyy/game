package com.point18.slg2d.world.module.buff

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.GET_BUFF
import com.point18.slg2d.world.wpm

var BuffM = BuffModule()

class BuffModule : IModule {
    override fun moduleInit() {
        // buff过期心跳
        wpm.hs.registerHeartHandler(BuffTimeOverHeartHandler())

        wpm.es.register(GET_BUFF, GetNewBuffEventHandler())
    }


}

