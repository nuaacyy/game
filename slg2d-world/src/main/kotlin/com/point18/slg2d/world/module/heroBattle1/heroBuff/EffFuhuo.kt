package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.FuhuoBuff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerDefaultBuffHandle

//复活策略
object FuhuoBuffStrategy : IBuffStrategy<FuhuoBuff> {
    override fun onAttach(buff: FuhuoBuff) {
        registerDefaultBuffHandle(buff, OnDead, ::onBuffTakeEffect)
        registerDefaultBuffHandle(buff, OnBuffHeart, ::onBuffHurt)
    }

    override fun onDetach(buff: FuhuoBuff) {

    }

    fun onBuffTakeEffect(buff: FuhuoBuff) {
        if (buff.attachEntity.currentState == DieState && buff.leftReviveCount > 0) {
            //进入复活状态
            buff.attachEntity.changeState(ReviveState)
            buff.reviveTime = buff.attachEntity.manager.currentTime + buff.skillEffProto.haveTime
            buff.leftReviveCount--

            buff.nextTakeEffectTime = buff.reviveTime
        }
    }

    fun onBuffHurt(buff: FuhuoBuff) {
        if (buff.attachEntity.currentState != ReviveState) {
            return
        }

        //进行复活
        val hpLimit = buff.attachEntity.getIntProperty(ARR_HPLIMIT, true)
        buff.attachEntity.changeIntProperty(Hp, (hpLimit * buff.skillEffProto.skillEffBasePoint / 10000).toInt())
        buff.attachEntity.manager.obstruct.toNearestGrid(buff.attachEntity)

        buff.nextTakeEffectTime = Int.MAX_VALUE
    }
}
