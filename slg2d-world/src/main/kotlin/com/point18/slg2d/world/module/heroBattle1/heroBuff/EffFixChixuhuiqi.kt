package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.ARR_MORALERECOVERY
import com.point18.slg2d.common.constg.Hp
import com.point18.slg2d.common.constg.Morale
import com.point18.slg2d.common.constg.OnBuffHeart
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerDefaultBuffHandle
import com.point18.slg2d.common.pc.pcs

//固定持续回气策略
object FixChixuhuiqiBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime

        registerDefaultBuffHandle(buff, OnBuffHeart, ::onBuffTakeEffect)
    }

    override fun onDetach(buff: Buff) {

    }

    fun onBuffTakeEffect(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime + 1000

        if (buff.attachEntity.getIntProperty(Hp, false) <= 0) {
            return
        }

        val addMorale = buff.attachEntity.getFloatProperty(ARR_MORALERECOVERY)
        var realAddMorale = addMorale
        val currentMorale = buff.attachEntity.getFloatProperty(Morale)
        val moraleLimit = pcs.basicProtoCache.heroMoraleLimit
        if (currentMorale + realAddMorale > moraleLimit) {
            realAddMorale = moraleLimit - currentMorale
        }

        //加气
        buff.attachEntity.changeFloatProperty(Morale, currentMorale + realAddMorale)
    }
}
