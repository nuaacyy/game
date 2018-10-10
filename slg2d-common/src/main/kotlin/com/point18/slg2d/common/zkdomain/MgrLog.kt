package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class MgrLog(
    override var id: Long,
    var operator: String,      // 操作人
    var operateType: Int,         // 操作类型
    var operateTime: Long, // 操作日期
    var operateData: String,// 操作数据
    var operateIp: String,    // 操作数据
    var operateRt: Int          // 操作返回
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}