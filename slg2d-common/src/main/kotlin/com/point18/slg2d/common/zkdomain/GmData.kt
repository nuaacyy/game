package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class GmData(
    override var id: Long,
    var rightsMap: MutableMap<Int, Int>,
    var sessionKey: Long,
    var getIP: String,
    var getAccName: String
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}