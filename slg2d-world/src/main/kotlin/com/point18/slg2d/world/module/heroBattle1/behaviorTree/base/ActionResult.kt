package com.point18.slg2d.world.module.heroBattle1.behaviorTree.base

enum class ActionResult(val code: Int) {
    None(0),
    Success(1),
    Running(2),
    Failure(3)
}