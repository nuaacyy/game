package com.point18.slg2d.home.module.event

import com.point18.slg2d.common.constg.BUY_SHOP_TOTAL
import com.point18.slg2d.home.module.EventData

data class BuyShopTotalEvent(
    val shopType: Int // 商店类型
) : EventData(BUY_SHOP_TOTAL)

