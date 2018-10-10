package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.*

//属性变化策略
object ShuxingbianhuaBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffAttach)

        registerDefaultBuffHandle(buff, OnBuffDetach, ::onBuffDetach)
    }

    override fun onDetach(buff: Buff) {
    }

    private fun onBuffAttach(buff: Buff) {
        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = buff.skillProto.id
        intParas[SkillEffId] = buff.skillEffProto.id
        buff.attachEntity.manager.receiveLogRequest(
            buff.comeFromEntity.id,
            buff.attachEntity.id,
            LogSkillEffect,
            intParas,
            null
        )
        val property = buff.skillEffProto.skillEffBaseType
        if (isHeroIntProperty(property)) {
            onIntLogPropertyChange(buff.attachEntity, property)
        } else {
            onFloatLogPropertyChange(buff.attachEntity, property)
        }

        if (buff.skillProto.type == PassiveSkill) {
            if (property == ARR_HPLIMIT) {
                //血量上限特殊处理
                buff.attachEntity.changeIntProperty(Hp, buff.attachEntity.getIntProperty(ARR_HPLIMIT))
            }
        }
    }

    private fun onBuffDetach(buff: Buff) {
        val property = buff.skillEffProto.skillEffBaseType
        if (isHeroIntProperty(property)) {
            onIntLogPropertyChange(buff.attachEntity, property)
        } else {
            onFloatLogPropertyChange(buff.attachEntity, property)
        }

        if (property == ARR_HPLIMIT) {
            //血量上限特殊处理
            val hp = buff.attachEntity.getIntProperty(Hp)
            val hpLimit = buff.attachEntity.getIntProperty(ARR_HPLIMIT)
            if (hp > hpLimit) {
                buff.attachEntity.changeIntProperty(Hp, hpLimit)
            }
        }
    }
}

