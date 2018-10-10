package com.point18.slg2d.world.module.heroBattle.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.SkillEffect
import com.point18.slg2d.world.module.heroBattle.fightLogic.calMovedPos2

//击退策略
class JituiStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnControl] ?: continue
                for (handle in handlerList) {
                    if (!handle(buff)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        //直接改坐标，可能需要附加一小段时间的无敌和眩晕Buff
        val newPos = calMovedPos2(skillEff.launchEntity, targetEntity, skillEff.skillEffProto.skillEffBasePoint)
        val newPosX = newPos.posX.toInt()
        val newPosY = newPos.posY.toInt()

        //打断当前行为
        targetEntity.interrupt(MoveState, AttackState, SkillState)

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

