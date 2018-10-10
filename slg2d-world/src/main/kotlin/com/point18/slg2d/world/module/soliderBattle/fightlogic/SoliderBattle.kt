package com.point18.slg2d.world.module.soliderBattle.fightlogic

import com.point18.slg2d.common.constg.FIGHT_RESULT_ING
import com.point18.slg2d.common.constg.FIGHT_RESULT_LOSE
import com.point18.slg2d.common.constg.FIGHT_RESULT_WIN

// 士兵战行为单例
val soliderBattleAction = SoliderBattle()

val onceMode = BattleMode()

// 士兵战
class SoliderBattle : IBattleAction {
    override fun beforeBattle(logic: SoliderFightLogic) {
        logic.fightState = FIGHT_RESULT_ING
    }

    override fun doBattle(logic: SoliderFightLogic) {
        onceMode.doBattle(logic)
    }

    override fun checkState(logic: SoliderFightLogic): Int {
        // 以一方英雄全灭未胜利条件
        var hasAtk = false
        var hasDef = false
        for (gezi in logic.fightGeziList) {
            for (unit in gezi.battleUnits) {
                if (gezi.isLeft) {
                    if (unit.troopsBeforeFight > 0) {
                        hasAtk = true
                    }
                } else {
                    if (unit.troopsBeforeFight > 0) {
                        hasDef = true
                    }
                }
            }
        }
        var state = FIGHT_RESULT_ING
        if (!hasAtk) {
            state = FIGHT_RESULT_LOSE
        }
        if (!hasDef) {
            state = FIGHT_RESULT_WIN
        }
        logic.fightState = state
        return state
    }

}
