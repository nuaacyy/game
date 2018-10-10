package com.point18.slg2d.world.module.heroBattle1.behaviorTree.node

import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.common.commonfunc.print

class ConditionNode<T>(
    val check: (t: T) -> Boolean,
    val leftNode: BNode<T>? = null,
    val rightNode: BNode<T>? = null,
    val explain: String? = null
) :
    BNode<T>() {
    override fun execute(data: T): ActionResult {

        if (check(data)) {
            if (leftNode == null) {
                return ActionResult.Failure
            }
            return leftNode.execute(data)
        }
        if (rightNode == null) {
            return ActionResult.Failure
        }
        explain.print()
        return rightNode.execute(data)
    }
}