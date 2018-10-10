package com.point18.slg2d.home.module.bank

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.Bank
import com.point18.slg2d.common.constg.BankAccelerate
import com.point18.slg2d.home.module.registerHeart

var bankCityM = BankModule()

class BankModule : IModule {
    override fun moduleInit() {
        registerHeart(Bank, BankHeartTriggerDeal())
        registerHeart(BankAccelerate, BankAccelerateHeartTriggerDeal())
    }
}