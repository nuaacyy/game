package com.point18.slg2d.world.module.transport

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.ALLOW_ALLIANCE
import com.point18.slg2d.common.constg.QUIT_ALLIANCE
import com.point18.slg2d.common.constg.TRANSPORT_START
import com.point18.slg2d.world.wpm

class TransportModule : IModule {
    override fun moduleInit() {
        //加入联盟事件
        wpm.es.register(ALLOW_ALLIANCE, JoinAllianceEventHandler())
        //离开联盟事件
        wpm.es.register(QUIT_ALLIANCE, QuitAllianceEventHandler())
        //运输开始事件
        wpm.es.register(TRANSPORT_START, TransportStartEventHandler())
    }



}

var TransportM: TransportModule = TransportModule()



