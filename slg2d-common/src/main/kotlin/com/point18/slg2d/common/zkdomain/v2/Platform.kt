package com.point18.slg2d.common.zkdomain.v2

import com.point18.slg2d.common.baseg.ZkDomain

data class Platform(
    override var id: Long,
    var opId: Long,
    var name: String,
    var gameId: Long

) : ZkDomain{
    override var dataVersion: Int = 2
}