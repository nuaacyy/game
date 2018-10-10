package com.point18.slg2d.world.module.soliderBattle.fightlogic

import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FIGHT_RESULT_LOSE
import com.point18.slg2d.common.constg.FIGHT_RESULT_WIN
import com.point18.slg2d.common.constg.isSolider
import com.point18.slg2d.common.constg.isTrap
import com.point18.slg2d.common.pc.pcs
import java.util.*

class BattleMode {
    //中间计算战斗单位
    data class MiddleBattleUnit(
        val unit: BattleUnit,           //战斗单位基本数据
        var originalSoliderNum: Double,      //初始的士兵数量
        var currentSoliderNum: Double,   //当前士兵数量
        var killMap: HashMap<Int, Double> = hashMapOf() //击杀列表
    )

    fun doBattle(logic: SoliderFightLogic) {
        val atkUnitsMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        val defUnitsMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        val atkSoliderMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        val atkTrapMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        val defSoliderMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        val defTrapMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        for (gezi in logic.fightGeziList) {
            for (unit in gezi.battleUnits) {
                if (unit.troopsBeforeFight == 0) {
                    continue
                }
                val solider = pcs.soliderCache.soliderProtoMap[unit.protoId]
                if (solider == null) {
                    com.point18.slg2d.common.commonfunc.normalLog.error("找不到士兵配置:%d", unit.protoId)
                    return
                }
                val unitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>
                var soliderOrTrapMap = atkSoliderMap
                if (gezi.isLeft) {
                    if (isSolider(solider.armyType)) {
                        soliderOrTrapMap = atkSoliderMap
                    } else if (isTrap(solider.armyType)) {
                        soliderOrTrapMap = atkTrapMap
                    }
                    unitsMap = atkUnitsMap
                } else {
                    if (isSolider(solider.armyType)) {
                        soliderOrTrapMap = defSoliderMap
                    } else if (isTrap(solider.armyType)) {
                        soliderOrTrapMap = defTrapMap
                    }
                    unitsMap = defUnitsMap
                }
                val soliderOrTrapList = soliderOrTrapMap.getOrPut(solider.armyType) { LinkedList() }
                soliderOrTrapList.add(
                    MiddleBattleUnit(
                        unit,
                        unit.troopsBeforeFight.toDouble(),
                        unit.troopsBeforeFight.toDouble()
                    )
                )
                val unitList = unitsMap.getOrPut(solider.armyType) { LinkedList() }
                unitList.add(
                    MiddleBattleUnit(
                        unit,
                        unit.troopsBeforeFight.toDouble(),
                        unit.troopsBeforeFight.toDouble()
                    )
                )
            }
        }

        //进行排序
        this.unitMapSort(atkSoliderMap)
        this.unitMapSort(atkTrapMap)
        this.unitMapSort(defSoliderMap)
        this.unitMapSort(defTrapMap)

        val atkKillRecord = hashMapOf<Int, HashMap<Int, Double>>()
        val defKillRecord = hashMapOf<Int, HashMap<Int, Double>>()

        var index = 0
        var leftAtkUnitsMap = atkUnitsMap
        var leftDefSoliderMap = defSoliderMap
        var leftDefTrapMap = defTrapMap
        while (true) {
            leftAtkUnitsMap = getLeftUnitMap(leftAtkUnitsMap)
            leftDefSoliderMap = getLeftUnitMap(leftDefSoliderMap)
            leftDefTrapMap = getLeftUnitMap(leftDefTrapMap)
            //判断攻防方是否有部队
            if (leftAtkUnitsMap.isEmpty()) {
                break
            }
            if (leftDefSoliderMap.isEmpty() && leftDefTrapMap.isEmpty()) {
                break
            }

            calHurt(leftAtkUnitsMap, leftDefSoliderMap, leftDefTrapMap, atkKillRecord)

            if (index++ > 20) {
                normalLog.lzWarn { "计算攻击方战斗超过20次，很可能已出错" }
                break
            }
        }
        index = 0
        var leftDefUnitsMap = defUnitsMap
        var leftAtkSoliderMap = atkSoliderMap
        var leftAtkTrapMap = atkTrapMap
        while (true) {
            leftDefUnitsMap = getLeftUnitMap(leftDefUnitsMap)
            leftAtkSoliderMap = getLeftUnitMap(leftAtkSoliderMap)
            leftAtkTrapMap = getLeftUnitMap(leftAtkTrapMap)
            //判断攻防方是否有部队
            if (leftDefUnitsMap.isEmpty()) {
                break
            }
            if (leftAtkSoliderMap.isEmpty() && leftAtkTrapMap.isEmpty()) {
                break
            }
            calHurt(leftDefUnitsMap, leftAtkSoliderMap, leftAtkTrapMap, defKillRecord)

            if (index++ > 20) {
                normalLog.lzWarn { "计算防守方战斗超过20次，很可能已出错" }
                break
            }
        }
        var atkLeftNum = 0.0
        var defLeftNum = 0.0
        var atkLoseNum = 0.0
        var defLoseNum = 0.0
        for ((_, unitList) in atkSoliderMap) {
            unitList.forEach {
                val unit = it.unit
                unit.troopsAfterFight = it.currentSoliderNum
                unit.killMap = atkKillRecord[unit.protoId] ?: hashMapOf()
                atkLeftNum += it.currentSoliderNum
                atkLoseNum += it.originalSoliderNum - it.currentSoliderNum
            }
        }
        for ((_, unitList) in atkTrapMap) {
            unitList.forEach {
                val unit = it.unit
                unit.troopsAfterFight = it.currentSoliderNum
                unit.killMap = atkKillRecord[unit.protoId] ?: hashMapOf()
                atkLeftNum += it.currentSoliderNum
                atkLoseNum += it.originalSoliderNum - it.currentSoliderNum
            }
        }
        for ((_, unitList) in defSoliderMap) {
            unitList.forEach {
                val unit = it.unit
                unit.troopsAfterFight = it.currentSoliderNum
                unit.killMap = defKillRecord[unit.protoId] ?: hashMapOf()
                defLeftNum += it.currentSoliderNum
                defLoseNum += it.originalSoliderNum - it.currentSoliderNum
            }
        }
        for ((_, unitList) in defTrapMap) {
            unitList.forEach {
                val unit = it.unit
                unit.troopsAfterFight = it.currentSoliderNum
                unit.killMap = defKillRecord[unit.protoId] ?: hashMapOf()
                defLeftNum += it.currentSoliderNum
                defLoseNum += it.originalSoliderNum - it.currentSoliderNum
            }
        }
        if (atkLeftNum == 0.0) {
            logic.fightState = FIGHT_RESULT_LOSE
            return
        }
        if (defLeftNum == 0.0) {
            logic.fightState = FIGHT_RESULT_WIN
            return
        }
        if (atkLoseNum >= defLoseNum) {
            logic.fightState = FIGHT_RESULT_LOSE
            return
        }
        logic.fightState = FIGHT_RESULT_WIN
    }

    //单位排序
    private fun unitMapSort(unitMap: HashMap<Int, LinkedList<MiddleBattleUnit>>) {
        for ((_, units) in unitMap) {
            units.sortWith(Comparator { a, b ->
                val solider1 = pcs.soliderCache.soliderProtoMap[a.unit.protoId]
                val solider2 = pcs.soliderCache.soliderProtoMap[b.unit.protoId]
                if (solider1 == null || solider2 == null) {
                    return@Comparator 0
                }
                return@Comparator solider2.step - solider1.step
            })
        }
    }

    //计算伤害
    private fun calHurt(
        atkUnitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>,
        defSoliderMap: HashMap<Int, LinkedList<MiddleBattleUnit>>,
        defTrapMap: HashMap<Int, LinkedList<MiddleBattleUnit>>,
        killRecord: HashMap<Int, HashMap<Int, Double>>
    ) {
        if (defSoliderMap.isEmpty() && defTrapMap.isEmpty()) {
            return
        }
        //攻击方按照士兵类别平分
        var avgDivideNum = 0
        if (defSoliderMap.isNotEmpty()) {
            avgDivideNum++
        }
        if (defTrapMap.isNotEmpty()) {
            avgDivideNum++
        }
        val avgDivideMap = divideUnitsMap(atkUnitsMap, avgDivideNum)

        atkUnitsMap.clear()

        //士兵平分
        if (defSoliderMap.isNotEmpty()) {
            //计算对战伤害
            for ((defArmyType, defUnitList) in defSoliderMap) {
                //攻击方平均值
                val soliderAvgDivideMap = divideUnitsMap(avgDivideMap, defSoliderMap.count())

                //防守方受击顺序
                val getHitOrderProto = pcs.getHitOrderCache.getHitOrderMap[defArmyType]
                if (getHitOrderProto == null) {
                    normalLog.error("攻击顺序配置中不存在:%d", defArmyType)
                    continue
                }
                for (priorArmyType in getHitOrderProto.atkOrder) {
                    val atkUnitList = soliderAvgDivideMap[priorArmyType] ?: continue
                    for (atkUnit in atkUnitList) {
                        for (defUnit in defUnitList) {
                            if (defUnit.currentSoliderNum == 0.0) {
                                continue
                            }
                            val needAtkNum = calNeedAtkNum(atkUnit, defUnit)
                            if (needAtkNum <= atkUnit.currentSoliderNum) {
                                //所需的攻击兵数量少于剩余的，当前防守战斗单位跪
                                atkUnit.currentSoliderNum -= needAtkNum
                                val killMap = killRecord.getOrPut(atkUnit.unit.protoId) { hashMapOf() }
                                killMap[defUnit.unit.protoId] = (killMap[defUnit.unit.protoId] ?: 0.0) +
                                    defUnit.currentSoliderNum
                                defUnit.currentSoliderNum = 0.00
                                break
                            }
                            val canAtkNum = calAttackDefNum(atkUnit, defUnit)
                            atkUnit.currentSoliderNum = 0.0
                            val killMap = killRecord.getOrPut(atkUnit.unit.protoId) { hashMapOf() }
                            killMap[defUnit.unit.protoId] = (killMap[defUnit.unit.protoId] ?: 0.0) +
                                canAtkNum
                            defUnit.currentSoliderNum -= canAtkNum
                        }
                    }
                }

                //剩余攻击方士兵合并
                combineUnitsMap(atkUnitsMap, soliderAvgDivideMap)
            }
        }
        //陷阱平分
        if (defTrapMap.isNotEmpty()) {
            //计算对战伤害
            for ((defArmyType, defUnitList) in defTrapMap) {
                val trapAvgDivideMap = divideUnitsMap(avgDivideMap, defTrapMap.count())

                //防守方受击顺序
                val getHitOrderProto = pcs.getHitOrderCache.getHitOrderMap[defArmyType]
                if (getHitOrderProto == null) {
                    normalLog.error("攻击顺序配置中不存在:%d", defArmyType)
                    continue
                }
                for (priorArmyType in getHitOrderProto.atkOrder) {
                    val atkUnitList = trapAvgDivideMap[priorArmyType] ?: continue
                    for (atkUnit in atkUnitList) {
                        for (defUnit in defUnitList) {
                            if (defUnit.currentSoliderNum == 0.0) {
                                continue
                            }
                            val needAtkNum = calNeedAtkNum(atkUnit, defUnit)
                            if (needAtkNum <= atkUnit.currentSoliderNum) {
                                //所需的攻击兵数量少于剩余的，当前防守战斗单位跪
                                atkUnit.currentSoliderNum -= needAtkNum
                                defUnit.currentSoliderNum = 0.00
                                break
                            }
                            val canAtkNum = calAttackDefNum(atkUnit, defUnit)
                            atkUnit.currentSoliderNum = 0.0
                            defUnit.currentSoliderNum -= canAtkNum
                        }
                    }
                }

                //剩余攻击方士兵合并
                combineUnitsMap(atkUnitsMap, trapAvgDivideMap)
            }
        }
    }

    private fun divideUnitsMap(
        unitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>,
        divideNum: Int
    ): HashMap<Int, LinkedList<MiddleBattleUnit>> {
        assert(divideNum > 0)
        val avgMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        for ((armyType, unitList) in unitsMap) {
            unitList.forEach {
                if (it.currentSoliderNum == 0.0) {
                    return@forEach
                }
                avgMap.getOrPut(armyType) { LinkedList() }
                    .add(MiddleBattleUnit(it.unit, it.originalSoliderNum, it.currentSoliderNum / divideNum))
            }
        }
        return avgMap
    }

    //单位合并
    private fun combineUnitsMap(
        combinedUnitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>,
        unitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>
    ) {
        for ((armyType, unitList) in unitsMap) {
            unitList.forEach {
                if (it.currentSoliderNum == 0.0) {
                    return@forEach
                }
                val cUnitList = combinedUnitsMap.getOrPut(armyType) { LinkedList() }
                val sameUnit = cUnitList.firstOrNull { cUnit -> cUnit.unit.protoId == it.unit.protoId }
                if (sameUnit == null) {
                    cUnitList.add(MiddleBattleUnit(it.unit, it.originalSoliderNum, it.currentSoliderNum))
                    return@forEach
                }
                sameUnit.currentSoliderNum += it.currentSoliderNum
            }
        }
    }

    private fun getLeftUnitMap(unitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>): HashMap<Int, LinkedList<MiddleBattleUnit>> {
        val leftUnitsMap = hashMapOf<Int, LinkedList<MiddleBattleUnit>>()
        for ((armyType, unitList) in unitsMap) {
            unitList.forEach {
                if (it.currentSoliderNum == 0.0) {
                    return@forEach
                }
                leftUnitsMap.getOrPut(armyType) { LinkedList() }.add(it)
            }
        }
        return leftUnitsMap
    }

    //清理死亡的单位
    private fun clearDeadUnit(unitsMap: HashMap<Int, LinkedList<MiddleBattleUnit>>) {
        val toRemoveArmy = hashSetOf<Int>()
        for ((armyType, unitList) in unitsMap) {
            unitList.removeIf { it.currentSoliderNum == 0.0 }
            if (unitList.isEmpty()) {
                toRemoveArmy.add(armyType)
            }
        }
        toRemoveArmy.forEach {
            unitsMap.remove(it)
        }
    }

    //计算中间伤害结果
    //返回 击杀的防守方数量
    fun calMiddleHurtResult(atkUnit: MiddleBattleUnit, defUnit: MiddleBattleUnit): Double {
        var killedNum = 0.0

        val defSolider = pcs.soliderCache.soliderProtoMap[defUnit.unit.protoId]
        if (defSolider == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("士兵配置中不存在:%d", defUnit.unit.protoId)
            return killedNum
        }
        val singleHurt = calSingleHurt(atkUnit.unit, defUnit.unit)

        //最大可攻击数量
        val canAtkNum = singleHurt * atkUnit.currentSoliderNum / defSolider.hp

        //需要的攻击数量
        val needAtkNum = defUnit.currentSoliderNum * defSolider.hp / singleHurt

        if (canAtkNum >= defUnit.currentSoliderNum) {
            //攻击方有剩余
            killedNum = defUnit.currentSoliderNum
            defUnit.currentSoliderNum = 0.0
            atkUnit.currentSoliderNum -= needAtkNum
        } else {
            //防守方有剩余
            killedNum = canAtkNum
            atkUnit.currentSoliderNum = 0.0
            defUnit.currentSoliderNum -= canAtkNum
        }
        return killedNum
    }

    //计算单兵伤害
    private fun calSingleHurt(atkUnit: BattleUnit, defUnit: BattleUnit): Double {
        val soliderAttackPara = pcs.basicProtoCache.soliderAttackPara
        val soliderDefendPara = pcs.basicProtoCache.soliderDefendPara
        val minAttDefDiff = pcs.basicProtoCache.minAttDefDiff
        val soliderHarmPara = pcs.basicProtoCache.soliderHarmPara
        val atkSolider = pcs.soliderCache.soliderProtoMap[atkUnit.protoId]
        if (atkSolider == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("士兵配置中不存在:%d", atkUnit.protoId)
            return 0.0
        }

        val defSolider = pcs.soliderCache.soliderProtoMap[defUnit.protoId]
        if (defSolider == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("士兵配置中不存在:%d", defUnit.protoId)
            return 0.0
        }

        val restrainMap = pcs.armyRestrainCache.armyRestrainMap[atkSolider.armyType]
        if (restrainMap == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("克制系数配置中攻击方不存在:%d", atkSolider.armyType)
            return 0.0
        }

        val restrainPara = restrainMap[defSolider.armyType]
        if (restrainPara == null) {
            com.point18.slg2d.common.commonfunc.normalLog.error("克制系数配置中防守方不存在:%d", defSolider.armyType)
            return 0.0
        }

        return Math.max(
            atkUnit.gongji * soliderAttackPara - defUnit.fangyu * soliderDefendPara,
            minAttDefDiff
        ) * soliderHarmPara * (restrainPara.restrainPara) / 10000
    }

    //计算所需攻击单位数量
    private fun calNeedAtkNum(atkUnit: MiddleBattleUnit, defUnit: MiddleBattleUnit): Double {
        val singleHurt = calSingleHurt(atkUnit.unit, defUnit.unit)
        val defSolider = pcs.soliderCache.soliderProtoMap[defUnit.unit.protoId]
        if (defSolider == null) {
            normalLog.error("士兵配置中不存在:%d", defUnit.unit.protoId)
            return 0.0
        }

        return defUnit.currentSoliderNum * defSolider.hp / singleHurt
    }

    //计算可攻击的防守方数量
    private fun calAttackDefNum(atkUnit: MiddleBattleUnit, defUnit: MiddleBattleUnit): Double {
        val singleHurt = calSingleHurt(atkUnit.unit, defUnit.unit)
        val defSolider = pcs.soliderCache.soliderProtoMap[defUnit.unit.protoId]
        if (defSolider == null) {
            normalLog.error("士兵配置中不存在:%d", defUnit.unit.protoId)
            return 0.0
        }

        return singleHurt * atkUnit.currentSoliderNum / defSolider.hp
    }
}

