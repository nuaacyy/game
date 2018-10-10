package com.point18.slg2d.common.zkdomain.v4

import com.point18.slg2d.common.baseg.ZkDomain

data class ServerProcess(
    override var id: Long,
    var processIp: String, // ip
    var procesName: String,
    var httpAddr: String,
    var httpPort: Int,
    var tcpAddr: String,
    var tcpPort: Int,
    var seedPort: Int,
    var platformId: Long,  // 平台的id
    var processType: Int,
    var kafkaPId: Int,
    var processNum: Int,  // 最多10个！否则kafka的分区ID会重复
    var clusterId: Long,   //   集群分组id
    var seedNode: Int // 是否是seednode，0不是，其他值是
) : ZkDomain {
    override var dataVersion: Int = 4
}