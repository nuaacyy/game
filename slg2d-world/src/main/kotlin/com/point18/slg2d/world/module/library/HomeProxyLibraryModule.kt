package com.point18.slg2d.world.module.library

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.BOSS_FIGHT
import com.point18.slg2d.common.constg.PVE_FIGHT_WIN
import com.point18.slg2d.world.wpm

var HomeProxyLibraryM = HomeProxyLibraryModule()

class HomeProxyLibraryModule : IModule {
    override fun moduleInit() {
        // PVE战斗胜利
        wpm.es.register(PVE_FIGHT_WIN, PveFightWinEventHandler())

        // 魔物战斗事件
        wpm.es.register(BOSS_FIGHT, BossFightEventHandler())
    }
}