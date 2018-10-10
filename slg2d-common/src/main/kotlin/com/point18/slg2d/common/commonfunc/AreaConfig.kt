package com.point18.slg2d.common.commonfunc

class AreaConfig {
    var areaId: Long = 0 // 游戏区在后台的唯一ID,合服和生成ID的关键数据

    var areaNo: Int = 0   // 游戏区worldID,标记玩家在哪个服(worldId)
    var areaName: String = ""// 游戏区名
    var pltAreaNo: Long = 0 // 游戏区在运营平台上的唯一编号(对于平台是唯一的,对于所有服务器有重复的)

    var opgameId: Int = 0// 混服组 id
    var areaState: Int = 0            // 游戏区状态
    var deployState: Boolean = false // 是否已经部署了
    var processId = 0
    var clusterId: Long = 0 // 所在集群ID

    // 下面是游戏区对应的数据库配置
    var payKey: String = ""
    var loginKey: String = ""
    var giftKey: String = ""// 这个key目前只有在礼包码那边用到了.协议文档里叫server_key
    var areaPublishTime: Long = 0 // 开服时间
    var nowSeason: Int = 0 // todo 游戏区赛季ID
}