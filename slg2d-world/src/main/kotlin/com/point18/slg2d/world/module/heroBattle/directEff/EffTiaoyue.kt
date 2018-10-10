package com.point18.slg2d.world.module.heroBattle.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.*

//跳跃策略
class TiaoyueStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        val launchEntity = skillEff.launchEntity

        val atkTargets = finder.findNormalAtkTarget(launchEntity)
        if (atkTargets.isEmpty()) {
            return
        }
        val newPos = calMovedPos1(skillEff.launchEntity, atkTargets[0], skillEff.skillEffProto.skillEffBasePoint)
        val newPosX = newPos.posX.toInt()
        val newPosY = newPos.posY.toInt()

        //打断当前行为
        launchEntity.interrupt(MoveState, AttackState, SkillState)

        //进入飞行状态
        launchEntity.flyTargetX = newPosX.toDouble()
        launchEntity.flyTargetY = newPosY.toDouble()
        var flyTime = 1000
        if (skillEff.skillEffProto.auraHandler != 0) {
            flyTime = skillEff.skillEffProto.auraHandler
        }

        launchEntity.actionEndTime = launchEntity.manager.currentTime + flyTime
        launchEntity.flySpeed = skillEff.skillEffProto.dartSpeed.toDouble()
        launchEntity.changeState(FlyState)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        val floatParas = hashMapOf<Int, Double>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        floatParas[ToPosX] = newPosX.toDouble()
        floatParas[ToPosY] = newPosY.toDouble()
        floatParas[MoveSpeed] = launchEntity.flySpeed
        launchEntity.manager.receiveLogRequest(
            launchEntity.id,
            launchEntity.id,
            LogSkillEffect,
            intParas,
            floatParas
        )
    }
}

