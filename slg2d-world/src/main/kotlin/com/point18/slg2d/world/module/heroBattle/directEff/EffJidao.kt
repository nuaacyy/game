package com.point18.slg2d.world.module.heroBattle.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.SkillEffect

//击倒策略
class JidaoStrategy : DirectEffectStrategy {
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
        val posX = targetEntity.getFloatProperty(PosX, false)
        val posY = targetEntity.getFloatProperty(PosY, false)

        //打断当前行为
        targetEntity.interrupt(MoveState, AttackState, SkillState)

        //进入飞行状态
        targetEntity.flyTargetX = posX
        targetEntity.flyTargetY = posY
        var flyTime = 1000
        if (skillEff.skillEffProto.auraHandler != 0) {
            flyTime = skillEff.skillEffProto.auraHandler
        }
        targetEntity.actionEndTime = targetEntity.manager.currentTime + flyTime
        targetEntity.changeState(FlyState)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            null
        )

    }
}
