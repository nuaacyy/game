package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain

data class IpHostCfg(
    override var id: Long,
    var gameId: Long,
    var processNum: Int, // 最多10个！否则kafka的分区ID会重复
    var procesName: String,
    var processIp: String,
    var kafkaAddr: String,
    var kafkaPort: Int,
    var shieldingWords: Any,
    var httpAddr: String,
    var httpPort: Int,
    var tcpAddr: String,
    var tcpPort: Int,
    var rpcPort: Int,
    var processType: Int,
    var dbHost: String,
    var dbPort: Int,
    var dbUser: String,
    var dbPwd: String,
    var areas: String,
    var processInnerAddr: String,
    var addresses: String,
    var groupId: Long,
    var kafkaEps: String,
    var kafkaPId: Int // 进程所属分区ID，10的整数倍！
    //上面是原来拉配置需要的字段，下面是proccess的部分字段，不知道作用
//    var logServerId: Long,
//    var clusterId: Long ,
//    var platformId: Long

) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}