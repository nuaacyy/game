package com.point18.slg2d.world.common

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.fightdomain.FightData
import pb4battle.BattleMsg

// 根据武将模板,等级,星级,觉醒级等信息 来计算出武将面板属性
data class GetHeroBasicInfoReturn(
    var atkW: Double,
    var defW: Double,
    var magicW: Double,
    var magicDefW: Double,
    var hpW: Double,
    var speedW: Double,
    var baojiW: Double,
    var baojilvW: Double,
    var hpRecoveryW: Double,
    var moraleRecoveryW: Double,
    var atkAddHpW: Double,
    var atkAddMoraleW: Double,
    var dodgeW: Double,
    var atkType: Int
)

fun getHeroBasicInfo(
    protoId: Int,
    lv: Int,
    starLv: Int,
    awake: Int,
    equipInfoMap: HashMap<Int, HeroEquipVo>?,
    additionPropertyMap: HashMap<Set<Int>, Map<Int, Int>>? = null
): (GetHeroBasicInfoReturn) {
    val unitBaseProto = pcs.unitBaseCache.fetchProtoById(protoId)
    if (unitBaseProto == null) {
        com.point18.slg2d.common.commonfunc.normalLog.error("找不到英雄单位$protoId")
        return GetHeroBasicInfoReturn(
            0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0
        )
    }
    val heroStarProtoMap = pcs.heroStarProtoCache.heroStarProtoCache[protoId]
    val heroStarProto = heroStarProtoMap?.get(starLv)

    val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[protoId]
    val heroRankProto = heroRankProtoMap?.get(awake)

    //基础成长率
    var atkG = unitBaseProto.attackGrowth
    var defG = unitBaseProto.defenceGrowth
    var magicG = unitBaseProto.magicGrowth
    var magicDefG = unitBaseProto.magicDefGrowth
    var hpG = unitBaseProto.hpGrowth

    //星级替换成长率
    if (heroStarProto != null) {
        for ((growType, grow) in heroStarProto.growMap) {
            when (growType) {
                com.point18.slg2d.common.constg.ARR_PUGONG_GROWTH -> atkG = grow
                com.point18.slg2d.common.constg.ARR_FANGYU_GROWTH -> defG = grow
                com.point18.slg2d.common.constg.ARR_MOULUE_GROWTH -> magicG = grow
                com.point18.slg2d.common.constg.ARR_MOUFANG_GROWTH -> magicDefG = grow
                com.point18.slg2d.common.constg.ARR_HP_GROWTH -> hpG = grow
            }
        }
    }

    //星级增加属性
    val starAddEffect = hashMapOf<Int, Double>()
    for (i in 1..starLv) {
        val starProtoMap = pcs.heroStarProtoCache.heroStarProtoCache[protoId]

        if (starProtoMap == null) {
            continue
        }

        val starProto = starProtoMap[i]

        if (starProto == null) {
            continue
        }

        for ((effectId, effectVal) in starProto.starEffectMap) {
            starAddEffect[effectId] = (starAddEffect[effectId] ?: 0.0) + effectVal
        }
    }

    //装备增加属性
    val equipAddEffect = hashMapOf<Int, Double>()
    if (equipInfoMap != null) {
        for ((equipId, vo) in equipInfoMap) {
            val heroTrophiesProtoMap = pcs.heroTrophiesRankProtoCache.heroTrophiesRanksMap[equipId]
            if (heroTrophiesProtoMap == null) {
                continue
            }

            val heroTrophiesProto = heroTrophiesProtoMap[vo.advLv]
            if (heroTrophiesProto == null) {
                continue
            }


            for ((effectId, effectVal) in heroTrophiesProto.equipEffectMap) {
                equipAddEffect[effectId] = (equipAddEffect[effectId] ?: 0.0) + effectVal
            }
            for ((effectId, effectVal) in heroTrophiesProto.equipOtherEffectMap) {
                equipAddEffect[effectId] = (equipAddEffect[effectId] ?: 0.0) + effectVal
            }
        }
    }

    //星象属性加成比例
    val starAttrAddEffectPercent = hashMapOf<Int, Int>()
    if (additionPropertyMap != null) {
        //处理增加的属性
        for ((starAttr, additionMap) in additionPropertyMap) {
            var add = false
            for (attr in starAttr) {
                if (!unitBaseProto.starAttributeMap.contains(attr)) {
                    continue
                }
                add = true
                break
            }
            if (!add) {
                continue
            }

            for ((effectId, effectVal) in additionMap) {
                starAttrAddEffectPercent[effectId] = (starAttrAddEffectPercent[effectId] ?: 0) + effectVal
            }
        }
    }

    // 基础属性+升星属性+觉醒属性+等级属性成长
    val atkW = (unitBaseProto.attack + (starAddEffect[ARR_WUGONG]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_WUGONG) ?: 0.0) + (equipAddEffect[ARR_WUGONG]
        ?: 0.0) + atkG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_WUGONG, 0)) / 10000
    val defW = (unitBaseProto.defence + (starAddEffect[ARR_WUFANG]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_WUFANG) ?: 0.0) + (equipAddEffect[ARR_WUFANG]
        ?: 0.0) + defG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_WUFANG, 0)) / 10000
    val magicW = (unitBaseProto.magic + (starAddEffect[ARR_FAGONG]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_FAGONG) ?: 0.0) + (equipAddEffect[ARR_FAGONG]
        ?: 0.0) + magicG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_FAGONG, 0)) / 10000
    val magicDefW = (unitBaseProto.magicDef + (starAddEffect[ARR_FAFANG]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_FAFANG) ?: 0.0) + (equipAddEffect[ARR_FAFANG]
        ?: 0.0) + magicDefG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(
        ARR_FAFANG,
        0
    )) / 10000
    val hpW =
        (unitBaseProto.hp + (starAddEffect[ARR_HPLIMIT]
            ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_HPLIMIT)
            ?: 0.0) + (equipAddEffect[ARR_HPLIMIT]
            ?: 0.0) + hpG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(
            ARR_HPLIMIT,
            0
        )) / 10000
    val speedW =
        (unitBaseProto.speed + (starAddEffect[ARR_SUDU]
            ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_SUDU)
            ?: 0.0) + (equipAddEffect[ARR_SUDU]
            ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_SUDU, 0)) / 10000
    val baojiW =
        (unitBaseProto.crit + (starAddEffect[ARR_BAOJI]
            ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_BAOJI)
            ?: 0.0) + (equipAddEffect[ARR_BAOJI]
            ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_BAOJI, 0)) / 10000
    val baojilvW = (unitBaseProto.critMult + (starAddEffect[ARR_BAOJILV]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_BAOJILV)
        ?: 0.0) + (equipAddEffect[ARR_BAOJILV]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_BAOJILV, 0)) / 10000
    val hpRecoveryW = (unitBaseProto.hpRecover + (starAddEffect[ARR_HPRECOVREY]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_HPRECOVREY)
        ?: 0.0) + (equipAddEffect[ARR_HPRECOVREY]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_HPRECOVREY, 0)) / 10000
    val moraleRecoveryW = (unitBaseProto.moraleRecover + (starAddEffect[ARR_MORALERECOVERY]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_MORALERECOVERY)
        ?: 0.0) + (equipAddEffect[ARR_MORALERECOVERY]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(
        ARR_MORALERECOVERY,
        0
    )) / 10000
    val atkAddHpW = (unitBaseProto.attackAddHp + (starAddEffect[ARR_ATKADDHP]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_ATKADDHP)
        ?: 0.0) + (equipAddEffect[ARR_ATKADDHP]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_ATKADDHP, 0)) / 10000
    val atkAddMoraleW = (unitBaseProto.attackAddMorale + (starAddEffect[ARR_ATKADDMORALE]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_ATKADDMORALE)
        ?: 0.0) + (equipAddEffect[ARR_ATKADDMORALE]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(
        ARR_ATKADDMORALE,
        0
    )) / 10000
    val dodgeW =
        (unitBaseProto.dodge + (starAddEffect[ARR_DODGE]
            ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_DODGE)
            ?: 0.0) + (equipAddEffect[ARR_DODGE]
            ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_DODGE, 0)) / 10000
    val atkType = unitBaseProto.attackType

    return GetHeroBasicInfoReturn(
        atkW, defW, magicW, magicDefW, hpW, speedW, baojiW, baojilvW, hpRecoveryW,
        moraleRecoveryW, atkAddHpW, atkAddMoraleW, dodgeW, atkType
    )
}

//获取玩家士兵战斗属性
data class GetSoliderFightInfoReturn(
    var att: Double,
    var def: Double,
    var hp: Int,
    var speed: Double,
    var atkCity: Double,
    var baoji: Double,
    var baojilv: Double,
    var state: Int
)

fun getSoliderFightInfo(
    soliderId: Int,
    soliderNum: Int,
    multiFight: Boolean,
    isAtkCity: Boolean,
    getAtkEffect: (effectId: Int) -> Int,
    getDefEffect: (effectId: Int) -> Int
): GetSoliderFightInfoReturn {
    val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
    if (soliderProto == null) {
        return GetSoliderFightInfoReturn(0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0, 0)
    }

    var atkAddNum = getAtkEffect(ResearchEffectAllAtkAdd)
    if (multiFight) {
        atkAddNum += getAtkEffect(MassAtkAdd)
    }
    if (isAtkCity) {
        atkAddNum -= getDefEffect(DefCityDefAdd)
    }
    var defAddNum = getAtkEffect(ResearchEffectAllDefAdd) + getAtkEffect(WalkHotDefAdd)
    var hpAddNum = getAtkEffect(ResearchEffectAllHpAdd) + getAtkEffect(WalkHotHpAdd)

    // 玩家武将享受科技加成
    when {
        soliderProto.armyType == BattleQishi -> {
            atkAddNum += getAtkEffect(ResearchEffectQishiAtkAdd)
            defAddNum += getAtkEffect(ResearchEffectQishiDefAdd)
            hpAddNum += getAtkEffect(ResearchEffectQishiHpAdd)
        }
        soliderProto.armyType == BattleZhanshi -> {
            atkAddNum += getAtkEffect(ResearchEffectZhanshiAtkAdd)
            defAddNum += getAtkEffect(ResearchEffectZhanshiDefAdd)
            hpAddNum += getAtkEffect(ResearchEffectZhanshiHpAdd)
        }
        soliderProto.armyType == BattleSheshou -> {
            atkAddNum += getAtkEffect(ResearchEffectSheshouAtkAdd)
            defAddNum += getAtkEffect(ResearchEffectSheshouDefAdd)
            hpAddNum += getAtkEffect(ResearchEffectSheshouHpAdd)
        }
        soliderProto.armyType == BattleTank -> {
            atkAddNum += getAtkEffect(AtkCityAtkAdd)
            defAddNum += getAtkEffect(AtkCityDefAdd)
            hpAddNum += getAtkEffect(AtkCityHpAdd)
        }
        isTrap(soliderProto.armyType) -> {
            defAddNum += getAtkEffect(TrapDefAdd)
            hpAddNum += getAtkEffect(TrapHpAdd)
        }
        //科技效果暂无
    }

    if (multiFight) {
        atkAddNum += getAtkEffect(JijieAtkAdd)
    }
    if (isTrap(soliderProto.armyType)) {
        atkAddNum += getAtkEffect(TrapAtkAdd)
    }

    val atkReduceNum = getDefEffect(ShowWarnResearchLv11)

    val att = soliderProto.attack * (1 + atkAddNum / 10000.0) * (1 - atkReduceNum / 10000.0)
    val def = soliderProto.defence * (1 + defAddNum / 10000.0)
    val hp = (soliderProto.hp * (1 + hpAddNum / 10000.0) * soliderNum).toInt()
    //科技效果暂无
    val speed = soliderProto.speed
    val atkCity = soliderProto.attCity
    val baoji = soliderProto.crit
    val baojilv = soliderProto.critMult

    return GetSoliderFightInfoReturn(att, def, hp, speed, atkCity, baoji, baojilv, WuliXing)
}

fun fightData2Builder(areaCache: AreaCache, fightData: FightData): BattleMsg.FightData.Builder {
    val fightDataBuilder = BattleMsg.FightData.newBuilder()
    for (heroData in fightData.heroList) {
        val heroBuilder = BattleMsg.HeroData.newBuilder()
        heroBuilder.protoId = heroData.protoId
        heroBuilder.lv = heroData.lv
        heroBuilder.star = heroData.star
        heroBuilder.awake = heroData.awake
        heroBuilder.asignHp = heroData.assignHp
        heroBuilder.initMorale = heroData.initMorale
        heroData.skillList?.forEach { skillId ->
            heroBuilder.addSkillList(skillId)
        }
        val hero = areaCache.heroCache.findHeroById(fightData.playerId, heroData.id)
        hero?.heroEquipInfoMap?.forEach {
            val equipBuilder = BattleMsg.EquipInfo.newBuilder()
            equipBuilder.equipId = it.key
            equipBuilder.equipRank = it.value.advLv
            heroBuilder.addEquipInfos(equipBuilder)
        }
        fightDataBuilder.addHeroList(heroBuilder)
    }
    return fightDataBuilder
}

fun convertHeroFightData(serverReport: BattleMsg.HeroFightReport): pb4client.HeroFightReport.Builder {
    val clientReportBuilder = pb4client.HeroFightReport.newBuilder()
    clientReportBuilder.fightResult = serverReport.fightResult
    serverReport.entitysList.forEach { serverEntity ->
        val entityBuilder = pb4client.FightEntity.newBuilder()
        entityBuilder.id = serverEntity.id
        serverEntity.intPropertysList.forEach { intInfo ->
            val intBuilder = pb4client.IntProperty.newBuilder()
            intBuilder.propertyType = intInfo.propertyType
            intBuilder.propertyValue = intInfo.propertyValue
            entityBuilder.addIntPropertys(intBuilder)
        }
        serverEntity.floatPropertysList.forEach { floatInfo ->
            val floatBuilder = pb4client.FloatProperty.newBuilder()
            floatBuilder.propertyType = floatInfo.propertyType
            floatBuilder.propertyValue = floatInfo.propertyValue
            entityBuilder.addFloatPropertys(floatBuilder)
        }
        clientReportBuilder.addEntitys(entityBuilder)
    }
    serverReport.operateRecordsList.forEach {
        val operateBuilder = pb4client.OperateRecord.newBuilder()
        operateBuilder.frame = it.frame
        operateBuilder.entityId = it.entityId
        operateBuilder.launchUniqueSkill = it.launchUniqueSkill
        operateBuilder.resetLaunchUniqueSkillFlag = it.resetLaunchUniqueSkillFlag
        clientReportBuilder.addOperateRecords(operateBuilder)
    }
    serverReport.recordsList.forEach { serverRecord ->
        val recordBuilder = pb4client.FightRecord.newBuilder()
        recordBuilder.frame = serverRecord.frame
        recordBuilder.senderId = serverRecord.senderId
        recordBuilder.receiverId = serverRecord.receiverId
        val requestBuilder = pb4client.FightRequest.newBuilder()
        requestBuilder.behaviorType = serverRecord.request.behaviorType
        serverRecord.request.intPropertysList.forEach { intInfo ->
            val intBuilder = pb4client.IntProperty.newBuilder()
            intBuilder.propertyType = intInfo.propertyType
            intBuilder.propertyValue = intInfo.propertyValue
            requestBuilder.addIntPropertys(intBuilder)
        }
        serverRecord.request.floatPropertysList.forEach { floatInfo ->
            val floatBuilder = pb4client.FloatProperty.newBuilder()
            floatBuilder.propertyType = floatInfo.propertyType
            floatBuilder.propertyValue = floatInfo.propertyValue
            requestBuilder.addFloatPropertys(floatBuilder)
        }
        serverRecord.request.arrayPropertysList.forEach { arrayInfo ->
            val arrayBuilder = pb4client.ArrayProperty.newBuilder()
            arrayBuilder.propertyType = arrayInfo.propertyType
            arrayInfo.intPropertysList.forEach { intInfo ->
                val intBuilder = pb4client.IntProperty.newBuilder()
                intBuilder.propertyType = intInfo.propertyType
                intBuilder.propertyValue = intInfo.propertyValue
                arrayBuilder.addIntPropertys(intBuilder)
            }
            arrayInfo.floatPropertysList.forEach { floatInfo ->
                val floatBuilder = pb4client.FloatProperty.newBuilder()
                floatBuilder.propertyType = floatInfo.propertyType
                floatBuilder.propertyValue = floatInfo.propertyValue
                arrayBuilder.addFloatPropertys(floatBuilder)
            }
            requestBuilder.addArrayPropertys(arrayBuilder)
        }
        serverRecord.request.entitysList.forEach { recordEntity ->
            val entityBuilder = pb4client.FightEntity.newBuilder()
            entityBuilder.id = recordEntity.id
            recordEntity.intPropertysList.forEach { intInfo ->
                val intBuilder = pb4client.IntProperty.newBuilder()
                intBuilder.propertyType = intInfo.propertyType
                intBuilder.propertyValue = intInfo.propertyValue
                entityBuilder.addIntPropertys(intBuilder)
            }
            recordEntity.floatPropertysList.forEach { floatInfo ->
                val floatBuilder = pb4client.FloatProperty.newBuilder()
                floatBuilder.propertyType = floatInfo.propertyType
                floatBuilder.propertyValue = floatInfo.propertyValue
                entityBuilder.addFloatPropertys(floatBuilder)
            }
            requestBuilder.addEntitys(entityBuilder)
        }
        recordBuilder.setRequest(requestBuilder)
        clientReportBuilder.addRecords(recordBuilder)
    }
    serverReport.statisticsList.forEach {
        val statisticsBuilder = pb4client.FightStatistics.newBuilder()
        statisticsBuilder.recordKey = it.recordKey
        statisticsBuilder.recordValue = it.recordValue
        clientReportBuilder.addStatistics(statisticsBuilder)
    }
    return clientReportBuilder
}

fun convertHeroFightData(clientReport: pb4client.HeroFightReport): BattleMsg.HeroFightReport.Builder {
    val serverReportBuilder = BattleMsg.HeroFightReport.newBuilder()
    serverReportBuilder.fightResult = clientReport.fightResult
    clientReport.entitysList.forEach { serverEntity ->
        val entityBuilder = BattleMsg.FightEntity.newBuilder()
        entityBuilder.id = serverEntity.id
        serverEntity.intPropertysList.forEach { intInfo ->
            val intBuilder = BattleMsg.IntProperty.newBuilder()
            intBuilder.propertyType = intInfo.propertyType
            intBuilder.propertyValue = intInfo.propertyValue
            entityBuilder.addIntPropertys(intBuilder)
        }
        serverEntity.floatPropertysList.forEach { floatInfo ->
            val floatBuilder = BattleMsg.FloatProperty.newBuilder()
            floatBuilder.propertyType = floatInfo.propertyType
            floatBuilder.propertyValue = floatInfo.propertyValue
            entityBuilder.addFloatPropertys(floatBuilder)
        }
        serverReportBuilder.addEntitys(entityBuilder)
    }
    clientReport.operateRecordsList.forEach {
        val operateBuilder = BattleMsg.OperateRecord.newBuilder()
        operateBuilder.frame = it.frame
        operateBuilder.entityId = it.entityId
        operateBuilder.launchUniqueSkill = it.launchUniqueSkill
        operateBuilder.resetLaunchUniqueSkillFlag = it.resetLaunchUniqueSkillFlag
        serverReportBuilder.addOperateRecords(operateBuilder)
    }
    clientReport.recordsList.forEach { clientRecord ->
        val recordBuilder = BattleMsg.FightRecord.newBuilder()
        recordBuilder.frame = clientRecord.frame
        recordBuilder.senderId = clientRecord.senderId
        recordBuilder.receiverId = clientRecord.receiverId
        val requestBuilder = BattleMsg.FightRequest.newBuilder()
        requestBuilder.behaviorType = clientRecord.request.behaviorType
        clientRecord.request.intPropertysList.forEach { intInfo ->
            val intBuilder = BattleMsg.IntProperty.newBuilder()
            intBuilder.propertyType = intInfo.propertyType
            intBuilder.propertyValue = intInfo.propertyValue
            requestBuilder.addIntPropertys(intBuilder)
        }
        clientRecord.request.floatPropertysList.forEach { floatInfo ->
            val floatBuilder = BattleMsg.FloatProperty.newBuilder()
            floatBuilder.propertyType = floatInfo.propertyType
            floatBuilder.propertyValue = floatInfo.propertyValue
            requestBuilder.addFloatPropertys(floatBuilder)
        }
        clientRecord.request.arrayPropertysList.forEach { arrayInfo ->
            val arrayBuilder = BattleMsg.ArrayProperty.newBuilder()
            arrayBuilder.propertyType = arrayInfo.propertyType
            arrayInfo.intPropertysList.forEach { intInfo ->
                val intBuilder = BattleMsg.IntProperty.newBuilder()
                intBuilder.propertyType = intInfo.propertyType
                intBuilder.propertyValue = intInfo.propertyValue
                arrayBuilder.addIntPropertys(intBuilder)
            }
            arrayInfo.floatPropertysList.forEach { floatInfo ->
                val floatBuilder = BattleMsg.FloatProperty.newBuilder()
                floatBuilder.propertyType = floatInfo.propertyType
                floatBuilder.propertyValue = floatInfo.propertyValue
                arrayBuilder.addFloatPropertys(floatBuilder)
            }
            requestBuilder.addArrayPropertys(arrayBuilder)
        }
        clientRecord.request.entitysList.forEach { recordEntity ->
            val entityBuilder = BattleMsg.FightEntity.newBuilder()
            entityBuilder.id = recordEntity.id
            recordEntity.intPropertysList.forEach { intInfo ->
                val intBuilder = BattleMsg.IntProperty.newBuilder()
                intBuilder.propertyType = intInfo.propertyType
                intBuilder.propertyValue = intInfo.propertyValue
                entityBuilder.addIntPropertys(intBuilder)
            }
            recordEntity.floatPropertysList.forEach { floatInfo ->
                val floatBuilder = BattleMsg.FloatProperty.newBuilder()
                floatBuilder.propertyType = floatInfo.propertyType
                floatBuilder.propertyValue = floatInfo.propertyValue
                entityBuilder.addFloatPropertys(floatBuilder)
            }
            requestBuilder.addEntitys(entityBuilder)
        }
        recordBuilder.setRequest(requestBuilder)
        serverReportBuilder.addRecords(recordBuilder)
    }
    clientReport.statisticsList.forEach {
        val statisticsBuilder = BattleMsg.FightStatistics.newBuilder()
        statisticsBuilder.recordKey = it.recordKey
        statisticsBuilder.recordValue = it.recordValue
        serverReportBuilder.addStatistics(statisticsBuilder)
    }
    return serverReportBuilder
}