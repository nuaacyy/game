package com.point18.slg2d.common.zkdomain.v4

import com.point18.slg2d.common.baseg.ZkDomain

data class CommCfg(
    override var id: Long,

    var groupName: String, // 集群名
    var seedNodes: String // 集群的AKKA节点地址

) : ZkDomain{
    override var dataVersion: Int = 4
}