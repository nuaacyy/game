package com.point18.slg2d.home.module.vip

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.VIP_LV_CHANGE
import com.point18.slg2d.common.constg.Vip
import com.point18.slg2d.home.module.registerEvent
import com.point18.slg2d.home.module.registerHeart

var vipM: VipModule = VipModule()

class VipModule : IModule {

    // 模块初始化
    override fun moduleInit() {

        //注册vip升级事件
        registerEvent(VIP_LV_CHANGE, VipChangeEventHandler())

        //注册心跳处理
        registerHeart(Vip, VipHeartTriggerDeal())
    }
}




