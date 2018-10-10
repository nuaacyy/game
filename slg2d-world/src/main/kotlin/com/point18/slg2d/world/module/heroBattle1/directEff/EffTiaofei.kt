package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.SkillEffect

//挑飞策略
class TiaofeiStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                val handlerList = buff.checkHandleMap[OnControl]
                if (handlerList == null) {
                    continue
                }
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
        //打断当前行为
        targetEntity.interrupt(MoveState, AttackState, SkillState)

        //进入飞行状态
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


