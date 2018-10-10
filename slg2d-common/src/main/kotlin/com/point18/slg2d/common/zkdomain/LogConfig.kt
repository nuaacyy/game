package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class LogConfig(
    override var id: Long,
    var zookeeperIp: String,
    var zookeeperPort: Int,
    var hbaseIp: String,
    var hbasePort: Int,
    var kafka: String,
    var logServerName: String,
    var columnFamilyName: String,
    var tableName: String,
    var columnName1: String,
    var columnName2: String
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}