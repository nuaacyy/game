package com.point18.slg2d.world.module.fightdomain

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.isWonderWar
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import java.util.*

//战斗后的士兵数据
class SoliderDataAfterFight {
    var leftSoliderDataMap: HashMap<Long, SoliderData> = hashMapOf() //剩余士兵数据
    var woundedSoliderDataMap: HashMap<Long, SoliderData> = hashMapOf() //伤兵数据
    var diedSoliderDataMap: HashMap<Long, SoliderData> = hashMapOf() //死兵数据
    var killedSoliderDataMap: HashMap<Long, SoliderData> = hashMapOf() //击杀数据
    var damageSoliderDataMap: HashMap<Long, SoliderData> = hashMapOf() //击伤数据

    //根据战后数据刷新行军组
    fun refreshGroup(areaCache: AreaCache, group: WalkForceGroup) {
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
        for (force in forces) {
            val playerId = force.playerId

            val newSoliderMap = hashMapOf<Int, Int>()
            for ((id, _) in force.soliderMap) {
                val leftSoliderData = this.leftSoliderDataMap[playerId]
                if (leftSoliderData == null) {
                    normalLog.error("FightData.kt  找不到战斗后剩余士兵数据:$playerId")
                    continue
                }
                val leftNum = leftSoliderData.soliderMap[id] ?: 0
                if (leftNum <= 0) {
                    continue
                }
                newSoliderMap[id] = leftNum
            }
            force.putSoliderMap(newSoliderMap)

            val woundedSoliderData = this.woundedSoliderDataMap[playerId]
            if (woundedSoliderData == null) {
                normalLog.error("FightData.kt  找不到战斗后伤兵数据:$playerId")
                continue
            }

            val changeBarracks = hashMapOf<Long, Barracks>()
            val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)

            if (isWonderWar(areaCache)) {
                // 奇观争夺期间
                val (eventWoundedMap, woundedMap)
                        = this.divideEventWoundedSolider(areaCache, playerId)

                for ((id, woundedNum) in eventWoundedMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canEventCureNum += woundedNum
                    changeBarracks[barrack.id] = barrack
                }

                for ((id, woundedNum) in woundedMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canCureNum += woundedNum
                }
            } else {
                // 和平期间
                for ((id, woundedNum) in woundedSoliderData.soliderMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canCureNum += woundedNum
                    changeBarracks[barrack.id] = barrack
                }
            }

            if (changeBarracks.count() == 0) {
                continue
            }

            // 重算军团实力
            targetAddVal(areaCache, playerId, SoliderStrength)
            targetAddVal(areaCache, playerId, TrapStrength)

            val session = fetchOnlinePlayerSession(areaCache, playerId)
            if (session != null) {
                for ((_, barrack) in changeBarracks) {
                    createBarracksChangeNotifier(barrack).notice(session)
                }
            }
        }
    }

    //根据战后数据刷新行军组
    fun refreshForceList(areaCache: AreaCache, forceList: List<WalkForce>) {
        for (force in forceList) {
            val playerId = force.playerId

            val newSoliderMap = hashMapOf<Int, Int>()
            for ((id, _) in force.soliderMap) {
                val leftSoliderData = this.leftSoliderDataMap[playerId]
                if (leftSoliderData == null) {
                    normalLog.error("FightData.kt  找不到战斗后剩余士兵数据:$playerId")
                    continue
                }
                val leftNum = leftSoliderData.soliderMap[id] ?: 0
                if (leftNum <= 0) {
                    continue
                }
                newSoliderMap[id] = leftNum
            }
            force.putSoliderMap(newSoliderMap)

            val woundedSoliderData = this.woundedSoliderDataMap[playerId]
            if (woundedSoliderData == null) {
                normalLog.error("FightData.kt  找不到战斗后伤兵数据:$playerId")
                continue
            }

            val changeBarracks = hashMapOf<Long, Barracks>()
            val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)

            if (isWonderWar(areaCache)) {
                // 奇观争夺期间
                val (eventWoundedMap, woundedMap)
                        = this.divideEventWoundedSolider(areaCache, playerId)

                for ((id, woundedNum) in eventWoundedMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canEventCureNum += woundedNum
                    changeBarracks[barrack.id] = barrack
                }

                for ((id, woundedNum) in woundedMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canCureNum += woundedNum
                }
            } else {
                // 和平期间
                for ((id, woundedNum) in woundedSoliderData.soliderMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canCureNum += woundedNum
                    changeBarracks[barrack.id] = barrack
                }
            }

            if (changeBarracks.count() == 0) {
                continue
            }

            // 重算军团实力
            targetAddVal(areaCache, playerId, SoliderStrength)
            targetAddVal(areaCache, playerId, TrapStrength)

            val session = fetchOnlinePlayerSession(areaCache, playerId)
            if (session != null) {
                for ((_, barrack) in changeBarracks) {
                    createBarracksChangeNotifier(barrack).notice(session)
                }
            }
        }
    }

    fun refreshWithOutGroup(areaCache: AreaCache, playerId: Long) {
        val changeBarracks = hashMapOf<Long, Barracks>()
        val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)

        val leftSoliderData = this.leftSoliderDataMap[playerId]
        if (leftSoliderData != null) {
            leftSoliderData.soliderMap.forEach {
                val barrack = barrackMap[it.key]
                if (barrack == null) {
                    return@forEach
                }
                barrack.soldierNum += it.value
                changeBarracks[barrack.id] = barrack
            }
        }

        val woundedSoliderData = this.woundedSoliderDataMap[playerId]
        if (woundedSoliderData != null) {
            if (isWonderWar(areaCache)) {
                // 奇观争夺期间
                val (eventWoundedMap, woundedMap)
                        = this.divideEventWoundedSolider(areaCache, playerId)

                for ((id, woundedNum) in eventWoundedMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canEventCureNum += woundedNum
                    changeBarracks[barrack.id] = barrack
                }

                for ((id, woundedNum) in woundedMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canCureNum += woundedNum
                }
            } else {
                // 和平期间
                for ((id, woundedNum) in woundedSoliderData.soliderMap) {
                    if (woundedNum == 0) {
                        continue
                    }
                    val barrack = barrackMap[id]
                    if (barrack == null) {
                        normalLog.error("FightData.kt  找不到兵营:$id")
                        continue
                    }

                    barrack.canCureNum += woundedNum
                    changeBarracks[barrack.id] = barrack
                }
            }
            for ((id, woundedNum) in woundedSoliderData.soliderMap) {
                if (woundedNum == 0) {
                    continue
                }
                val barrack = barrackMap[id]
                if (barrack == null) {
                    normalLog.error("FightData.kt  找不到兵营:$id")
                    continue
                }

                barrack.canCureNum += woundedNum
                changeBarracks[barrack.id] = barrack
            }
        }

        if (changeBarracks.isEmpty()) {
            return
        }

        // 重算军团实力
        targetAddVal(areaCache, playerId, SoliderStrength)
        targetAddVal(areaCache, playerId, TrapStrength)

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            for ((_, barrack) in changeBarracks) {
                createBarracksChangeNotifier(barrack).notice(session)
            }
        }
    }

    // 活动伤兵数据
    data class EventWoundedSoliderData(
        var eventWoundedMap: HashMap<Int, Int>,
        var woundedMap: HashMap<Int, Int>
    )

    private fun divideEventWoundedSolider(
        areaCache: AreaCache,
        playerId: Long
    ): EventWoundedSoliderData {
        val woundedSoliderData = this.woundedSoliderDataMap[playerId]
        if (woundedSoliderData == null) {
            normalLog.error("FightData.kt  找不到战斗后伤兵数据:$playerId")
            return EventWoundedSoliderData(hashMapOf(), hashMapOf())
        }

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("FightData.kt  找不到玩家信息:$playerId")
            return EventWoundedSoliderData(hashMapOf(), hashMapOf())
        }

        val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)

        val woundedSolider = hashMapOf<Int, Int>()
        val woundedTrap = hashMapOf<Int, Int>()
        for ((id, num) in woundedSoliderData.soliderMap) {
            val soliderProto = pcs.soliderCache.soliderProtoMap[id]
            if (soliderProto == null) {
                normalLog.error("FightData.kt  找不到士兵配置:$id")
                continue
            }

            if (isSolider(soliderProto.armyType)) {
                woundedSolider[id] = num
            } else {
                woundedTrap[id] = num
            }
        }

        var nowEventCureSoliderNum = 0  // 玩家当前的伤兵数量(活动伤兵营)
        var nowEventCureTrapNum = 0     // 玩家当前的伤兵数量(活动伤兵营)
        for ((_, b) in barrackMap) {
            val unitProto = pcs.soliderCache.soliderProtoMap[b.soldierId]
            if (unitProto == null) {
                normalLog.error("FightData.kt  找不到士兵配置:${b.soldierId}")
                continue
            }

            if (isSolider(unitProto.armyType)) {
                nowEventCureSoliderNum += b.canEventCureNum + b.nowEventCureNum
            } else {
                nowEventCureTrapNum += b.canEventCureNum + b.nowEventCureNum
            }
        }

        val soliderAddRate =
            getResearchEffectValue(areaCache, NO_FILTER, player, ResearchEffectCureMaxAdd)
        val baseSoliderNum =
            getResearchEffectValue(areaCache, NO_FILTER, player, CureMaxNum)
        val baseTrapNum = getResearchEffectValue(areaCache, NO_FILTER, player, TrapNumAdd)
        val activityAdd = pcs.basicProtoCache.activityHospitalNum // 活动伤兵营加成倍数

        var canEventCureSoliderNum =
            (baseSoliderNum * (1.0 + soliderAddRate / 10000.0)).toInt() * activityAdd - nowEventCureSoliderNum
        if (canEventCureSoliderNum < 0 || !isWonderWar(areaCache)) {
            canEventCureSoliderNum = 0
        }
        var canEventCureTrapNum = baseTrapNum * activityAdd - nowEventCureTrapNum
        if (canEventCureTrapNum < 0 || !isWonderWar(areaCache)) {
            canEventCureTrapNum = 0
        }

        val rstSolider = calHospitalUnit(woundedSolider, canEventCureSoliderNum)
        val eventWoundedSoliderMap = rstSolider.finalWoundedMap
        val woundedSoliderMap = rstSolider.finalDiedMap

        val rstTrap = calHospitalUnit(woundedTrap, canEventCureTrapNum)
        val eventWoundedTrapMap = rstTrap.finalWoundedMap
        val woundedTrapMap = rstTrap.finalDiedMap

        val eventWoundedMap = hashMapOf<Int, Int>()
        val woundedMap = hashMapOf<Int, Int>()
        for ((id, num) in eventWoundedSoliderMap) {
            eventWoundedMap[id] = (eventWoundedMap[id] ?: 0) + num
        }
        for ((id, num) in eventWoundedTrapMap) {
            eventWoundedMap[id] = (eventWoundedMap[id] ?: 0) + num
        }
        for ((id, num) in woundedSoliderMap) {
            woundedMap[id] = (woundedMap[id] ?: 0) + num
        }
        for ((id, num) in woundedTrapMap) {
            woundedMap[id] = (woundedMap[id] ?: 0) + num
        }

        return EventWoundedSoliderData(eventWoundedMap, woundedMap)
    }
}