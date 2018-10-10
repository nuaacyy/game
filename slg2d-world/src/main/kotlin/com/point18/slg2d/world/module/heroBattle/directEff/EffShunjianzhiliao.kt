package com.point18.slg2d.world.module.heroBattle.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.SkillEffect
import com.point18.slg2d.world.module.heroBattle.fightLogic.calHurtCrit

//瞬间治疗策略
class ShunjianzhiliaoStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        //检测自身是否有禁疗效果
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnHpRecovery]
                if (handlerList == null) {
                    continue
                }
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
        val baseProperty = skillEff.launchEntity.getFloatProperty(skillEff.skillEffProto.skillEffBaseType)

        val addHp =
            baseProperty * skillEff.skillEffProto.skillEffBasePoint / 10000 + skillEff.skillEffProto.skillEffExtraPoint
        val calHurtCritRst = calHurtCrit(targetEntity, addHp)
        val finalAddHp = Math.ceil(calHurtCritRst.hurt).toInt()
        var realAddHp = finalAddHp
        val currentHp = targetEntity.getIntProperty(Hp, false)
        val hpLimit = targetEntity.getIntProperty(ARR_HPLIMIT)
        if (currentHp + realAddHp > hpLimit) {
            realAddHp = hpLimit - currentHp
        }

        //加血
        targetEntity.changeIntProperty(Hp, currentHp + realAddHp)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        intParas[IsCrit] = boolToInt(calHurtCritRst.isCrit)
        intParas[AddHp] = finalAddHp
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            null
        )

        //技能伤害时间点
    }
}


