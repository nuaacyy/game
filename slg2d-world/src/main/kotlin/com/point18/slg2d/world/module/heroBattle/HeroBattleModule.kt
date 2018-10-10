package com.point18.slg2d.world.module.heroBattle

import com.point18.slg2d.world.module.heroBattle.directEff.directEffInit
import com.point18.slg2d.world.module.heroBattle.fightLogic.logicInit
import com.point18.slg2d.world.module.heroBattle.heroBuff.buffInit

var HeroBattleModuleM = HeroBattleModule()

class HeroBattleModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {
        buffInit()
        directEffInit()
        logicInit()
    }
}
