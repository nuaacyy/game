package com.point18.slg2d.world.module.heroBattle1.fightLogic.actionNode

import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity

//释放技能行为节点
class LaunchSkillActionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        val launchingSkill = data.launchingSkill ?: return ActionResult.Failure
        launchingSkill.launch()
        return ActionResult.Success
    }
}