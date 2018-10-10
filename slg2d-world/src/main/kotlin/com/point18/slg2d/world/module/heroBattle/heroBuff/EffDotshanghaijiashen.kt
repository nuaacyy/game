package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.OnDotHurt
import com.point18.slg2d.world.module.heroBattle.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.registerChangeHurtBuffHandle

//dot伤害加深策略
object DotshanghaijiashenBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerChangeHurtBuffHandle(buff, OnDotHurt, ::onDotHurt)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onDotHurt(buff: Buff, atkEntity: Entity, hurt: Int): Int {
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
