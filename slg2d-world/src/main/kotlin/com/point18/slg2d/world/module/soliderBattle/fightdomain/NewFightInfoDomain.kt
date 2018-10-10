package com.point18.slg2d.world.module.soliderBattle.fightdomain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*

fun newFightInfoD(): SoliderFightInfoData {
    return SoliderFightInfoData(
        0, 0, 0,
        newReportFightPlayerInfo(0L, "", "", 0, 0), newReportFightPlayerInfo(0L, "", "", 0, 0),
        LinkedList(), LinkedList(), hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf()
    )
}

// 一份详细战报结构体
data class SoliderFightInfoData(
    var fightType: Int,// `json:"fight_type"`   //战斗类别
    var fightResult: Int,// `json:"fight_result"` //战斗结果
    var reportType: Int,// `json:"report_type"`  //战报类别

    var atkFightPlayerInfo: ReportFightPlayerInfo,// `json:"atk_fight_player_info"` //攻击方信息
    var defFightPlayerInfo: ReportFightPlayerInfo,// `json:"def_fight_player_info"` //防守方信息

    var soliderForceInfo: LinkedList<ReportSoliderForce>,//`json:"solider_force_info"`    //当前阵容信息
    var heroForceInfo: LinkedList<ReportHeroForce>,//`json:"hero_force_info"`       //当前阵容信息

    @JsonIgnore
    var atkSoliderForceMap: HashMap<Int, ReportSoliderForce>,//`json:"-"`
    @JsonIgnore
    var atkHeroForceMap: HashMap<Int, ReportHeroForce>,//`json:"-"`
    @JsonIgnore
    var defSoliderForceMap: HashMap<Int, ReportSoliderForce>,//`json:"-"`
    @JsonIgnore
    var defHeroForceMap: HashMap<Int, ReportHeroForce>    //`json:"-"`
)
