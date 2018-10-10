package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs

//瞬间伤害策略
class ShunjianshanghaiStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnBeforeHurt]
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
        //技能伤害计算时则根据技能的攻击类型来选择使用英雄的物攻还是法攻
        //技能伤害（物/法） = 普攻伤害（物/法）×技能伤害率 + 技能额外伤害
        //最终伤害 = 普攻伤害（技能伤害）*暴击伤害
        val launchingSkill = skillEff.launchEntity.launchingSkill
        if (launchingSkill == null) {
            normalLog.lzWarn { "计算瞬间伤害时，实体无释放中的技能" }
            return
        }

        var isDoge = false
        var isCrit = false
        var costHp = 0
        //不是大招时，判断闪避
        if (skillEff.skillProto.type != UniqueSkill
            && getRandInt(10000) < targetEntity.getFloatProperty(ARR_DODGE) / pcs.basicProtoCache.dodgePara * 10000
        ) {
            isDoge = true
        } else {
            val hurt =
                calNormalHurt(skillEff.launchEntity, targetEntity) * calBuffAddition(
                    skillEff.launchEntity,
                    targetEntity
                )
            var skillHurt =
                hurt * skillEff.skillEffProto.skillEffBasePoint / 10000 + skillEff.skillEffProto.skillEffExtraPoint
            if (skillEff.skillEffProto.harmChangeRate != 0) {
                var calHurt = skillHurt
                val changedHurt = skillHurt * (10000 + skillEff.skillEffProto.changeLimit) / 10000
                for (i in 1 until launchingSkill.launchedCount) {
                    calHurt *= (10000 + skillEff.skillEffProto.harmChangeRate) / 10000
                    if ((calHurt < skillHurt && calHurt < changedHurt) ||
                        (calHurt > skillHurt && calHurt > changedHurt)
                    ) {
                        calHurt = changedHurt
                        break
                    }
                }
                skillHurt = calHurt
            }
            val calHurtCritRst = calHurtCrit(skillEff.launchEntity, skillHurt)
            isCrit = calHurtCritRst.isCrit
            costHp = Math.ceil(calHurtCritRst.hurt).toInt()
            var realCostHp = costHp
            val currentHp = targetEntity.getIntProperty(Hp, false)
            if (currentHp < realCostHp) {
                realCostHp = currentHp
            }
            //扣血
            targetEntity.changeIntProperty(Hp, currentHp - realCostHp)
        }

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        intParas[IsDodge] = boolToInt(isDoge)
        intParas[IsCrit] = boolToInt(isCrit)
        intParas[CostHp] = costHp
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            null
        )

        if (costHp == 0) {
            return
        }
        //受到伤害后的处理（伤害反弹）
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.afterHurtHandleMap[OnAfterHurt]
                if (handlerList == null) {
                    continue
                }
                for (handle in handlerList) {
                    handle(skillEff.launchEntity, buff, costHp)
                }
            }
        }
    }
}


