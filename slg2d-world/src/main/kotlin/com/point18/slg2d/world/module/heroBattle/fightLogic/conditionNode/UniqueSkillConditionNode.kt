package com.point18.slg2d.world.module.heroBattle.fightLogic.conditionNode

import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.checkCamp
import com.point18.slg2d.world.module.heroBattle.fightLogic.finder
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Morale
import com.point18.slg2d.common.constg.UniqueSkillList
import com.point18.slg2d.common.pc.pcs

//大招条件
class UniqueSkillConditionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        val uniqueSkills = data.arrayPropertyMap[UniqueSkillList]
        if (uniqueSkills == null) {
            return ActionResult.Failure
        }
        if (!data.launchUniqueSkill){
            return ActionResult.Failure
        }

        for (uniqueSkill in uniqueSkills) {
            val skillProto = pcs.heroSkillProtoCache.heroSkillMap[uniqueSkill]
            if (skillProto == null) {
                normalLog.lzWarn { "英雄技能配置不存在：$uniqueSkill" }
                continue
            }
            val currentMorale = data.getFloatProperty(Morale, false)
            if (skillProto.costMorale > currentMorale) {
                continue
            }

            if (skillProto.target != 0) {
                //目标查找校验
                val camp = checkCamp(data, skillProto.target)
                if (finder.findByRange(data, camp, skillProto.range.toDouble()) == null) {
                    //范围内无目标
                    continue
                }
            }
            return ActionResult.Success
        }

        return ActionResult.Failure
    }
}