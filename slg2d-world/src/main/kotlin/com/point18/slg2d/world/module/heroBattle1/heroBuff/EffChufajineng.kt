package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.ChufaBuff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Skill
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerDefaultBuffHandle
import com.point18.slg2d.common.pc.pcs

//触发技能策略
object ChufajinnengBuffStrategy : IBuffStrategy<ChufaBuff> {
    override fun onAttach(buff: ChufaBuff) {
        when (buff.skillEffProto.triggerMoment) {
            OnDead -> {
                registerDefaultBuffHandle(buff, OnDead, ::triggerSkill)
            }
            OnProtectCoverRemove -> {
                registerDefaultBuffHandle(buff, OnProtectCoverRemove, ::triggerSkill)
            }
            OnHpChange -> {
                buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime
                registerDefaultBuffHandle(buff, OnBuffHeart, ::onHpChange)
                registerDefaultBuffHandle(buff, OnHpChange, ::onHpChange)
            }
        }

    }

    override fun onDetach(buff: ChufaBuff) {
    }

    fun onHpChange(buff: ChufaBuff) {
        if (buff.skillEffProto.triggerConditions.count() != 2) {
            return
        }

        val hpLimit = buff.attachEntity.getIntProperty(ARR_HPLIMIT, true)
        val currentHp = buff.attachEntity.getIntProperty(Hp, false)
        if (currentHp >= hpLimit * buff.skillEffProto.triggerConditions[0] / 10000) {
            return
        }

        if (buff.coolOverTime > buff.attachEntity.manager.currentTime) {
            return
        }

        buff.coolOverTime = buff.attachEntity.manager.currentTime + buff.skillEffProto.triggerConditions[1]
        triggerSkill(buff)
    }

    private fun triggerSkill(buff: ChufaBuff) {
        val skillId = buff.skillEffProto.skillEffBasePoint.toInt()
        val launchSkillProto = pcs.heroSkillProtoCache.heroSkillMap[skillId] ?: return

        val skill = Skill(buff.attachEntity, launchSkillProto)
        buff.attachEntity.launchingSkill = skill
        skill.takeSkillEffect()
    }
}

