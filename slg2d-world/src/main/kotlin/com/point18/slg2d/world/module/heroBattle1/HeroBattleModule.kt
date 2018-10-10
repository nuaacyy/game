package com.point18.slg2d.world.module.heroBattle1

import com.point18.slg2d.world.module.heroBattle1.directEff.directEffInit
import com.point18.slg2d.world.module.heroBattle1.fightLogic.logicInit
import com.point18.slg2d.world.module.heroBattle1.heroBuff.buffInit

var HeroBattleModuleM = HeroBattleModule()

class HeroBattleModule : com.point18.slg2d.common.baseg.IModule {
    override fun moduleInit() {
        buffInit()
        directEffInit()
        logicInit()
    }
}
