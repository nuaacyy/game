package com.point18.slg2d.common.zkdomain.v5

import com.point18.slg2d.common.baseg.ZkDomain

data class CommCfg(
    override var id: Long,

    var groupName: String, // 集群名
    var seedNodes: String, // 集群的AKKA节点地址
    var kafkaAddrs: String = "", // 集群的日志kafka会聚地址
    var dcLogTopic: String = "" // dc日志的topic

) : ZkDomain {
    override var dataVersion: Int = 5
}