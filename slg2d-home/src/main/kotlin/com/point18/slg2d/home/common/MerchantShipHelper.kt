package com.point18.slg2d.home.common

import com.point18.slg2d.home.dc.MerchantShipInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

data class MerchantShipRefreshResult(
    val result: ResultCode,
    val refreshInfo: HashMap<Int, MerchantShipInfo>?
)

// 刷新商船信息
fun merchantShipRefresh(castleLv: Int): MerchantShipRefreshResult {

    val shipProto = pcs.tradShipRefreshProtoCache.tradShipRefreshProtoMapByGroup[castleLv]
    if (shipProto == null) {

        return MerchantShipRefreshResult(ResultCode.NO_PROTO, null)
    }

    val refreshInfo = hashMapOf<Int, MerchantShipInfo>()
    val groupId = com.point18.slg2d.common.pc.findValueFromDropBag(shipProto.groupMap)

    val groups = pcs.tradShipProtoCache.tradShipProtoMapByGroup[groupId]
    if (groups == null || groups.count() != 4) {
        return MerchantShipRefreshResult(ResultCode.NO_PROTO, null)
    }

    for (group in groups) {
        refreshInfo[group.grid] = MerchantShipInfo(
            group.grid, 1, group.id, 0, 0
        )
    }

    return MerchantShipRefreshResult(ResultCode.SUCCESS, refreshInfo)
}
