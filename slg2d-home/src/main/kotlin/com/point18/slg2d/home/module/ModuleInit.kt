package com.point18.slg2d.home.module

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.baseg.modules
import com.point18.slg2d.home.module.bank.bankCityM
import com.point18.slg2d.home.module.equip.equipM
import com.point18.slg2d.home.module.getFightInfo.fightInfoM
import com.point18.slg2d.home.module.giftBag.giftBagM
import com.point18.slg2d.home.module.gm.gmM
import com.point18.slg2d.home.module.guildhouse.guildHouseM
import com.point18.slg2d.home.module.heroInvincible.heroInvincibleM
import com.point18.slg2d.home.module.innerCity.innerCityM
import com.point18.slg2d.home.module.library.libraryM
import com.point18.slg2d.home.module.mail.mailM
import com.point18.slg2d.home.module.photo.iconM
import com.point18.slg2d.home.module.research.researchM
import com.point18.slg2d.home.module.serverdeal.serverDealM
import com.point18.slg2d.home.module.talent.talentM
import com.point18.slg2d.home.module.task.taskM
import com.point18.slg2d.home.module.vip.vipM

fun initModules() {
    registerModule(bankCityM)
    registerModule(equipM)
    registerModule(guildHouseM)
    registerModule(fightInfoM)
    registerModule(heroInvincibleM)
    registerModule(innerCityM)
    registerModule(researchM)
    registerModule(serverDealM)
    registerModule(talentM)
    registerModule(vipM)
    registerModule(libraryM)
    registerModule(gmM)
    registerModule(taskM)
    registerModule(iconM)
    registerModule(mailM)
    registerModule(giftBagM)

    // 执行初始化
    modules.forEach { it.moduleInit() }
}

fun <T> registerModule(module: T) where T : IModule {
    modules.add(module)
}