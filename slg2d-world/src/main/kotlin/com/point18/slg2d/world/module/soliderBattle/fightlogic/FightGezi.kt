package com.point18.slg2d.world.module.soliderBattle.fightlogic

import java.util.*

// 部队中格子
data class FightGezi(
    var fightLogic: SoliderFightLogic,
    var playerId: Long,
    var isLeft: Boolean, // 是否是左进攻
    var battleUnits: LinkedList<BattleUnit> //战斗单位
)

fun newFightGezi(fightLogic: SoliderFightLogic): FightGezi {
    return FightGezi(fightLogic, 0L, false, LinkedList())
}
