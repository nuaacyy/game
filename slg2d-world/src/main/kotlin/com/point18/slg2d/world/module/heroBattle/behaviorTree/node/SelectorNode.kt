package com.point18.slg2d.world.module.heroBattle.behaviorTree.node

import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.common.commonfunc.print

/**
 * 选择节点，子节点有一个成功就成功，否则失败
 */
class SelectorNode<T>(vararg nodes: BNode<T>,val explain: String? = null) : BNode<T>() {
    init {
        nodes.forEach {
            childList.add(it)
            it.parent = this
        }
    }

    override fun execute(data: T): ActionResult {
        for (node in this.childList) {
            val rst = node.execute(data)
            if (rst == ActionResult.Success) {
                explain.print()
                return ActionResult.Success
            }
        }
        return ActionResult.Failure
    }
}