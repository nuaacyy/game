package com.point18.slg2d.common.zkdomain.v4

import com.point18.slg2d.common.baseg.ZkDomain

data class MgrInfo(
        override var id: Long,
        var version: Int
) : ZkDomain {
    override var dataVersion: Int = 4
}