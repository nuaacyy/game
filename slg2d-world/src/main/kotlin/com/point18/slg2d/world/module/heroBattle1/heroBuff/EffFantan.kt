package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*

//反弹策略
object FantanBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffTakeEffect)
        registerFantanBuffHandle(buff, OnAfterHurt, ::onAfterHurt)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onBuffTakeEffect(buff: Buff) {
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

    }

    fun onAfterHurt(entity: Entity, buff: Buff, hurt: Int) {
        if (buff.attachEntity.getIntProperty(Hp, false) <= 0) {
            return
        }

        val currentHp = entity.getIntProperty(Hp, false)
        val costHp = (hurt * buff.skillEffProto.skillEffBasePoint / 10000).toInt()
        var realCostHp = costHp
        if (realCostHp > currentHp) {
            realCostHp = currentHp
        }

        //扣血
        entity.changeIntProperty(Hp, currentHp - realCostHp)

        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = buff.skillProto.id
        intParas[SkillEffId] = buff.skillEffProto.id
        intParas[CostHp] = costHp
        entity.manager.receiveLogRequest(entity.id, entity.id, LogSkillEffect, intParas, null)
    }

}

