package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class MgrInfo(
    override var id: Long,
    var version: Int
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}