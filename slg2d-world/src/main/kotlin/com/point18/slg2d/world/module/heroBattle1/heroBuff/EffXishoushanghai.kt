package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.OnBeHurt2
import com.point18.slg2d.common.constg.OnProtectCoverRemove
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.HudunBuff
import com.point18.slg2d.world.module.heroBattle1.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.registerChangeHurtBuffHandle

//吸收伤害策略
object XishoushanghaiBuffStrategy : IBuffStrategy<HudunBuff> {
    override fun onAttach(buff: HudunBuff) {
        registerChangeHurtBuffHandle(buff, OnBeHurt2, ::onBeHurt)
    }

    override fun onDetach(buff: HudunBuff) {

    }

    fun onBeHurt(buff: HudunBuff, atkEntity: Entity, hurt: Int): Int {
        if (buff.leftTakeInHurt > hurt) {
            buff.leftTakeInHurt -= hurt
            return 0
        }
        val leftTakeInHurt = buff.leftTakeInHurt
        buff.leftTakeInHurt = 0
        buff.loseEffectTime = buff.attachEntity.manager.currentTime

        for ((_, buffList) in buff.attachEntity.buffMap) {
            for (bf in buffList) {
                val handlerList = bf.defaultHandleMap[OnProtectCoverRemove] ?: continue
                for (handle in handlerList) {
                    handle(bf)
                }
            }
        }
        return hurt - leftTakeInHurt
    }
}
