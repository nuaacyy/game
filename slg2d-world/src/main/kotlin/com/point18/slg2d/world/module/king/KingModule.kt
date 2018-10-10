package com.point18.slg2d.world.module.king

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.KING_LV_CHANGE
import com.point18.slg2d.world.wpm

var KingM = KingModule()

class KingModule : IModule {
    override fun moduleInit() {
        wpm.es.register(KING_LV_CHANGE, KingLvChangeEventHandler())
    }

}
