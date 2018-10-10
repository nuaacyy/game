package com.point18.slg2d.world.module.soliderBattle.fightlogic

data class BattleUnit(
    var protoId: Int, //配置Id 英雄/士兵
    var gongji: Double, //物理攻击力
    var fangyu: Double, //物理防御力
    var troopsBeforeFight: Int, //战斗前兵力
    var troopsAfterFight: Double, //战斗后兵力
    var killMap: HashMap<Int, Double> //击杀列表
)

fun newBattleUnit(gezi: FightGezi): BattleUnit {
    val unit = BattleUnit(
        0, 0.0, 0.0, 0, 0.0, hashMapOf()
    )
    gezi.battleUnits.add(unit)
    return unit
}
