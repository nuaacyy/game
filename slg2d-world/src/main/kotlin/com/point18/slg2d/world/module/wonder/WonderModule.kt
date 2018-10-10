package com.point18.slg2d.world.module.wonder

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.ALLIANCE_NAME_CHANGE
import com.point18.slg2d.common.constg.GOD_OF_WAR_ON
import com.point18.slg2d.common.constg.QUIT_ALLIANCE
import com.point18.slg2d.world.wpm

var WonderM: WonderModule = WonderModule()

class WonderModule : IModule {
    // 初始化
    override fun moduleInit() {
        // 奇观争夺心跳
        wpm.hs.registerHeartHandler(WonderWarHeartHandler())

        // 处理退出联盟事件
        wpm.es.register(QUIT_ALLIANCE, QuitAllianceEventHandler())

        // 处理联盟改名事件
        wpm.es.register(ALLIANCE_NAME_CHANGE, AllianceNameChangeEventHandler())

        // 处理获得战神庇护之盾buff事件
        wpm.es.register(GOD_OF_WAR_ON, GodOfWarOnEventHandler())
    }
}


