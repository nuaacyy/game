package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.calHurtCrit
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerDefaultBuffHandle

//持续治疗策略
object ChixuzhiliaoBuffStrategy : IBuffStrategy<Buff> {
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

        for ((_, buffList) in buff.attachEntity.buffMap) {
            for (eachBuff in buffList) {
                val handlerList = eachBuff.checkHandleMap[OnHpRecovery] ?: continue

                for (handle in handlerList) {
                    if (!handle(eachBuff)) {
                        return
                    }
                }
            }
        }

        //基准属性值
        val baseProperty = buff.comeFromEntityClone.getFloatProperty(buff.skillEffProto.skillEffBaseType)

        val addHp = baseProperty * buff.skillEffProto.skillEffBasePoint / 10000 + buff.skillEffProto.skillEffExtraPoint
        val calHurtCritRst = calHurtCrit(buff.attachEntity, addHp)
        val finalAddHp = Math.ceil(calHurtCritRst.hurt).toInt()
        var realAddHp = finalAddHp
        val currentHp = buff.attachEntity.getIntProperty(Hp, false)
        val hpLimit = buff.attachEntity.getIntProperty(ARR_HPLIMIT)
        if (currentHp + realAddHp > hpLimit) {
            realAddHp = hpLimit - currentHp
        }

        //加血
        buff.attachEntity.changeIntProperty(Hp, currentHp + realAddHp)

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = buff.skillProto.id
        intParas[SkillEffId] = buff.skillEffProto.id
        intParas[IsCrit] = boolToInt(calHurtCritRst.isCrit)
        intParas[AddHp] = finalAddHp
        buff.attachEntity.manager.receiveLogRequest(
            buff.comeFromEntity.id,
            buff.attachEntity.id,
            LogSkillEffect,
            intParas,
            null
        )

    }
}
