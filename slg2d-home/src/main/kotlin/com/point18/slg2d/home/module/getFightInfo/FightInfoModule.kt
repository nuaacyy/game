package com.point18.slg2d.home.module.getFightInfo

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.module.ILongTimeHeartDeal
import com.point18.slg2d.home.module.registerLongTimeHeart

class FightInfoModule : IModule {
    override fun moduleInit() {
        registerLongTimeHeart(object : ILongTimeHeartDeal {
            override fun dealHeart(session: PlayerActor) {
                dealBattleReportExpire(session)
            }
        })
    }
}

var fightInfoM: FightInfoModule = FightInfoModule()
