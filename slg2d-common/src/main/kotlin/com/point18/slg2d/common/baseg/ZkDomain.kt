package com.point18.slg2d.common.baseg

// zookeeper里面的数据结构版本 , 每次版本加1
const val ZK_DATA_STRUCT_VERSION = 6

interface ZkDomain {
    var id: Long
    val dataVersion: Int
}