package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.OnHurt
import com.point18.slg2d.world.module.heroBattle.fightLogic.*

//背后造成伤害加深策略
object BeihouzaochengshanghaijiashenBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerChangeHurtBuffHandle(buff, OnHurt, ::onBeHurt)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onBeHurt(buff: Buff, atkEntity: Entity, hurt: Int): Int {
        val targetEntity1 = atkEntity.manager.entityMap[atkEntity.atkTargetId] ?: return hurt

        val targetEntity2 = atkEntity.manager.entityMap[targetEntity1.atkTargetId] ?: return hurt

        if (!inBack(
                targetEntity1.gridX,
                targetEntity1.gridY,
                targetEntity2.gridX,
                targetEntity2.gridY,
                atkEntity.gridX,
                atkEntity.gridY
            )
        ) {
            return hurt
        }

        var newHurt = hurt
        if (buff.skillEffProto.isConduce == 1) {
            //伤害减免
            newHurt = (newHurt * (10000 - buff.skillEffProto.skillEffBasePoint) / 10000).toInt()
        } else {
            //伤害加深
            newHurt = (newHurt * (10000 + buff.skillEffProto.skillEffBasePoint) / 10000).toInt()
        }
        return newHurt
    }
}
