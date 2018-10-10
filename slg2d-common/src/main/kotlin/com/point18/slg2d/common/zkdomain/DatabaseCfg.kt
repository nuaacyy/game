package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain
import java.util.*

class ZkDatabaseConfig(
    val shardId: Long,
    val socket: String,
    val database: String,
    val user: String,
    val password: String
)

class DataSourceList(
    val datasourceList: ZkDatabaseConfig,
    var dataSources: LinkedList<ZkDatabaseConfig>?,
    var clusterId: Long // 所属集群ID
)

data class DataSource(
    override var id: Long,

    val dsMap: MutableMap<String, DataSourceList>
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}