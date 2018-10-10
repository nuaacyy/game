package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.SkillEffect
import com.point18.slg2d.world.module.heroBattle1.fightLogic.calHurtCrit
import com.point18.slg2d.common.pc.pcs

//瞬间回气策略
class ShunjianhuiqiStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        //基准属性值
        val addMorale = skillEff.skillEffProto.skillEffBasePoint
        val calHurtCritRst = calHurtCrit(targetEntity, addMorale)
        val finalAddMorale = calHurtCritRst.hurt
        var realAddMorale = finalAddMorale
        val currentMorale = targetEntity.getFloatProperty(Morale, false)
        val moraleLimit = pcs.basicProtoCache.heroMoraleLimit
        if (currentMorale + realAddMorale > moraleLimit) {
            realAddMorale = moraleLimit - currentMorale
        }

        //加气
        targetEntity.changeFloatProperty(Morale, currentMorale + realAddMorale)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        intParas[IsCrit] = boolToInt(calHurtCritRst.isCrit)
        val floatParas = hashMapOf<Int, Double>()
        floatParas[AddMorale] = finalAddMorale
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            floatParas
        )
    }
}


