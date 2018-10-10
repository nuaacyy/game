package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerCheckBuffHandle
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerDefaultBuffHandle

//魅惑策略
object MeihuoBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffTakeEffect)
        registerCheckBuffHandle(buff, OnSelectTarget, ::onBuffCheck)
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

    fun onBuffCheck(buff: Buff): Boolean {
        //检测自身是否有禁控制效果
        for ((_, buffList) in buff.attachEntity.buffMap) {
            for (eachBuff in buffList) {
                val handlerList = eachBuff.checkHandleMap[OnControl] ?: continue

                for (handle in handlerList) {
                    if (!handle(eachBuff)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}

