package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.OnCheckDistance
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerCheckDistanceBuffHandle

//滞空策略
object ZhikongBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        //设定下次生效时机
        registerCheckDistanceBuffHandle(buff, OnCheckDistance, ::onCheckDistance)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onCheckDistance(buff: Buff, range: Double): Boolean {
        if (buff.skillEffProto.skillEffBasePoint >= range) {
            return true
        }
        return false
    }
}
