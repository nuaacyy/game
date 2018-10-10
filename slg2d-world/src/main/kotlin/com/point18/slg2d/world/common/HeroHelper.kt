package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Hero
import com.point18.slg2d.world.area4data.HeroProperty

// 刷新武将属性
fun generateHeroRefPropsByDbData(areaCache: AreaCache, hero: Hero): (HeroProperty) {
    // 从模板中找到该武将的模板 用来计算增加的属性
    // 计算战斗数值
    val (atkW, defW, _, _, hpW, speedW, baojiW, baojilvW, _, _, _, _, _, _) =
        getHeroBasicInfo(hero.protoId, hero.level, hero.advLv, hero.awake, hero.heroEquipInfoMap)

    return HeroProperty(
        hero.id,
        hero.protoId,
        hero.level,
        hero.advLv,
        hero.exp,
        atkW,
        hpW,
        defW,
        speedW,
        baojiW,
        baojilvW,
        hero.skillId1,
        hero.skillId2,
        hero.skillId3,
        hero.skillId4,
        hero.awake,
        hero.intSkillId1,
        hero.intSkillId2,
        hero.intSkillId3,
        hero.intHeroAddress,
        hero.mainHeroState,
        hero.posState,
        hero.heroEquipInfoMap,
        hero.starUpEndTime,
        hero.superUpEndTime,
        hero.mainHeroStateStartTime,
        hero.mainHeroStateEndTime,
        hero.heroStrength,
        hero.onFloorIdx
    )
}