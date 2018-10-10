package com.point18.slg2d.world.module.fightdomain

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.isWonderWar
import com.point18.slg2d.world.event.PrisonEvent
import com.point18.slg2d.world.wpm
import pb4client.TestFightData
import xyz.ariane.util.lzDebug
import java.util.*

//战报的奖励数据
data class RewardInfoForReport(
    var kingExp: Int,        //君主经验
    var heroExp: HashMap<Int, Int>,   //每个武将获得的经验
    var resVos: LinkedList<ResVo>   //资源道具奖励
)

fun createRewardInfoForReport(): RewardInfoForReport {
    return RewardInfoForReport(0, hashMapOf(), LinkedList())
}

// 战斗数据 +++++++++++++++++++++++++++++++++
class FightData(
    var playerId: Long,                  //主城玩家ID
    var groupId: Long,            //行军组ID
    var heroList: LinkedList<HeroData>,  //英雄数据
    var soliderDataList: LinkedList<SoliderData>,          //士兵数据
    var damagedRate: HashMap<Int, Double>,         //伤害率
    var loseSoliderMap: HashMap<Int, Int>,             //失去的士兵数量(用于进行计算)
    var killMap: HashMap<Int, HashMap<Int, Double>>,//杀伤比例列表
    var additionPropertyMap: HashMap<Set<Int>, Map<Int, Int>>? = null,    //星象加成效果
    var additionBuffMap: Map<Int, Int>? = null              //额外的buff效果
){
    //计算战斗后士兵
    fun calSoliderAfterFight(areaCache: AreaCache, dieRate: Int): SoliderDataAfterFight {
        assert(dieRate in 0..10000) { "士兵死亡率错误$dieRate" }

        val finalSoliderData = SoliderDataAfterFight()

        // 所有兵种的Map
        val totalMap = hashMapOf<Int, Int>() // <SoliderProto id, num>
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                if (num <= 0) {
                    continue
                }
                totalMap[id] = (totalMap[id] ?: 0) + num
            }
        }

        // 失去士兵的Map，key为士兵类型（步兵，弓兵...）。士兵类型和兵种不一样，一种类型的士兵包含不同阶级的兵种
        val loseSoliderByTypeMap = hashMapOf<Int, SoliderByArmyType>() // <armyType, >
        for ((id, _) in totalMap) {
            val loseNum = this.loseSoliderMap[id] ?: 0  //该兵种失去士兵数量
            val soliderProto = pcs.soliderCache.soliderProtoMap[id]
            if (soliderProto == null) {
                normalLog.error("FightData.kt  找不到士兵配置信息:$id")
                continue
            }
            val loseSoliderByType = loseSoliderByTypeMap.getOrPut(
                soliderProto.armyType
            ) { SoliderByArmyType(soliderProto.armyType, 0, LinkedList()) }
            loseSoliderByType.totalSolider += loseNum
            loseSoliderByType.soliderList.add(
                SoliderByStep(
                    id,
                    soliderProto.step,
                    loseNum,
                    0
                )
            )
        }

        // 按照死兵率，计算出失去士兵中包括的伤兵和死兵，死兵从每个类型的低阶兵种开始死
        val totalWoundedMap = hashMapOf<Int, Int>() //总伤兵Map
        val totalDieMap = hashMapOf<Int, Int>() //总死兵Map(dieRate造成的死兵，从低阶兵开始死)
        for ((_, loseSoliderByType) in loseSoliderByTypeMap) {
            var allDieNum = loseSoliderByType.totalSolider * dieRate / 10000
            loseSoliderByType.soliderList.sortBy { it.step }

            for (soliderByStep in loseSoliderByType.soliderList) {
                if (soliderByStep.woundedNum >= allDieNum) {
                    soliderByStep.woundedNum -= allDieNum
                    soliderByStep.changeNum += allDieNum
                    break
                }
                soliderByStep.changeNum = soliderByStep.woundedNum
                allDieNum -= soliderByStep.woundedNum
                soliderByStep.woundedNum = 0
            }
            for (soliderByStep in loseSoliderByType.soliderList) {
                totalWoundedMap[soliderByStep.protoId] = (totalWoundedMap[soliderByStep.protoId] ?: 0) +
                        soliderByStep.woundedNum
                totalDieMap[soliderByStep.protoId] = (totalDieMap[soliderByStep.protoId] ?: 0) + soliderByStep.changeNum
            }
        }

        // 预先计算出分配完士兵后余下的无家可归的死伤士兵
        val tempWoundedLeftMap = hashMapOf<Int, Int>() //余下的伤兵
        val tempDiedLeftMap = hashMapOf<Int, Int>() //余下的死兵
        for ((id, num) in totalWoundedMap) {
            tempWoundedLeftMap[id] = num
        }
        for ((id, num) in totalDieMap) {
            tempDiedLeftMap[id] = num
        }
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                // 按总伤兵：总士兵，计算出每个玩家的伤兵数量(这个值向下取整)
                val woundedNum = (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1)
                tempWoundedLeftMap[id] = (tempWoundedLeftMap[id] ?: 0) - woundedNum

                // 按总死兵：总士兵，计算出每个玩家的死兵数量(这个值向下取整)
                val dieNum = (totalDieMap[id] ?: 0) * num / (totalMap[id] ?: 1)
                tempDiedLeftMap[id] = (tempDiedLeftMap[id] ?: 0) - dieNum
            }
        }

        // 正式分配死伤给每个玩家
        for (soliderData in this.soliderDataList) {
            val woundedSolider = hashMapOf<Int, Int>()
            val woundedTrap = hashMapOf<Int, Int>()
            val dieMap = hashMapOf<Int, Int>()
            val leftMap = hashMapOf<Int, Int>()

            for ((id, num) in soliderData.soliderMap) {
                val canWoundedMoreNum = num - (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1) - (totalDieMap[id]
                    ?: 0) * num / (totalMap[id] ?: 1)
                var woundedMoreNum = tempWoundedLeftMap[id] ?: 0
                if (canWoundedMoreNum < tempWoundedLeftMap[id] ?: 0) {
                    woundedMoreNum = canWoundedMoreNum
                }
                val woundedNum = (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1) + woundedMoreNum
                tempWoundedLeftMap[id] = (tempWoundedLeftMap[id] ?: 0) - woundedMoreNum

                // 通过模板判断是士兵还是陷阱
                val soliderProto = pcs.soliderCache.soliderProtoMap[id]
                if (soliderProto == null) {
                    normalLog.error("FightData.kt  找不到士兵配置信息：$id")
                    continue
                }
                if (isSolider(soliderProto.armyType)) {
                    woundedSolider[id] = woundedNum
                } else {
                    woundedTrap[id] = woundedNum
                }

                val canDieMoreNum = num - woundedNum - (totalDieMap[id] ?: 0) * num / (totalMap[id] ?: 1)
                var dieMoreNum = tempDiedLeftMap[id] ?: 0
                if (canDieMoreNum < tempDiedLeftMap[id] ?: 0) {
                    dieMoreNum = canDieMoreNum
                }
                dieMap[id] = (totalDieMap[id] ?: 0) * num / (totalMap[id] ?: 1) + dieMoreNum
                tempDiedLeftMap[id] = (tempDiedLeftMap[id] ?: 0) - dieMoreNum
            }

            val player = areaCache.playerCache.findPlayerById(soliderData.playerId)
            if (player == null) {
                normalLog.error("FightData.kt  找不到玩家信息:${soliderData.playerId}")
                continue
            }

            val barracks = areaCache.barracksCache.findBarracksMapByPlayerId(soliderData.playerId)

            var nowCureSoliderNum = 0 // 玩家当前的伤兵数量
            var nowCureTrapNum = 0 // 玩家当前的伤兵数量
            var nowEventCureSoliderNum = 0 // 玩家当前的伤兵数量(活动伤兵营)
            var nowEventCureTrapNum = 0 // 玩家当前的伤兵数量(活动伤兵营)
            for ((_, b) in barracks) {
                val unitProto = pcs.soliderCache.soliderProtoMap[b.soldierId]
                if (unitProto == null) {
                    normalLog.error("FightData.kt  找不到士兵配置信息：${b.soldierId}")
                    continue
                }
                if (isSolider(unitProto.armyType)) {
                    nowCureSoliderNum += b.canCureNum + b.nowCureNum
                    nowEventCureSoliderNum += b.canEventCureNum + b.nowEventCureNum
                } else {
                    nowCureTrapNum += b.canCureNum + b.nowCureNum
                    nowEventCureTrapNum += b.canEventCureNum + b.nowEventCureNum
                }
            }
            val soliderAddRate = getResearchEffectValue(areaCache, NO_FILTER, player, ResearchEffectCureMaxAdd)
            val baseSoliderNum = getResearchEffectValue(areaCache, NO_FILTER, player, CureMaxNum)
            val activityAdd = pcs.basicProtoCache.activityHospitalNum // 活动伤兵营加成倍数

            var canCureSoliderNum = (baseSoliderNum * (1.0 + soliderAddRate / 10000.0) - nowCureSoliderNum).toInt()
            if (canCureSoliderNum < 0) {
                canCureSoliderNum = 0
            }
            var canEventCureSoliderNum =
                (baseSoliderNum * (1.0 + soliderAddRate / 10000.0) * activityAdd - nowEventCureSoliderNum).toInt()
            if (canEventCureSoliderNum < 0 || !isWonderWar(areaCache)) {
                canEventCureSoliderNum = 0
            }

            val rstSolider = calHospitalUnit(
                woundedSolider,
                canCureSoliderNum + canEventCureSoliderNum
            )
            val woundedSoliderMap = rstSolider.finalWoundedMap
            val diedSoliderMap = rstSolider.finalDiedMap

            normalLog.lzDebug {
                "原伤兵:$woundedSolider,伤兵剩余容量:$canCureSoliderNum,活动伤兵剩余容量" +
                        "计算后伤兵:$woundedSoliderMap,计算后死兵:$diedSoliderMap"
            }

            val baseTrapNum = getResearchEffectValue(areaCache, NO_FILTER, player, CureTrapMaxNum)
            var canCureTrapNum = baseTrapNum - nowCureTrapNum
            if (canCureTrapNum < 0) {
                canCureTrapNum = 0
            }
            var canEventCureTrapNum = baseTrapNum * activityAdd - nowEventCureTrapNum
            if (canEventCureTrapNum < 0 || !isWonderWar(areaCache)) {
                canEventCureTrapNum = 0
            }

            val rstTrap =
                calHospitalUnit(woundedTrap, canCureTrapNum + canEventCureTrapNum)
            val woundedTrapMap = rstTrap.finalWoundedMap
            val diedTrapMap = rstTrap.finalDiedMap

            val woundedMap = hashMapOf<Int, Int>()
            for ((id, num) in woundedSoliderMap) {
                woundedMap[id] = (woundedMap[id] ?: 0) + num
            }
            for ((id, num) in woundedTrapMap) {
                woundedMap[id] = (woundedMap[id] ?: 0) + num
            }
            for ((id, num) in diedSoliderMap) {
                dieMap[id] = (dieMap[id] ?: 0) + num
            }
            for ((id, num) in diedTrapMap) {
                dieMap[id] = (dieMap[id] ?: 0) + num
            }
            for ((id, num) in soliderData.soliderMap) {
                leftMap[id] = (leftMap[id] ?: 0) + num - (woundedMap[id] ?: 0) - (dieMap[id] ?: 0)
            }
            finalSoliderData.woundedSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, woundedMap)
            finalSoliderData.diedSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, dieMap)
            finalSoliderData.leftSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, leftMap)
        }
        return finalSoliderData
    }

    //计算士兵击杀数据
    fun calSoliderKillInfo(defLoseSoliderMap: HashMap<Int, Int>): HashMap<Long, SoliderData> {
        val killSoliderDataList = hashMapOf<Long, SoliderData>()

        // 所有兵种的Map
        val totalMap = hashMapOf<Int, Int>()
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                if (num <= 0) {
                    continue
                }
                totalMap[id] = (totalMap[id] ?: 0) + num
            }
        }

        val leftLoseSoliderMap = hashMapOf<Int, Int>()
        for ((id, num) in defLoseSoliderMap) {
            leftLoseSoliderMap[id] = num
        }

        // 根据配置的士兵杀敌权重调整杀伤列表
        val killWeightMap = hashMapOf<Int, HashMap<Int, Double>>()
        var totalKillWeight = 0.0
        for ((atkId, killedMap) in this.killMap) {
            val soliderProto = pcs.soliderCache.soliderProtoMap[atkId]
            if (soliderProto == null) {
                continue
            }
            for ((killId, killNum) in killedMap) {
                val killedWeightMap = killWeightMap.getOrPut(atkId) { hashMapOf() }
                killedWeightMap[killId] = killNum * soliderProto.killWeight
                totalKillWeight += killNum * soliderProto.killWeight
            }
        }

        val totalKillInfo = hashMapOf<Long, HashMap<Int, Int>>()
        for (soliderData in this.soliderDataList) {
            val killInfo = hashMapOf<Int, Int>()
            for ((id, num) in soliderData.soliderMap) {
                val killedWeightMap = killWeightMap.getOrPut(id) { hashMapOf() }
                for ((killId, killNum) in defLoseSoliderMap) {
                    val count = (killNum * (killedWeightMap[killId] ?: 0.0) / totalKillWeight).toInt()
                    killInfo[killId] = (killInfo[killId] ?: 0) + count
                    leftLoseSoliderMap[killId] = (leftLoseSoliderMap[killId] ?: 0) - count
                }
            }
            totalKillInfo[soliderData.playerId] = killInfo
        }

        //根据剩余击杀数进行修正
        for ((id, num) in leftLoseSoliderMap) {
            if (num <= 0) {
                continue
            }
            for ((playerId, killInfo) in totalKillInfo) {
                killInfo[id] = (killInfo[id] ?: 0) + num
                totalKillInfo[playerId] = killInfo
                break
            }
        }

        for ((playerId, killInfo) in totalKillInfo) {
            killSoliderDataList[playerId] = SoliderData(playerId, killInfo)
        }
        return killSoliderDataList
    }

    //计算士兵击伤数据
    fun calSoliderDamageInfo(defDamageSoliderMap: HashMap<Int, Int>): HashMap<Long, SoliderData> {
        val damageSoliderDataList = hashMapOf<Long, SoliderData>()

        // 所有兵种的Map
        val totalMap = hashMapOf<Int, Int>()
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                if (num <= 0) {
                    continue
                }
                totalMap[id] = (totalMap[id] ?: 0) + num
            }
        }

        val leftDamageSoliderMap = hashMapOf<Int, Int>()
        for ((id, num) in defDamageSoliderMap) {
            leftDamageSoliderMap[id] = num
        }

        // 根据配置的士兵杀敌权重调整杀伤列表
        val damageWeightMap = hashMapOf<Int, HashMap<Int, Double>>()
        var totalDamageWeight = 0.0
        for ((atkId, killedMap) in this.killMap) {
            val soliderProto = pcs.soliderCache.soliderProtoMap[atkId] ?: continue
            for ((killId, killNum) in killedMap) {
                val killedWeightMap = damageWeightMap.getOrPut(atkId) { hashMapOf() }
                killedWeightMap[killId] = killNum * soliderProto.killWeight
                totalDamageWeight += killNum * soliderProto.killWeight
            }
        }

        val totalDamageInfo = hashMapOf<Long, HashMap<Int, Int>>()
        for (soliderData in this.soliderDataList) {
            val killInfo = hashMapOf<Int, Int>()
            for ((id, num) in soliderData.soliderMap) {
                val damagedWeightMap = damageWeightMap.getOrPut(id) { hashMapOf() }
                for ((damageId, damageNum) in defDamageSoliderMap) {
                    val count = (damageNum * (damagedWeightMap[damageId] ?: 0.0) / totalDamageWeight).toInt()
                    killInfo[damageId] = (killInfo[damageId] ?: 0) + count
                    leftDamageSoliderMap[damageId] = (leftDamageSoliderMap[damageId] ?: 0) - count
                }
            }
            totalDamageInfo[soliderData.playerId] = killInfo
        }

        //根据剩余击伤数进行修正
        for ((id, num) in leftDamageSoliderMap) {
            if (num <= 0) {
                continue
            }
            for ((playerId, killInfo) in totalDamageInfo) {
                killInfo[id] = (killInfo[id] ?: 0) + num
                totalDamageInfo[playerId] = killInfo
                break
            }
        }

        for ((playerId, killInfo) in totalDamageInfo) {
            damageSoliderDataList[playerId] = SoliderData(playerId, killInfo)
        }
        return damageSoliderDataList
    }

    data class SoliderRecoveryResult(
        var totalRecoveryNum: Int,
        var finalSoliderData: SoliderDataAfterFight
    )

    //计算无医院影响的士兵
    fun calSoliderAfterFightWithOutHospital(dieRate: Int): SoliderDataAfterFight {
        assert(dieRate in 0..10000) { "士兵死亡率错误$dieRate" }

        val finalSoliderData = SoliderDataAfterFight()
        val totalMap = hashMapOf<Int, Int>()
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                if (num <= 0) {
                    continue
                }
                totalMap[id] = (totalMap[id] ?: 0) + num
            }
        }
        val soliderMapByType = hashMapOf<Int, SoliderByArmyType>()
        for ((id, _) in totalMap) {
            val woundedNum = this.loseSoliderMap[id] ?: 0
            val soliderProto = pcs.soliderCache.soliderProtoMap[id]
            if (soliderProto == null) {
                normalLog.error("FightData.kt  找不到英雄配置:$id")
                continue
            }
            val soliderByType = soliderMapByType.getOrPut(soliderProto.armyType) {
                SoliderByArmyType(
                    soliderProto.armyType,
                    0,
                    LinkedList()
                )
            }
            soliderByType.totalSolider += woundedNum
            soliderByType.soliderList.add(
                SoliderByStep(
                    id,
                    soliderProto.step,
                    woundedNum,
                    0
                )
            )
        }
        val totalWoundedMap = hashMapOf<Int, Int>()
        val totalDieMap = hashMapOf<Int, Int>()
        for ((_, soliderByArmy) in soliderMapByType) {
            var allDieNum = soliderByArmy.totalSolider * dieRate / 10000
            soliderByArmy.soliderList.sortBy { it.step }

            for (soliderByStep in soliderByArmy.soliderList) {
                if (soliderByStep.woundedNum >= allDieNum) {
                    soliderByStep.woundedNum -= allDieNum
                    soliderByStep.changeNum += allDieNum
                    break
                }
                soliderByStep.changeNum = soliderByStep.woundedNum
                allDieNum -= soliderByStep.woundedNum
                soliderByStep.woundedNum = 0
            }
            for (soliderByStep in soliderByArmy.soliderList) {
                totalWoundedMap[soliderByStep.protoId] = (totalWoundedMap[soliderByStep.protoId] ?: 0) +
                        soliderByStep.woundedNum
                totalDieMap[soliderByStep.protoId] = (totalDieMap[soliderByStep.protoId] ?: 0) + soliderByStep.changeNum
            }
        }

        //分配给每个玩家
        val tempWoundedLeftMap = hashMapOf<Int, Int>()
        val tempDiedLeftMap = hashMapOf<Int, Int>()
        for ((id, num) in totalWoundedMap) {
            tempWoundedLeftMap[id] = num
        }
        for ((id, num) in totalDieMap) {
            tempDiedLeftMap[id] = num
        }
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                val woundedNum = (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1)
                tempWoundedLeftMap[id] = (tempWoundedLeftMap[id] ?: 0) - woundedNum

                val diedNum = totalDieMap.getOrDefault(id, 0) * num / totalMap.getOrDefault(id, 0)
                tempDiedLeftMap[id] = tempDiedLeftMap.getOrDefault(id, 0) - diedNum
            }
        }

        for (soliderData in this.soliderDataList) {
            val woundedMap = hashMapOf<Int, Int>()
            val dieMap = hashMapOf<Int, Int>()
            val leftMap = hashMapOf<Int, Int>()
            val killMap = hashMapOf<Int, Int>()
            for ((id, num) in soliderData.soliderMap) {
                val canWoundedMoreNum = num - (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1) - (totalDieMap[id]
                    ?: 0) * num / (totalMap[id] ?: 1)
                var woundedMoreNum = tempWoundedLeftMap[id] ?: 0
                if (canWoundedMoreNum < tempWoundedLeftMap[id] ?: 0) {
                    woundedMoreNum = canWoundedMoreNum
                }
                woundedMap[id] = (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1) + woundedMoreNum
                tempWoundedLeftMap[id] = (tempWoundedLeftMap[id] ?: 0) - woundedMoreNum

                val canDieMoreNum = num - (woundedMap[id] ?: 0) - (totalDieMap[id] ?: 0) * num / (totalMap[id] ?: 1)
                val dieMoreNum = tempDiedLeftMap[id] ?: 0
                if (canDieMoreNum < tempDiedLeftMap[id] ?: 0) {
                    woundedMoreNum = canDieMoreNum
                }
                dieMap[id] = (totalDieMap[id] ?: 0) * num / (totalMap[id] ?: 1) + dieMoreNum
                tempDiedLeftMap[id] = (tempDiedLeftMap[id] ?: 0) - dieMoreNum
                val allKillMap = this.killMap[id]
                if (allKillMap == null) {
                    continue
                }
                val rate = 1.0 * num / (totalMap[id] ?: 1)
                for ((killId, killNum) in allKillMap) {
                    killMap[killId] = (killMap[killId] ?: 0) + (killNum * rate).toInt()
                }
            }

            for ((id, num) in soliderData.soliderMap) {
                leftMap[id] = (leftMap[id] ?: 0) + num - (woundedMap[id] ?: 0) - (dieMap[id] ?: 0)
            }
            finalSoliderData.woundedSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, woundedMap)
            finalSoliderData.diedSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, dieMap)
            finalSoliderData.leftSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, leftMap)
            finalSoliderData.killedSoliderDataMap[soliderData.playerId] =
                    SoliderData(soliderData.playerId, killMap)
        }
        return finalSoliderData
    }

    //士兵恢复
    fun soliderRecovery(areaCache: AreaCache, recoveryRate: Int): SoliderRecoveryResult {
        assert(recoveryRate in 0..10000) { "士兵回复率错误$recoveryRate" }
        val finalSoliderData = SoliderDataAfterFight()
        val totalMap = hashMapOf<Int, Int>()
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                totalMap[id] = (totalMap[id] ?: 0) + num
            }
        }

        val woundedMapByType = hashMapOf<Int, SoliderByArmyType>()
        for ((id, _) in totalMap) {
            val woundedNum = this.loseSoliderMap[id] ?: 0
            val soliderProto = pcs.soliderCache.soliderProtoMap[id]
            if (soliderProto == null) {
                normalLog.error("FightData.kt  找不到士兵配置:$id")
                continue
            }

            val woundedSolider = woundedMapByType.getOrPut(
                soliderProto.armyType
            ) { SoliderByArmyType(soliderProto.armyType, 0, LinkedList()) }
            woundedSolider.totalSolider += woundedNum
            woundedSolider.soliderList.add(
                SoliderByStep(
                    id,
                    soliderProto.step,
                    woundedNum,
                    0
                )
            )
        }

        for ((_, soliderByArmy) in woundedMapByType) {
            soliderByArmy.soliderList.sortByDescending { it.step }

            var recoveryNum = soliderByArmy.totalSolider * recoveryRate / 10000
            for (soliderByStep in soliderByArmy.soliderList) {
                if (soliderByStep.woundedNum >= recoveryNum) {
                    soliderByStep.woundedNum -= recoveryNum
                    soliderByStep.changeNum += recoveryNum
                    break
                }
                soliderByStep.changeNum = soliderByStep.woundedNum
                recoveryNum -= soliderByStep.woundedNum
                soliderByStep.woundedNum = 0
            }
        }

        var totalRecoveryNum = 0
        val totalWoundedMap = hashMapOf<Int, Int>()
        for ((_, soliderByType) in woundedMapByType) {
            for (soliderByStep in soliderByType.soliderList) {
                totalWoundedMap[soliderByStep.protoId] = soliderByStep.woundedNum
                totalRecoveryNum += soliderByStep.changeNum
            }
        }

        val tempWoundedLeftMap = hashMapOf<Int, Int>()
        for ((id, num) in totalWoundedMap) {
            tempWoundedLeftMap[id] = num
        }
        for (soliderData in this.soliderDataList) {
            for ((id, num) in soliderData.soliderMap) {
                val woundedNum = (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1)
                tempWoundedLeftMap[id] = (tempWoundedLeftMap[id] ?: 0) - woundedNum
            }
        }

        for (soliderData in this.soliderDataList) {
            val woundedSolider = hashMapOf<Int, Int>()
            val woundedTrap = hashMapOf<Int, Int>()
            val dieMap = hashMapOf<Int, Int>()
            val leftMap = hashMapOf<Int, Int>()
            for ((id, num) in soliderData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[id]
                if (soliderProto == null) {
                    normalLog.error("FightData.kt  找不到士兵配置:$id")
                    continue
                }

                val woundedNum = (totalWoundedMap[id] ?: 0) * num / (totalMap[id] ?: 1) + (tempWoundedLeftMap[id] ?: 0)
                tempWoundedLeftMap[id] = 0
                if (isSolider(soliderProto.armyType)) {
                    woundedSolider[id] = woundedNum
                } else {
                    woundedTrap[id] = woundedNum
                }
            }

            val player = areaCache.playerCache.findPlayerById(soliderData.playerId)
            if (player == null) {
                normalLog.error("FightData.kt  找不到玩家信息:${soliderData.playerId}")
                continue
            }

            val barracks = areaCache.barracksCache.findBarracksMapByPlayerId(soliderData.playerId)

            var nowCureSoliderNum = 0// 玩家当前的伤兵数量
            var nowCureTrapNum = 0 // 玩家当前的伤兵数量
            var nowEventCureSoliderNum = 0 // 玩家当前的伤兵数量(活动伤兵营)
            var nowEventCureTrapNum = 0 // 玩家当前的伤兵数量(活动伤兵营)
            for ((_, b) in barracks) {
                val unitProto = pcs.soliderCache.soliderProtoMap[b.soldierId]
                if (unitProto == null) {
                    normalLog.error("FightData.kt  找不到士兵配置:${b.soldierId}")
                    continue
                }

                if (isSolider(unitProto.armyType)) {
                    nowCureSoliderNum += b.canCureNum + b.nowCureNum
                    nowEventCureSoliderNum += b.canEventCureNum + b.nowEventCureNum
                } else {
                    nowCureTrapNum += b.canCureNum + b.nowCureNum
                    nowEventCureTrapNum += b.canEventCureNum + b.nowEventCureNum
                }
            }
            val soliderAddRate =
                getResearchEffectValue(areaCache, NO_FILTER, player, ResearchEffectCureMaxAdd)
            val baseSoliderNum = getResearchEffectValue(areaCache, NO_FILTER, player, CureMaxNum)
            val activityAdd = pcs.basicProtoCache.activityHospitalNum // 活动伤兵营加成倍数

            var canCureSoliderNum = (baseSoliderNum * (1.0 + soliderAddRate / 10000.0) - nowCureSoliderNum).toInt()
            if (canCureSoliderNum < 0) {
                canCureSoliderNum = 0
            }
            var canEventCureSoliderNum =
                (baseSoliderNum * (1.0 + soliderAddRate / 10000.0) * activityAdd - nowEventCureSoliderNum).toInt()
            if (canEventCureSoliderNum < 0 || !isWonderWar(areaCache)) {
                canEventCureSoliderNum = 0
            }

            val rstSolider = calHospitalUnit(
                woundedSolider,
                canCureSoliderNum + canEventCureSoliderNum
            )
            val woundedSoliderMap = rstSolider.finalWoundedMap
            val diedSoliderMap = rstSolider.finalDiedMap

            val baseTrapNum = getResearchEffectValue(areaCache, NO_FILTER, player, TrapNumAdd)
            var canCureTrapNum = baseTrapNum - nowCureTrapNum
            if (canCureTrapNum < 0) {
                canCureTrapNum = 0
            }
            var canEventCureTrapNum = baseTrapNum * activityAdd - nowEventCureTrapNum
            if (canEventCureTrapNum < 0 || !isWonderWar(areaCache)) {
                canEventCureTrapNum = 0
            }

            val rstTrap =
                calHospitalUnit(woundedTrap, canCureTrapNum + canEventCureTrapNum)
            val woundedTrapMap = rstTrap.finalWoundedMap
            val diedTrapMap = rstTrap.finalDiedMap

            val woundedMap = hashMapOf<Int, Int>()
            for ((id, num) in woundedSoliderMap) {
                woundedMap[id] = (woundedMap[id] ?: 0) + num
            }
            for ((id, num) in woundedTrapMap) {
                woundedMap[id] = (woundedMap[id] ?: 0) + num
            }
            for ((id, num) in diedSoliderMap) {
                dieMap[id] = (dieMap[id] ?: 0) + num
            }
            for ((id, num) in diedTrapMap) {
                dieMap[id] = (dieMap[id] ?: 0) + num
            }

            for ((id, num) in soliderData.soliderMap) {
                leftMap[id] = (leftMap[id] ?: 0) + num - (woundedMap[id] ?: 0) - (dieMap[id] ?: 0)
            }

            finalSoliderData.woundedSoliderDataMap[soliderData.playerId] = SoliderData(
                soliderData.playerId,
                woundedMap
            )
            finalSoliderData.diedSoliderDataMap[soliderData.playerId] = SoliderData(
                soliderData.playerId,
                dieMap
            )
            finalSoliderData.leftSoliderDataMap[soliderData.playerId] = SoliderData(
                soliderData.playerId,
                leftMap
            )
        }

        return SoliderRecoveryResult(totalRecoveryNum, finalSoliderData)
    }

    //判断是否全军覆没
    fun checkAllWipedOut(): Boolean {
        if (this.soliderDataList.count() > 0) {
            for (soliderData in this.soliderDataList) {
                for ((id, _) in soliderData.soliderMap) {
                    if (this.damagedRate[id] != 1.0) {
                        return false
                    }
                }
            }
        }
        return true
    }

    //检测领主是否在部队内
    fun checkHaveMainHero(areaCache: AreaCache): Boolean {
        val player = areaCache.playerCache.findPlayerById(this.playerId)
        if (player == null) {
            return false
        }
        for (heroData in this.heroList) {
            if (heroData.id == player.mainHeroId) {
                return true
            }
        }
        return false
    }

    //判定监禁
    fun checkPrison(
        areaCache: AreaCache,
        atkGroup: WalkForceGroup?,
        defPlayerId: Long
    ) {
        //攻击方回城
        if (this.checkAllWipedOut() && this.checkHaveMainHero(areaCache)) {
            val losePlayerId = this.playerId
            wpm.es.fire(
                areaCache, 0, PRISON_AFTER_PVP,
                PrisonEvent(defPlayerId, losePlayerId)
            )
            val player = requireNotNull(areaCache.playerCache.findPlayerById(losePlayerId))
            atkGroup?.removeHero(areaCache, player.mainHeroId)
        }
    }
}

// 英雄数据
data class HeroData(
    val id: Long,
    val protoId: Int,
    val lv: Int,
    val star: Int,
    val awake: Int,
    var assignHp: Int, //指定的血量
    val pos: Int,     //位置
    val skillList: LinkedList<Int>? = null,
    var initMorale: Double = 0.0 //初始的士气
)

// 部队数据
data class SoliderData(
    var playerId: Long,
    var soliderMap: HashMap<Int, Int> //部队数据 SoliderProtoId-SoliderNum
)

data class SoliderByArmyType(
    var armyType: Int,
    var totalSolider: Int,
    var soliderList: LinkedList<SoliderByStep>
)

data class SoliderByStep(
    var protoId: Int,
    var step: Int,
    var woundedNum: Int,
    var changeNum: Int
)

data class UnitByStep(
    var step: Int,
    var unitMap: HashMap<Int, Int>
)

data class CalHospitalUnitResult(
    var finalWoundedMap: HashMap<Int, Int>,
    var finalDiedMap: HashMap<Int, Int>
)

//计算医院数据
fun calHospitalUnit(woundedMap: Map<Int, Int>, allNum: Int): CalHospitalUnitResult {
    val rst = CalHospitalUnitResult(hashMapOf(), hashMapOf())
    val finalWoundedMap = rst.finalWoundedMap
    val finalDiedMap = rst.finalDiedMap
    val unitList = LinkedList<UnitByStep>()
    for ((id, num) in woundedMap) {
        val unitProto = pcs.soliderCache.soliderProtoMap[id]
        if (unitProto == null) {
            normalLog.error("FightData.kt  找不到士兵配置:$id")
            continue
        }
        var unitByStep: UnitByStep? = null
        for (ul in unitList) {
            if (ul.step == unitProto.step) {
                unitByStep = ul
                break
            }
        }
        if (unitByStep == null) {
            unitByStep = UnitByStep(
                unitProto.step,
                hashMapOf()
            )
            unitList.add(unitByStep)
        }
        unitByStep.unitMap[id] = (unitByStep.unitMap[id] ?: 0) + num
    }
    unitList.sortByDescending { it.step }
    var leftNum = allNum
    for (unitByStep in unitList) {
        var index = 0
        while (true) {
            var needAvgNum = 0
            for ((_, num) in unitByStep.unitMap) {
                if (num > 0) {
                    needAvgNum++
                }
            }
            if (needAvgNum == 0) {
                break
            }
            if (leftNum < needAvgNum) {
                break
            }
            val averageNum = leftNum / needAvgNum
            val addedNum = leftNum - averageNum * needAvgNum
            leftNum = 0
            normalLog.lzDebug { " 剩余数量${averageNum * needAvgNum + addedNum},平均值$averageNum,额外值$addedNum" }
            for ((id, num) in unitByStep.unitMap) {
                if (num == 0) {
                    continue
                }
                val divideNum = averageNum + addedNum
                if (num > divideNum) {
                    unitByStep.unitMap[id] = num - divideNum
                } else {
                    unitByStep.unitMap[id] = 0
                    leftNum += divideNum - num
                }
            }
            normalLog.lzDebug { "第${index}回合,等级${unitByStep.step}:${unitByStep.unitMap}" }
            index++
            if (index > 100000) {
                normalLog.error("FightData.kt  战斗结算触发了保险丝！")
                break
            }
        }
    }
    for (unitByStep in unitList) {
        for ((id, num) in unitByStep.unitMap) {
            if (num == 0) {
                continue
            }
            finalDiedMap[id] = (finalDiedMap[id] ?: 0) + num
        }
    }
    for ((id, num) in woundedMap) {
        if (num - (finalDiedMap[id] ?: 0) == 0) {
            continue
        }
        finalWoundedMap[id] = num - (finalDiedMap[id] ?: 0)
    }
    return rst
}

//玩家主城部队
fun createFightDataByPlayer(areaCache: AreaCache, playerId: Long): FightData {
    val fightData = FightData(
        playerId,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )
    val myArmyData = SoliderData(
        playerId,
        hashMapOf()
    )
    val barracks = areaCache.barracksCache.findBarracksByPlayerId(playerId)
    for (barrack in barracks) {
        if (barrack.soldierNum <= 0) {
            continue
        }
        myArmyData.soliderMap[barrack.soldierId] = (myArmyData.soliderMap[barrack.soldierId] ?: 0) + barrack.soldierNum
    }
    fightData.soliderDataList.add(myArmyData)

    //加载英雄阵型 todo getReserveHeroList()得到的是补全的英雄 补全规则优先级：星数》阶数》等级》id
    val defHeroList = areaCache.heroCache.getReserveHeroList(playerId)
    val loadedHeroMap = hashMapOf<Long, Int>()
    for (hero in defHeroList) {
        if (hero.posState != IN_CITY) {
            continue
        }
        if (hero.mainHeroState != NO_MAIN_HERO && hero.mainHeroState != MAIN_HERO) {
            continue
        }
        fightData.heroList.add(
            HeroData(
                hero.id,
                hero.protoId,
                hero.level,
                hero.advLv,
                hero.awake,
                0,
                0
            )
        )
        loadedHeroMap[hero.id] = 1
    }
    val player = areaCache.playerCache.findPlayerById(playerId)
    if (player == null) {
        normalLog.error("FightData.kt  找不到玩家信息:$playerId")
        return fightData
    }

    if (loadedHeroMap.count() < player.intHeroNum) {
        val heroList = LinkedList<Hero>()
        val heroMap = areaCache.heroCache.findHeroMapByPlayer(playerId)
        for ((_, hero) in heroMap) {
            if (hero.posState != IN_CITY) {
                continue
            }
            if (hero.mainHeroState != NO_MAIN_HERO && hero.mainHeroState != MAIN_HERO) {
                continue
            }
            heroList.add(hero)
        }
        heroList.sortWith(Comparator { a, b ->
            if (a.advLv > b.advLv) {
                return@Comparator 1
            }
            if (a.advLv < b.advLv) {
                return@Comparator -1
            }
            if (a.awake > b.awake) {
                return@Comparator 1
            }
            if (a.awake < b.awake) {
                return@Comparator -1
            }
            if (a.level > b.level) {
                return@Comparator 1
            }
            if (a.level < b.level) {
                return@Comparator -1
            }
            if (a.protoId > b.protoId) {
                return@Comparator 1
            }
            if (a.protoId < b.protoId) {
                return@Comparator -1
            }
            return@Comparator 0
        })
        var lackNum = player.intHeroNum - loadedHeroMap.count()
        if (lackNum > heroList.count()) {
            lackNum = heroList.count()
        }
        for (i in 0 until lackNum) {
            fightData.heroList.add(
                HeroData(
                    heroList[i].id,
                    heroList[i].protoId,
                    heroList[i].level,
                    heroList[i].advLv,
                    heroList[i].awake,
                    0,
                    0
                )
            )
        }
    }

    //加载协防部队
    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
    if (castle == null) {
        normalLog.error("FightData.kt  找不到玩家对应的城池信息:${player.focusCastleId}")
        return fightData
    }
    val reinforceGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(castle.x, castle.y, Reinforce)
    for (group in reinforceGroups) {
        val soliderMap = hashMapOf<Int, Int>()
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        for (force in forces) {
            for ((id, num) in force.soliderMap) {
                if (num <= 0) {
                    continue
                }
                soliderMap[id] = (soliderMap[id] ?: 0) + num
            }
        }
        fightData.soliderDataList.add(
            SoliderData(
                group.mainPlayerId,
                soliderMap
            )
        )
    }
    return fightData
}

fun createFightDataByFog(fog: Fog): FightData {
    val fightData = FightData(
        0,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )
    fightData.soliderDataList.add(SoliderData(0, fog.soliderMap))
    val fogProto = pcs.mapOpenProtoCache.mapOpenMap[fog.fogId]
    if (fogProto == null) {
        assert(false)
        return fightData
    }
    val soliderTeamProto = pcs.soliderTeamProtoCache.soliderTeamMap[fogProto.army]
    if (soliderTeamProto == null) {
        assert(false)
        return fightData
    }
    soliderTeamProto.heroMap.forEach { _, hero ->
        fightData.heroList.add(
            HeroData(
                0,
                hero.protoId,
                hero.lv,
                hero.star,
                hero.awake,
                0,
                0
            )
        )
    }
    fightData.additionBuffMap = soliderTeamProto.effectMap
    return fightData
}

//魔物
fun createFightDataByMonster(monster: MonsterProto, leftHp: Int): FightData {
    val fightData = FightData(
        0,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )
    val skillList = LinkedList<Int>()
    val unitProto = pcs.unitBaseCache.fetchProtoById(monster.unit)
    if (unitProto != null) {
        skillList.add(unitProto.skill1)
        skillList.add(unitProto.skill2)
        skillList.add(unitProto.skill3)
        skillList.add(unitProto.skill4)
    }
    fightData.heroList.add(
        HeroData(
            0,
            monster.unit,
            monster.unitLv,
            monster.unitStar,
            monster.unitStep,
            leftHp,
            5,
            skillList
        )
    )
    return fightData
}

// 世界BOSS
fun createFightDataByWorldBoss(worldBoss: MonsterWorldProto, currentHp: Int): FightData {
    val fightData = FightData(
        0,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )
    val skillList = LinkedList<Int>()
    val unitProto = pcs.unitBaseCache.fetchProtoById(worldBoss.unit)
    if (unitProto != null) {
        skillList.add(unitProto.skill1)
        skillList.add(unitProto.skill2)
        skillList.add(unitProto.skill3)
        skillList.add(unitProto.skill4)
    }
    fightData.heroList.add(
        HeroData(
            0,
            worldBoss.id,
            worldBoss.unitLv,
            worldBoss.star,
            worldBoss.awake,
            currentHp,
            0,
            skillList
        )
    )
    return fightData
}

//遗迹
fun createFightDataBySoliderTeamId(soliderTeamId: Int): FightData {
    val fightData = FightData(
        0,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )
    val lineupProto = pcs.soliderTeamProtoCache.soliderTeamMap[soliderTeamId]
    if (lineupProto == null) {
        normalLog.error("FightData.kt  找不到部队配置表信息:$soliderTeamId")
        return fightData
    }

    for ((_, hero) in lineupProto.heroMap) {
        fightData.heroList.add(
            HeroData(
                0,
                hero.protoId,
                hero.lv,
                hero.star,
                hero.awake,
                0,
                0
            )
        )
    }
    val soliderData = SoliderData(
        0,
        hashMapOf()
    )
    for ((soliderId, soliderNum) in lineupProto.soliderMap) {
        if (soliderNum <= 0) {
            continue
        }
        soliderData.soliderMap[soliderId] = (soliderData.soliderMap[soliderId] ?: 0) + soliderNum
    }
    fightData.soliderDataList.add(soliderData)
    return fightData
}

//部队群组
fun createFightDataByForceGroup(
    areaCache: AreaCache,
    group: WalkForceGroup
): FightData {
    val fightData = FightData(
        group.mainPlayerId,
        group.id,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )

    val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
    for (force in forces) {
        for (heroId in force.heroIdList) {
            val hero = areaCache.heroCache.findHeroById(force.playerId, heroId)
            if (hero == null) {
                normalLog.error("FightData.kt  找不到玩家英雄信息：{}", heroId)
                continue
            }

            fightData.heroList.add(
                HeroData(
                    heroId,
                    hero.protoId,
                    hero.level,
                    hero.advLv,
                    hero.awake,
                    0,
                    0
                )
            )
        }
        fightData.soliderDataList.add(
            SoliderData(
                force.playerId,
                force.soliderMap
            )
        )
    }

    return fightData
}

//多部队群组
fun createFightDataByForceGroupList(
    areaCache: AreaCache,
    groupList: List<WalkForceGroup>,
    mainPlayerId: Long
): FightData {
    val fightData = FightData(
        mainPlayerId,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )

    for (group in groupList) {
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        for (force in forces) {
            for (heroId in force.heroIdList) {
                val hero = areaCache.heroCache.findHeroById(force.playerId, heroId)
                if (hero == null) {
                    normalLog.error("FightData.kt  找不到玩家英雄信息：{}", heroId)
                    continue
                }

                fightData.heroList.add(
                    HeroData(
                        heroId,
                        hero.protoId,
                        hero.level,
                        hero.advLv,
                        hero.awake,
                        0,
                        0
                    )
                )
            }
            fightData.soliderDataList.add(
                SoliderData(
                    force.playerId,
                    force.soliderMap
                )
            )
        }
    }

    return fightData
}

//英雄阵型
fun createFightDataByArmyPlan(areaCache: AreaCache, armyPlan: ArmyPlan): FightData {
    val fightData = FightData(
        armyPlan.playerId,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )

    for ((pos, heroId) in armyPlan.heroMap) {
        val hero = areaCache.heroCache.findHeroById(armyPlan.playerId, heroId)
        if (hero == null) {
            normalLog.error("FightData.kt  找不到玩家英雄信息：{}", heroId)
            continue
        }
        val skillList = LinkedList<Int>()
        skillList.add(hero.skillId1)
        skillList.add(hero.skillId2)
        skillList.add(hero.skillId3)
        skillList.add(hero.skillId4)

        fightData.heroList.add(
            HeroData(
                heroId,
                hero.protoId,
                hero.level,
                hero.advLv,
                hero.awake,
                0,
                pos,
                skillList
            )
        )
    }
    return fightData
}

fun createFightDataByUnitTeam(unitTeamProto: UnitTeamProto): FightData {
    val fightData = FightData(
        0,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )

    for ((pos, unitData) in unitTeamProto.gridMap) {
        fightData.heroList.add(
            HeroData(
                0,
                unitData.unitProto,
                unitData.lv,
                unitData.starLv,
                unitData.awakeLv,
                0,
                pos,
                LinkedList(unitData.skillIds)
            )
        )
    }
    return fightData
}

fun createFightData(playerId: Long, heroList: LinkedList<Hero>, soliderMap: HashMap<Int, Int>): FightData {
    val fightData = FightData(
        playerId,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )
    heroList.forEach {
        fightData.heroList.add(
            HeroData(
                it.id,
                it.protoId,
                it.level,
                it.advLv,
                it.awake,
                0,
                0
            )
        )
    }
    fightData.soliderDataList.add(
        SoliderData(
            playerId,
            soliderMap
        )
    )
    return fightData
}

//测试数据
fun createFightDataByTestFightData(testFightData: TestFightData): FightData {
    val fightData = FightData(
        0,
        0,
        LinkedList(),
        LinkedList(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf(),
        hashMapOf()
    )

    for (hero in testFightData.herosList) {
        fightData.heroList.add(
            HeroData(
                0,
                hero.id,
                hero.lv,
                hero.star,
                hero.awake,
                0,
                0
            )
        )
    }
    val soliderData = SoliderData(
        0, hashMapOf()
    )
    for (solider in testFightData.solidersList) {
        soliderData.soliderMap[solider.id] = (soliderData.soliderMap[solider.id] ?: 0) + solider.num
    }
    fightData.soliderDataList.add(soliderData)
    return fightData
}