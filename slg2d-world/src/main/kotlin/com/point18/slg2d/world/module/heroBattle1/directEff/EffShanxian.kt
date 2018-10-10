package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*

//闪现策略
class ShanxianStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        val launchEntity = skillEff.launchEntity

        val findRst =
            finder.findFullScreenAoe(launchEntity, EnemyForce, 1, Farest, 0.0, 0, 0.0, false, 1)
        if (findRst.targetEntityList.isEmpty()) {
            return
        }
        val atkTarget = findRst.targetEntityList[0]

        val distance = calDistance(launchEntity, atkTarget)
        if (distance == 0.0) {
            return
        }
        val newPos = calMovedPos1(launchEntity, atkTarget, distance + 1)
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

