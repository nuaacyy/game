package com.point18.slg2d.home.module.research

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.RESEARCH_EFFECT_CHANGE
import com.point18.slg2d.common.constg.ResearchLvUp
import com.point18.slg2d.home.module.registerEvent
import com.point18.slg2d.home.module.registerHeart

class ResearchModule : IModule {
    override fun moduleInit() {
        // 注册心跳
        registerHeart(ResearchLvUp, ResearchHeart())

        // 科技效果发生变化事件
        registerEvent(RESEARCH_EFFECT_CHANGE, ResearchEffectChangeEventHandler())
    }
}

var researchM: ResearchModule = ResearchModule()
