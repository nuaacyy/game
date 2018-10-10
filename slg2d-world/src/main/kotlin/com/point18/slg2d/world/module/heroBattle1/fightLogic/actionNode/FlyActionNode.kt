package com.point18.slg2d.world.module.heroBattle1.fightLogic.actionNode

import com.point18.slg2d.common.constg.PosX
import com.point18.slg2d.common.constg.PosY
import com.point18.slg2d.common.constg.UpdateTime
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.calSqrt
import com.point18.slg2d.common.pc.pcs

//飞行行为节点
class FlyActionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        //更新当前坐标
        val posX = data.getFloatProperty(PosX, false)
        val posY = data.getFloatProperty(PosY, false)
        val targetX = data.flyTargetX
        val targetY = data.flyTargetY
        if (posX == targetX && posY == targetY) {
            return ActionResult.Success
        }
        val distance = calSqrt(targetX - posX, targetY - posY)
        val speed = data.flySpeed
        val moveDistance = speed * UpdateTime / 1000 / pcs.basicProtoCache.heroSpeedPara
        var moveRate = moveDistance / distance
        if (moveRate > 1) {
            moveRate = 1.0
        }
        data.setPos(posX + moveRate * (targetX - posX), posY + moveRate * (targetY - posY))
        return ActionResult.Success
    }
}