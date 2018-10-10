package com.point18.slg2d.world.module.heroBattle1.behaviorTree.node

import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.common.commonfunc.print

/**
 * 顺序节点，全部成功才返回成功
 */
class SequenceNode<T>(vararg nodes: BNode<T>,val explain: String? = null) : BNode<T>() {
    init {
        nodes.forEach {
            childList.add(it)
            it.parent = this
        }
    }

    override fun execute(data: T): ActionResult {
        for (node in this.childList) {
            val rst = node.execute(data)
            if (rst == ActionResult.Failure) {
                return ActionResult.Failure
            }
        }
        explain.print()
        return ActionResult.Success
    }
}