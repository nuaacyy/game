package com.point18.slg2d.common.configuration

import org.apache.curator.framework.CuratorFramework
import xyz.ariane.util.ZkNodeObjectCache

/**
 * MySQL数据源配置
 */
class ZkDatasourceConfig {
    /** 物理分片id */
    var shardId: Int = -1

    /** ip:port */
    lateinit var socket: String

    /** 数据库名 */
    lateinit var database: String

    /** 用户名 */
    lateinit var user: String

    /** 密码 */
    lateinit var password: String
}

/** 数据源配置 */
data class ZkDataSourceConfigList(val datasources: ArrayList<ZkDatasourceConfig> = ArrayList())

/** IP对应数据源配置的容器 */
data class ZkDsConfigIpMap(val dsMap: Map<String, ZkDataSourceConfigList> = hashMapOf())

/** 数据源节点数据缓存 */
class ZkDataSourceConfigCache(zk: CuratorFramework, path: String, onUpdate: (ZkDsConfigIpMap) -> Unit) :
        ZkNodeObjectCache<ZkDsConfigIpMap>(zk, path, ZkDsConfigIpMap::class.java, onUpdate)