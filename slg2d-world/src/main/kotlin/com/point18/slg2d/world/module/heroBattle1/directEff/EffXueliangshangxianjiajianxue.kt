package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.SkillEffect

//按照当前血量上限加减血策略
class XueliangshangxianjiajianxueStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        if (skillEff.skillEffProto.skillEffBasePoint >= 0) {
            return true
        }
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
        val hp = targetEntity.getIntProperty(Hp , false)
        val hpLimit = targetEntity.getIntProperty(ARR_HPLIMIT)
        val changeHp = (hpLimit * skillEff.skillEffProto.skillEffBasePoint / 10000).toInt()
        var leftHp = hp
        if (skillEff.skillEffProto.isConduce == 1) {
            leftHp -= changeHp
        } else {
            leftHp += changeHp
        }

        targetEntity.changeIntProperty(Hp, leftHp)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        if (skillEff.skillEffProto.isConduce == 1) {
            intParas[CostHp] = changeHp
        } else {
            intParas[AddHp] = changeHp
        }
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas
        )
    }
}


