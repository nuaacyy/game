package com.point18.slg2d.world.module.heroBattle1.fightLogic.actionNode

import com.point18.slg2d.common.constg.BulletSpeed
import com.point18.slg2d.common.constg.LogBeginAttack
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.ActionResult
import com.point18.slg2d.world.module.heroBattle1.behaviorTree.base.BNode
import com.point18.slg2d.world.module.heroBattle1.fightLogic.AtkEntityInfo
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.calDistance
import com.point18.slg2d.world.module.heroBattle1.fightLogic.takeNormalAtk
import com.point18.slg2d.common.pc.pcs
import java.util.*

//释放攻击行为节点
class LaunchAttackActionNode : BNode<Entity>() {
    override fun execute(data: Entity): ActionResult {
        val targetId = data.atkTargetId
        val targetEntity = data.manager.entityMap[targetId] ?: return ActionResult.Failure

        //发动攻击的时间已到
        data.atkLaunchTime = Int.MAX_VALUE

        val bulletSpeed = data.intPropertyMap[BulletSpeed] ?: 0
        if (bulletSpeed == 0) {
            takeNormalAtk(data, data.atkIndex, targetEntity)
            return ActionResult.Success
        }
        data.manager.receiveLogRequest(
            data.id,
            targetEntity.id,
            LogBeginAttack,
            null,
            null
        )
        val distance = calDistance(data, targetEntity)
        val flyTime =
            Math.ceil(distance * pcs.basicProtoCache.heroSpeedPara / bulletSpeed * 1000.0).toInt()
        val takeEffTime = data.manager.currentTime + flyTime
        val beAtkList = targetEntity.beAtkMap.getOrPut(takeEffTime) { LinkedList() }
        beAtkList.add(AtkEntityInfo(data, data.atkIndex))
        return ActionResult.Success
    }
}