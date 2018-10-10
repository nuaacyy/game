package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.MERCHANT_SHIP_BUY_SURPRISE
import com.point18.slg2d.home.module.EventData

data class MerchantShipBuySurpriseEvent(val isSurprise: Boolean) : EventData(MERCHANT_SHIP_BUY_SURPRISE)

