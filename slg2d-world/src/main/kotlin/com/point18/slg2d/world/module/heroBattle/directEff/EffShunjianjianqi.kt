package com.point18.slg2d.world.module.heroBattle.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.SkillEffect

//瞬间减气策略
class ShunjianjianqiStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnBeforeHurt] ?: continue
                for (handle in handlerList) {
                    if (!handle(buff)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        //基准属性值

        val costMorale = skillEff.skillEffProto.skillEffBasePoint
        var realCostMorale = costMorale
        val currentMorale = targetEntity.getFloatProperty(Morale, false)
        if (currentMorale < realCostMorale) {
            realCostMorale = currentMorale
        }

        //减气
        targetEntity.changeFloatProperty(Morale, currentMorale - realCostMorale)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        val floatParas = hashMapOf<Int, Double>()
        floatParas[CostMorale] = costMorale
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            floatParas
        )
    }
}


