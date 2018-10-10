package com.point18.slg2d.home.module.heroInvincible

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.HeroSuperUp
import com.point18.slg2d.home.module.registerHeart

class HeroInvincibleModule : IModule {
    override fun moduleInit() {
        // 升阶心跳
        registerHeart(HeroSuperUp, HeartHeroSuperUp())
    }

}

var heroInvincibleM: HeroInvincibleModule = HeroInvincibleModule()
