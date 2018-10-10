package com.point18.slg2d.world.module.heroBattle1.fightLogic.conditionNode

import com.point18.slg2d.common.constg.OnStartLaunchSkill
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity

//技能吟唱条件节点
class SkillSingConditionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        //检查buff状态
        for ((_, buffList) in data.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnStartLaunchSkill] ?: continue

                for (handle in handlerList) {
                    if (!handle(buff)) {
                        return ActionResult.Failure
                    }
                }
            }
        }
        return ActionResult.Success
    }
}