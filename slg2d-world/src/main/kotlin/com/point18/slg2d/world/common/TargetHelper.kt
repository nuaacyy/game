package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Hero
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.world.event.PlayerActivityChange
import com.point18.slg2d.world.event.PowerAdd
import com.point18.slg2d.world.event.PowerChange
import com.point18.slg2d.world.event.TargetChange
import com.point18.slg2d.world.msgnotice.createPlayerPowerChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import com.point18.slg2d.world.wpm
import xyz.ariane.util.lzDebug
import java.util.*

//增加统计目标数值
fun targetAddVal(areaCache: AreaCache, playerId: Long, targetId: Int, valList: List<Long>? = null) {
    val target = areaCache.targetCache.findMyTargetByPlayerId(playerId) ?: return

    val oldPower = target.getTotalPower()
    var powerChange = false
    val targetMap = hashMapOf<Int, Long>()

    if (valList == null || valList.isEmpty()) {
        when (targetId) {
            SoliderStrength -> {
                var soliderPower = 0L
                val soliderMap = hashMapOf<Int, HashMap<Int, Int>>()
                //部队士兵实力
                val forces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)
                for (force in forces) {
                    for ((soliderId, soliderNum) in force.soliderMap) {
                        val proto = pcs.soliderCache.soliderProtoMap[soliderId] ?: continue
                        if (!isSolider(proto.armyType)) {
                            continue
                        }
                        soliderPower += soliderNum * proto.power

                        val armyMap = soliderMap.getOrPut(proto.armyType) { hashMapOf() }
                        armyMap[proto.step] = (armyMap[proto.step] ?: 0) + soliderNum
                    }
                }
                //兵营士兵实力
                val barracks = areaCache.barracksCache.findBarracksByPlayerId(target.playerId)
                for (barrack in barracks) {
                    val proto = pcs.soliderCache.soliderProtoMap[barrack.soldierId] ?: continue
                    if (!isSolider(proto.armyType)) {
                        continue
                    }
                    soliderPower += barrack.soldierNum * proto.power

                    val armyMap = soliderMap.getOrPut(proto.armyType) { hashMapOf() }
                    armyMap[proto.step] = (armyMap[proto.step] ?: 0) + barrack.soldierNum
                }

                if (soliderPower > target.soliderStrength) {
                    wpm.es.fire(
                        areaCache,
                        playerId,
                        POWER_ADD,
                        PowerAdd(SoliderTrapPower, soliderPower - target.soliderStrength)
                    )
                }

                powerChange = powerChange || target.soliderStrength != soliderPower
                target.soliderStrength = soliderPower

                targetMap[BubingNum] = soliderMap[BattleZhanshi]?.values?.sum()?.toLong() ?: 0L
                targetMap[Bubing1Num] = soliderMap[BattleZhanshi]?.get(1)?.toLong() ?: 0L
                targetMap[Bubing2Num] = soliderMap[BattleZhanshi]?.get(2)?.toLong() ?: 0L
                targetMap[Bubing3Num] = soliderMap[BattleZhanshi]?.get(3)?.toLong() ?: 0L
                targetMap[GongbingNum] = soliderMap[BattleSheshou]?.values?.sum()?.toLong() ?: 0L
                targetMap[Gongbing1Num] = soliderMap[BattleSheshou]?.get(1)?.toLong() ?: 0L
                targetMap[Gongbing2Num] = soliderMap[BattleSheshou]?.get(2)?.toLong() ?: 0L
                targetMap[Gongbing3Num] = soliderMap[BattleSheshou]?.get(3)?.toLong() ?: 0L
                targetMap[QibingNum] = soliderMap[BattleQishi]?.values?.sum()?.toLong() ?: 0L
                targetMap[Qibing1Num] = soliderMap[BattleQishi]?.get(1)?.toLong() ?: 0L
                targetMap[Qibing2Num] = soliderMap[BattleQishi]?.get(2)?.toLong() ?: 0L
                targetMap[Qibing3Num] = soliderMap[BattleQishi]?.get(3)?.toLong() ?: 0L
                targetMap[ChebingNum] = soliderMap[BattleTank]?.values?.sum()?.toLong() ?: 0L
                targetMap[Chebing1Num] = soliderMap[BattleTank]?.get(1)?.toLong() ?: 0L
                targetMap[Chebing2Num] = soliderMap[BattleTank]?.get(2)?.toLong() ?: 0L
                targetMap[Chebing3Num] = soliderMap[BattleTank]?.get(3)?.toLong() ?: 0L
            }
            TrapStrength -> {
                var trapPower = 0L
                //陷阱实力
                val barracks = areaCache.barracksCache.findBarracksByPlayerId(target.playerId)
                for (barrack in barracks) {
                    val proto = pcs.soliderCache.soliderProtoMap[barrack.soldierId]
                    if (proto == null) {
                        //Assert
                        continue
                    }
                    if (!isTrap(proto.armyType)) {
                        continue
                    }
                    trapPower += barrack.soldierNum * proto.power
                }

                if (trapPower > target.trapStrength) {
                    wpm.es.fire(
                        areaCache,
                        playerId,
                        POWER_ADD,
                        PowerAdd(SoliderTrapPower, trapPower - target.trapStrength)
                    )
                }

                powerChange = powerChange || target.trapStrength != trapPower
                target.trapStrength = trapPower
            }
            MoveCity -> {
                target.moveCityCount++
            }
            FailDefCityCount -> {
                target.defenseFailed++
            }
            SucessDefCityCount -> {
                target.defenseSuccess++
            }
            ScoutCount -> {
                target.scoutNum++
            }
            MassCount -> {
                target.massNum++
            }
            JoinMassCount -> {
                target.joinMassNum++
            }
            AtkMonsterCount -> {
                target.atkMonsterNum++
            }
            WinRecord -> {
                target.winRecord++
                targetMap[WinRecord] = target.winRecord.toLong()
            }
            FailRecord -> {
                target.failRecord++
                targetMap[FailRecord] = target.failRecord.toLong()
            }
            AttackWinRecord -> {
                target.attackWinRecord++
                targetMap[AttackWinRecord] = target.attackWinRecord.toLong()
            }
            DefendWinRecord -> {
                target.defendWinRecord++
                targetMap[DefendWinRecord] = target.defendWinRecord.toLong()
            }
            AttackFailRecord -> {
                target.attackFailRecord++
                targetMap[AttackFailRecord] = target.attackFailRecord.toLong()
            }
            DefendFailRecord -> {
                target.defendFailRecord++
                targetMap[DefendFailRecord] = target.defendWinRecord.toLong()
            }
            JjcWinRecord -> {
                target.jjcWinRecord++
                targetMap[JjcWinRecord] = target.jjcWinRecord.toLong()
            }
            CatchKingNum -> {
                target.getPrisonNum++
                targetMap[CatchKingNum] = target.getPrisonNum.toLong()
            }
            KingBeCatchNum -> {
                target.bePrisonNum++
                targetMap[KingBeCatchNum] = target.bePrisonNum.toLong()
            }
            KillKingNum -> {
                target.killKingNum++
                targetMap[KillKingNum] = target.killKingNum.toLong()
            }
            KingBeKillNum -> {
                target.kingBeKillNum++
                targetMap[KingBeKillNum] = target.kingBeKillNum.toLong()
            }
            CatchKingEscapeNum -> {
                target.catchKingEscapeNum++
                targetMap[CatchKingEscapeNum] = target.catchKingEscapeNum.toLong()
            }
            KingEscapeNum -> {
                target.kingEscapeNum++
                targetMap[KingEscapeNum] = target.kingEscapeNum.toLong()
            }
        }
    } else if (valList.size == 1) {
        val value = valList[0]
        when (targetId) {
            TotalKill -> {
                target.totalKill += value
                target.lastKillSoliderTime = getNowTime()
                areaCache.rankCache.rankMap[KillSoliderRank]?.updateValue(target)
            }
            ResearchStrength -> {
                powerChange = powerChange || target.researchStrength != value
                if (value > target.researchStrength) {
                    wpm.es.fire(
                        areaCache,
                        playerId,
                        POWER_ADD,
                        PowerAdd(ResearchPower, value - target.researchStrength)
                    )

                    wpm.es.fire(
                        areaCache,
                        playerId,
                        PLAYER_ACTIVITY_CHANGE,
                        PlayerActivityChange(
                            RESEARCH_ADD_FIGHT_VALUE_1,
                            (value - target.researchStrength).toInt(),
                            0
                        )
                    )
                }
                target.researchStrength = value
            }
            BuildStrength -> {
                powerChange = powerChange || target.buildStrength != value
                if (value > target.buildStrength) {
                    wpm.es.fire(
                        areaCache,
                        playerId,
                        POWER_ADD,
                        PowerAdd(BuildingPower, value - target.buildStrength)
                    )

                    wpm.es.fire(
                        areaCache,
                        playerId,
                        PLAYER_ACTIVITY_CHANGE,
                        PlayerActivityChange(
                            BUILD_ADD_FIGHT_VALUE_1,
                            (value - target.buildStrength).toInt(),
                            0
                        )
                    )
                }
                target.buildStrength = value
            }
            HeroStrength -> {
                powerChange = powerChange || target.heroStrength != value
                target.heroStrength = value
            }
            KingStrength -> {
                powerChange = powerChange || target.kingStrength != value
                target.kingStrength = value
            }
            MissionStrength -> {
                powerChange = powerChange || target.missionStrength != value
                target.missionStrength = value
            }
            TotalHelpAlly -> {
                target.totalHelpAlly += value.toInt()
                targetMap[TotalHelpAlly] = target.totalHelpAlly.toLong()
            }
            ResearchCount -> {
                target.researchCount += value.toInt()
            }
            ReceiveOnlineGiftCount -> {
                target.receiveOnlineGiftCount += value.toInt()
            }
            PlunderResNum -> {
                target.plunderResNum += value
            }
            LoseSoliderNum -> {
                target.soliderDieNum += value
                targetMap[LoseSoliderNum] = target.soliderDieNum
            }
            LoseTrapNum -> {
                target.trapDieNum += value
                targetMap[LoseTrapNum] = target.trapDieNum
            }
            TotalCureNum -> {
                target.cureSoliderNum += value
                targetMap[TotalCureNum] = target.cureSoliderNum
            }
            HoldCountryPos -> {
                val posId = value.toInt()
                target.posTypeMap[posId] = (target.posTypeMap[posId] ?: 0) + 1
            }
            WeakenStrength -> {
                target.weakenStrength += value
                targetMap[WeakenStrength] = target.weakenStrength
            }
            TransportFoodNum -> {
                target.transportResMap[RES_FOOD] = (target.transportResMap[RES_FOOD] ?: 0L) + value
                targetMap[TransportFoodNum] = target.transportResMap[RES_FOOD] ?: 0L
            }
            TransportStoneNum -> {
                target.transportResMap[RES_STONE] = (target.transportResMap[RES_STONE] ?: 0L) + value
                targetMap[TransportStoneNum] = target.transportResMap[RES_STONE] ?: 0L
            }
            TransportWoodNum -> {
                target.transportResMap[RES_WOOD] = (target.transportResMap[RES_WOOD] ?: 0L) + value
                targetMap[TransportWoodNum] = target.transportResMap[RES_WOOD] ?: 0L
            }
            TransportIronNum -> {
                target.transportResMap[RES_IRON] = (target.transportResMap[RES_IRON] ?: 0L) + value
                targetMap[TransportIronNum] = target.transportResMap[RES_IRON] ?: 0L
            }
            TransportCoinNum -> {
                target.transportResMap[RES_COIN] = (target.transportResMap[RES_COIN] ?: 0L) + value
                targetMap[TransportCoinNum] = target.transportResMap[RES_COIN] ?: 0L
            }
            MaxJJcRank -> {
                if (target.maxJjcRank in 1..value) {
                    return
                }
                target.maxJjcRank = value.toInt()
                targetMap[MaxJJcRank] = target.maxJjcRank.toLong()
            }
            NowJJcRank -> {
                if (value <= 0 || value > 100) {
                    return
                }
                target.lastJjcRankTime = getNowTime()
                target.jjcRank = value.toInt()
                areaCache.rankCache.rankMap[JjcRank]?.updateValue(target)
                targetMap[NowJJcRank] = target.jjcRank.toLong()
            }
            MonsterScore -> {
                target.killMonsterScore += value
                target.lastKillMonsterScoreTime = getNowTime()
                areaCache.rankCache.rankMap[KillMonsterScore]?.updateValue(target)
                targetMap[MonsterScore] = target.killMonsterScore
            }
            GetKingRewardNum -> {
                target.getRewardGoldNum += value
                targetMap[GetKingRewardNum] = target.getRewardGoldNum
            }
        }
    } else if (valList.size == 2) {
        val value1 = valList[0]
        val value2 = valList[1]
        when (targetId) {
            MakeEquip -> {
                val makeMap = target.makeEquipMap
                makeMap[value1.toInt()] = (makeMap[value1.toInt()] ?: 0) + value2.toInt()
            }
            FarmResNum -> {
                val resType = value1.toInt()
                target.farmResNumMap[resType] = (target.farmResNumMap[resType] ?: 0) + value2
                target.farmResCountMap[resType] = (target.farmResCountMap[resType] ?: 0) + 1
                targetMap[TotalFarmNum] = target.farmResNumMap.values.sum()
            }
            FarmEmptyCount -> {
                val resType = value1.toInt()
                val count = value2.toInt()
                target.farmEmptyMap[resType] = (target.farmEmptyMap[resType] ?: 0) + count
            }
        }
    } else if (valList.size == 3) {
        val value1 = valList[0]
        val value2 = valList[1]
        val value3 = valList[2]
        when (targetId) {
            AtkMonster -> {
                val monsterType = value1.toInt()
                val monsterLv = value2.toInt()
                val atkNum = value3.toInt()
                val monsterMap = target.atkMonsterMap.getOrPut(monsterType) { hashMapOf() }
                monsterMap[monsterLv] = (monsterMap[monsterLv] ?: 0) + atkNum
            }
            KillMonster -> {
                val monsterType = value1.toInt()
                val monsterLv = value2.toInt()
                val killNum = value3.toInt()
                val monsterMap = target.killMonsterMap.getOrPut(monsterType) { hashMapOf() }
                monsterMap[monsterLv] = (monsterMap[monsterLv] ?: 0) + killNum
            }
            MakeSoliderCount -> {
                val soliderType = value1.toInt()
                val soliderLv = value2.toInt()
                val soliderMap = target.makeSoliderMap.getOrPut(soliderType) { hashMapOf() }
                soliderMap[soliderLv] = (soliderMap[soliderLv] ?: 0) + value3
            }
            MakeTrapCount -> {
                val trapType = value1.toInt()
                val trapLv = value2.toInt()
                val num = valList[2]
                val trapMap = target.makeTrapMap.getOrPut(trapType) { hashMapOf() }
                trapMap[trapLv] = (trapMap[trapLv] ?: 0) + num
            }
            KillSoliderNum -> {
                val soliderType = value1.toInt()
                val soliderLv = value2.toInt()
                val num = valList[2]
                val soliderMap = target.killSoliderMap.getOrPut(soliderType) { hashMapOf() }
                soliderMap[soliderLv] = (soliderMap[soliderLv] ?: 0) + num

                var soliderTotalKill = 0L
                var trapTotalKill = 0L
                target.killSoliderMap.forEach { killMap ->
                    val armyType = killMap.key
                    killMap.value.forEach {
                        if (isSolider(armyType)) {
                            soliderTotalKill += it.value
                        } else {
                            trapTotalKill += it.value
                        }
                    }
                }
                targetMap[KillSoliderNum] = soliderTotalKill
                targetMap[KillTrapNum] = trapTotalKill
            }
            DamageSoliderCount -> {
                val soliderType = value1.toInt()
                val soliderLv = value2.toInt()
                val num = valList[2]
                val soliderMap = target.damageSoliderMap.getOrPut(soliderType) { hashMapOf() }
                soliderMap[soliderLv] = (soliderMap[soliderLv] ?: 0) + num

                var soliderTotalDamage = 0L
                target.damageSoliderMap.forEach { entry ->
                    val armyType = entry.key
                    entry.value.forEach {
                        if (isSolider(armyType)) {
                            soliderTotalDamage += it.value
                        }
                    }
                }
                targetMap[TotalDamageNum] = soliderTotalDamage
            }
        }
    }

    if (powerChange) {
        val newPower = target.getTotalPower()
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val notifier = createPlayerPowerChangeNotifier(newPower)
            notifier.notice(session)
        }

        wpm.es.fire(
            areaCache, playerId, POWER_CHANGE, PowerChange(oldPower, newPower)
        )

        //更新玩家实力到公共服
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player != null && player.allianceId != 0L) {
            val updateInfoMap = hashMapOf<Int, String>()
            updateInfoMap[UpdatePower] = newPower.toString()
            updateAllianceMemberInfo(areaCache, player.allianceId, playerId, updateInfoMap)
        }

        val rankInfo = areaCache.rankCache.rankMap[PowerRank]
        if (rankInfo != null) {
            rankInfo.updateValue(target)
            target.lastPowerChangeTime = getNowTime()
        }

        if (newPower > oldPower) {
            val changeValue = newPower - oldPower

            wpm.es.fire(
                areaCache,
                playerId,
                PLAYER_ACTIVITY_CHANGE,
                PlayerActivityChange(FIGHT_VALUE_ADD, changeValue.toInt(), 0)
            )
        }
    }

    if (targetMap.isNotEmpty()) {
        syncData2Home(areaCache, playerId, TargetSync, toJson(targetMap))
    }

    wpm.es.fire(areaCache, playerId, TARGET_CHANGE, TargetChange())
}

//刷新所有英雄实力
fun refreshAllHeroPower(areaCache: AreaCache, playerId: Long, notice: Boolean) {
    val heroList = areaCache.heroCache.findHeroMapByPlayer(playerId)
    val changeHeroList = hashMapOf<Long, Hero>()
    for ((_, hero) in heroList) {
        //重算英雄实力
        val heroPower = calHeroPower(
            hero.protoId,
            hero.level,
            hero.advLv,
            hero.awake,
            hero.skillId1,
            hero.skillId2,
            hero.skillId3,
            hero.skillId4,
            hero.heroEquipInfoMap
        )
        if (hero.heroStrength != heroPower) {
            hero.heroStrength = heroPower
            changeHeroList[hero.id] = hero
        }
    }
    if (changeHeroList.count() == 0) {
        return
    }
    if (!notice) {
        return
    }
    //发送英雄属性变化通知
    val notifier = createValueChgNotifier()
    for ((id, hero) in changeHeroList) {
        notifier.append(id, HERO_POWER, hero.heroStrength)
    }

    val session = fetchOnlinePlayerSession(areaCache, playerId)
    if (session != null) {
        notifier.notice(session)
    }

}

//计算英雄战力
fun calHeroPower(
    heroProtoId: Int,
    lv: Int,
    star: Int,
    awake: Int,
    skillId1: Int,
    skillId2: Int,
    skillId3: Int,
    skillId4: Int,
    equipInfoMap: HashMap<Int, HeroEquipVo>? = null
): Long {
    val unitBaseProto = pcs.unitBaseCache.fetchProtoById(heroProtoId)
    if (unitBaseProto == null) {
        normalLog.error("找不到英雄单位$heroProtoId")
        return 0
    }
    if (unitBaseProto.npcType == UNIT_TYPE_MONSTER) {
        return unitBaseProto.battleNum
    }

    val (atkW, defW, magicW, magicDefW, hpW, speedW, baojiW, baojilvW, hpRecoveryW,
            moraleRecoveryW, atkAddHpW, atkAddMoraleW, dodgeW, atkType) = getHeroBasicInfo(
        heroProtoId,
        lv,
        star,
        awake,
        equipInfoMap
    )
    val fightPara = pcs.fightingParaProtoCache.fightingParaProtoMapByAtkType[atkType]
    if (fightPara == null) {
        normalLog.lzDebug { "TargetHelper.kt : pc.pcs.fightingParaProtoCache.fightingParaProtoMapByAtkType[atkType] == null" }
        return 0
    }
    var atk = atkW
    var atkPara = fightPara.attackPara
    if (atkType == FashuXing) {
        atk = magicW
        atkPara = fightPara.defencePara
    }
    val propertyPower =
        atk * atkPara +
                defW * fightPara.defencePara +
                magicDefW * fightPara.magicDefPara +
                hpW * fightPara.hpPara +
                hpRecoveryW * fightPara.hpRecoverPara +
                moraleRecoveryW * fightPara.moraleRecoverPara +
                atkAddHpW * fightPara.attackAddHpPara +
                atkAddMoraleW * fightPara.attackAddMoralePara +
                dodgeW * fightPara.dodgePara +
                baojiW * fightPara.critPara

    val unlockSkillIds = LinkedList<Int>()
    val rankMap = pcs.heroRankProtoCache.heroRankProtoCache[heroProtoId]
    if (rankMap == null) {
        normalLog.lzDebug { "TargetHelper.kt : pc.pcs.heroRankProtoCache.heroRankProtoCache[heroProtoId] == null" }
        return 0
    }
    val heroRankProto = rankMap[awake]
    if (heroRankProto == null) {
        normalLog.lzDebug { "TargetHelper.kt : rankMap[awake] == null" }
        return 0
    }
    for (pos in heroRankProto.unlockSkillPosList) {
        when (pos) {
            1 ->
                unlockSkillIds.add(skillId1)
            2 ->
                unlockSkillIds.add(skillId2)
            3 ->
                unlockSkillIds.add(skillId3)
            4 ->
                unlockSkillIds.add(skillId4)
        }
    }
    var skillPower = 0.0
    for (skillId in unlockSkillIds) {
        val skill = pcs.heroSkillProtoCache.heroSkillMap[skillId] ?: continue

        skillPower += skill.fightValue
    }

    return propertyPower.toLong() + skillPower.toLong()
}