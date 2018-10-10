package com.point18.slg2d.home.module.talent

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.TALENT_LV_CHANGE
import com.point18.slg2d.common.constg.TALENT_RESET
import com.point18.slg2d.common.constg.TALENT_EFFECT
import com.point18.slg2d.common.constg.TalentResYield
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.module.registerEvent

class TalentModule : IModule {

    override fun moduleInit() {
        //监听君主升级事件，增加天赋点
        //监听天赋升级事件
        registerEvent(TALENT_LV_CHANGE, TalentLvChangeEventHandler())

        //监听重置天赋事件
        registerEvent(TALENT_RESET, TalentResetEventHandler())
    }

    fun handleTalentChange(
        session: PlayerActor,
        refreshResHelper: RefreshResourceHelper,
        effectHelper: ResearchEffectHelper
    ) {
        refreshResHelper.refreshResource(session, TalentResYield)
        effectHelper.syncEffect2World(session, TALENT_EFFECT)
    }
}

var talentM: TalentModule = TalentModule()
