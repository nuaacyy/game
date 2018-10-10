package com.point18.slg2d.world.module.heroBattle.fightLogic

//直接效果策略
interface DirectEffectStrategy {
    fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean

    fun doEffect(skillEff: SkillEffect, targetEntity: Entity)
}

var directEffStrategyMap = hashMapOf<Int, DirectEffectStrategy>()

//选择直接效果策略
fun selectDirectEffStrategy(skillType: Int): DirectEffectStrategy? {
    return directEffStrategyMap[skillType]
}
