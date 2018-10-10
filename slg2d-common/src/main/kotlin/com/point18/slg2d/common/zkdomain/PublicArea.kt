package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class PublicArea(
    override var id: Long,
    var dbName: String, // dbName
    var processNo: Int,       // 所属processNo
    var processId: Long,  // 属于哪个物理机
    var pltAreaNo: Long

) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}