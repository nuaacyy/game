package com.point18.slg2d.world.module.heroBattle.fightLogic.actionNode

import com.point18.slg2d.common.constg.Morale
import com.point18.slg2d.common.constg.SkillState
import com.point18.slg2d.common.constg.UniqueSkillList
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.Skill
import com.point18.slg2d.common.pc.pcs

//释放技能行为
class UniqueSkillActionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        val uniqueSkills = requireNotNull(data.arrayPropertyMap[UniqueSkillList])
        for (uniqueSkill in uniqueSkills) {
            val skillProto = pcs.heroSkillProtoCache.heroSkillMap[uniqueSkill]
            if (skillProto == null) {
                continue
            }
            val currentMorale = data.getFloatProperty(Morale, false)
            if (skillProto.costMorale > currentMorale) {
                continue
            }

            //设置当前状态
            data.changeState(SkillState)
            val activeSkill = Skill(data, skillProto)
            activeSkill.sing()
            data.changeFloatProperty(Morale, currentMorale - skillProto.costMorale)
            if (data.resetLaunchUniqueSkillFlag) {
                data.launchUniqueSkill = false
            }
            return ActionResult.Success
        }
        return ActionResult.Failure
    }
}