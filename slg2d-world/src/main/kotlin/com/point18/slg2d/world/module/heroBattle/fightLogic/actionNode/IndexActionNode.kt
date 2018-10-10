package com.point18.slg2d.world.module.heroBattle.fightLogic.actionNode

import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity

class IndexActionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        data.actionIndex++
        return ActionResult.Success
    }
}