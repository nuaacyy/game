package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.registerCheckBuffHandle
import com.point18.slg2d.world.module.heroBattle.fightLogic.registerDefaultBuffHandle

//定身策略
object DingshenBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        //注册生效时机
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffTakeEffect)
        //注册生效节点
        registerCheckBuffHandle(buff, OnStartMove, ::onBuffCheck)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onBuffTakeEffect(buff: Buff) {
        //打断当前行为
        buff.attachEntity.interrupt(MoveState)

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

