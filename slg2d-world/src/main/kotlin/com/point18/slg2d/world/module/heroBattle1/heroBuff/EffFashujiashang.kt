package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.LogSkillEffect
import com.point18.slg2d.common.constg.OnBuffAttach
import com.point18.slg2d.common.constg.SkillEffId
import com.point18.slg2d.common.constg.SkillId
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerDefaultBuffHandle

//法术加伤策略
object FashujiashangBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffTakeEffect)
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
}


