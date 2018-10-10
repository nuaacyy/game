package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.SkillForInt
import com.point18.slg2d.home.dc.Hero
import com.point18.slg2d.common.pc.pcs

// 内政信息发生变化之后需要重新刷新计算的数据
fun getNeedRefreshType(hero: Hero): HashMap<Int, Int> {
    // 刷新内政数据事件,先获取内政武将数据
    val effectIds = hashMapOf<Int, Int>()
    val unitBasic = pcs.unitBaseCache.fetchProtoById(hero.protoId)

    if (unitBasic == null) {
        return effectIds
    }

    val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
    if (heroRankProtoMap == null) {
        return effectIds
    }

    val heroRankProto = heroRankProtoMap[hero.awake]
    if (heroRankProto == null) {
        return effectIds
    }

    for ((_, IntSkillIndex) in heroRankProto.rpgSkillLockMap) {
        var skillId = 0
        if (IntSkillIndex == 1) {
            skillId = unitBasic.intSkill1
        } else if (IntSkillIndex == 2) {
            skillId = unitBasic.intSkill2
        } else if (IntSkillIndex == 3) {
            skillId = unitBasic.intSkill3
        }

        val intSkill = pcs.intSkillCache.intSkillMap[skillId]
        if (intSkill == null) {
            return effectIds
        }

        val intSkillProtoMap = pcs.intSkillCache.intSkillLvMap[intSkill.skillId]
        if (intSkillProtoMap == null) {
            return effectIds
        }

        val intSkillProto = intSkillProtoMap[hero.advLv]
        if (intSkillProto != null) {
            for ((k, v) in intSkillProto.skillEffsMap) {
                effectIds[k] = v
            }
        }
    }

    return effectIds
}

// 获取一个内政武将身上的技能+装备套装所能提供的加成
fun getIntHeroAddByRefreshType(
    hero: Hero,
    effType: Int
): (Int) {
    // 刷新内政数据事件,先获取内政武将数据
    var addNum = 0.0
    val unitBasic = pcs.unitBaseCache.fetchProtoById(hero.protoId)

    if (unitBasic == null) {
        return addNum.toInt()
    }

    val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
    if (heroRankProtoMap == null) {
        return addNum.toInt()
    }

    val heroRankProto = heroRankProtoMap[hero.awake]
    if (heroRankProto == null) {
        return addNum.toInt()
    }

    for ((intSkillIndex, _) in heroRankProto.rpgSkillLockMap) {
        var skillId = 0
        if (intSkillIndex == 1) {
            skillId = unitBasic.intSkill1
        } else if (intSkillIndex == 2) {
            skillId = unitBasic.intSkill2
        } else if (intSkillIndex == 3) {
            skillId = unitBasic.intSkill3
        }

        val intSkill = pcs.intSkillCache.intSkillMap[skillId]
        if (intSkill == null) {
            return addNum.toInt()
        }

        val heroStarProtoCacheMap = pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId]

        if (heroStarProtoCacheMap == null) {
            return addNum.toInt()
        }
        val heroStarProtoCache = heroStarProtoCacheMap[hero.advLv]
        if (heroStarProtoCache == null) {
            return addNum.toInt()
        }

        val intSkillProtoMap = pcs.intSkillCache.intSkillLvMap[intSkill.skillId]

        if (intSkillProtoMap == null) {
            return addNum.toInt()
        }

        val intSkillProto = intSkillProtoMap[heroStarProtoCache.mainStar]
        if (intSkillProto != null) {
            for ((k, v) in intSkillProto.skillEffsMap) {
                if (k != effType) {
                    continue
                }
                addNum += v
            }
        }
    }

    return addNum.toInt()
}

fun getIntHeroByRefreshType(
    hero: Hero
): (HashMap<Int, Int>) {
    // 刷新内政数据事件,先获取内政武将数据
    var addNum = 0.0
    val unitBasic = pcs.unitBaseCache.fetchProtoById(hero.protoId)

    if (unitBasic == null) {
        return hashMapOf()
    }

    val heroRankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
    if (heroRankProtoMap == null) {
        return hashMapOf()
    }

    val heroRankProto = heroRankProtoMap[hero.awake]
    if (heroRankProto == null) {
        return hashMapOf()
    }

    val effectMap = hashMapOf<Int, Int>()

    for ((intSkillIndex, _) in heroRankProto.rpgSkillLockMap) {
        var skillId = 0
        if (intSkillIndex == 1) {
            skillId = unitBasic.intSkill1
        } else if (intSkillIndex == 2) {
            skillId = unitBasic.intSkill2
        } else if (intSkillIndex == 3) {
            skillId = unitBasic.intSkill3
        }

        val intSkill = pcs.intSkillCache.intSkillMap[skillId]
        if (intSkill == null) {
            continue
        }

        if (intSkill.effectUse != SkillForInt) {
            //过滤掉战斗属性
            continue
        }

        val heroStarProtoCacheMap = pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId]

        if (heroStarProtoCacheMap == null) {
            continue
        }
        val heroStarProtoCache = heroStarProtoCacheMap[hero.advLv]
        if (heroStarProtoCache == null) {
            continue
        }

        val intSkillProtoMap = pcs.intSkillCache.intSkillLvMap[intSkill.skillId]

        if (intSkillProtoMap == null) {
            continue
        }

        val intSkillProto = intSkillProtoMap[heroStarProtoCache.mainStar]
        if (intSkillProto != null) {
            for ((k, v) in intSkillProto.skillEffsMap) {
                addNum += v
                effectMap[k] = (effectMap[k] ?: 0) + addNum.toInt()
            }
        }
    }

    return effectMap
}