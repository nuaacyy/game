package com.point18.slg2d.world.module.soliderBattle.fight

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.soliderBattle.fightdomain.*
import com.point18.slg2d.world.module.soliderBattle.fightlogic.*
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ATK_SIDE
import com.point18.slg2d.common.constg.CELL_CASTLE
import com.point18.slg2d.common.constg.DEF_SIDE
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.common.*
import java.util.*

// 一场战斗
data class SoliderFight(
    var areaCache: AreaCache,
    var posX: Int,
    var posY: Int,
    var fightLogic: SoliderFightLogic,
    var atkFightData: FightData, //攻击方战斗数据
    var defFightData: FightData //防守方战斗数据
) {

    fun initAndStart(): SoliderFightInfoData {
        this.fillFightPlayerInfo()
        val cellType = areaCache.mapCellCache.getCellType(this.posX, this.posY)
        // 加载部队数据
        this.loadForces(this.atkFightData, this.defFightData, true, cellType == CELL_CASTLE)
        this.loadForces(this.defFightData, this.atkFightData, false, false)

        // 开始战斗
        this.fightLogic.mainFight()

        // 战斗统计计算
        val tempAtkLoseMap = hashMapOf<Int, Double>()
        val tempDefLoseMap = hashMapOf<Int, Double>()
        for (i in 0 until this.fightLogic.fightGeziList.count()) {
            val fightGezi = this.fightLogic.fightGeziList[i]
            var hasUnit = false
            for (unit in fightGezi.battleUnits) {
                if (unit.protoId == 0) {
                    continue
                }
                hasUnit = true
                break
            }
            if (!hasUnit) {
                continue
            }

            for (unit in fightGezi.battleUnits) {
                if (unit.protoId == 0) {
                    continue
                }
                val killInfoList = LinkedList<KillInfo>()
                for ((id, num) in unit.killMap) {
                    killInfoList.add(KillInfo(id, num))
                }
                val leftTroops = unit.troopsAfterFight.toInt()
                var fightData = this.atkFightData
                if (fightGezi.isLeft) {
                    val atkForce = this.fightLogic.newSoliderFightResp.atkSoliderForceMap[unit.protoId]
                    if (atkForce == null) {
                        normalLog.error("找不到攻击方数据:%d", unit.protoId)
                        return this.fightLogic.newSoliderFightResp
                    }
                    atkForce.bingliAfterFight = leftTroops
                    atkForce.killData = killInfoList
                    tempAtkLoseMap[unit.protoId] = (tempAtkLoseMap[unit.protoId] ?: 0.0) + unit.troopsBeforeFight -
                        unit.troopsAfterFight
                } else {
                    val defForce = this.fightLogic.newSoliderFightResp.defSoliderForceMap[unit.protoId]
                    if (defForce == null) {
                        normalLog.error("找不到攻击方数据:%d", unit.protoId)
                        return this.fightLogic.newSoliderFightResp
                    }
                    defForce.bingliAfterFight = leftTroops
                    defForce.killData = killInfoList
                    fightData = this.defFightData
                    tempDefLoseMap[unit.protoId] = (tempDefLoseMap[unit.protoId] ?: 0.0) + unit.troopsBeforeFight -
                        unit.troopsAfterFight
                }
                fightData.damagedRate[unit.protoId] = (unit.troopsBeforeFight - unit.troopsAfterFight) /
                    unit.troopsBeforeFight
                fightData.killMap[unit.protoId] = unit.killMap
            }
        }

        for ((id, num) in tempAtkLoseMap) {
            this.atkFightData.loseSoliderMap[id] = num.toInt()
        }
        for ((id, num) in tempDefLoseMap) {
            this.defFightData.loseSoliderMap[id] = num.toInt()
        }

        return this.fightLogic.newSoliderFightResp
    }

    //填充战斗方信息
    fun fillFightPlayerInfo() {
        //加载攻击方信息
        val atkPlayer = areaCache.playerCache.findPlayerById(this.atkFightData.playerId)
        var atkPlayerName = ""
        var atkAllianceName = ""
        var atkLordUnitId = 0
        if (atkPlayer != null) {
            atkPlayerName = atkPlayer.name
            atkAllianceName = atkPlayer.allianceName
            val hero = areaCache.heroCache.findHeroById(atkFightData.playerId, atkPlayer.mainHeroId)
            if (hero != null) {
                atkLordUnitId = hero.protoId
            }
        }
        this.fightLogic.newSoliderFightResp.atkFightPlayerInfo =
            newReportFightPlayerInfo(this.atkFightData.playerId, atkPlayerName, atkAllianceName, 0, 0, atkLordUnitId)

        //加载防守方
        val relicCell = areaCache.relicCache.findRelicByXY(this.posX, this.posY)
        if (relicCell != null) {
            this.fightLogic.newSoliderFightResp.defFightPlayerInfo =
                newReportFightPlayerInfo(0, "", "", relicCell.relicId, 0)
            return
        }
        val defPlayer = areaCache.playerCache.findPlayerById(this.defFightData.playerId)
        var defPlayerName = ""
        var defAllianceName = ""
        var defLordUnitId = 0
        if (defPlayer != null) {
            defPlayerName = defPlayer.name
            defAllianceName = defPlayer.allianceName
            val hero = areaCache.heroCache.findHeroById(defFightData.playerId, defPlayer.mainHeroId)
            if (hero != null) {
                defLordUnitId = hero.protoId
            }
        }
        this.fightLogic.newSoliderFightResp.defFightPlayerInfo =
            newReportFightPlayerInfo(this.defFightData.playerId, defPlayerName, defAllianceName, 0, 0, defLordUnitId)
    }

    //加载部队
    fun loadForces(atkFightData: FightData, defFightData: FightData, isLeft: Boolean, isAtkCity: Boolean) {
        val defPlayer = areaCache.playerCache.findPlayerById(defFightData.playerId)

        val atkIntHeroList = LinkedList<IntHeroData>()
        for (heroData in atkFightData.heroList) {
            atkIntHeroList.add(IntHeroData(heroData.protoId, heroData.star, heroData.awake))
        }
        val atkIntEffects = getIntHeroEffect(atkIntHeroList)

        val defIntHeroList = LinkedList<IntHeroData>()
        for (heroData in defFightData.heroList) {
            defIntHeroList.add(IntHeroData(heroData.protoId, heroData.star, heroData.awake))
        }
        val defIntEffects = getIntHeroEffect(defIntHeroList)

        //加载士兵
        val totalArmyMap = hashMapOf<Int, HashMap<Int, Int>>()
        for (armyData in atkFightData.soliderDataList) {
            for ((soliderId, soliderNum) in armyData.soliderMap) {
                val proto = pcs.soliderCache.soliderProtoMap[soliderId]
                if (proto == null) {
                    continue
                }
                val armyMap = totalArmyMap.getOrPut(proto.armyType) { hashMapOf() }
                armyMap[soliderId] = (armyMap[soliderId] ?: 0) + soliderNum
            }
        }

        val soliderGeziMap = hashMapOf<Int, FightGezi>()

        val multiFight = atkFightData.groupId != 0L && atkFightData.soliderDataList.count() > 1
        val player = areaCache.playerCache.findPlayerById(atkFightData.playerId)
        for ((armyType, solider) in totalArmyMap) {
            var gezi = soliderGeziMap[armyType]
            if (gezi == null) {
                gezi = newFightGezi(this.fightLogic)
                gezi.isLeft = isLeft
                gezi.playerId = atkFightData.playerId
                soliderGeziMap[armyType] = gezi
            }

            for ((soliderId, soliderNum) in solider) {
                val unit = newBattleUnit(gezi)
                if (player != null) {
                    this.loadPlayerSolider(
                        player,
                        defPlayer,
                        soliderId,
                        soliderNum,
                        multiFight,
                        isAtkCity,
                        unit,
                        atkFightData.additionBuffMap,
                        defFightData.additionBuffMap,
                        atkIntEffects,
                        defIntEffects
                    )
                } else {
                    this.loadNpcSolider(
                        soliderId,
                        soliderNum,
                        unit,
                        defPlayer,
                        atkFightData.additionBuffMap,
                        defFightData.additionBuffMap,
                        atkIntEffects,
                        defIntEffects
                    )
                }
            }
        }

        for ((_, gezi) in soliderGeziMap) {
            this.fightLogic.fightGeziList.add(gezi)
        }

        //初始化战报的部队信息
        for (heroData in atkFightData.heroList) {
            val heroForce = newReportHeroForce()
            heroForce.protoId = heroData.protoId
            val heroProto = pcs.unitBaseCache.fetchProtoById(heroData.protoId)
            if (heroProto == null) {
                continue
            }
            var equips: HashMap<Int, HeroEquipVo>? = null
            if (heroData.id != 0L) {
                val hero = areaCache.heroCache.findHeroById(atkFightData.playerId, heroData.id)
                if (hero != null) {
                    equips = hero.heroEquipInfoMap
                    heroForce.king = hero.mainHeroState != 0
                }
            }
            heroForce.heroPower = calHeroPower(
                heroData.protoId, heroData.lv, heroData.star, heroData.awake,
                heroProto.skill1, heroProto.skill2, heroProto.skill3, heroProto.skill4, equips
            )

            if (isLeft) {
                heroForce.leftOrRight = ATK_SIDE
                this.fightLogic.newSoliderFightResp.atkHeroForceMap[heroForce.protoId] = heroForce
            } else {
                heroForce.leftOrRight = DEF_SIDE
                this.fightLogic.newSoliderFightResp.defHeroForceMap[heroForce.protoId] = heroForce
            }

            this.fightLogic.newSoliderFightResp.heroForceInfo.add(heroForce)
        }

        for ((_, soliderData) in totalArmyMap) {
            for ((soliderId, soliderNum) in soliderData) {
                val soliderForce = newReportSoliderForce()
                soliderForce.protoId = soliderId
                soliderForce.bingliBeforeFight = soliderNum
                if (isLeft) {
                    soliderForce.leftOrRight = ATK_SIDE
                    this.fightLogic.newSoliderFightResp.atkSoliderForceMap[soliderForce.protoId] = soliderForce
                } else {
                    soliderForce.leftOrRight = DEF_SIDE
                    this.fightLogic.newSoliderFightResp.defSoliderForceMap[soliderForce.protoId] = soliderForce
                }
                this.fightLogic.newSoliderFightResp.soliderForceInfo.add(soliderForce)
            }
        }
    }

    //加载玩家士兵
    fun loadPlayerSolider(
        atkPlayer: Player,
        defPlayer: Player?,
        soliderId: Int,
        soliderNum: Int,
        multiFight: Boolean,
        isAtkCity: Boolean,
        unit: BattleUnit,
        atkAdditionEffect: Map<Int, Int>? = null,
        defAdditionEffect: Map<Int, Int>? = null,
        aktIntHeroEffectMap: Map<Int, Int>? = null,
        defIntHeroEffectMap: Map<Int, Int>? = null
    ) {
        // 获取目标部队格子
        unit.protoId = soliderId
        unit.troopsBeforeFight = soliderNum
        unit.troopsAfterFight = soliderNum.toDouble()

        val rst = getSoliderFightInfo(
            soliderId,
            unit.troopsBeforeFight,
            multiFight,
            isAtkCity,
            { effectId ->
                var effectVal = getResearchEffectValue(this.areaCache, NO_FILTER, atkPlayer, effectId)
                effectVal += atkAdditionEffect?.get(effectId) ?: 0
                effectVal += aktIntHeroEffectMap?.get(effectId) ?: 0
                effectVal
            },
            { effectId ->
                var effectVal = 0
                if (defPlayer != null) {
                    effectVal = getResearchEffectValue(this.areaCache, NO_FILTER, defPlayer, effectId)
                }
                effectVal += defAdditionEffect?.get(effectId) ?: 0
                effectVal += defIntHeroEffectMap?.get(effectId) ?: 0
                effectVal
            }
        )
        unit.gongji = rst.att
        unit.fangyu = rst.def
    }

    //加载NPC士兵
    fun loadNpcSolider(
        soliderId: Int,
        soliderNum: Int,
        unit: BattleUnit,
        defPlayer: Player? = null,
        atkAdditionEffect: Map<Int, Int>? = null,
        defAdditionEffect: Map<Int, Int>? = null,
        aktIntHeroEffectMap: Map<Int, Int>? = null,
        defIntHeroEffectMap: Map<Int, Int>? = null
    ) {
        unit.protoId = soliderId

        val rst = getSoliderFightInfo(
            soliderId,
            soliderNum,
            false,
            false,
            { effectId ->
                var effectVal = atkAdditionEffect?.get(effectId) ?: 0
                effectVal += aktIntHeroEffectMap?.get(effectId) ?: 0
                effectVal
            },
            { effectId ->
                var effectVal = 0
                if (defPlayer != null) {
                    effectVal = getResearchEffectValue(this.areaCache, NO_FILTER, defPlayer, effectId)
                }
                effectVal += defAdditionEffect?.get(effectId) ?: 0
                effectVal += defIntHeroEffectMap?.get(effectId) ?: 0
                effectVal
            })
        unit.gongji = rst.att
        unit.fangyu = rst.def

        unit.troopsBeforeFight = soliderNum
        unit.troopsAfterFight = soliderNum.toDouble()
    }
}

fun createSoliderFight(
    areaCache: AreaCache,
    posX: Int,
    posY: Int,
    reportType: Int,
    atkFightData: FightData,
    defFightData: FightData
): SoliderFight {
    return SoliderFight(
        areaCache,
        posX,
        posY,
        createSoliderFightLogic(reportType),
        atkFightData,
        defFightData
    )
}


