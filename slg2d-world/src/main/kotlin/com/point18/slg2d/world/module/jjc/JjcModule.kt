package com.point18.slg2d.world.module.jjc

import com.point18.slg2d.world.wpm

var JjcM = JjcModule()

class JjcModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {

        // 心跳查看奖励
        wpm.hs.registerHeartHandler(JjcHeartHandler())
    }


}
