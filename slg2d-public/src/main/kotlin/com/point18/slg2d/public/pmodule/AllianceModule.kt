package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.baseg.TIMETASK
import com.point18.slg2d.public.ppm

class AllianceModule : IModule {
    override fun moduleInit() {
        ppm.hs.registerHeartHandler(FightInfoPastTimeHeartHandler())

        // 联盟活动心跳
        ppm.hs.registerHeartHandler(ActivityTimeOverHeartHandler())

        // 清空玩家联盟周排行数据
        ppm.hs.registerHeartHandler(AllianceRankClearHeartHandler())

        // 定时清理数据
        ppm.hs.registerHeartHandler(TimeTaskDeleteExpireEventHandler())

        // 处理联盟总动员的活动开启与关闭
        ppm.mhs.registerHeartHandler(DealPublicActivityHeartHandler())

    }
}

var AllianceM = AllianceModule()

