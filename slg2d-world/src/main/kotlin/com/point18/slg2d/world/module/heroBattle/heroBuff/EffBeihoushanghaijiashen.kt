package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.OnBeHurt1
import com.point18.slg2d.world.module.heroBattle.fightLogic.*

//背后伤害加深策略
object BeihoushanghaijiashenBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerChangeHurtBuffHandle(buff, OnBeHurt1, ::onBeHurt)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onBeHurt(buff: Buff, atkEntity: Entity, hurt: Int): Int {
        val targetEntity = buff.attachEntity.manager.entityMap[buff.attachEntity.atkTargetId] ?: return hurt

        if (!inBack(
                buff.attachEntity.gridX,
                buff.attachEntity.gridY,
                targetEntity.gridX,
                targetEntity.gridY,
                atkEntity.gridX,
                atkEntity.gridY
            )
        ) {
            return hurt
        }

        var newHurt = hurt
        if (buff.skillEffProto.isConduce == 1) {
            //伤害加深
            newHurt = (newHurt * (10000 + buff.skillEffProto.skillEffBasePoint) / 10000).toInt()
        } else {
            //伤害减免
            newHurt = (newHurt * (10000 - buff.skillEffProto.skillEffBasePoint) / 10000).toInt()
        }
        return newHurt
    }
}
