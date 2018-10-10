package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.ARR_HPLIMIT
import com.point18.slg2d.common.constg.ARR_HPRECOVREY
import com.point18.slg2d.common.constg.Hp
import com.point18.slg2d.common.constg.OnBuffHeart
import com.point18.slg2d.world.module.heroBattle.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.registerDefaultBuffHandle

//固定持续回血策略
object FixChixuhuixueBuffStrategy : IBuffStrategy<Buff> {
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

        val addHp = buff.attachEntity.getIntProperty(ARR_HPRECOVREY)
        var realAddHp = addHp
        val currentHp = buff.attachEntity.getIntProperty(Hp
            , false)
        val hpLimit = buff.attachEntity.getIntProperty(ARR_HPLIMIT)
        if (currentHp + realAddHp > hpLimit) {
            realAddHp = hpLimit - currentHp
        }

        //加血
        buff.attachEntity.changeIntProperty(Hp, currentHp + realAddHp)
    }
}

