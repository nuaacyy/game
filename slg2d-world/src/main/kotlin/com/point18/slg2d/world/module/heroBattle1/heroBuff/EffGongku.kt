package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*
import java.util.*

//共苦策略
object GongkuBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffTakeEffect)
        registerChangeHurtBuffHandle(buff, OnBeHurt3, ::onBeHurt)
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

    fun onBeHurt(buff: Buff, atkEntity: Entity, hurt: Int): Int {
        if (hurt <= 0) {
            return 0
        }
        val targetEntitys = LinkedList<Entity>()
        for (entity in buff.attachEntity.manager.entityList) {
            if (entity.id == buff.attachEntity.id) {
                continue
            }
            if ((entity.getIntProperty(Hp, false)) <= 0) {
                continue
            }
            //判断共苦buff
            val buffList = entity.buffMap[SKILL_GONGKU]
            if (buffList == null || buffList.count() == 0) {
                continue
            }
            for (gongkuBuff in buffList) {
                if (gongkuBuff.relatedId != buff.relatedId) {
                    //非同一个共苦
                    continue
                }
                targetEntitys.add(entity)
                break
            }
        }
        if (targetEntitys.count() == 0) {
            return hurt
        }
        val avgHurt = (hurt * buff.skillEffProto.skillEffBasePoint / 10000 / targetEntitys.count()).toInt()
        for (entity in targetEntitys) {
            var realCostHp = avgHurt
            val currentHp = entity.getIntProperty(Hp, false)
            if (currentHp < realCostHp) {
                realCostHp = currentHp
            }
            entity.changeIntProperty(Hp, realCostHp)

            //添加buff生效日志
            val intParas = hashMapOf<Int, Int>()
            intParas[SkillId] = buff.skillProto.id
            intParas[SkillEffId] = buff.skillEffProto.id
            intParas[CostHp] = avgHurt
            buff.attachEntity.manager.receiveLogRequest(
                buff.comeFromEntity.id,
                entity.id,
                LogSkillEffect,
                intParas,
                null
            )
        }
        return hurt - avgHurt * targetEntitys.count()
    }

}


