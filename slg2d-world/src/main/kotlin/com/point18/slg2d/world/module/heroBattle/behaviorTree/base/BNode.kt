package com.point18.slg2d.world.module.heroBattle.behaviorTree.base

import java.util.*

open class BNode<T> {
    var parent: BNode<T>? = null
    protected val childList = LinkedList<BNode<T>>()

    open fun execute(data: T): ActionResult {
        return ActionResult.Success
    }
}