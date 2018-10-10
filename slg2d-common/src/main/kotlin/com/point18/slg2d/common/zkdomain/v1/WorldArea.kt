package com.point18.slg2d.common.zkdomain.v1

import com.point18.slg2d.common.baseg.ZkDomain

data class WorldArea(

    override var id: Long,

    var areaNo: Int,
    var pltAreaNo: Long,
    var areaState: Int,
    var gameAreaName: String,
    var whiteRosters: String,
    var blackRosters: String,
    var opgameId: Int,
    var payKey: String,
    var loginKey: String,
    var giftKey: String,
    var openAreaDate: Long,
    var deployState: Int,
    var clusterId: Long  // 所在集群Id

) : ZkDomain {
    override var dataVersion: Int = 1
}