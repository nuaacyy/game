package com.point18.slg2d.world.module.cave

import com.point18.slg2d.world.wpm

var CaveM = CaveModule()

class CaveModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {
        //藏兵心跳
        wpm.hs.registerHeartHandler(CaveHeartHandler())
    }
}