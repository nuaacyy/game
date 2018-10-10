package com.point18.slg2d.world.module.heroBattle1.fightLogic

import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import java.util.*

class SkillEffect(
    var id: Int,              //唯一Id
    var skillProto: com.point18.slg2d.common.pc.HeroSkillProto,  //技能配置
    var skillEffProto: com.point18.slg2d.common.pc.HeroSkillEffProto, //技能效果配置
    var launchEntity: Entity               //发动技能效果的实体
) {
    //发动技能效果
    fun launchSkillEffect() {
        //身上有特定的buff效果才能发动
        for ((effType, _) in this.skillEffProto.conditionMap) {
            val buffList = this.launchEntity.buffMap[effType]
            if (buffList == null || buffList.count() == 0) {
                return
            }
        }

        val strategy = finder.selectFindStrategy(this.skillEffProto.putRange)
        if (strategy == null) {
            return
        }
        val findRst = strategy(
            this.launchEntity,
            this.skillEffProto.faction,
            this.skillEffProto.unitType,
            this.skillEffProto.special,
            this.skillEffProto.aoeRadius.toDouble(),
            this.skillEffProto.lineType,
            this.skillEffProto.lineLengthInt,
            this.skillEffProto.repeat == TRUE,
            this.skillEffProto.aoeNum
        )
        val mainEntity = findRst.mainEntity
        if (mainEntity == null || findRst.targetEntityList.isEmpty()) {
            return
        }
        val launchingSkill = this.launchEntity.launchingSkill
        if (launchingSkill == null) {
            normalLog.lzWarn { "执行技能效果，但无释放中的技能" }
            return
        }
        //释放技能效果日志
        val intParas = hashMapOf<Int, Int>()
        intParas[SkillId] = this.skillProto.id
        intParas[SkillEffId] = this.skillEffProto.id
        val floatParas = hashMapOf<Int, Double>()
        floatParas[FromPosX] = this.launchEntity.getFloatProperty(PosX, false)
        floatParas[FromPosY] = this.launchEntity.getFloatProperty(PosY, false)
        val arrayParas = LinkedList<Pair<Int, Pair<HashMap<Int, Int>, HashMap<Int, Double>>>>()
        arrayParas.add(
            Pair(
                SkillMainEntity,
                Pair(
                    hashMapOf(
                        Pair(EntityId, mainEntity.id)
                    ),
                    hashMapOf(
                        Pair(PosX, mainEntity.getFloatProperty(PosX, false)),
                        Pair(PosY, mainEntity.getFloatProperty(PosY, false))
                    )
                )
            )
        )

        val targetEntityList = findRst.targetEntityList
        val takeEffectMap = launchingSkill.takeEffectMap.getOrPut(this.skillEffProto.id) { hashMapOf() }
        val takeEffectEntityList = LinkedList<Entity>()
        for (targetEntity in targetEntityList) {
            if (this.skillEffProto.onceEffect != 0) {
                if (takeEffectMap[targetEntity.id] == 1) {
                    continue
                }
                takeEffectMap[targetEntity.id] = 1
            }
            takeEffectEntityList.add(targetEntity)

            arrayParas.add(
                Pair(
                    SkillTargetEntity,
                    Pair(
                        hashMapOf(
                            Pair(EntityId, targetEntity.id)
                        ),
                        hashMapOf(
                            Pair(PosX, targetEntity.getFloatProperty(PosX, false)),
                            Pair(PosY, targetEntity.getFloatProperty(PosY, false))
                        )
                    )
                )
            )
        }

        this.launchEntity.manager.receiveLogRequest(
            this.launchEntity.id,
            this.launchEntity.id,
            LogLaunchSkillEffect,
            intParas,
            floatParas,
            null,
            arrayParas
        )


        for (targetEntity in takeEffectEntityList) {
            if (this.skillEffProto.bulletTime == 0 && this.skillEffProto.bulletSpeed == 0) {
                //对每个目标立即执行技能效果
                this.takeSkillEffect(targetEntity)
                continue
            }

            var flyTime = this.skillEffProto.bulletTime
            if (flyTime == 0) {
                val distance = calDistance(this.launchEntity, targetEntity)
                flyTime =
                    Math.ceil(distance * pcs.basicProtoCache.heroSpeedPara / this.skillEffProto.bulletSpeed * 1000.0)
                        .toInt()
            }
            //给每个目标挂一个待生效的技能
            val takeEffTime = this.launchEntity.manager.currentTime + flyTime
            val effList = targetEntity.skillEffMap.getOrPut(takeEffTime) { LinkedList() }
            effList.add(this)
            targetEntity.skillEffMap[takeEffTime] = effList
        }
    }

    //执行技能效果
    fun takeSkillEffect(targetEntity: Entity) {
        if (this.skillEffProto.isBuff()) {
            //附加buff效果
            this.applyBuff(targetEntity)
        } else {
            //执行直接效果
            this.dealDirectEffect(targetEntity)
        }
        if (this.skillEffProto.isHitNum != 0) {
            val afterSkillEffectProto = pcs.heroSkillEffProtoCache.heroSkillEffMap[this.skillEffProto.isHitNum]
            if (afterSkillEffectProto == null) {
                normalLog.lzWarn { "找不到${this.skillEffProto.id}的后置技能效果" }
                return
            }
            val afterSkillEffect = newSkillEffect(this.launchEntity, this.skillProto, afterSkillEffectProto)
            afterSkillEffect.launchSkillEffect()
        }
    }

    //附加buff
    fun applyBuff(targetEntity: Entity) {
        //判断免疫、效果冲突等
        val factory = selectBuffFactory(skillEffProto.skillType)
        val newBuff = factory.createBuff(this.launchEntity, targetEntity, skillProto, skillEffProto, this.id)

        val checkRst = newBuff.checkConflict()
        when (checkRst.result) {
            ConflictAndReplace -> {
                val buff = checkRst.buff
                if (buff != null) {
                    buff.detach()
                }
            }
            ConflictAndNoReplace -> {
                return
            }
        }

        //附加buff
        newBuff.attach()
    }

    //执行直接效果
    fun dealDirectEffect(targetEntity: Entity) {
        val skillType = this.skillEffProto.skillType
        val strategy = selectDirectEffStrategy(skillType)
        if (strategy == null) {
            normalLog.lzWarn { "找不到技能效果类型${skillType}的生效策略" }
            return
        }
        if (!strategy.checkEffective(this, targetEntity)) {
            return
        }
        strategy.doEffect(this, targetEntity)
    }
}

fun newSkillEffect(entity: Entity, skillProto: com.point18.slg2d.common.pc.HeroSkillProto, skillEffProto: com.point18.slg2d.common.pc.HeroSkillEffProto): SkillEffect {
    return SkillEffect(
        entity.manager.genId(),
        skillProto,
        skillEffProto,
        entity
    )
}
