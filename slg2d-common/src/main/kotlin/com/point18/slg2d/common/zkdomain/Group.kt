package com.point18.slg2d.common.zkdomain

import com.point18.slg2d.common.baseg.ZK_DATA_STRUCT_VERSION
import com.point18.slg2d.common.baseg.ZkDomain
import java.util.*

data class Group(
    override var id: Long,
    var groupName: String,
    var state: Int,
    var platforms: LinkedList<Int>,
    var rights: LinkedList<Int>,
    var qa: Int
) : ZkDomain {
    override var dataVersion: Int = ZK_DATA_STRUCT_VERSION
}