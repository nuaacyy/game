package com.point18.slg2d.world.module.heroBattle1.heroBuff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.heroBattle1.fightLogic.*
import com.point18.slg2d.common.pc.pcs

//直线移动释放技能策略
object MoveShifangjinnengBuffStrategy : IBuffStrategy<MoveLaunchSkillBuff> {
    override fun onAttach(buff: MoveLaunchSkillBuff) {
        registerDefaultBuffHandle(buff, OnBuffAttach, ::onBuffAttach)

        registerDefaultBuffHandle(buff, OnBuffHeart, ::onBuffTakeEffect)
    }

    override fun onDetach(buff: MoveLaunchSkillBuff) {
    }

    fun onBuffAttach(buff: MoveLaunchSkillBuff) {
        val currentTime = buff.attachEntity.manager.currentTime

        //设定下次生效时机
        buff.nextTakeEffectTime = currentTime + buff.skillEffProto.haveTime

        //设定对象的存活时间
        buff.attachEntity.intPropertyMap[AliveTime] = currentTime + buff.skillEffProto.auraHandler

        //在释放者位置创建克隆对象
        val sceneEntity = buff.attachEntity.clone()
        sceneEntity.entityType = Skill
        buff.attachEntity.manager.pushEntity(sceneEntity)

        //克隆对象飞行
        val newPos = calMovedPos1(buff.comeFromEntity, buff.attachEntity, buff.skillEffProto.skillEffBasePoint)
        val newPosX = newPos.posX.toInt()
        val newPosY = newPos.posY.toInt()
        sceneEntity.flyTargetX = newPosX.toDouble()
        sceneEntity.flyTargetY = newPosY.toDouble()
        sceneEntity.actionEndTime = currentTime + buff.skillEffProto.auraHandler
        sceneEntity.flySpeed = buff.skillEffProto.dartSpeed.toDouble()
        sceneEntity.changeState(FlyState)

        //buff绑定到克隆对象
        buff.controlEntity = sceneEntity
    }

    fun onBuffTakeEffect(buff: MoveLaunchSkillBuff) {
        //设定下次生效时机
        buff.nextTakeEffectTime = buff.attachEntity.manager.currentTime + buff.skillEffProto.haveTime

        val controlEntity = buff.controlEntity ?: return

        val launchSkillId = buff.skillEffProto.skillEffBasePoint.toInt()
        val launchSkillProto = pcs.heroSkillProtoCache.heroSkillMap[launchSkillId] ?: return

        val skill = Skill(controlEntity, launchSkillProto)
        controlEntity.launchingSkill = skill
        skill.takeSkillEffect()
    }

}


