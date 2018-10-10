package com.point18.slg2d.world.module.heroBattle1.fightLogic.conditionNode

import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.finder
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ActionOrder
import com.point18.slg2d.common.constg.AtkAnimation
import com.point18.slg2d.common.constg.NormalAttack
import com.point18.slg2d.common.constg.OnStartAttack

class AttackConditionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        //检查buff状态
        for ((_, buffList) in data.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnStartAttack] ?: continue

                for (handle in handlerList) {
                    if (!handle(buff)) {
                        return ActionResult.Failure
                    }
                }
            }
        }

        //目标查找校验
        val targetEntityList = finder.findNormalAtkTarget(data)
        if (targetEntityList.isEmpty()) {
            return ActionResult.Failure
        }

        val actionOrder = data.arrayPropertyMap[ActionOrder]
        if (actionOrder == null || actionOrder.isEmpty()) {
            normalLog.lzWarn { "实体无行为顺序配置:${data.id}" }
            return ActionResult.Failure
        }

        val actionIndex = data.actionIndex % actionOrder.count()
        if (actionOrder[actionIndex] != NormalAttack) {
            return ActionResult.Failure
        }

        val atkOrder = data.arrayPropertyMap[AtkAnimation]
        if (atkOrder == null || atkOrder.isEmpty()) {
            normalLog.lzWarn { "实体无行为顺序配置:${data.id}" }
            return ActionResult.Failure
        }
        return ActionResult.Success
    }
}