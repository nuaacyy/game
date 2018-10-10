package com.point18.slg2d.world.module.heroBattle.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle.fightLogic.Buff
import com.point18.slg2d.world.module.heroBattle.fightLogic.IBuffStrategy
import com.point18.slg2d.world.module.heroBattle.fightLogic.Skill
import com.point18.slg2d.world.module.heroBattle.fightLogic.registerDefaultBuffHandle
import com.point18.slg2d.common.pc.pcs
import java.util.*
import java.util.Arrays.asList

//释放技能策略
object ShifangjinnengBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime
        //设定对象的存活时间
        buff.attachEntity.intPropertyMap[AliveTime] =
            buff.attachEntity.manager.currentTime + buff.skillEffProto.auraHandler

        registerDefaultBuffHandle(buff, OnBuffHeart, ::onBuffTakeEffect)

        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = buff.skillProto.id
        intParas[SkillEffId] = buff.skillEffProto.id
        buff.attachEntity.manager.receiveLogRequest(
            buff.comeFromEntity.id,
            buff.attachEntity.id,
            LogCreateSceneEntity,
            intParas,
            null,
            asList(buff.attachEntity)
        )
    }

    override fun onDetach(buff: Buff) {
    }

    fun onBuffTakeEffect(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime + buff.skillEffProto.haveTime

        val launchSkillId = buff.skillEffProto.skillEffBasePoint.toInt()
        val launchSkillProto = pcs.heroSkillProtoCache.heroSkillMap[launchSkillId] ?: return

        val skill = Skill(buff.attachEntity, launchSkillProto)
        buff.attachEntity.launchingSkill = skill
        skill.takeSkillEffect()
    }

}


