package com.point18.slg2d.world.module.heroBattle.fightLogic.actionNode

import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.finder
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ARR_INTERVAL
import com.point18.slg2d.common.constg.AtkAnimation
import com.point18.slg2d.common.constg.AttackState

//释放技能行为节点
class AttackActionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        val targetEntityList = finder.findNormalAtkTarget(data)
        if (targetEntityList.isEmpty()) {
            return ActionResult.Failure
        }
        val atkOrder = data.arrayPropertyMap[AtkAnimation]
        if (atkOrder == null || atkOrder.isEmpty()) {
            normalLog.lzWarn { "实体无行为顺序配置:${data.id}" }
            return ActionResult.Failure
        }
        val animationTime = atkOrder[data.atkIndex]
        //设置攻击目标
        data.atkTargetId = targetEntityList[0].id
        //设置攻击发动时间
        data.atkLaunchTime = data.manager.currentTime + animationTime
        //设置攻击动作
        val interval = Math.ceil(data.getFloatProperty(ARR_INTERVAL) * 1000.0).toInt()
        data.actionEndTime = data.manager.currentTime + interval
        //设置当前状态
        data.changeState(AttackState)
        //切换下次攻击标志
        data.atkIndex = (data.atkIndex + 1) % atkOrder.count()

        return ActionResult.Success
    }
}