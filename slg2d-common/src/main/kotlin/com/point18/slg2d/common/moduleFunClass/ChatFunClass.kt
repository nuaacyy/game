package com.point18.slg2d.common.moduleFunClass

import java.io.Serializable

class SimplifiedFightInfo(
    var reportType: Int, // 魔物,集结战报,侦察等,参考下面的reportType
    var mainPlayer: String, // 右边人的名字
    var mainPlayerAlliance: String, // 右边人的联盟
    var atkOrDef: Int, // 攻击或防守
    var targetName: String, // 魔物或者对手的名字
    var allianceOrLv: String, // 魔物等级或者对手的联盟名
    var reportId: Long, // report战报id
    var mainIconId: Int,
    var iconId: Int,
    var monster: Int,    // 魔物模板id
    var worldId: Long    // 战报所在的服务器
) : Serializable

class SimplifiedMassInfo(
    var massId: Long,  // 集结id
    var massName: String
) : Serializable

class LocationShareInfo(
    var areaNo: Int, // 服务器
    var x: Int,      // x
    var y: Int,      // y
    var locationName: String  // 分享的名字
) : Serializable