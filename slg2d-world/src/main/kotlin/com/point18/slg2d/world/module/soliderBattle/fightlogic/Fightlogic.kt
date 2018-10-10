package com.point18.slg2d.world.module.soliderBattle.fightlogic

import com.point18.slg2d.common.constg.FIGHT_RESULT_ING
import com.point18.slg2d.common.constg.FIGHT_RESULT_LOSE
import com.point18.slg2d.common.constg.FIGHT_RESULT_WIN
import com.point18.slg2d.common.constg.SoliderBattle
import com.point18.slg2d.world.module.soliderBattle.fightdomain.SoliderFightInfoData
import com.point18.slg2d.world.module.soliderBattle.fightdomain.newFightInfoD
import java.util.*

interface IBattleAction {
    // 战斗准备
    fun beforeBattle(logic: SoliderFightLogic)

    // 战斗
    fun doBattle(logic: SoliderFightLogic)

    // 检测战斗状态
    fun checkState(logic: SoliderFightLogic): Int
}

class SoliderFightLogic(
    var fightState: Int,// 战斗结果 0-进攻中  1-进攻方全灭  2-防守方全灭  3-规定回合未结束
    var fightGeziList: LinkedList<FightGezi>,// 部队信息
    var newSoliderFightResp: SoliderFightInfoData, // 新版本详细战报
    var battleAction: IBattleAction // 针对不同战斗的内部处理
){
    /**
    战斗逻辑
     */
    fun mainFight() {
        // 准备回合
        this.fightReady()

        // 战斗主逻辑,返回战斗结果
        this.fight()
    }

    /**
    战斗准备回合
     */
    fun fightReady() {
        // 战前处理
        this.battleAction.beforeBattle(this)
    }

    fun fight() {
        // 检测当前的战斗状态
        this.battleAction.checkState(this)
        if (this.fightState == FIGHT_RESULT_LOSE || this.fightState == FIGHT_RESULT_WIN) {
            this.newSoliderFightResp.fightResult = this.fightState
            return
        }

        this.battleAction.doBattle(this)
        if (this.fightState == FIGHT_RESULT_LOSE || this.fightState == FIGHT_RESULT_WIN) {
            this.newSoliderFightResp.fightResult = this.fightState
            return
        }

    }
}

fun createSoliderFightLogic(reportType: Int): SoliderFightLogic {
    val fightLogic = SoliderFightLogic(
        FIGHT_RESULT_ING,
        LinkedList(),
        newFightInfoD(),
        soliderBattleAction
    )
    fightLogic.newSoliderFightResp.reportType = reportType
    fightLogic.newSoliderFightResp.fightType = SoliderBattle

    return fightLogic
}
