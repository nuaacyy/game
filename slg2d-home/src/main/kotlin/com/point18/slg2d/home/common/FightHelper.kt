package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.pc.pcs

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
    equipInfos: HashMap<Int, HeroEquipVo>?,
    additionPropertyMap: HashMap<HashSet<Int>, HashMap<Int, Int>>? = null
): (GetHeroBasicInfoReturn) {
    val unitBaseProto = pcs.unitBaseCache.fetchProtoById(protoId)
    if (unitBaseProto == null) {
        normalLog.error("找不到英雄单位$protoId")
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
                ARR_PUGONG_GROWTH -> atkG = grow
                ARR_FANGYU_GROWTH -> defG = grow
                ARR_MOULUE_GROWTH -> magicG = grow
                ARR_MOUFANG_GROWTH -> magicDefG = grow
                ARR_HP_GROWTH -> hpG = grow
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
    if (equipInfos != null) {
        for ((equipId, vo) in equipInfos) {
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
        ?: 0.0) + magicDefG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_FAFANG, 0)) / 10000
    val hpW =
        (unitBaseProto.hp + (starAddEffect[ARR_HPLIMIT]
            ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_HPLIMIT)
            ?: 0.0) + (equipAddEffect[ARR_HPLIMIT]
            ?: 0.0) + hpG * (lv - 1)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_HPLIMIT, 0)) / 10000
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
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_BAOJILV) ?: 0.0) + (equipAddEffect[ARR_BAOJILV]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_BAOJILV, 0)) / 10000
    val hpRecoveryW = (unitBaseProto.hpRecover + (starAddEffect[ARR_HPRECOVREY]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_HPRECOVREY)
        ?: 0.0) + (equipAddEffect[ARR_HPRECOVREY]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_HPRECOVREY, 0)) / 10000
    val moraleRecoveryW = (unitBaseProto.moraleRecover + (starAddEffect[ARR_MORALERECOVERY]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_MORALERECOVERY)
        ?: 0.0) + (equipAddEffect[ARR_MORALERECOVERY] ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(
        ARR_MORALERECOVERY,
        0
    )) / 10000
    val atkAddHpW = (unitBaseProto.attackAddHp + (starAddEffect[ARR_ATKADDHP]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_ATKADDHP) ?: 0.0) + (equipAddEffect[ARR_ATKADDHP]
        ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(ARR_ATKADDHP, 0)) / 10000
    val atkAddMoraleW = (unitBaseProto.attackAddMorale + (starAddEffect[ARR_ATKADDMORALE]
        ?: 0.0) + (heroRankProto?.rankEffectMap?.get(ARR_ATKADDMORALE)
        ?: 0.0) + (equipAddEffect[ARR_ATKADDMORALE] ?: 0.0)) * (10000 + starAttrAddEffectPercent.getOrDefault(
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