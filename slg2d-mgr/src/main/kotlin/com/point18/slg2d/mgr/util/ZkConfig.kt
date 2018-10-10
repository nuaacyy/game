package com.point18.slg2d.mgr.util

data class ZkConfig(
    var zkHost: String,
    var zkPort: Int,
    val rootNode: String,
    var clear: Boolean
)