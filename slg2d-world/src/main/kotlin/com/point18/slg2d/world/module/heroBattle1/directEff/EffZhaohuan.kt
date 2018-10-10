package com.point18.slg2d.world.module.heroBattle1.directEff

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.fightdomain.HeroData
import com.point18.slg2d.world.module.heroBattle1.fightLogic.DirectEffectStrategy
import com.point18.slg2d.world.module.heroBattle1.fightLogic.Entity
import com.point18.slg2d.world.module.heroBattle1.fightLogic.SkillEffect
import com.point18.slg2d.world.module.heroBattle1.fightLogic.newEntityByHeroData
import java.util.*

//召唤策略
class ZhaohuanStrategy : DirectEffectStrategy {
    override fun checkEffective(skillEff: SkillEffect, targetEntity: Entity): Boolean {
        return true
    }

    override fun doEffect(skillEff: SkillEffect, targetEntity: Entity) {
        val summonedEntitys = LinkedList<Entity>()
        for (entity in skillEff.launchEntity.manager.entityList) {
            if (entity.entityType != Summoned) {
                continue
            }
            if (entity.intPropertyMap[AttachHeroId] != skillEff.launchEntity.intPropertyMap[AttachHeroId]) {
                continue
            }
            summonedEntitys.add(entity)
        }
        if (summonedEntitys.count() > 0) {
            summonedEntitys.sortBy { it.intPropertyMap[AliveTime] }
        }
        var callNum = skillEff.skillEffProto.callUnitNum
        val maxCallNum = skillEff.skillEffProto.callUnitMaxExistNum
        if (callNum > maxCallNum) {
            callNum = maxCallNum
        }
        if (maxCallNum < callNum + summonedEntitys.count()) {
            val destoryNum = callNum + summonedEntitys.count() - maxCallNum
            for (i in 0 until destoryNum) {
                //通过设置存活时间去摧毁召唤物
                summonedEntitys[i].intPropertyMap[AliveTime] = 0
            }
        }

        val unitId = skillEff.skillEffProto.callUnitId
        val unitLv = skillEff.skillEffProto.callUnitLv

        val heroData = HeroData(0, unitId, unitLv, 1, 1, 0, 0)

        val callEntityList = LinkedList<Entity>()
        for (i in 0 until callNum) {
            val callEntity = newEntityByHeroData(
                skillEff.launchEntity.manager,
                heroData,
                skillEff.launchEntity.getIntProperty(FightSide, false),
                Summoned,
                null
            )
            if (callEntity == null) {
                continue
            }

            callEntity.intPropertyMap[UnitType] = Summoned
            callEntity.intPropertyMap[AliveTime] =
                (skillEff.launchEntity.manager.currentTime + skillEff.skillEffProto.auraHandler)
            val gridX = skillEff.launchEntity.gridX
            val gridY = skillEff.launchEntity.gridY
            callEntity.floatPropertyMap[PosX] = gridX.toDouble()
            callEntity.floatPropertyMap[PosY] = gridY.toDouble()
            callEntity.gridX = gridX
            callEntity.gridY = gridY

            callEntityList.add(callEntity)

            skillEff.launchEntity.manager.pushEntity(callEntity)
        }

        //添加技能生效日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = skillEff.skillProto.id
        intParas[SkillEffId] = skillEff.skillEffProto.id
        skillEff.launchEntity.manager.receiveLogRequest(
            skillEff.launchEntity.id,
            targetEntity.id,
            LogSkillEffect,
            intParas,
            null,
            callEntityList
        )

        callEntityList.forEach {
            targetEntity.manager.obstruct.toNearestGrid(it)
        }
    }
}
