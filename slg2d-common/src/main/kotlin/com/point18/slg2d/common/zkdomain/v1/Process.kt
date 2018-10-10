package com.point18.slg2d.common.zkdomain.v1

import com.point18.slg2d.common.baseg.ZkDomain

data class Process(
    override var id: Long,
    var processIp: String, // ip
    var procesName: String,
    var httpAddr: String,
    var httpPort: Int,
    var tcpAddr: String,
    var tcpPort: Int,
    var rpcPort: Int,
    var platformId: Long,  // 平台的id
    var processType: Int,
    var kafkaPId: Int,
    var processNum: Int,  // 最多10个！否则kafka的分区ID会重复
    var clusterId: Long   //   集群分组id

) : ZkDomain {
    override var dataVersion: Int = 1
}