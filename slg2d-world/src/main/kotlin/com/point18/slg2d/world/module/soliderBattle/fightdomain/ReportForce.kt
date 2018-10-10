package com.point18.slg2d.world.module.soliderBattle.fightdomain

import java.util.*

// 阵容信息
data class ReportSoliderForce(
    var leftOrRight: Int,                //`json:"leftOrRight"`  //是否是进攻方  0-是 1-不是
    var protoId: Int,                //`json:"protoId"` //士兵的模板ID
    var bingliBeforeFight: Int,        //`json:"bingliBeforeFight"` //战斗前兵力
    var bingliAfterFight: Int,        //`json:"bingliAfterFight"`  //战斗后兵力
    var killData: LinkedList<KillInfo>       //`json:"kill_data"` //击杀数据
)

data class KillInfo(
    var soliderId: Int,// `json:"solider_id"`
    var killNum: Double// `json:"kill_num"`
)

fun newReportSoliderForce(): ReportSoliderForce {
    return ReportSoliderForce(0, 0, 0, 0,  LinkedList())
}

data class ReportHeroForce(
    var leftOrRight: Int,//`json:"leftOrRight"`    //是否是进攻方  0-是 1-不是
    var protoId: Int,//`json:"protoId"`   //英雄模板ID
    var heroPower: Long,//`json:"heroPower"` //英雄战力
    var king: Boolean// `json:"isKing"`    //是否是领主
)

fun newReportHeroForce(): ReportHeroForce {
    return ReportHeroForce(0, 0, 0, false)
}

data class ReportFightPlayerInfo(
    var playerId: Long,//`json:"player_id"`
    var playerName: String,//`json:"player_name"`
    var allianceName: String,//`json:"alliance_name"`
    var relicId: Int,//`json:"relic_id"`
    var strongholdId: Int,    //`json:"stronghold_id"`
    var lordProtoId: Int
)

fun newReportFightPlayerInfo(
    playerId: Long,
    playerName: String,
    allianceName: String,
    relicId: Int,
    strongholdId: Int,
    lordProtoId: Int = 0
): ReportFightPlayerInfo {
    return ReportFightPlayerInfo(playerId, playerName, allianceName, relicId, strongholdId, lordProtoId)
}
