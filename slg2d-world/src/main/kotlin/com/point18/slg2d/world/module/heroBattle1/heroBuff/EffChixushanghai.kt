package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*

//持续伤害策略
object ChixushanghaiBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime

        registerDefaultBuffHandle(buff, OnBuffHeart, ::onBuffTakeEffect)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onBuffTakeEffect(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime + buff.skillEffProto.haveTime

        if (buff.attachEntity.getIntProperty(Hp, false) <= 0) {
            return
        }

        //检测自身是否有无敌效果
        for ((_, buffList) in buff.attachEntity.buffMap) {
            for (bf in buffList) {
                val handlerList = bf.checkHandleMap[OnBeforeHurt] ?: continue
                for (handle in handlerList) {
                    if (!handle(bf)) {
                        return
                    }
                }
            }
        }

        //计算伤害
        val hurt =
            calNormalHurt(buff.comeFromEntityClone, buff.attachEntity) * calBuffAddition(
                buff.comeFromEntityClone,
                buff.attachEntity
            )
        val skillHurt = hurt * buff.skillEffProto.skillEffBasePoint / 10000 + buff.skillEffProto.skillEffExtraPoint
        val calHurtCritRst = calHurtCrit(buff.comeFromEntityClone, skillHurt)
        var costHp = Math.ceil(calHurtCritRst.hurt).toInt()

        for ((_, buffList) in buff.attachEntity.buffMap) {
            for (bf in buffList) {
                val handlerList = bf.beHurtHandleMap[OnDotHurt] ?: continue
                for (handle in handlerList) {
                    costHp = handle(bf, buff.comeFromEntity, costHp)
                }
            }
        }

        var realCostHp = costHp
        val currentHp = buff.attachEntity.getIntProperty(Hp, false)
        if (currentHp < realCostHp) {
            realCostHp = currentHp
        }
        //扣血
        buff.attachEntity.changeIntProperty(Hp, currentHp - realCostHp)

        //添加buff生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = buff.skillProto.id
        intParas[SkillEffId] = buff.skillEffProto.id
        intParas[IsCrit] = boolToInt(calHurtCritRst.isCrit)
        intParas[CostHp] = costHp
        buff.attachEntity.manager.receiveLogRequest(
            buff.comeFromEntity.id,
            buff.attachEntity.id,
            LogSkillEffect,
            intParas
        )

    }

}

