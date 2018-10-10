package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*

//钩人策略
class GourenStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        val distance = calDistance(skillEff.launchEntity, targetEntity)
        if (distance <= 1) {
            return
        }
        val moveDistance = distance - 1
        val newPos = calMovedPos1(skillEff.launchEntity, targetEntity, moveDistance)
        val newPosX = newPos.posX.toInt()
        val newPosY = newPos.posY.toInt()

        //打断当前行为
        skillEff.launchEntity.interrupt(MoveState, AttackState, SkillState)

        //进入飞行状态
        targetEntity.flyTargetX = newPosX.toDouble()
        targetEntity.flyTargetY = newPosY.toDouble()
        var flyTime = 1000
        if (skillEff.skillEffProto.auraHandler != 0) {
            flyTime = skillEff.skillEffProto.auraHandler
        }

        targetEntity.actionEndTime = targetEntity.manager.currentTime + flyTime
        targetEntity.flySpeed = skillEff.skillEffProto.dartSpeed.toDouble()
        targetEntity.changeState(FlyState)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        val floatParas = hashMapOf<Int, Double>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        floatParas[ToPosX] = newPosX.toDouble()
        floatParas[ToPosY] = newPosY.toDouble()
        floatParas[MoveSpeed] = targetEntity.flySpeed
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            floatParas
        )
    }
}

