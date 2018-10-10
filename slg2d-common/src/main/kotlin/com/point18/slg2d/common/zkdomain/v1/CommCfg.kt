package com.point18.slg2d.common.zkdomain.v1

import com.point18.slg2d.common.baseg.ZkDomain

data class CommCfg(
    override var id: Long,

    var groupName: String, // 集群名
    var seedNodes: String, // 集群的AKKA节点地址
    var kafkaEps: String, // 集群的kafka消息队列地址
    var redisAddrs: String, // redis地址
    var redisPwd: String  // redis密码

) : ZkDomain{
    override var dataVersion: Int = 1
}