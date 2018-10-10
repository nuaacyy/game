package com.point18.slg2d.world.module.heroBattle.fightLogic.conditionNode

import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.checkCamp
import com.point18.slg2d.world.module.heroBattle.fightLogic.finder
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs

class CommonSkillConditionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        //根据攻击顺序进入下一个攻击状态，根据技能查找目标
        val atkOrder = data.arrayPropertyMap[ActionOrder]
        if (atkOrder == null) {
            normalLog.lzWarn { "实体无攻击顺序配置:${data.id}" }
            return ActionResult.Failure
        }
        val skillId: Int
        val atkAction = data.actionIndex % atkOrder.count()
        when (atkOrder[atkAction]) {
            SkillA -> {
                val skillMap = data.arrayPropertyMap[ActiveSkillList]
                if (skillMap == null || skillMap.count() < 1) {
                    normalLog.error("攻击顺序到技能A，但不存在技能A")
                    return ActionResult.Failure
                }
                skillId = skillMap[0]
            }
            SkillB -> {
                val skillMap = data.arrayPropertyMap[ActiveSkillList]
                if (skillMap == null || skillMap.count() < 2) {
                    normalLog.error("攻击顺序到技能B，但不存在技能B")
                    return ActionResult.Failure
                }
                skillId = skillMap[1]
            }
            SkillC -> {
                val skillMap = data.arrayPropertyMap[ActiveSkillList]
                if (skillMap == null || skillMap.count() < 3) {
                    normalLog.error("攻击顺序到技能C，但不存在技能C")
                    return ActionResult.Failure
                }
                skillId = skillMap[2]
            }
            else ->
                return ActionResult.Failure
        }
        val skillProto = pcs.heroSkillProtoCache.heroSkillMap[skillId]
        if (skillProto == null) {
            normalLog.error("英雄技能配置不存在:$skillId")
            return ActionResult.Failure
        }

        //目标查找校验
        val camp = checkCamp(data, skillProto.target)
        if (finder.findByRange(data, camp, skillProto.range.toDouble()) == null) {
            //范围内无目标
            return ActionResult.Failure
        }

        return ActionResult.Success
    }
}