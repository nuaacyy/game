package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*
import com.point18.slg2d.common.pc.pcs

//突进技能策略
object TujinBuffStrategy : IBuffStrategy<Buff> {
    override fun onAttach(buff: Buff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffAttach)

        registerDefaultBuffHandle(buff, OnBuffHeart, ::onBuffTakeEffect)
    }

    override fun onDetach(buff: Buff) {
    }

    fun onBuffAttach(buff: Buff) {
        val currentTime = buff.attachEntity.manager.currentTime

        val targetEntitys = finder.findNormalAtkTarget(buff.attachEntity)
        if (targetEntitys.isEmpty()) {
            buff.loseEffectTime = currentTime
            return
        }
        val targetEntity = targetEntitys[0]

        //设定下次生效时机
        buff.nextTakeEffectTime = currentTime + buff.skillEffProto.haveTime

        //设定对象的存活时间
        buff.loseEffectTime = currentTime + buff.skillEffProto.auraHandler

        //自身飞行
        val newPos = calMovedPos1(buff.attachEntity, targetEntity, buff.skillEffProto.skillEffExtraPoint.toDouble())
        val newPosX = newPos.posX.toInt()
        val newPosY = newPos.posY.toInt()
        buff.attachEntity.flyTargetX = newPosX.toDouble()
        buff.attachEntity.flyTargetY = newPosY.toDouble()
        buff.attachEntity.actionEndTime = currentTime + buff.skillEffProto.auraHandler
        buff.attachEntity.flySpeed = buff.skillEffProto.dartSpeed.toDouble()
        buff.attachEntity.changeState(FlyState)

        //添加buff生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = buff.skillProto.id
        intParas[SkillEffId] = buff.skillEffProto.id
        val floatParas = hashMapOf<Int, Double>()
        floatParas[ToPosX] = newPosX.toDouble()
        floatParas[ToPosY] = newPosY.toDouble()
        buff.attachEntity.manager.receiveLogRequest(
            buff.comeFromEntity.id,
            buff.attachEntity.id,
            LogSkillEffect,
            intParas,
            floatParas
        )
    }

    fun onBuffTakeEffect(buff: Buff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime + buff.skillEffProto.haveTime

        val launchSkillId = buff.skillEffProto.skillEffBasePoint.toInt()
        val launchSkillProto = pcs.heroSkillProtoCache.heroSkillMap[launchSkillId] ?: return

        val launchingSkill = buff.attachEntity.launchingSkill
        if (launchingSkill == null || launchingSkill.skillProto.id != launchSkillId) {
            val skill = Skill(buff.attachEntity, launchSkillProto)
            buff.attachEntity.launchingSkill = skill
        }
        buff.attachEntity.launchingSkill?.takeSkillEffect()
    }
}


