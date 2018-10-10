package com.point18.slg2d.world.module.heroBattle.directEff

import com.point18.slg2d.common.constg.SkillEffId
import com.point18.slg2d.common.constg.SkillId
import com.point18.slg2d.world.module.heroBattle.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.SkillEffect
import java.util.*

//移除有益效果策略
class YichuyouyiStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        var toDetachBuffList: LinkedList<Buff>? = null
        for ((_, buffList) in targetEntity.buffMap) {
            for (buff in buffList) {
                if (buff.loseEffectTime == Int.MAX_VALUE) {
                    //代表了被动类技能，不能移除
                    continue
                }
                if (buff.skillEffProto.isConduce == 1) {
                    //有害效果
                    continue
                }
                if (skillEff.skillEffProto.siftSkillTypeList.isNotEmpty()
                    && !skillEff.skillEffProto.siftSkillTypeList.contains(buff.skillEffProto.skillType)
                ) {
                    //不在过滤范围内
                    continue
                }
                if (toDetachBuffList == null) {
                    toDetachBuffList = LinkedList()
                }
                toDetachBuffList.add(buff)
            }
        }
        if (toDetachBuffList != null) {
            for (detachBuff in toDetachBuffList) {
                detachBuff.detach()
            }
        }

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            com.point18.slg2d.common.constg.LogSkillEffect,
            intParas,
            null
        )
    }
}

