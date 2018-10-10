package com.point18.slg2d.world.module.walk.fightStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.common.updateAllianceMemberInfo
import com.point18.slg2d.world.event.*
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.SoliderDataAfterFight
import com.point18.slg2d.world.wpm
import java.util.Arrays.asList

abstract class BaseSoliderFightDeal : ISoliderFightDeal {
    //分配战斗后的击杀击伤
    fun divideSoliderAfterFight(
        atkFightData: FightData,
        defFightData: FightData,
        atkInfoAfterFight: SoliderDataAfterFight,
        defInfoAfterFight: SoliderDataAfterFight
    ) {
        // 把攻方的死兵分配给守方的击杀
        val atkLoseMap = hashMapOf<Int, Int>()
        for ((_, soliderData) in atkInfoAfterFight.diedSoliderDataMap) {
            for ((id, num) in soliderData.soliderMap) {
                atkLoseMap[id] = (atkLoseMap[id] ?: 0) + num
            }
        }
        defInfoAfterFight.killedSoliderDataMap = defFightData.calSoliderKillInfo(atkLoseMap)

        // 把攻方的伤兵分配给守方的击伤
        val atkDamageMap = hashMapOf<Int, Int>()
        for ((_, soliderData) in atkInfoAfterFight.woundedSoliderDataMap) {
            for ((id, num) in soliderData.soliderMap) {
                atkDamageMap[id] = (atkDamageMap[id] ?: 0) + num
            }
        }
        defInfoAfterFight.damageSoliderDataMap = defFightData.calSoliderDamageInfo(atkDamageMap)

        // 把守方的死兵分配给攻方的击杀
        val defLoseMap = hashMapOf<Int, Int>()
        for ((_, soliderData) in defInfoAfterFight.diedSoliderDataMap) {
            for ((id, num) in soliderData.soliderMap) {
                defLoseMap[id] = (defLoseMap[id] ?: 0) + num
            }
        }
        atkInfoAfterFight.killedSoliderDataMap = atkFightData.calSoliderKillInfo(defLoseMap)

        // 把守方的伤兵分配给攻方的击伤
        val defDamageMap = hashMapOf<Int, Int>()
        for ((_, soliderData) in defInfoAfterFight.woundedSoliderDataMap) {
            for ((id, num) in soliderData.soliderMap) {
                defDamageMap[id] = (defDamageMap[id] ?: 0) + num
            }
        }
        atkInfoAfterFight.damageSoliderDataMap = atkFightData.calSoliderDamageInfo(defDamageMap)
    }

    //统计战斗数据
    fun statisticSoliderData(
        areaCache: AreaCache,
        runType: Int,
        soliderDataAfterFight: SoliderDataAfterFight
    ): Int {
        // 统计总击杀/击伤
        var atkTotalDamageOrKill = 0

        // 处理击杀数据
        for ((_, soliderData) in soliderDataAfterFight.killedSoliderDataMap) {
            var totalKill = 0
            var weakPower = 0
            for ((soliderProtoId, num) in soliderData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[soliderProtoId]
                if (soliderProto == null) {
                    continue
                }

                totalKill += num
                weakPower += soliderProto.power * num

                var action = 0
                when {
                    soliderProto.step == 1 -> action = KILL_SOLIDER_1
                    soliderProto.step == 2 -> action = KILL_SOLIDER_2
                    soliderProto.step == 3 -> action = KILL_SOLIDER_3
                    soliderProto.step == 4 -> action = KILL_SOLIDER_4
                }

                wpm.es.fire(
                    areaCache,
                    soliderData.playerId,
                    PLAYER_ACTIVITY_CHANGE,
                    PlayerActivityChange(action, num, 0)
                )

                targetAddVal(
                    areaCache,
                    soliderData.playerId,
                    KillSoliderNum,
                    asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), num.toLong())
                )
            }
            //添加soliderData.PlayerId的总击杀数totalKill
            val player = areaCache.playerCache.findPlayerById(soliderData.playerId)
            if (player == null) {
                normalLog.error("找不到玩家信息:%d", soliderData.playerId)
                continue
            }
            if (player.allianceId != 0L) {
                val updateInfo = hashMapOf<Int, String>()
                updateInfo[UpdateKillSolider] = totalKill.toString()
                updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfo)
            }

            targetAddVal(
                areaCache,
                soliderData.playerId,
                TotalKill,
                asList(totalKill.toLong())
            )

            targetAddVal(
                areaCache,
                soliderData.playerId,
                WeakenStrength,
                asList(weakPower.toLong())
            )

            wpm.es.fire(
                areaCache,
                soliderData.playerId,
                KILL_SOLDIER,
                KillSoliderEvent(soliderData.soliderMap)
            )

            atkTotalDamageOrKill += totalKill
        }

        // 处理击伤数据
        for ((_, soliderData) in soliderDataAfterFight.damageSoliderDataMap) {
            var totalDamage = 0
            var weakPower = 0
            for ((soliderProtoId, num) in soliderData.soliderMap) {
                val soliderProto = pcs.soliderCache.soliderProtoMap[soliderProtoId]
                if (soliderProto == null) {
                    continue
                }

                totalDamage += num
                weakPower += soliderProto.power * num

                targetAddVal(
                    areaCache,
                    soliderData.playerId,
                    DamageSoliderCount,
                    asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), num.toLong())
                )
            }

            targetAddVal(
                areaCache,
                soliderData.playerId,
                WeakenStrength,
                asList(weakPower.toLong())
            )

            wpm.es.fire(
                areaCache,
                soliderData.playerId,
                DAMAGE_SOLDIER,
                DamageSoliderEvent(soliderData.soliderMap)
            )

            atkTotalDamageOrKill += totalDamage
        }

        // 处理死兵数据
        for ((_, soliderData) in soliderDataAfterFight.diedSoliderDataMap) {
            var totalDie = 0
            for ((soliderProtoId, num) in soliderData.soliderMap) {
                totalDie += num

                val soliderProto = pcs.soliderCache.soliderProtoMap[soliderProtoId]
                if (soliderProto == null) {
                    continue
                }

                val isLeader = false
                val action = getLoseSoliderCondition(isLeader, runType, soliderProto.step)

                wpm.es.fire(
                    areaCache,
                    soliderData.playerId,
                    PLAYER_ACTIVITY_CHANGE,
                    PlayerActivityChange(action, num, 0)
                )

                targetAddVal(
                    areaCache,
                    soliderData.playerId,
                    KillSoliderNum,
                    asList(soliderProto.armyType.toLong(), soliderProto.step.toLong(), num.toLong())
                )
            }
            //添加soliderData.PlayerId的总死亡数totalDie
            val player = areaCache.playerCache.findPlayerById(soliderData.playerId)
            if (player == null) {
                normalLog.error("找不到玩家信息:%d", soliderData.playerId)
                continue
            }
            if (player.allianceId != 0L) {
                val updateInfo = hashMapOf<Int, String>()
                updateInfo[UpdateCureSolider] = totalDie.toString()
                updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfo)
            }
        }

        //添加玩家的士兵死亡统计
        for ((_, soliderData) in soliderDataAfterFight.diedSoliderDataMap) {
            var loseSolider = 0
            var loseTrap = 0
            for ((soliderId, num) in soliderData.soliderMap) {
                val soliderPorto = pcs.soliderCache.soliderProtoMap[soliderId] ?: continue
                if (isSolider(soliderPorto.armyType)) {
                    loseSolider += num
                } else {
                    loseTrap += num
                }
            }
            targetAddVal(
                areaCache,
                soliderData.playerId,
                LoseSoliderNum,
                asList(loseSolider.toLong())
            )
            targetAddVal(
                areaCache,
                soliderData.playerId,
                LoseTrapNum,
                asList(loseTrap.toLong())
            )
        }

        return atkTotalDamageOrKill
    }

    //战斗的统计
    fun statisticFightData(
        areaCache: AreaCache,
        atkFightData: FightData,
        defFightData: FightData,
        fightResult: Int,
        isMass: Boolean
    ) {
        atkFightData.soliderDataList.forEach {
            wpm.es.fire(
                areaCache, it.playerId, ATK_PLAYER,
                AtkEvent(fightResult)
            )

            if (fightResult == FIGHT_RESULT_WIN) {
                targetAddVal(
                    areaCache,
                    it.playerId,
                    WinRecord
                )

                targetAddVal(
                    areaCache,
                    it.playerId,
                    AttackWinRecord
                )

                if (isMass) {
                    wpm.es.fire(
                        areaCache,
                        it.playerId,
                        MASS,
                        MassEvent(it.playerId == atkFightData.playerId)
                    )

                    if (it.playerId == atkFightData.playerId) {
                        targetAddVal(
                            areaCache,
                            atkFightData.playerId,
                            MassCount
                        )
                    } else {
                        targetAddVal(
                            areaCache,
                            it.playerId,
                            JoinMassCount
                        )
                    }
                }
            } else {
                targetAddVal(
                    areaCache,
                    it.playerId,
                    FailRecord
                )

                targetAddVal(
                    areaCache,
                    it.playerId,
                    AttackFailRecord
                )

            }
        }

        defFightData.soliderDataList.forEach {
            if (fightResult == FIGHT_RESULT_LOSE) {
                targetAddVal(
                    areaCache,
                    it.playerId,
                    SucessDefCityCount
                )

                targetAddVal(
                    areaCache,
                    it.playerId,
                    WinRecord
                )

                targetAddVal(
                    areaCache,
                    it.playerId,
                    DefendWinRecord
                )
            } else {
                targetAddVal(
                    areaCache,
                    it.playerId,
                    FailRecord
                )
            }
        }
    }
}